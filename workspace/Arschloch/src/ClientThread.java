
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ClientThread extends Thread {
	/**
	 * Der ClientThread, der mit dem jeweiligen Client kommuniziert.
	 * Als erstes wird der Name ausgehandelt; Dazu schreibt der Client solange
	 * Strings auf die socket, wie er keine NameGibtsSchonException zurueck bekommt
	 */
	private boolean bereit;
	private Spieler spieler;
	private ArrayList<ClientThread> clients; 
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket clientSocket;
	boolean gibtsSchon = false;
	
	/**
	 * Konstruktor des ClientThreads. 
	 * @param clients Liste mit allen anderen clients
	 * @param clientSocket die socket zum kommunizieren
	 */
	
	public ClientThread(Socket clientSocket, ArrayList<ClientThread> clients){
		this.clients = clients;
		this.clientSocket = clientSocket;
		try {
			this.input = new ObjectInputStream(this.clientSocket.getInputStream());
			this.output = new ObjectOutputStream(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Gibt zurueck, ob der Spieler bereit zum Starten ist
	 * @return true, wenn der Spieler bereit ist
	 */
	public boolean istBereit() {
		return this.bereit;
	}

	/**
	 * Gibt den Namen des Spielers, der dem Thread zugeordnet ist, zurueck
	 * @return name des Spielers
	 */
	public String getSpielerName(){
		return this.spieler.getName();
	}
	
	public void out(Object o){
		try {
			this.output.writeObject(o);
			this.output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		//als erstes den Namen aushandeln
		this.bereit = false;
		
		do {
			gibtsSchon = false;
			String name = "";
			try {
				this.out("+");
				name = input.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			for (int i = 0; i<clients.size()-1; i++){
				//nicht den letzten abfragen, der hat ja noch keinen Namen
				ClientThread th = clients.get(i);
				if (th.getSpielerName().equals(name)){
					gibtsSchon = true;
				}
			}
			if(!gibtsSchon){
				this.spieler.setName(name);
				this.out("~ACCEPTED-Welcome "+name);
			}
			else{
				this.out(new NameGibtsSchonException("Diesen Namen gibt es bereits."));
			}
		}
		while (gibtsSchon);
		
		//warten, bis das bereit-Signal kommt
		while (!this.bereit){
			try {
				this.out("#");
				if (input.readBoolean() == true){this.bereit = true;}
			}  catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
