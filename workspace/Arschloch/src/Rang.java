
public class Rang {
	private String rang;
	private int id;
	
	/**
	 * Konstruktor von Rang
	 * @param rang String mit dem Rang. Erlaubt sind: "King", "Vieze", "Arsch", "ViezeArsch"
	 * @throws RangGibtsNichException wird geworfen, wenn es den übergebenen Rang nicht gibt
	 */
	public Rang(String rang) throws RangGibtsNichException{
		if (rang.equals("King") || rang.equals("Vieze") || rang.equals("Arsch") || rang.equals("ViezeArsch")){
			this.rang = rang;
			String[] raenge = new String[]{"Arsch", "ViezeArsch", "Vieze", "King"};
			for (int i=0; i<4; i++){
				if (rang.equals(raenge[i])){
					this.id = i;
				}
			}
		}
		else{
			throw new RangGibtsNichException();
		}
	}
	
	/**
	 * Gibt den Rang als String zurück
	 * @return String mit dem Rang
	 */
	public String getRang(){
		return this.rang;
	}
	
	/**
	 * Gibt die ID des Ranges zurück:
	 * Arsch = 0, ViezeArsch = 1, Vieze = 2, Koenig = 3
	 * @return int mit der ID
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * Vergleicht den Rang mit dem übergebenen.
	 * @param rang der Rang, mit dem verglichen werden soll
	 * @return false, wenn der übergebene Rang größer ist
	 */
	public boolean istHoeher(Rang rang){
		return this.id > rang.getId();
	}
	
	/**
	 * Vergleicht den Rang mit dem übergebenen.
	 * @param rang der Rang, mit dem verglichen werden soll
	 * @return false, wenn der übergebene Rang kleiner ist
	 */
	public boolean istNiedriger(Rang rang){
		return this.id < rang.getId();
	}
}
