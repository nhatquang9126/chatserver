package Client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientChatRoom {
	private static final String URL = "localhost";
	private static final int PORT = 1234;
	
	private void startClient() {
		try {
			Socket socket = new Socket(URL, PORT);
			System.out.println("Connected to server");
			
			//Liên tục đọc dữ liệu từ server
			ClientListenerChatRoom client = new ClientListenerChatRoom(socket);
			new Thread(client).start();
			
			//Liên tục đọc dữ liẹu từ scanner
			OutputStream output = socket.getOutputStream();
			Scanner sc = new Scanner(System.in);
			while(true) {
				String message = sc.nextLine();
				output.write(message.getBytes());
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
	       ClientChatRoom chatClient = new ClientChatRoom();
	       chatClient.startClient();
	    }
}