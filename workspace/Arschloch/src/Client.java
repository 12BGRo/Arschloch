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
	private boolean bereitschaft = false;
	
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
		start();
	
	}
	
	private void start() {
		this.run();		
	}

	/**
	 * Liest eine vom Server empfangene Nachricht und
	 * gibt diese als String zurueck.
	 * @param Empfangene Nachricht als String
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
	 * @param message Die Nachricht welche uebergeben werden soll als Object
	 */
	public void send(Object message){
		try {
			this.output.writeObject(message);
			this.output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Fragt den Namen vom Benutzer ab.
	 * @return Name als String
	 */
	public String getName(){
		
		String name = null;
		System.out.println("Bitte gib deinen Spieler-Namen ein:");
		name = sc.next();
		return name;
	}
	
	/**
	 * <pre>Fragt die Bereitschaft vom Benutzer ab, wenn er noch nicht bereit ist.</pre>
	 * 
	 * @return Bereitschaft als boolean(true)
	 */
	public boolean getBereitschaft(){
		if(!bereitschaft){
		System.out.println("Sind sie bereit?");
		if(sc.nextLine().equalsIgnoreCase("ja"));{
			this.bereitschaft = true;
		}
		}
		return this.bereitschaft;
	}
	

	
	/**
	 * <pre>Prueft welcher Tag am Anfang der Nachricht steht und fuehrt dann die entsprechenden Methoden aus.
	 * 
	 * "~" = Gibt die Nachricht in die Console / Text Area aus.
	 * "-" = Weisst den Client an die Verbindung zu trennen.
	 * "#" = Fragt die Bereitschaft des Spielers an.
	 * "+" = Fordert den Namen des Spielers an.
	 * "*" = Sagt dem Client, dass er nun die Karten erhaelt.
	 * "^" = Sagt dem Client, dass nun die Karten auf dem Stapel folgen.
	 * </pre>
	 * @param command
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void checkMessage(String command) throws ClassNotFoundException, IOException{
		
		if(command.startsWith("+")){
			send(this.getName());
		}
		
		if(command.startsWith("#")){
			send(this.getBereitschaft());
		}
		
		if(command.startsWith("-")){
			this.send("Bye");
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(command.startsWith("~")){
			System.out.println(command);
		}
		
		if(command.startsWith("*")){
			this.clientKarten.add((Spielkarte) this.input.readObject());
		}
		
		if(command.startsWith("^")){
			
		}
	}
	
	@Override
	public void run() {
		
			String message = null;
			try {
				message = this.receive();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if(message!= null){
			try {
				checkMessage(message);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			}
			
		
		
		
	}
	
	
}
