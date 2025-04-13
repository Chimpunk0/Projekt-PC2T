package projekt_BPC_pC2T;

import java.time.LocalDate;

public abstract class Student {
	
	protected int id;
	private String meno;
	protected String priezvisko;
	protected final typyOdborov odbor;
	private int posledneId = 0;
	private LocalDate datumNarodenia;

	public Student(String meno, String priezvisko, LocalDate datumNarodenia, typyOdborov odbor) {
		this.id = ++posledneId;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.datumNarodenia = datumNarodenia;
		this.odbor = odbor;
	}
	
	abstract String vykonajSchopnost();

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}
	
	public int getposledneId() {
		return posledneId;
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

	public LocalDate getDatumNarodenia() {
		return datumNarodenia;
	}

	public void setDatumNarodenia(LocalDate datumNarodenia) {
		this.datumNarodenia = datumNarodenia;
	}
	
	public typyOdborov getOdbor() {
		return odbor;
	}

	

}
