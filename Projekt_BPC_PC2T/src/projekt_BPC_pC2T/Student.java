package projekt_BPC_pC2T;

public abstract class Student {
	
	private int ID;
	private String meno;
	private String priezvisko;
	private String odbor;
	
	private Integer[] datumNarodenia;

	public Student(int iD, String meno, String priezvisko, Integer[] datumNarodenia, String odbor) {
		ID = iD;
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

	public String getOdbor() {
		return odbor;
	}

	public void setOdbor(String odbor) {
		this.odbor = odbor;
	}
	
	

}
