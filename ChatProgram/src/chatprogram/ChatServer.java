package chatprogram;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
//import java.util.Collections;
//import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;

public class ChatServer {
	// 서버용 소켓, 채팅용 스레드풀, 유저와 연결(소켓)을 관리하기 위한 Map 필드
	private ServerSocket serverSoc;
	ExecutorService threadPool = Executors.newFixedThreadPool(100);
	private Map<String, SocketClient> chatRoom = new Hashtable<String, SocketClient>();
//  HashMap을 동기화하는 것도 가능
//	Map<String, SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<String, SocketClient>());
	
	// 서버 동작
	public void start(int portNo) throws IOException {
		serverSoc = new ServerSocket(portNo);
		System.out.println("[서버]" + portNo + "번 포트로 시작");
	}
	
	public void run() {
		Thread acceptThread = new Thread(() -> {
			while (true) {
				try {
					System.out.println("[서버]연결 요청 대기 중");
					Socket socket = serverSoc.accept();
					
					SocketClient clientSoc = new SocketClient(this, socket);
					
					InetSocketAddress isa = (InetSocketAddress)socket.getRemoteSocketAddress();
					clientSoc.setAddrInfo(isa.getHostString());
					System.out.println("[서버]" + clientSoc.getAddrInfo() + "님의 연결 요청 수락");
					
					clientSoc.receive();
				} catch (IOException e) {
					// 서버 닫혀서 acceptThread 종료
					System.exit(0);
					// e.printStackTrace();
				}
			}
		});
		acceptThread.setName("Thread-accept");
		acceptThread.setDaemon(true);
		acceptThread.start();
	}
	
	public void stop() {
		try {
			serverSoc.close();
			threadPool.shutdownNow();
			chatRoom.values().stream()
				.forEach(clientSoc -> clientSoc.close());
			System.out.println("[서버]종료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 유저와 연결된 소켓 관리
	public void addToChatRoom(SocketClient clientSoc) {
		chatRoom.put(clientSoc.getKey(), clientSoc);
		
		System.out.println(notice("입장", clientSoc.getKey()));
	}
	
	public void removeFromChatRoom(SocketClient clientSoc) {
		chatRoom.remove(clientSoc.getKey());
		
		System.out.println(notice("퇴장", clientSoc.getKey()));
	}
	
	// 상태 알림 형식
	private String notice(String status, String userInfo) {
		return "<알림>\n- " + status + ": " + userInfo + "\n- 현재 채팅자 수: " + chatRoom.size() + "명";
	}
	
	// chatRoom에 입장해 있는 메세지 전송(Map 목록에 있는 소켓에 메세지 일괄 전송하도록 하기)
	public void sendToAll(SocketClient sender, String receivedMessage) {
		String sendMessage = mkMessage(sender.getKey(), receivedMessage);
		
		Collection<SocketClient> clientSocs = chatRoom.values();
		for (SocketClient clientSoc : clientSocs) {
			if (clientSoc == sender) {
				continue;
			}
			clientSoc.send(sendMessage);
		}
	}
	
	private String mkMessage(String key, String receivedMessage) {
		JSONObject json = new JSONObject();
		json.put("senderKey", key);
		json.put("message", receivedMessage);
		String sendMessage = json.toString();
		
		return sendMessage;
	}
	
	// 실행 부분
	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer();
			chatServer.start(55555);
			chatServer.run();
			
			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("[서버]서버를 종료하려면 stop 입력");
				String command = scanner.nextLine();
				
				if (command.toLowerCase().equals("stop")) {
					break;
				}
			}
			scanner.close();
			
			chatServer.stop();
		} catch (IOException e) {
			System.err.print("[서버]");
			e.printStackTrace();
		}
	}
}