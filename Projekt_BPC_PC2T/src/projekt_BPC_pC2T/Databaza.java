package projekt_BPC_pC2T;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Databaza {
	
	private Map <Integer, Student> prvkyDatabazy;
	
	public Databaza() {
		prvkyDatabazy = new HashMap<Integer, Student>();
	}
	Scanner skener = new Scanner(System.in);
	
	
	
	public boolean pridatStudenta(int id, String meno, String priezvisko, LocalDate datum, String odbor) {
		if (odbor.equals(typyOdborov.IBE.name()) ) {
			
			if (prvkyDatabazy.put(id, new BPC_IBE(id, meno, priezvisko, datum, typyOdborov.IBE)) == null) {
				return true;
			}
			else return false;
		}
		else if (odbor.equals(typyOdborov.TLI.name())) {
			if (prvkyDatabazy.put(id, new BPC_TLI(id, meno, priezvisko, datum, typyOdborov.TLI)) == null) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public Student  najstStudenta(int id) {
		return prvkyDatabazy.get(id); 
	
		
	}

	public boolean vymazatStudenta(int id) {

		if (prvkyDatabazy.get(id) == null) {
			return false;
		}
		prvkyDatabazy.remove(id);
		return true;
	}
	
	
}
