package projekt_BPC_pC2T;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import projekt_BPC_pC2T.MySQL.DBManagement;

public class StudentManagement {

	public static boolean pridatStudenta(int id, String meno, String priezvisko, LocalDate datum, String odbor) {
		Student student;	
		if (odbor.equalsIgnoreCase(typyOdborov.IBE.toString()) ) {
				student = new BPC_IBE(id, meno, priezvisko, datum, typyOdborov.IBE);
			}
		else if (odbor.equalsIgnoreCase(typyOdborov.TLI.toString())) {
			student = new BPC_TLI(id, meno, priezvisko, datum, typyOdborov.TLI);
		}
		else return false;
		DBManagement.studentiMap.put(id, student);
		return true;
	}

	public static Student  najstStudenta(int id) {
		return DBManagement.studentiMap.get(id); 
	
		
	}

	public static HashMap<Integer, Student> getStudentiMap() {
		return DBManagement.studentiMap;
	}

	public static void addToStudentiMap(int id, Student student) {
		DBManagement.studentiMap.put(id, student);
	}

	public static boolean vymazatStudenta(int id) {
		if (DBManagement.studentiMap.get(id) == null) {
			return false;
		}
		DBManagement.studentiMap.remove(id);
		return true;
	}

	public static Map<typyOdborov, List<Student>> getZoradenychStudentovPodlaOdboru() {
	    Map<typyOdborov, List<Student>> studentiPodlaOdboru = new HashMap<>();
	
	    for (Student student : DBManagement.studentiMap.values()) {
	        typyOdborov odbor = student.getOdbor();
	        studentiPodlaOdboru.computeIfAbsent(odbor, k -> new ArrayList<>()).add(student);
	    }
	
	
	    for (List<Student> studenti : studentiPodlaOdboru.values()) {
	        studenti.sort(Comparator.comparing(Student::getPriezvisko));
	    }
	
	    return studentiPodlaOdboru;
	}

	public static void vypisZoradenychStudentov () {
	Map <typyOdborov, List<Student>> studentiPodlaOdboru = getZoradenychStudentovPodlaOdboru();
	
	for (typyOdborov odbor : typyOdborov.values()) {
		List<Student> studenti = studentiPodlaOdboru.get(odbor);
		
		System.out.println("\n/---------------------------------------------------------------------\\");
		System.out.println("\033[1;35m" + "Odbor: "  + odbor + "\033[0m");
		for (Student student : studenti) {
			String rokNarodenia = String.valueOf(student.getDatumNarodenia().getYear());
			
			System.out.print("\033[1;32m" + "ID: " + "\033[0m" + student.getID());
			System.out.print("\t\033[1;32m" + "rok narodenia: " + "\033[0m" + rokNarodenia);
			System.out.println("\t\033[1;32m" + "Meno a priezvisko: " + "\033[0m" + student.getMeno() + " " + student.getPriezvisko());
			
	
			
			}
		System.out.println("\033[0;1m" + "\\---------------------------------------------------------------------/" + "\033[0m");
			
		}
	}

}
