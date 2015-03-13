
public class Spielkarte {
	
	private String name;
	private int farbe;
	private int wert;
	StringBuilder sb = new StringBuilder();
	
	public Spielkarte(String name, int farbe, int wert){
		this.farbe = farbe;
		this.name = name;
		this.wert = wert;
	}

	
	public String toString(){
		String retValue;
		
		sb.append(name);
		sb.append(" ");
		sb.append(farbe);
		sb.append(" ");
		sb.append(wert);
		
		retValue = sb.toString();
		
		return retValue;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFarbe() {
		return farbe;
	}

	public void setFarbe(int farbe) {
		this.farbe = farbe;
	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}
}
