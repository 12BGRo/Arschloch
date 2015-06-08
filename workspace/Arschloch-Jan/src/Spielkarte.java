
public class Spielkarte  {

	private String name;
	private int farbe;
	private int wert;
	private int id;

	public Spielkarte(String name, int farbe, int wert){
		this.farbe = farbe;
		this.name = name;
		this.wert = wert;
	}

	public Spielkarte(int id){
		int fab = 1;
		int wet = 7;
		for (int i = 1; i < 33; i++){
			if (fab>4){
				fab = 1;
				wet ++;
			}
			if (i == id){
				this.farbe = fab;
				this.wert = wet;
			}
			fab ++;
		}
		String nam1 = "";
		switch(this.farbe){
		case 1: 
			nam1 = "Kreuz ";
			break;
		case 2:
			nam1 = "Pik ";
			break;
		case 3:
			nam1 = "Herz ";
			break;
		case 4:
			nam1 = "Karo ";
			break;
		}

		String nam2 = "" + this.wert;
		if (this.wert > 10){
			switch(this.wert){
			case 11: 
				nam2 = "Bube";
				break;
			case 12:
				nam2 = "Dame";
				break;
			case 13:
				nam2 = "König";
				break;
			case 14:
				nam2 = "Ass";
				break;
			}
		}
		this.name = nam1 + nam2;

	}


	public String getName() {
		return name;
	}



	public int getFarbe() {
		return farbe;
	}


	public int getWert() {
		return wert;
	}

}
