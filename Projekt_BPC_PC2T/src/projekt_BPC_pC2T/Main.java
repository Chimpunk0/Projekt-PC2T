package projekt_BPC_pC2T;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import projekt_BPC_pC2T.MySQL.DBManagement;
import projekt_BPC_pC2T.MySQL.DeleteQueries;
import projekt_BPC_pC2T.MySQL.InsertQueries;
import projekt_BPC_pC2T.MySQL.SelectQueries;



public class Main {
	
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

	

	public static void main(String[] args) {
		
		
	    
	   DBManagement.connectDB();
	   DBManagement.createTable();
	   DBManagement.inicializaciaMaxId();
	   int posledneId = DBManagement.getAktualneMaxId();
	   DBManagement.nacitajStudentovDoMapy();
	   HashMap<Integer, Student> studenti = DBManagement.getStudentiMap();
	   
		
		
		//db.pridatStudenta(studentTLI);
		//db.najstStudenta(studentTLI.getID());
		
		Scanner skener = new Scanner(System.in);
		int volba;
		Student studentx = new BPC_IBE(10, "Jozko", "Mrkvicka", LocalDate.parse("2000-11-11"), typyOdborov.IBE);
		studentx.pridatZnamku(1);
		studentx.pridatZnamku(5);
		studentx.pridatZnamku(4);
		studentx.pridatZnamku(1);
		System.out.println(studentx.getZnamky());
		
		
		
		boolean run = true;
		while(run) {
			System.out.println("Vyberte pozadovanu cinnost:");
			System.out.println("|1 | .. vytvorenie noveho studenta");
			System.out.println("|2 | .. vlozenie znamky studentovi");
			System.out.println("|3 | .. prepustenie studenta");
			System.out.println("|4 | .. vyhladavanie studenta");
			System.out.println("|5 | .. spustenie specialnej schopnosti");
			System.out.println("|5 | .. abecedne zoradenie studentov");
			System.out.println("|6 | .. vypis celkoveho priemeru");
			System.out.println("|7 | .. vypis poctu studentov");
			System.out.println("|8 | .. ulozenie studenta do suboru");
			System.out.println("|9 | .. nacitanie studenta zo suboru");
			System.out.println("|10| .. konec");
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
				String odbor = skener.next();
				int id = ++posledneId;
				
				
				if (DBManagement.pridatStudenta(id, meno, priezvisko, datum, odbor))
				{
					InsertQueries.insertStudent(id, meno, priezvisko, datum, odbor);
					System.out.println("Student uspesne pridany");
				}
				else {
					System.out.println("Studenta sa nepodarilo pridat");
				}
				
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
						if (hladanyStudent.getOdbor().equals(typyOdborov.IBE)){
							System.out.println("WIP");
						}
					
					}
				}
				
				break;
			case 10:
				run = false;
				System.out.println("KONIEC programu");
				return;
			}
	}

	}

}
