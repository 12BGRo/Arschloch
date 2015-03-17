import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ArschlochServer implements Runnable{
	private ArrayList<ClientThread> clients;
	private Verwaltung verwaltung;
	private ServerSocket serverSocket;
	
	public ArschlochServer(){
		this.clients = new ArrayList<ClientThread>();
		this.serverSocket = new ServerSocket(12345);
	}

	/**
	 * Prüft, ob genug Spieler für ein Spiel online sind (3)
	 * @return true, wenn es genug Spieler gibt
	 */
	public boolean gibtsGenugSpieler(){
		return this.clients.size() < 3;
	}
	
	/**
	 * Prüft, ob alle Spieler bereit zum Starten sind
	 * @return true, wenn alle bereit sind
	 */
	public boolean sindAlleBereit(){
		boolean bereit = true;
		for (ClientThread cl : this.clients){
			if (!cl.istBereit()){
				bereit = false;
			}
		}
		
	}
	
	/**
	 * Prüft, ob der Server voll ist (6 Spieler)
	 * @return true, wenn 6 Spieler online sind
	 */
	public boolean istVoll(){
		return this.clients.size() == 6;
	}
	
	@Override
	public void run() {
		while (!(this.sindAlleBereit() && this.gibtsGenugSpieler())){
			Socket clientSocket = this.serverSocket.accept();
			this.clients.add(new ClientThread());
		}
	}
}
