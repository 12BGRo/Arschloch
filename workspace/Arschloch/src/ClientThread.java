import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


public class ClientThread {
	private boolean bereit;
	private Spieler spieler;
	private ArrayList<ClientThread> clients;
	private BufferedReader input;
	private PrintStream output;
	private Socket clientSocket;
	
	public ClientThread(ArrayList<ClientThread> clients, Socket clientSocket){
		this.clients = clients;
		this.clientSocket = clientSocket;
	}
	
	/**
	 * Gibt zurück, ob der Spieler bereit zum Starten ist
	 * @return true, wenn der Spieler bereit ist
	 */
	public boolean istBereit() {
		return this.bereit;
	}

	public String getSpielerName(){
		return this.spieler.getName();
	}
}
