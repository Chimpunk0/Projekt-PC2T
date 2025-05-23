package projekt_BPC_pC2T;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import projekt_BPC_pC2T.MySQL.DBManagement;

public abstract class Student {
	
	protected int id;
	private String meno;
	protected String priezvisko;
	private int posledneId = DBManagement.getAktualneMaxId();
	protected final typyOdborov odbor;
	private LocalDate datumNarodenia;
	private List<Integer> znamky;

	public Student(int id, String meno, String priezvisko, LocalDate datumNarodenia, typyOdborov odbor) {
		this.id = id;
		this.meno = meno;
		this.priezvisko = priezvisko;
		this.datumNarodenia = datumNarodenia;
		this.odbor = odbor;
		this.znamky = new ArrayList<Integer>();
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
	
	public String formatovanyDatumNarodenia() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy").withResolverStyle(ResolverStyle.STRICT);
		
		return datumNarodenia.format(formatter);
	}

	public void setDatumNarodenia(LocalDate datumNarodenia) {
		this.datumNarodenia = datumNarodenia;
	}
	
	public typyOdborov getOdbor() {
		return odbor;
	}
	
	public void pridatZnamku(int znamka) {
		this.znamky.add(znamka);
	}
	
	public List<Integer> getZnamky() {
		
		return znamky;
	}
	
	public double getPriemer() {
        if (znamky.isEmpty()) {
            return 0.0; 
        }
        int pocitadlo = 0;
        double hodnotaZnamok = 0;
        for (int znamka : znamky) {
        	hodnotaZnamok += znamka;
        	++pocitadlo;
        }
        return hodnotaZnamok / pocitadlo;
    }

	

}
