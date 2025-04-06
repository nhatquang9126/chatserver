package Server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomServer {
	private static final int PORT = 1234;
	private List<ClientHandlerChatRoom> clients = new ArrayList<>();
	private ServerSocket socket;
	
	public void startServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println("Server started. Listening on port: " + PORT);
			
			//Client connect java
			while(true) {
				InetAddress inetAddress = socket.getInetAddress();
				if (inetAddress != null) {
				    String clientIP = inetAddress.getHostAddress();
				    System.out.println("Client connected: " + clientIP);
				} else {
				    System.out.println("Client address is null.");
				}

				Socket clientSocket =  serverSocket.accept();
				System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());
				
				ClientHandlerChatRoom clientHandler = new ClientHandlerChatRoom(clientSocket, System.currentTimeMillis()+"", this);
				clients.add(clientHandler);
				new Thread(clientHandler).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void broadcastMessage(String id, String message) {
		for (ClientHandlerChatRoom client : clients) {
			 if(!(client.getId().equals(id)))
	                client.sendMessage(id+" : " +message);
	        }
		}
	public static void main(String[] args) {
		ChatRoomServer chatServer = new ChatRoomServer();
        chatServer.startServer();
    }
	
}
