package chatprogram;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.json.JSONObject;

public class SocketClient {
	private ChatServer chatServer;
	private Socket socket;
	private String key;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String addrInfo;
	private boolean quietExit = false;

	// 생성자(서버 및 소켓 연결, 데이터 연결)
	public SocketClient(ChatServer chatServer, Socket socket) {
		try {
			this.chatServer = chatServer;
			this.socket = socket;
			
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// getter, setter
	public String getKey() {
		return key;
	}
	public String getAddrInfo() {
		return addrInfo;
	}
	public void setKey(String chatName) {
		this.key = chatName + "@" + addrInfo;
	}
	public void setAddrInfo(String hostString) {
		this.addrInfo = hostString;
	}
	public void setQuietExit(boolean quietExit) {
		this.quietExit = quietExit;
	}
	
	// 메세지 전송
	public void send(String sendMessage) {
		try {
			dos.writeUTF(sendMessage);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 요청 처리(입장, 메세지 전송, 퇴장)
	public void receive() {
		chatServer.threadPool.execute(() -> {
			try {
				while (true) {
					String receivedData = dis.readUTF();
					
					JSONObject json = new JSONObject(receivedData);
					String request = json.getString("request");
					String data = json.getString("data");
					
					switch(request) {
					// 메세지 전송
					case "msg":
						String msg = ")" + data;
						chatServer.sendToAll(this, msg);
						break;
						
						// 입장
					case "entry":
						this.setKey(data);
						chatServer.addToChatRoom(this);
						
						String entry = "님이 입장하셨습니다.";
						chatServer.sendToAll(this, entry);
						break;
						
						// 조용히 퇴장
					case "quietExit":
						this.quietExit = Boolean.valueOf(data);
					}
				}
			} catch (IOException e) {
				// 퇴장
				if (!quietExit) {
					String exit = "님이 퇴장하셨습니다.";
					chatServer.sendToAll(this, exit);
				}
				
				this.close();
			}
		});
	}
	
	// 서버와 연결 끊고(chatRoom에서 삭제) 소켓 닫기
	public void close() {
		try {
			chatServer.removeFromChatRoom(this);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}