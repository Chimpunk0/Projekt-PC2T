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
	
	public static int lenCeleCisla(Scanner skener) {
		int cislo = 0;
		try
		{
			cislo = skener.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vinimka typu "+e.toString());
			System.out.println("zadajte prosim cele cislo ");
			skener.nextLine();
			cislo = lenCeleCisla(skener);
		}
		return cislo;
	}
	
	public static void cakajEnter () {
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
			
			volba = lenCeleCisla(skener);
			
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
				id = lenCeleCisla(skener);
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
							int znamka = lenCeleCisla(skener);
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
				System.out.println("zadajte ID studenta: ");
				int idVyhodeneho = lenCeleCisla(skener);
				if (idVyhodeneho > posledneId) {
					System.out.println("Zadali ste ID mimo rozsah.");
				}
				else {
					Student prepusteny = DBManagement.najstStudenta(idVyhodeneho);
					if (prepusteny == null){
						System.out.println("Student nebol najdeny");
					}
					else {
					DBManagement.vymazatStudenta(idVyhodeneho);
					DeleteQueries.deleteStudent(idVyhodeneho);
					System.out.println("Student bol vymazany");
					}
				}
				break;
				
			case 4: 
				
				System.out.println("zadajte ID studenta: ");
				int hladaneId = lenCeleCisla(skener);
				if (hladaneId > posledneId) {
					System.out.println("Student s tymto ID neexistuje.");
				}
				else {
					Student hladanyStudent = DBManagement.najstStudenta(hladaneId);
					if (hladanyStudent == null) {
						System.out.println("Student nebol najdeny");
					}
					else {
					
					System.out.println("/-----------------------------------------------\\");
					System.out.println( "Meno a priezvisko: " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko());
					System.out.println("odbor: " + hladanyStudent.getOdbor());
					System.out.println("dátum narodenia: " + hladanyStudent.getDatumNarodenia());
					System.out.println("\\-----------------------------------------------/");
					}
					skener.nextLine();
					cakajEnter();
				}
				
				break;
			case 5:
				System.out.println("zadajte ID studenta: ");
				int idSchopnost = lenCeleCisla(skener);
				if (idSchopnost > posledneId) {
					System.out.println("Student s tymto ID neexistuje.");
				}
				else {
					Student hladanyStudent = DBManagement.najstStudenta(idSchopnost);
					if (hladanyStudent == null) {
						System.out.println("Student nebol najdeny");
					}
					else {
						if (hladanyStudent.getOdbor() == typyOdborov.IBE)
						{
							System.out.println("Meno " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko() +
												" prevedene do hashu: " + hladanyStudent.vykonajSchopnost());
						}
						else if (hladanyStudent.getOdbor() == typyOdborov.TLI)
						{
							System.out.println("Meno " + hladanyStudent.getMeno() + " " + hladanyStudent.getPriezvisko() + 
							"prevedene do morseovky: " + hladanyStudent.vykonajSchopnost());

						}
					
					}
	
				}
				
				break;
			case 6:
				break;
			case 7:
				Ostatne.vycistiKonzolu();
				System.out.println("Vypis celkoveho priemeru pre odbor");
				System.out.println("----------------------------------");
				System.out.print("Zadajte ziadany odbor: ");
				String hladanyOdbor = skener.next().toUpperCase();
				float priemer = SelectQueries.celkovyPriemer(hladanyOdbor);
				System.out.println(priemer);
				skener.nextLine();
				cakajEnter();
				break;
			case 8:
				Ostatne.vycistiKonzolu();
				System.out.println("Vypis celkoveho poctu ziakov v odbore");
				System.out.println("----------------------------------");
				System.out.print("Zadajte ziadany odbor: ");
				String hladanyOdbor2 = skener.next().toUpperCase();
				int pocetStudentov = SelectQueries.pocetStudentov(hladanyOdbor2);
				System.out.println("Pocet studentov v odbore " + hladanyOdbor2 + ": " + pocetStudentov);
				skener.nextLine();
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
			}
	}

	}

}
