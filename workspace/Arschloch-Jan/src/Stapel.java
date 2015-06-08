import java.util.ArrayList;


public class Stapel {

	
	private ArrayList<Spielkarte> obersteKarten;
	private ArrayList<Spielkarte> ausDemSpiel;
	
	public Stapel(){
		this.obersteKarten = new ArrayList<Spielkarte>();
		this.ausDemSpiel = new ArrayList<Spielkarte>();
	}
	
	/**
	 * Gibt die obersten Karten als KartenContainer zurück
	 * @return die obersteKarten
	 */
	public ArrayList<Spielkarte> getKarten() {
		return obersteKarten;
	}
	
	/**
	 * Setzt die obersten Karten des Stapels
	 * und verschiebt die bisher obsten Karten in die ArrayList ausDemSpiel
	 * @param karten die neuen obersten Karten
	 */
	public void setKarten(ArrayList<Spielkarte> karten) {
		this.ausDemSpiel.addAll(this.obersteKarten);
		this.obersteKarten = karten;
	}
}
