package chatprogram;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.JSONObject;

public class ChatUser {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String chatName;
	
	// 서버와 연결
	public void connect(String addr, int portNo) {
		try {
			socket = new Socket(addr, portNo);
			
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			System.out.println("[알림]서버와 연결 성공");
		} catch (UnknownHostException e) {
			System.out.println("[알림]주소를 잘못 입력하셨습니다.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("[알림]서버와 연결 실패");
			System.exit(0);
		}
	}
	
	// 서버와 연결 해제
	public void unconnect() {
		try {
			socket.close();
			System.out.println("[알림]서버와 연결 종료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 대화명 설정
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	
	// 서버에 요청내용 생성
	public String mkRequest(String request, String data) {
		JSONObject json = new JSONObject();
		json.put("request", request);
		json.put("data", data);
		return json.toString();
	}
	
	// 서버(소켓)와 통신
	public void send(String message) {
		try {
			dos.writeUTF(message);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 채팅방 입장
	public void enterChatRoom() {
		String message = mkRequest("entry", chatName);
		this.send(message);
		System.out.println("[알림]채팅방에 입장하였습니다.");
	}
	
	// 채팅방 퇴장(서버와 연결은 끊지 않음)
	public void exitChatRoom(boolean quietExit) {
		if (quietExit) {
			String message = mkRequest("quietExit", "true");
			this.send(message);
			
			System.out.println("[알림]채팅방에서 조용히 퇴장하였습니다.");
		} else {
			System.out.println("[알림]채팅방에서 퇴장하였습니다.");
		}
	}
	
	// 채팅방에 메세지 전송
	public void sendMessage(String message) {
		String msg = mkRequest("msg", message);
		this.send(msg);
		System.out.println("[알림]메세지 전송 완료");
	}
	
	// 메세지 수신
	public void receive() {
		Thread receiveThread = new Thread(() -> {
			try {
				while (true) {
					String msg = dis.readUTF();
					JSONObject jsonForRead = new JSONObject(msg);
					String key = jsonForRead.getString("senderKey");
					String message = jsonForRead.getString("message");
					
					System.out.println(key + message);
				}
			} catch (IOException e) {
				if (Thread.activeCount() > 1) {
					System.out.println("[알림]서버와 연결 끊김");
					System.exit(0);
				}
			}
		});
		receiveThread.setName("Thread-receive");
		receiveThread.setDaemon(true);
		receiveThread.start();
	}

	// 실행 부분
	public static void main(String[] args) {
		ChatUser chatUser = new ChatUser();
		chatUser.connect("localhost", 55555);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("[알림]대화명: ");
		String chatName = scanner.nextLine();
		chatUser.setChatName(chatName);
		
		chatUser.enterChatRoom();
		
		chatUser.receive();
		
		while (true) {
			System.out.println("[알림]메세지 입력 후 Enter");
			System.out.println("[알림]채팅방을 퇴장하시려면 exit만 입력하세요.");
			
			String content = scanner.nextLine();
			if (content.toLowerCase().equals("exit")) {
				System.out.println("[알림]조용히 퇴장하시겠습니까? (y/n)");
				String answer = scanner.nextLine();
				
				if (answer.toLowerCase().equals("y")) {
					chatUser.exitChatRoom(true);
					break;
				} else if (answer.toLowerCase().equals("n")) {
					chatUser.exitChatRoom(false);
					break;
				}
				System.out.println("[알림]취소되었습니다. 채팅모드로 돌아갑니다.");
			}
			
			chatUser.sendMessage(content);
		}
		scanner.close();
		
		chatUser.unconnect();
	}
}