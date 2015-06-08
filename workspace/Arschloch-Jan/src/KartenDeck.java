
public class KartenDeck {

	private Spielkarte[] karten = new Spielkarte[32];
	
	public KartenDeck(){
		
		this.fuellen();
		this.mischen();
	}
	/**
	 * Gibt eine Zahl aus dem karten Array zurueck und setzt die Stelle,
	 * welche zurueckgegeben wurde gleich null.
	 * @return
	 */
	public Spielkarte getKarte(){
		Spielkarte retValue = null;
		
		for(int i=0;i<32;i++){
			if(karten[i] != null){ //Prueft ob karten an der Stelle i ein Objekt enhaelt
				retValue = karten[i];
				karten[i] = null;
				i=32;
			}
			
		}
		
		return retValue;
	}
	
	/**
	 * Fuellt das Array "karten" mit den 32 verschiedenen Karten in einem Skat-Kartendeck. 
	 */
	public void fuellen(){
		
		for (int i = 1; i < 33; i++){
			this.karten[i-1] = new Spielkarte(i);
		}
	}
	
	/**
	 * Mischt die Objekte in dem Karten Array 150-mal zufaellig durch.
	 */
	public void mischen(){
		Spielkarte[] tempShuffle = new Spielkarte[2];
		for(int i=1;i<150;i++){
			//Zwei Zufallszahlen werden erzeugt
			int zahl1 = (int) (Math.random()*(31-0+1)+0);
			int zahl2 = (int) (Math.random()*(31-0+1)+0);
			//Prueft ob die beiden Zahlen gleich sind
			if(zahl1 == zahl2){
				if(i>0){
				i--;
				}
			}
			
			//Tauscht die beiden zufaelligen Stellen aus dem karten Array
			else{
			tempShuffle[0] = karten[zahl1];
			tempShuffle[1] = karten[zahl2];
			karten[zahl1] = tempShuffle[1];
			karten[zahl2] = tempShuffle[0];
			}
		}
		
		
	}
	
	
	
	
}
