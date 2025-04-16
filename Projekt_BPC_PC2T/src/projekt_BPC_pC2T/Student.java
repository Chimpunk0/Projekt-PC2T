package projekt_BPC_pC2T;

import java.time.LocalDate;
import java.util.List;

import projekt_BPC_pC2T.MySQL.DBManagement;

public abstract class Student {
	
	protected int id;
	private String meno;
	protected String priezvisko;
	private int posledneId = DBManagement.getAktualneMaxId();
	protected final typyOdborov odbor;
	private LocalDate datumNarodenia;
	private List znamky;

	public Student(int id, String meno, String priezvisko, LocalDate datumNarodenia, typyOdborov odbor) {
		this.id = id;
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
