package projekt_BPC_pC2T;

public enum Odbor{
	IBE, TLI
}

public abstract class Student {
	
	private int ID;
	private String meno;
	private String priezvisko;
	protected final typyOdborov odbor;
	private static int posldedneID = 0;
	private Integer[] datumNarodenia;

	public Student(int iD, String meno, String priezvisko, Integer[] datumNarodenia, typyOdborov odbor) {
		this.ID = ++posldedneID;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.datumNarodenia = datumNarodenia;
		this.odbor = odbor;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getPriezvisko() {
		return priezvisko;
	}

	public void setPriezvisko(String priezvisko) {
		this.priezvisko = priezvisko;
	}

	public Integer[] getDatumNarodenia() {
		return datumNarodenia;
	}

	public void setDatumNarodenia(Integer[] datumNarodenia) {
		this.datumNarodenia = datumNarodenia;
	}
	
	public typyOdborov getOdbor() {
		return odbor;
	}

	public String vypisDatumNarodenia() {
        if (datumNarodenia != null && datumNarodenia.length == 3) {
            return (datumNarodenia[0] + "." + datumNarodenia[1] + "." + datumNarodenia[2]);
        } else {
            return ("Dátum narodenia nie je zadaný správne.") ;
        }
	
	}

}
