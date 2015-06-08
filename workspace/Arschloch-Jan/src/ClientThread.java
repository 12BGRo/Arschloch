
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ClientThread extends Thread {
	/**
	 * Der ClientThread, der mit dem jeweiligen Client kommuniziert.
	 * Als erstes wird der Name ausgehandelt; Dazu schreibt der Client solange
	 * Strings auf die socket, wie er keine NameGibtsSchonException zurueck bekommt
	 */
	private Spieler spieler;
	private ArrayList<ClientThread> clients; 
	private BufferedReader input;
	private PrintWriter output;
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
			this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream());
			this.output = new PrintWriter(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gibt zurueck, ob der Spieler bereit zum Starten ist
	 * @return true, wenn der Spieler bereit ist
	 */
	public boolean istBereit() {
		return this.spieler.istBereit();
	}

	
	
	/**
	 * Gibt den Namen des Spielers, der dem Thread zugeordnet ist, zurueck
	 * @return name des Spielers
	 */
	public String getSpielerName(){
		return this.spieler.getName();
	}
	
	public void out(String o){
		this.output.write(o);
		this.output.flush();
	}
	
	public void run(){
		//als erstes den Namen aushandeln
		
		do {
			gibtsSchon = false;
			String name = "";
			try {
				name = input.readLine();
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
				this.out("+");
			}
			else{
				this.out("-");
			}
		}
		while (gibtsSchon);
		
		//warten, bis das bereit-Signal kommt
		while (!this.spieler.istBereit()){
			try {
				if (input.readLine().equals("+bereit")){
					this.spieler.setBereit(true);
				}
			}  catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}