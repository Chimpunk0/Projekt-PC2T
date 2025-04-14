package projekt_BPC_pC2T;

import java.time.LocalDate;

public class BPC_IBE extends Student {
	
	public BPC_IBE(int id, String meno, String priezvisko, LocalDate datumNarodenia, typyOdborov odbor) {

		super(id, meno, priezvisko, datumNarodenia, typyOdborov.IBE);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	String vykonajSchopnost() {
		String menoAPriezvisko = this.getMeno()+this.getPriezvisko();
		String hash = String.valueOf(menoAPriezvisko.hashCode());
		return hash;
	}




	
	
}
