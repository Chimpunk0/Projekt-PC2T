package projekt_BPC_pC2T;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Databaza {
	
	private Map <Integer, Student> studenti = new HashMap<>();
	Scanner skener = new Scanner(System.in);
	
	public void pridatStudenta() {
		
		System.out.print("zadajte meno a priezvisko: ");
		String meno = skener.next();
		String priezvisko = skener.next();
		System.out.println("zadajte datum narodenie studenta");
		int[] datumNarodenia = new int[3];
		for (int i = 0; i < 3; i++)
		{
			datumNarodenia[i] = skener.nextInt();
		}
		System.out.print("zadajte odbor, do ktoreho chcete studenta priradit: ");
		String odbor = skener.next();
		if (odbor.equals(typyOdborov.IBE) ) {
			

		}
		else if (odbor.equals("TLI")) {
			
		}
	}
	
	public void  najstStudenta(int ID) {
		Student hladanyStudent = studenti.get(ID); 
	
		System.out.println("/-----------------------------------------------\\");
		System.out.println( "Meno a priezvisko: " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko());
		System.out.println("odbor: " + hladanyStudent.getOdbor());
		System.out.println("dátum narodenia: " + hladanyStudent.vypisDatumNarodenia());
		System.out.println("\\-----------------------------------------------/");
	}

}
