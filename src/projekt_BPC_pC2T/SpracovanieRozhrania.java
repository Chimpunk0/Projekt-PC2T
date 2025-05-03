package projekt_BPC_pC2T;

public class SpracovanieRozhrania {
	
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
				+ "     _             _            _  ______                \n"
				+ "    | |           | |          | | | ___ \\               \n"
				+ " ___| |_ _   _  __| | ___ _ __ | |_| |_/ / __ _ ______ _ \n"
				+ "/ __| __| | | |/ _` |/ _ \\ '_ \\| __| ___ \\/ _` |_  / _` |\n"
				+ "\\__ \\ |_| |_| | (_| |  __/ | | | |_| |_/ / (_| |/ / (_| |\n"
				+ "|___/\\__|\\__,_|\\__,_|\\___|_| |_|\\__\\____/ \\__,_/___\\__,_|\n"
				+ "\n"
				+ "");
		
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
		System.out.println("|11| –> vypis databazy");
		System.out.println("|12| -> konec");
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
	
}


