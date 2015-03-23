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