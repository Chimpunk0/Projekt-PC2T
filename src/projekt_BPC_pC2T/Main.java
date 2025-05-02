package projekt_BPC_pC2T;


import java.time.LocalDate;
import java.util.Scanner;



import projekt_BPC_pC2T.MySQL.DBManagement;
import projekt_BPC_pC2T.MySQL.DeleteQueries;
import projekt_BPC_pC2T.MySQL.InsertQueries;
import projekt_BPC_pC2T.MySQL.SelectQueries;



public class Main {
	
	private static final Scanner skener = new Scanner(System.in);
	
	private static void cakajEnter () {
		System.out.println("Stlacte Enter na pokracovanie...");
		skener.nextLine();
		SpracovanieRozhrania.vycistiKonzolu();
		SpracovanieRozhrania.vypisVolby();
	}

	
	

	public static void main(String[] args) {
		 
	   DBManagement.connectDB();
	   DBManagement.createTable();
	   DBManagement.inicializaciaMaxId();
	   int posledneId = DBManagement.getAktualneMaxId();
	   DBManagement.nacitajStudentovDoMapy();
	  
	   
	   
	   int volba;
	   boolean run = true;
	   SpracovanieRozhrania.vypisVolby();
	   
		
	   while(run) {
			
			volba = SpracovanieVstupov.lenCeleCisla(skener);
			
		switch(volba)
			{
			case 1:
				
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Pridanie studenta" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while(true) {
					System.out.println("0 pre navrat do menu");
					System.out.print("zadajte meno: ");
					String meno = skener.next();
					
					if (meno.equals("0")) {
						break;
					}
					
					System.out.print("Zadajte priezvisko: ");
					String priezvisko = skener.next();
					
					if (meno.equals("0") || priezvisko.equals("0")) {
						break;
					}
					
					skener.nextLine();
					System.out.print("zadajte datum narodenie studenta: ");
					LocalDate datum = SpracovanieVstupov.lenLocalDate(skener);
					System.out.print("zadajte odbor, do ktoreho chcete studenta priradit (TLI/IBE): ");
					typyOdborov odbor = SpracovanieVstupov.lenOdbor(skener);
					
					
					int id = ++posledneId;
					if (StudentManagement.pridatStudenta(id, meno, priezvisko, datum, odbor.toString()))
					{
						InsertQueries.insertStudent(id, meno, priezvisko, datum, odbor.toString());
						System.out.println("Student s ID: " + id + " uspesne pridany");
						break;
					}
					else {
						System.out.println("Studenta sa nepodarilo pridat");
					}
					
					break;
				}
				skener.nextLine();
				cakajEnter();
				break;
			case 2:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Pridanie znamky studentovi" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while(true) {
				System.out.print("Zadajte ID ziadaneho studenta (0 pre navrat do menu): ");
				int id = SpracovanieVstupov.lenCeleCisla(skener);
				
				if(id == 0) {
					break;
				}
				
				if (id > posledneId) {
					System.out.println("Mimo rozsah");
					continue;
				}
				
				Student student = StudentManagement.najstStudenta(id);
				if ( student == null) {
					System.out.println("Student neexistuje");
					continue;
				}
				
				System.out.println("Známky študenta " + student.getMeno() + ": " + student.getZnamky());
				System.out.print("Pridajte znamku: ");
				
				int znamka = SpracovanieVstupov.lenZnamka(skener);
							
				student.pridatZnamku(znamka);
				InsertQueries.insertZnamka(id, znamka, student.getOdbor().toString());
				System.out.println("Známky študenta " + student.getMeno() + ": " + student.getZnamky());	
				break;
						
				}
				skener.nextLine();
				cakajEnter();
				break;
			case 3:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Vymazanie studenta" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while (true) {
					
					System.out.println("zadajte ID studenta (0 pre navrat do menu): ");
					int idVyhodeneho = SpracovanieVstupov.lenCeleCisla(skener);
					
					if (idVyhodeneho == 0) {
						break;
					}
					
					if (idVyhodeneho > posledneId) {
						System.out.println("Zadali ste ID mimo rozsah.");
						continue;
					}
					
					
					Student prepusteny = StudentManagement.najstStudenta(idVyhodeneho);
					if (prepusteny == null){
						System.out.println("Student nebol najdeny");
						continue;
						}
						

						StudentManagement.vymazatStudenta(idVyhodeneho);
						DeleteQueries.deleteStudent(idVyhodeneho);
						System.out.println("Student bol vymazany");
						break;
					}
				skener.nextLine();
				cakajEnter();
				break;
				
			case 4: 
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Vyhladanie studenta" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while(true) {
					System.out.print("zadajte ID studenta (0 pre navrat do menu): ");
					int hladaneId = SpracovanieVstupov.lenCeleCisla(skener);
					
					if(hladaneId == 0) {
						break;
					}
					
					if (hladaneId > posledneId) {
						System.out.println("Student s tymto ID neexistuje.");
						continue;
					}
					
					Student hladanyStudent = StudentManagement.najstStudenta(hladaneId);
					if (hladanyStudent == null) {
						System.out.println("Student nebol najdeny");
						continue;
					}
						
						
					System.out.println("\033[0;1m" + "/-------------------------------------------\\" + "\033[0m");
					System.out.println("\033[1;32m" + "ID: " + "\033[0m" + hladanyStudent.getID());
					System.out.println("\033[1;32m" + "Meno a priezvisko: " + "\033[0m" + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko());
					System.out.println("\033[1;32m" + "dátum narodenia: " + "\033[0m" + hladanyStudent.formatovanyDatumNarodenia());
					System.out.println("\033[1;32m" + "odbor: " + "\033[0m" + hladanyStudent.getOdbor());
					System.out.println("\033[1;32m" + "známky: " + "\033[0m" + hladanyStudent.getZnamky());
					System.out.println("\033[1;32m" + "priemer: " + "\033[0m" + hladanyStudent.getPriemer());
					System.out.println("\033[0;1m" + "\\-------------------------------------------/" + "\033[0m");
					skener.nextLine();
					break;
					

				}
				cakajEnter();
				break;
			case 5:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Schopnost studenta" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while(true) {
					System.out.println("zadajte ID studenta (0 pre navrat do menu): ");
					int idSchopnost = SpracovanieVstupov.lenCeleCisla(skener);
					
					if (idSchopnost == 0) {
						break;
					}
					
					if (idSchopnost > posledneId) {
						System.out.println("Student s tymto ID neexistuje.");
						continue;
					}
					
					Student hladanyStudent = StudentManagement.najstStudenta(idSchopnost);
					if (hladanyStudent == null) {
						System.out.println("Student nebol najdeny");
						continue;
					}
						
					if (hladanyStudent.getOdbor() == typyOdborov.IBE)
					{
						System.out.println("/------------------------------------------------------------\\");
						System.out.println("Meno " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko() +
											" prevedene do hashu: " + hladanyStudent.vykonajSchopnost());
						System.out.println("\\------------------------------------------------------------/");
					}
					else if (hladanyStudent.getOdbor() == typyOdborov.TLI)
					{
						System.out.println("/-----------------------------------------------------------------------------------------------------------------\\");
						System.out.println("Meno " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko() + 
											" prevedene do morseovky: " + hladanyStudent.vykonajSchopnost());
						System.out.println("\\-----------------------------------------------------------------------------------------------------------------/");

	
					}
					skener.nextLine();
					cakajEnter();
					break;

				}
		
				break;
				
			case 6:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Zoradenie studentov" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				StudentManagement.vypisZoradenychStudentov();
				skener.nextLine();
				cakajEnter();
				break;
			case 7:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Vypis celkoveho priemeru pre odbor" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				System.out.print("Zadajte ziadany odbor (0 pre navrat do menu): ");
				while(true) {
					
					
					typyOdborov hladanyOdbor = SpracovanieVstupov.lenOdbor(skener);
					
					if (hladanyOdbor == null) {
						break;
					}
					
					float priemer = SelectQueries.celkovyPriemer(hladanyOdbor.toString());
					System.out.println(priemer);
					skener.nextLine();
					break;
					
					
				}
				cakajEnter();
				break;
			case 8:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Vypis celkoveho poctu ziakov v odbore" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				System.out.print("Zadajte ziadany odbor (0 pre navrat do menu): ");
				while(true) {
					typyOdborov hladanyOdbor = SpracovanieVstupov.lenOdbor(skener);
					
					if(hladanyOdbor == null) {
						break;
					}
					
					int pocetStudentov = SelectQueries.pocetStudentov(hladanyOdbor.toString());
					System.out.println("Pocet studentov v odbore " + hladanyOdbor + ": " + pocetStudentov);
					skener.nextLine();
					
					break;
				}
				cakajEnter();
				break;
			case 9:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Ulozenie do suboru" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while(true) {
					System.out.println("zadajte ID studenta (0 pre navrat do menu): ");
					int idUkladanie = SpracovanieVstupov.lenCeleCisla(skener);
					
					if (idUkladanie == 0) {
						break;
					}
					
					if (idUkladanie > posledneId) {
						System.out.println("Student s tymto ID neexistuje.");
						continue;
					}
					
					if (Txtsubor.ulozDoSuboru(idUkladanie)) {
						System.out.println("Student s ID "+idUkladanie+" bol ulozeny do suboru.");
						skener.nextLine();
						break;
					}
					else {
						System.out.println("Studenta sa nepodarilo ulozit do suboru.");
						skener.nextLine();
						break;
					}
					
				}
				
				cakajEnter();	
				break;
			case 10:
				boolean vlozitDoDatabazy;
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Nacitanie zo suboru" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				while(true) {
					System.out.print("zadajte nazov suboru alebo jeho cestu (0 pre navrat do menu): ");
					String filename = skener.next();
					
					if (filename.equals("0")) {
						break;
					}
					
					System.out.print("Chcete ulozit studenta od databazy? (y/n): ");
					vlozitDoDatabazy = SpracovanieVstupov.ulozZoSuboru(skener);
					
					
					
					boolean loadResult = Txtsubor.nacitajZoSuboru(filename, vlozitDoDatabazy);
			        if (loadResult) {
			            break;
			        } else {
			            System.out.println("Nacitanie zo suboru sa nepodarilo.");
			        }
			    }
				skener.nextLine();
				cakajEnter();
			    break;
			case 11:
				System.out.println(FormatovanyText.MAGENTA_BOLD + "Vypis databazy" + FormatovanyText.RRESET);
				System.out.println("------------------------------");
				SelectQueries.vypisDatabazy();
				skener.nextLine();
				 cakajEnter();
				break;
			case 12:
				run = false;
				skener.close();
				SpracovanieRozhrania.vycistiKonzolu();
				System.out.println("KONIEC programu");
				return;
			default: 
				System.out.println("Neplatna volba");
				skener.nextLine();
				cakajEnter();
			}
	}

	}

}
