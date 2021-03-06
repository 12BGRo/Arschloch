import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ArschlochServer implements Runnable{
	private ArrayList<ClientThread> clients;
	private Verwaltung verwaltung;
	private ServerSocket serverSocket;
	private boolean gameRunning = false;

	public ArschlochServer(){
		this.clients = new ArrayList<ClientThread>();
		try {
			this.serverSocket = new ServerSocket(12345);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Prueft, ob genug Spieler fuer ein Spiel online sind (3)
	 * @return true, wenn es genug Spieler gibt
	 */
	public boolean gibtsGenugSpieler(){
		return this.clients.size() < 3;
	}

	/**
	 * Prueft, ob alle Spieler bereit zum Starten sind
	 * @return true, wenn alle bereit sind
	 */
	public boolean sindAlleBereit(){
		boolean bereit = true;
		for (ClientThread cl : this.clients){
			if (!cl.istBereit()){
				bereit = false;
			}
		}
		return bereit;

	}

	/**
	 * Prueft, ob der Server voll ist (6 Spieler)
	 * @return true, wenn 6 Spieler online sind
	 */

	public boolean istVoll(){
		return this.clients.size() == 6;
	}

	@Override
	public void run() {
		while(!gameRunning){
			while (!(this.sindAlleBereit()) || !(this.gibtsGenugSpieler())){
				Socket clientSocket = null;
				try {
					clientSocket = this.serverSocket.accept();
				} catch (IOException e) {

					e.printStackTrace();
				}
				this.clients.add(new ClientThread(clientSocket, clients));
			}
			verwaltung = new Verwaltung(clients.size());
			verwaltung.austeilen();
		}
	}
}
