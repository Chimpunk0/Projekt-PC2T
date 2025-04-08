package projekt_BPC_pC2T;

public class Main {

	public static void main(String[] args) {
		String bezDiakritiky;
		bezDiakritiky = Morseovka.diakritikaMinus("čšťšč fdfds");
		System.out.println(bezDiakritiky);
		
		System.out.println(Morseovka.predkladDoMorseovky("Šimon", "Poláák"));

	}

	

}
