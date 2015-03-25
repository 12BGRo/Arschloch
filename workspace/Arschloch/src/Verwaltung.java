import java.util.ArrayList;


public class Verwaltung {

	private ArrayList<Spieler> spieler = new ArrayList<Spieler>();
	private int anzahlSpieler;
	private KartenDeck deck;
	private Stapel Stapel;
	private Spieler dran;
	private boolean ersterDurchlauf;
	
	public Verwaltung(int anzahlSpieler) {
		this.anzahlSpieler = anzahlSpieler;
		this.deck = new KartenDeck();
	}

	public void austeilen() {
		int kartenProSpieler;
		kartenProSpieler = 32-(32%this.spieler.size())/this.spieler.size();
		
		for(int i = 0;i <= kartenProSpieler; i++) {
			
			for(int j = 0; j <= this.spieler.size();j++) {
				spieler.get(j).addKarte(this.deck.getKarte());		
				
			}
		}
	}
	
	public void removeSpieler(Spieler spieler){
		
		if(this.spieler.contains(spieler)){
			this.spieler.remove(spieler);
		}
	}

}
