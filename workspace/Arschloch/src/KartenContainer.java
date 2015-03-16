import java.util.ArrayList;


public class KartenContainer {
	private ArrayList<Spielkarte> karten;
	
	public KartenContainer(){
		this.karten = new ArrayList<Spielkarte>();
	}
	
	/**
	 * Fügt dem KartenContainer eine Spielkarte hinzu und überprüft, ob diese passt.
	 * z.B. ob wenn versucht wird, eine 7 und eine 8 zusammen abzulegen, wird eine KarteTypPasstNichtException geworfen.
	 * @param karte Die zinzuzufügende Karte
	 * @exception KarteTypPasstNichtException wird geworfen, wenn die übergebene karte nicht passt
	 */
	public void addKarte(Spielkarte karte){
		//ueberpruefen, ob die karte passt
		//zB passt keine 9 zu einer 7, eine 7 aber zu noch einer 7
		if (this.karten.size() != 0){
			if (this.karten.get(this.karten.size()-1).getWert() == karte.getWert()){
				this.karten.add(karte);
			}
			else throw new KarteTypPasstNichtException();
		}
		else {
			this.karten.add(karte);
		}
	}
	
	/**
	 * Gibt den Wert der Karte(n) zurück
	 * @return den Wert der Karte(n) (z.B. 7 für eine 7, 11 für einen Buben)
	 */
	public int getWert(){
		return this.karten.get(0).getWert();
	}
	
	/**
	 * Gibt die Anzhal der Karten zurück, z.B. zwei Stück bedeutet ein Doppel
	 * @return die Anzahl der Karten
	 */
	public int getAnzahl(){
		return this.karten.size();
	}
	
	/**
	 * Gibt eine Karte aus dem Container zurück und löscht diese aus ihm.
	 * Wird noch nicht benutzt
	 * @param index Die Nummer der Karte
	 * @return
	 */
	public Spielkarte getKarte(int index){
		Spielkarte ret = this.karten.get(index);
		this.karten.remove(index);
		return ret;
	}
}
