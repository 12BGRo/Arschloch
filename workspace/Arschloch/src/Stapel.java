import java.util.ArrayList;


public class Stapel {

	
	private KartenContainer obersteKarten;
	
	public Stapel(){
		this.obersteKarten = new KartenContainer();
	}
	/**
	 * Gibt den KartenContainer zurück
	 * @return KartenContainer obersteKarten
	 */
	public KartenContainer getKarten() {
		return obersteKarten;
	}
	/**
	 * 
	 * @param karten
	 */
	public void setKarten(KartenContainer karten) {
		this.obersteKarten = karten;
	}
}
