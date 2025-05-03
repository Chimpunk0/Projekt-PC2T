package projekt_BPC_pC2T;


public enum FormatovanyText {
	
	RRESET("\033[0m"),
	
	BOLD("\033[0;1m"),
	
	BLACK_BOLD("\033[1;30m"),  
    RED_BOLD("\033[1;31m"),     
    GREEN_BOLD("\033[1;32m"),   
    YELLOW_BOLD("\033[1;33m"),  
    BLUE_BOLD("\033[1;34m"),    
    MAGENTA_BOLD("\033[1;35m"),
    CYAN_BOLD("\033[1;36m"),
    WHITE_BOLD("\033[1;37m");
    
    private final String kód;
    
    FormatovanyText(String kód){
    	this.kód = kód;
    }
    
    @Override
    public String toString() {
    	return kód;
    }
}
