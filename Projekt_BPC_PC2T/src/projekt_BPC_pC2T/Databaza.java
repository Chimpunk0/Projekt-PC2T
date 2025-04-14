package projekt_BPC_pC2T;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Databaza {
	
	private Map <Integer, Student> prvkyDatabazy;
	private int dalsieID = 1;
	
	public Databaza() {
		prvkyDatabazy = new HashMap<Integer, Student>();
	}
	Scanner skener = new Scanner(System.in);
	int id = 1;
	
	public boolean pridatStudenta(String meno, String priezvisko, LocalDate datum, String odbor) {
<<<<<<< HEAD
		int noveID = dalsieID;
		dalsieID++;
		
=======
>>>>>>> 9c03aacf32fd96b71c34c4e164bc97b7be736d02
		if (odbor.equals(typyOdborov.IBE.name()) ) {
			
			if (prvkyDatabazy.put(noveID, new BPC_IBE(noveID, meno, priezvisko, datum, typyOdborov.IBE)) == null) {
				id=id++;
				return true;
			}
			else return false;
		}
		else if (odbor.equals(typyOdborov.TLI.name())) {
<<<<<<< HEAD
			if (prvkyDatabazy.put(noveID, new BPC_TLI(noveID, meno, priezvisko, datum, typyOdborov.TLI)) == null) {
				id=id++;
=======
			if (prvkyDatabazy.put(id, new BPC_TLI(meno, priezvisko, datum, typyOdborov.TLI)) == null) {
>>>>>>> 9c03aacf32fd96b71c34c4e164bc97b7be736d02
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
