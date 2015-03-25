
public class Verwaltung {

	private Spieler[] spieler;
	private KartenDeck deck;
	private Stapel Stapel;
	private Spieler dran;
	private boolean ersterDurchlauf;
	
	public Verwaltung(int anzahlSpieler) {
		this.spieler = new Spieler[anzahlSpieler];
		this.deck = new KartenDeck();
	}

	public void austeilen() {
		int kartenProSpieler;
		kartenProSpieler = 32-(32%this.spieler.length)/this.spieler.length;
		
		for(int i = 0;i <= kartenProSpieler; i++) {
			
			for(int j = 0; j <= this.spieler.length;j++) {
				spieler[j].addKarte(this.deck.getKarte());				
			}
		}
	}

}
