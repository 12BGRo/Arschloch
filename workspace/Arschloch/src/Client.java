import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client implements Runnable {

	
	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String playername;
	private ArrayList<Spielkarte> clientKarten = new ArrayList<Spielkarte>();
	
	Scanner sc = new Scanner(System.in);
	

	public Client (Socket socket) throws IOException{
		
		this.socket = socket;
		try {
			this.output = new ObjectOutputStream(this.socket.getOutputStream());
			this.input = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Sendet so oft einen Namen an den Client-Thread, bis er eine Bestaetigung erhaelt
		try {
			do{	
				playername = getName();
				this.output.writeUTF(playername);	
			}
			while(!receive().startsWith("+"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	
	}
	
	/**
	 * Liest eine vom Server empfangene Nachricht und
	 * gibt die sie als String zurueck.
	 * @param message Empfangene Nachricht
	 */
	public String receive() throws ClassNotFoundException{
		String message = null;
		try {
			message = this.input.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	
	/**
	 * Sendet die als String uebergebene Nachricht an den Server-Thread.
	 * @param message Die Nachricht welche uebergeben werden soll als String
	 */
	public void send(String message){
		try {
			this.output.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Fordert eine Namenseingabe vom Benutzer an
	String getName(){
		
		String name = null;
		System.out.println("Bitte gib deinen Spieler-Namen ein:");
		name = sc.next();
		return name;
	}
	
	/**
	 * Sendet dem Client-Thread, ob der Client / Benutzer bereit ist oder nicht.
	 * @param status true = bereit || false = nicht bereit
	 */
	void setBereitschaft(boolean status){
		try {
			this.output.writeBoolean(status);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkMessage(String command){
		
		
	}
	
	@Override
	public void run() {
		
			String message = null;
			try {
				message = this.receive();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			checkMessage(message);
			
			
		
		
		
	}
	
	
}
