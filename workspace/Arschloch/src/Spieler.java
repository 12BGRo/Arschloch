import java.util.ArrayList;


public class Spieler {
	private String name;
	private ArrayList<Spielkarte> handkarten;
	private Rang rang;
	
	public Spieler(String name){
		this.name = name;
	}
	
	
	/**
	 * Fuegt dem Spieler eine neue Handkarte hinzu, z.B. beim Austeilen
	 * @param karte Die Karte, die hinzugefuegt werden soll
	 * @return 
	 */
	public void addKarte(Spielkarte karte){
		this.handkarten.add(karte);
	}
	
	/**
	 * soll aufgerufen werden, wenn der Spieler eine (oder mehrer) Karten aus 
	 * seiner Hand ablegt. Die Karten werden in einem KartenContainer zusammengefasst.
	 * Die Methode prueft, ob der Spieler alle uebergebenen Karten hat, wenn nicht, wird eine 
	 * SpielerHatDieKarteNichtExection geworfen. Die abgelegten Karten werden aus den Handkarten geloescht.
	 * @param karten KartenContainer mit den Karten, die ablegt werden sollen.
	 * @return die abgelegten Karten
	 * @throws SpielerHatDieKartenNichtException 
	 * @exception SpielerHatDieKarteNichtException wird geworfen, wenn der Spieler die karte nicht hat
	 */
	
	public KartenContainer ablegen(KartenContainer karten) throws SpielerHatDieKartenNichtException{
		ArrayList<Spielkarte> kartenn = new ArrayList<Spielkarte>();
		for (int i=0; i<karten.getAnzahl();i++){
			kartenn.add(karten.getKarte(i));
		}
		//pruefen, ob der spieler die karten hat
		boolean hat = true;
		for (Spielkarte sp : kartenn){
			if (!this.handkarten.contains(sp)){
				hat = false;
			}
		}
		if (!hat){
			
				throw new SpielerHatDieKartenNichtException();
			
		}
		else {
		//aus den handkarten loeschen
		for (Spielkarte sp : kartenn){
			this.handkarten.remove(sp);
		}
		return karten;
		}
	}
	
	/**
	 * Setzt den Rang des Spielers
	 * @param rang Rang des Spielers
	 */
	public void setRang(Rang rang){
		this.rang = rang;
	}
	
	/**
	 * Gibt den aktuellen Rang des Spielers zurueck.
	 * @return Rang des Spielers
	 */
	public Rang getRang(){
		return this.rang;
	}
	
	/**
	 * Gibt die Anzahl der Handkarten des Spielers als int zurueck.
	 * @return Anzahl der Handkarten
	 */
	public int getAnzahlHandKarten(){
		return this.handkarten.size();
	}
	
	/**
	 * Gibt den Namen des Spielers zurueck
	 * @return name des Spielers
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Setzt den Namen des Spielers
	 * @param name name des Spielers
	 */
	public void setName(String name){
		this.name = name;
	}
}
