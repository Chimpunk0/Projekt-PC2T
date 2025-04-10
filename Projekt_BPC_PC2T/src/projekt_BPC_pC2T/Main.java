package projekt_BPC_pC2T;

import java.util.Scanner;



public class Main {
	
	public static int lenCeleCisla(Scanner skener) {
		int cislo = 0;
		try
		{
			cislo = skener.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			skener.nextLine();
			cislo = lenCeleCisla(skener);
		}
		return cislo;
	}


	public static void main(String[] args) {
		
		Databaza db = new Databaza();
		
		System.out.println("\n"
				+ "\n"
				+ "╔────────────────────────────────────────╗\n"
				+ "│ __              ___    ___             │\n"
				+ "│/\\ \\            /\\_ \\  /\\_ \\            │\n"
				+ "│\\ \\ \\___      __\\//\\ \\ \\//\\ \\     ___   │\n"
				+ "│ \\ \\  _ `\\  /'__`\\\\ \\ \\  \\ \\ \\   / __`\\ │\n"
				+ "│  \\ \\ \\ \\ \\/\\  __/ \\_\\ \\_ \\_\\ \\_/\\ \\L\\ \\│\n"
				+ "│   \\ \\_\\ \\_\\ \\____\\/\\____\\/\\____\\ \\____/│\n"
				+ "│    \\/_/\\/_/\\/____/\\/____/\\/____/\\/___/ │\n"
				+ "╚────────────────────────────────────────╝\n"
				+ "\n"
				+ "");
		
		BPC_TLI studentTLI = new BPC_TLI(10, "jozko", "Mrkvicka",new Integer[] {34, 56, 4040});
		System.out.println(studentTLI.getMeno());
		studentTLI.vypisDatumNarodenia();
		
		db.pridatStudenta(studentTLI);
		db.najstStudenta(studentTLI.getID());
		
		Scanner skener = new Scanner(System.in);
		int volba;
		
			
		
		
		boolean run = true;
		while(run) {
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vytvorenie noveho studenta");
			System.out.println("2 .. vlozenie znamky studentovi");
			System.out.println("3 .. prepustenie studenta");
			System.out.println("4 .. vyhladavanie studenta");
			System.out.println("5 .. spustenie specialnej schopnosti");
			System.out.println("5 .. abecedne zoradenie studentov");
			System.out.println("6 .. vypis celkoveho priemeru");
			System.out.println("7 .. vypis poctu studentov");
			System.out.println("8 .. ulozenie studenta do suboru");
			System.out.println("9 .. nacitanie studenta zo suboru");
			System.out.println("10 .. konec");
			volba = lenCeleCisla(skener);
			
			switch(volba)
			{
			case 1: 
				System.out.print("Zadajte meno studenta: ");
				
				
				
				break;
			case 10:
				run = false;
				System.out.println("KONIEC programu");
				return;
			}
	}

	}

}
