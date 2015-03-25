import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Runnable {

	
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String playername;
	
	Scanner sc = new Scanner(System.in);
	

	public Client (Socket socket) throws IOException{
		
		this.socket = socket;
		try {
			this.output = new ObjectOutputStream(this.socket.getOutputStream());
			this.input = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		do{	
			playername = getName();
			this.output.writeUTF(playername);	
		}
		while(!this.input.readUTF().equals("ACCEPTED"));
		
	
	}
	
	public String receive() throws ClassNotFoundException{
		String message = null;
		try {
			message = this.input.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	
	public void send(String message){
		try {
			this.output.writeUTF(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	String getName(){
		
		String name = null;
		System.out.println("Bitte gib deinen Spieler-Namen ein:");
		name = sc.next();
		return name;
	}
	
	
	void setBereitschaft(boolean status){
		try {
			this.output.writeBoolean(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
	
		
		
	}
	
	
}
