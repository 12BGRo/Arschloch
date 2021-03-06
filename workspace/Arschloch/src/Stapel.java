import java.util.ArrayList;


public class Stapel {

	
	private KartenContainer obersteKarten;
	private ArrayList<Spielkarte> ausDemSpiel;
	
	public Stapel(){
		this.obersteKarten = new KartenContainer();
		this.ausDemSpiel = new ArrayList<Spielkarte>();
	}
	
	/**
	 * Gibt die obersten Karten als KartenContainer zurück
	 * @return die obersteKarten
	 */
	public KartenContainer getKarten() {
		return obersteKarten;
	}
	
	/**
	 * Setzt die obersten Karten des Stapels
	 * und verschiebt die bisher obsten Karten in die ArrayList ausDemSpiel
	 * @param karten die neuen obersten Karten
	 */
	public void setKarten(KartenContainer karten) {
		for (int i = 0; i<this.obersteKarten.getAnzahl(); i++){
			this.ausDemSpiel.add(this.obersteKarten.getKarte(i));
		}
		this.obersteKarten = karten;
	}
}
