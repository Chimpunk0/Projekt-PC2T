package projekt_BPC_pC2T;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SpracovanieVstupov {

	public static boolean ulozZoSuboru(Scanner skener) {
		boolean vlozitDoDatabazy;
		while(true) {
		String odpoved = skener.next().toLowerCase();
		
		if (odpoved.equals("y")) {
			vlozitDoDatabazy = true;
			break;
		}
		else if (odpoved.equals("n")) {
			vlozitDoDatabazy = false;
			break;
		}
		else {
			System.out.print("Neplatny Vstup, (y/n): ");
			continue;
		}
		
		}
		return vlozitDoDatabazy;
	}

	public static int lenZnamka(Scanner skener) {
		while(true) {
			int znamka = SpracovanieVstupov.lenCeleCisla(skener);
			if (znamka < 1 || znamka > 5) {
				System.out.println("Mimo rozsah, (1-5)!");
				System.out.print("Znova: ");
				continue;
			}
			return znamka;
		}
		
		
	}

	public static LocalDate lenLocalDate(Scanner skener) {
		while(true) {
				String vstup = skener.nextLine().trim();
				LocalDate datum = null;
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.M.yyyy");
				datum = LocalDate.parse(vstup, formatter);
				LocalDate dnes = LocalDate.now();
				if (datum.isAfter(dnes)) {
					System.out.print("Datum narodenia nemoze byt v buducnosti! (DD.M.RRRR): ");
					continue;
				}
				return datum;
			}
			catch(DateTimeParseException e) {
				System.out.println("Neplatny datum! Zadajte v tvare DD.M.RRRR: ");
				
			}	
		}
	}

	public static typyOdborov lenOdbor(Scanner skener) {
	    while (true) {
	        String input = skener.next().trim().toUpperCase();
	
	        if (input.equals("0")) {
	            return null; 
	        }
	
	        try {
	            return typyOdborov.valueOf(input);
	        } catch (IllegalArgumentException e) {
	            System.out.print("Neplatny odbor! Povolene hodnoty: IBE, TLI alebo 0: ");
	        }
	    }
	}

	public static int lenCeleCisla(Scanner skener) {
		int cislo = 0;
		try
		{
			cislo = skener.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vynimka typu "+e.toString());
			System.out.println("Zadajte prosim cele cislo: ");
			skener.nextLine();
			cislo = lenCeleCisla(skener);
		}
		return cislo;
	}

}
