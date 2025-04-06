package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.Buffer;

public class ClientHandlerChatRoom implements Runnable{
	private Socket mySocket;
	private ChatRoomServer chatRoomServer;
	private String id;
	private InputStream input;
	private OutputStream output;
	
	public ClientHandlerChatRoom(Socket mySocket, String id, ChatRoomServer chatServer) {
		this.mySocket = mySocket;
		this.id = id;
		this.chatRoomServer = chatRoomServer;
		
		try {
			this.input = mySocket.getInputStream();
			this.output = mySocket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			byte[] buffer = new byte[1024];
			int bytesRead;
			while((bytesRead = input.read(buffer)) != -1) {
				String message = new String(buffer, 0, bytesRead);
				chatRoomServer.broadcastMessage(this.id, message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	public void sendMessage(String message) {
		try {
			output.write(message.getBytes());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getId() {
        return id;
    }
	
}
