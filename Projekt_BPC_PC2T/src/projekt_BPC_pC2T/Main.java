package projekt_BPC_pC2T;

import java.sql.*;

import java.time.LocalDate;
import java.util.HashMap;
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
		Ostatne.vycistiKonzolu();
		Ostatne.vypisVolby();
	}

	
	

	public static void main(String[] args) {
		 
	   DBManagement.connectDB();
	   DBManagement.createTable();
	   DBManagement.inicializaciaMaxId();
	   int posledneId = DBManagement.getAktualneMaxId();
	   DBManagement.nacitajStudentovDoMapy();
	   //HashMap<Integer, Student> studenti = DBManagement.getStudentiMap();
	   SelectQueries.vypisDatabazy();
	   
	   
	   int volba;
	   boolean run = true;
	   Ostatne.vypisVolby();
	   
		
	   while(run) {
			
			volba = Ostatne.lenCeleCisla(skener);
			
		switch(volba)
			{
			case 1: 
				System.out.print("zadajte meno a priezvisko: ");
				String meno = skener.next();
				String priezvisko = skener.next();
				skener.nextLine();
				System.out.print("zadajte datum narodenie studenta (RRRR-MM-DD): ");
				String vstup = skener.nextLine();
				LocalDate datum = LocalDate.parse(vstup);
				System.out.print("zadajte odbor, do ktoreho chcete studenta priradit (TLI/IBE): ");
				String odbor = skener.next().toUpperCase();
				int id = ++posledneId;
				
				
				if (DBManagement.pridatStudenta(id, meno, priezvisko, datum, odbor))
				{
					InsertQueries.insertStudent(id, meno, priezvisko, datum, odbor);
					System.out.println("Student is ID: " + id + " uspesne pridany");
				}
				else {
					System.out.println("Studenta sa nepodarilo pridat");
				}
				skener.nextLine();
				cakajEnter();
				break;
			case 2:
				System.out.print("Zadajte ID ziadaneho studenta: ");
				id = Ostatne.lenCeleCisla(skener);
				if (id > posledneId) {
					System.out.println("Mimo rozsah");
				}
					else {
						Student student = DBManagement.najstStudenta(id);
						if ( student == null) {
							System.out.println("Student neexistuje");
						}
						else {
							System.out.println("Známky študenta " + student.getMeno() + ": " + student.getZnamky());
							System.out.print("Pridajte znamku: ");
							int znamka = Ostatne.lenCeleCisla(skener);
							if (znamka < 1 || znamka > 5) {
								System.out.println("Mimo rozsah, (1-5)!");
							}
								else {
									student.pridatZnamku(znamka);
									InsertQueries.insertZnamka(id, znamka, student.getOdbor().toString());
									System.out.println("Známky študenta " + student.getMeno() + ": " + student.getZnamky());	
									
								}
						
						}
						
				}
				skener.nextLine();
				cakajEnter();
				break;
			case 3:
				while (true) {
					
					System.out.println("zadajte ID studenta (0 pre navrat do menu): ");
					int idVyhodeneho = Ostatne.lenCeleCisla(skener);
					
					if (idVyhodeneho == 0) {
						break;
					}
					
					if (idVyhodeneho > posledneId) {
						System.out.println("Zadali ste ID mimo rozsah.");
						continue;
					}
					
					
					Student prepusteny = DBManagement.najstStudenta(idVyhodeneho);
					if (prepusteny == null){
						System.out.println("Student nebol najdeny");
						continue;
						}
						

						DBManagement.vymazatStudenta(idVyhodeneho);
						DeleteQueries.deleteStudent(idVyhodeneho);
						System.out.println("Student bol vymazany");
					}
				
				cakajEnter();
				break;
				
			case 4: 
				while(true) {
					System.out.println("zadajte ID studenta (0 pre navrat do menu): ");
					int hladaneId = Ostatne.lenCeleCisla(skener);
					
					if(hladaneId == 0) {
						break;
					}
					
					if (hladaneId > posledneId) {
						System.out.println("Student s tymto ID neexistuje.");
						continue;
					}
					
					Student hladanyStudent = DBManagement.najstStudenta(hladaneId);
					if (hladanyStudent == null) {
						System.out.println("Student nebol najdeny");
						continue;
					}
						
						
					System.out.println("\033[0;1m" + "/-------------------------------------------\\" + "\033[0m");
					System.out.println("\033[1;32m" + "ID: " + "\033[0m" + hladanyStudent.getID());
					System.out.println("\033[1;32m" + "Meno a priezvisko: " + "\033[0m" + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko());
					System.out.println("\033[1;32m" + "dátum narodenia: " + "\033[0m" + hladanyStudent.getDatumNarodenia());
					System.out.println("\033[1;32m" + "odbor: " + "\033[0m" + hladanyStudent.getOdbor());
					System.out.println("\033[1;32m" + "známky: " + "\033[0m" + hladanyStudent.getZnamky());
					System.out.println("\033[1;32m" + "priemer: " + "\033[0m" + hladanyStudent.getPriemer());
					System.out.println("\033[0;1m" + "\\-------------------------------------------/" + "\033[0m");
					skener.nextLine();
					cakajEnter();
					break;
					

				}
				cakajEnter();
				break;
			case 5:
				while(true) {
					System.out.println("zadajte ID studenta (0 pre navrat do menu): ");
					int idSchopnost = Ostatne.lenCeleCisla(skener);
					
					if (idSchopnost == 0) {
						break;
					}
					
					if (idSchopnost > posledneId) {
						System.out.println("Student s tymto ID neexistuje.");
						continue;
					}
					
					Student hladanyStudent = DBManagement.najstStudenta(idSchopnost);
					if (hladanyStudent == null) {
						System.out.println("Student nebol najdeny");
						continue;
					}
						
					if (hladanyStudent.getOdbor() == typyOdborov.IBE)
					{
						System.out.println("/-------------------------------------------------------------\\");
						System.out.println("Meno " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko() +
											" prevedene do hashu: " + hladanyStudent.vykonajSchopnost());
						System.out.println("\\------------------------------------------------------------/");
					}
					else if (hladanyStudent.getOdbor() == typyOdborov.TLI)
					{
						System.out.println("/-----------------------------------------------------------------------------------------------------------------\\");
						System.out.println("Meno " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko() + 
						"prevedene do morseovky: " + hladanyStudent.vykonajSchopnost());
						System.out.println("\\-----------------------------------------------------------------------------------------------------------------/");

	
					}
					skener.nextLine();
					cakajEnter();
					break;

				}
		
				break;
				
			case 6:
				DBManagement.vypisZoradenychStudentov();
				skener.nextLine();
				cakajEnter();
				break;
			case 7:
				System.out.println("Vypis celkoveho priemeru pre odbor ");
				System.out.println("----------------------------------");
				System.out.print("Zadajte ziadany odbor (0 pre navrat do menu): ");
				while(true) {
					
					
					typyOdborov hladanyOdbor = Ostatne.lenOdbor(skener);
					
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
				System.out.println("Vypis celkoveho poctu ziakov v odbore");
				System.out.println("----------------------------------");
				System.out.print("Zadajte ziadany odbor (0 pre navrat do menu): ");
				while(true) {
					typyOdborov hladanyOdbor = Ostatne.lenOdbor(skener);
					
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
				break;
			case 10:
				break;
			case 11:
				run = false;
				skener.close();
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
