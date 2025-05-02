package projekt_BPC_pC2T;

import java.util.HashMap;
import java.text.Normalizer;

public class Morseovka {
	
	
	static private final HashMap<Character, String>morseovka = new HashMap<Character, String>();
	
	static {
		morseovka.put('a', ".-");
        morseovka.put('b', "-...");
        morseovka.put('c', "-.-.");
        morseovka.put('d', "-..");
        morseovka.put('e', ".");
        morseovka.put('f', "..-.");
        morseovka.put('g', "--.");
        morseovka.put('h', "....");
        morseovka.put('i', "..");
        morseovka.put('j', ".---");
        morseovka.put('k', "-.-");
        morseovka.put('l', ".-..");
        morseovka.put('m', "--");
        morseovka.put('n', "-.");
        morseovka.put('o', "---");
        morseovka.put('p', ".--.");
        morseovka.put('q', "--.-");
        morseovka.put('r', ".-.");
        morseovka.put('s', "...");
        morseovka.put('t', "-");
        morseovka.put('u', "..-");
        morseovka.put('v', "...-");
        morseovka.put('w', ".--");
        morseovka.put('x', "-..-");
        morseovka.put('y', "-.--");
        morseovka.put('z', "--..");
        morseovka.put(' ', " ");
	}
	
	static public String diakritikaMinus(String slovo) {
		String textBezDiakritiky = Normalizer.normalize(slovo, Normalizer.Form.NFD);
		return textBezDiakritiky.replaceAll("[^\\p{ASCII}]", "");
			}
	
	static public String predkladDoMorseovky(String meno, String priezvisko) {
		String menoAPriezvisko = meno + " " + priezvisko;
		menoAPriezvisko = menoAPriezvisko.toLowerCase();
		menoAPriezvisko = diakritikaMinus(menoAPriezvisko);
		String textVMorseovke = "|";
		
		for (int i = 0; i < menoAPriezvisko.length(); i++)
		{
			textVMorseovke+= morseovka.get(menoAPriezvisko.charAt(i)) + "|";
			
		}
		
		return textVMorseovke;
	}
	


}
