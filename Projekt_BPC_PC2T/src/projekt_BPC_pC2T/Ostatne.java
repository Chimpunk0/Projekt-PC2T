package projekt_BPC_pC2T;

import java.util.Scanner;

import projekt_BPC_pC2T.MySQL.DBManagement;

public class Ostatne {
	
	public enum OS {
		WINDOWS, LINUX, MAC
	};
	
	private static OS os = null;
	
	public static OS getOS() {
        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            }
        }
        return os;
    }
	
	public static void vypisVolby () {
		
		System.out.println("\n"
				+ "\n"
				+ " _   _             ____                                                              \n"
				+ "| \\ | | __ _ ___  |  _ \\ _ __ ___   __ _ _ __ __ _ _ __ ___    _   _  ___  ___ _   _ \n"
				+ "|  \\| |/ _` / __| | |_) | '__/ _ \\ / _` | '__/ _` | '_ ` _ \\  | | | |/ _ \\/ _ \\ | | |\n"
				+ "| |\\  | (_| \\__ \\ |  __/| | | (_) | (_| | | | (_| | | | | | | | |_| |  __/  __/ |_| |\n"
				+ "|_| \\_|\\__,_|___/ |_|   |_|  \\___/ \\__, |_|  \\__,_|_| |_| |_|  \\__, |\\___|\\___|\\__, |\n"
				+ "                                   |___/                       |___/           |___/ \n"
				+ "\n");
		
		System.out.println("\n/--------------------------------------------\\");
		System.out.println("|Vyberte pozadovanu cinnost:                 |");
		System.out.println("+--+-----------------------------------------+");
		System.out.println("|1 | -> vytvorenie noveho studenta");
		System.out.println("|2 | -> vlozenie znamky studentovi");
		System.out.println("|3 | -> prepustenie studenta");
		System.out.println("|4 | –> vyhladavanie studenta");
		System.out.println("|5 | -> spustenie specialnej schopnosti");
		System.out.println("|6 | -> abecedne zoradenie studentov");
		System.out.println("|7 | –> vypis celkoveho priemeru");
		System.out.println("|8 | -> vypis poctu studentov");
		System.out.println("|9 | -> ulozenie studenta do suboru");
		System.out.println("|10| –> nacitanie studenta zo suboru");
		System.out.println("|11| -> konec");
		System.out.println("\\--+----------------------------------------/\n");
		System.out.print("Volba: ");
		
	}
	
	public static void vycistiKonzolu() {
		try {
	        if (!System.console().equals(null)) {
	            if (getOS() == OS.WINDOWS) {
	                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	            } else {
	                System.out.print("\033[H" + "\033[2J");
	                System.out.flush();
	            }
	        }
	    } catch (Exception e) {
	      
	        for (int i = 0; i < 50; i++) System.out.println();
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
			System.out.println("zadajte prosim cele cislo: ");
			skener.nextLine();
			cislo = lenCeleCisla(skener);
		}
		return cislo;
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
		

	
	
	/*public static boolean overenieId (int id) {
		
		if (id > DBManagement.getAktualneMaxId()) {
			System.out.println("Mimo rozsah");
			return false;
		}
			else {
				Student student = DBManagement.najstStudenta(id);
				if ( student == null) {
					System.out.println("Student neexistuje");
					return false;
				}
				return true;
	}
	
	
	}*/
	
	public static Integer ziskajOvereneID(Scanner skener, int posledneId) {
	    System.out.print("Zadajte ID študenta: ");
	    int id = lenCeleCisla(skener);
	    if (id > posledneId) {
	        System.out.println("Mimo rozsah");
	        return null;
	    }
	    return id;
	}
}


