package projekt_BPC_pC2T;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import projekt_BPC_pC2T.MySQL.DBManagement;
import projekt_BPC_pC2T.MySQL.InsertQueries;

public class Txtsubor {
	
	public static boolean ulozDoSuboru(int id) {
		try {
			Student student = StudentManagement.najstStudenta(id);
			
			if (student == null) {
				System.out.println("Student s tymto ID neexistuje");
				return false;
			}
			
			File adresar = new File("txtsubory");
	        if (!adresar.exists()) {
	            adresar.mkdir();
	        }
			
			FileWriter fw = new FileWriter("txtsubory/id"+id+".txt");
			BufferedWriter out = new BufferedWriter(fw);
			out.write(new String("ID: " + student.getID()));
			out.newLine();
			out.write(new String("Meno: " + student.getMeno()));
			out.newLine();
			out.write(new String("Priezvisko: " + student.getPriezvisko()));
			out.newLine();
			out.write(new String("Datum narodenia: " + student.getDatumNarodenia()));
			out.newLine();
			out.write(new String("Odbor: " + student.getOdbor()));
			out.newLine();
			
			List<Integer> znamky = student.getZnamky();
			out.write(new String("Znamky: "));
			if (znamky.size()==0) {
				out.write(new String ("-"));
			}
			for (int i = 0; i<znamky.size();i++) {
				out.write(String.valueOf(znamky.get(i)));
				if (i<znamky.size()-1) {
					out.write(new String (", "));
				}
			}
			
			out.newLine();
			
			out.close();
			fw.close();
		}
		catch (IOException e) {
			System.out.println("Subor nie je mozne vytvorit");
			return false;
		}
		return true;
	}
	
	
	public static boolean nacitajZoSuboru(String filename, boolean vlozitDoDatabazy) {
		Student student;
		String meno = "", priezvisko = "";
		LocalDate datumNarodenia = null;
		List<Integer> znamky = new ArrayList<>();
		typyOdborov odbor = null;
		int id = 0;
		
		
		FileReader fr=null;
		BufferedReader in=null;

		try {
			fr = new FileReader("txtsubory/" + filename);
			in = new BufferedReader(fr);
			String riadok;
			while ((riadok=in.readLine()) != null) {
				String[] casti = riadok.split(":",2);
				if (casti.length !=2) continue;
				
				String kluc = casti[0].trim();
				String hodnota = casti[1].trim();
				
				switch(kluc) {
				case "ID":
					id=Integer.parseInt(hodnota);
					break;
				case "Meno":
					meno=hodnota;
					break;
				case "Priezvisko":
					priezvisko=hodnota;
					break;
				case "Datum narodenia":
					datumNarodenia = LocalDate.parse(hodnota);
					break;
				case "Odbor":
					odbor = typyOdborov.valueOf(hodnota);
					break;
				case "Znamky":
					if (!hodnota.equals("-")) {
						for (String znamka : hodnota.split(", ")) {
							znamky.add(Integer.parseInt(znamka));
						}
					}
					
					break;
				}
				
			}
			
			if (id == 0) {
				int posledneId  =  DBManagement.getAktualneMaxId();
				id = ++posledneId;
			}
			
			
			
			if (odbor == typyOdborov.IBE) {
			    student = new BPC_IBE(id, meno, priezvisko, datumNarodenia, odbor);
			} else {
			    student = new BPC_TLI(id, meno, priezvisko, datumNarodenia, odbor);
			}
			  for (int znamka : znamky) {
	                student.pridatZnamku(znamka);
	            }
			  
			  if (vlozitDoDatabazy == true) {
					StudentManagement.addToStudentiMap(id, student);
					InsertQueries.insertStudent(id, meno, priezvisko, datumNarodenia, odbor.toString());
					for (int znamka : znamky){
						InsertQueries.insertZnamka(id, znamka, odbor.toString());
					}
					System.out.println("Student bol pridany");
				}
			  else System.out.println("Student nebol pridany");
			  

			  
			
			  System.out.println("\033[0;1m/-------------------------------------------\\\033[0m");
	            System.out.println("\033[1;32mID: \033[0m" + student.getID());
	            System.out.println("\033[1;32mMeno a priezvisko: \033[0m" + student.getMeno() + " " + student.getPriezvisko());
	            System.out.println("\033[1;32mDátum narodenia: \033[0m" + student.formatovanyDatumNarodenia());
	            System.out.println("\033[1;32mOdbor: \033[0m" + student.getOdbor());
	            System.out.println("\033[1;32mZnamky: \033[0m" + student.getZnamky());
	            System.out.println("\033[0;1m\\-------------------------------------------/\033[0m");
	            
	            return true;
		}
		
		catch (FileNotFoundException e) {
	        System.out.println("Subor neexistuje!");
	        return false;
	    } catch (Exception e) {
	        System.out.println("Chyba: " + e.getMessage());
	        return false;
	    }
		
		finally
		{
			try
			{	if (in!=null)
				{
					in.close();
					fr.close();
				}
			}
			catch (IOException e) {
				System.out.println("Soubor nie je mozne zavriet");
				return false;
			} 
		}
		
	}
	
	
	public static boolean nacitajZoSuboru(String filename) {
		return nacitajZoSuboru(filename, false);
	}
	

}
