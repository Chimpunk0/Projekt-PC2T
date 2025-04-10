package projekt_BPC_pC2T;

public class BPC_IBE extends Student {
	
	public BPC_IBE(int iD, String meno, String priezvisko, Integer[] datumNarodenia) {

		super(iD, meno, priezvisko, datumNarodenia);
		this.odbor = "IBE";
		
		// TODO Auto-generated constructor stub
	}

	
	public static int hash(String meno, String priezvisko) {
		String menoAPriezvisko = meno + priezvisko;
		
		return menoAPriezvisko.hashCode();
	}
	
}
