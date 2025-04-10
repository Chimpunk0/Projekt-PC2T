package projekt_BPC_pC2T;

import java.util.HashMap;
import java.util.Map;

public class Databaza {
	
	private Map <Integer, Student> studenti = new HashMap<>();
	
	public void pridatStudenta(Student student) {
		studenti.put(student.getID(), student);
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
