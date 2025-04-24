package projekt_BPC_pC2T.MySQL;

import com.mysql.cj.jdbc.MysqlDataSource;


import projekt_BPC_pC2T.BPC_IBE;
import projekt_BPC_pC2T.BPC_TLI;
import projekt_BPC_pC2T.Student;
import projekt_BPC_pC2T.typyOdborov;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManagement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentbaza"; 
    private static final String USER = "Simon";
    private static final String PASS = "mamRadJablka";
    
    public static MysqlDataSource dataSource; 

    public static boolean connectDB() {
        dataSource = new MysqlDataSource();
        dataSource.setURL(DB_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("Connected via DataSource!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
	public static boolean createTable() {
		
		String sql1 = "CREATE TABLE IF NOT EXISTS studenti (" +
					 "id INT PRIMARY KEY, " +
					 "meno VARCHAR(50), " +
					 "priezvisko VARCHAR(50), " +
					 "dátum_narodenia DATE, " +
					 "odbor VARCHAR(20))";
		
		String sql2 = "CREATE TABLE IF NOT EXISTS znamky (" +
				 "id INT AUTO_INCREMENT PRIMARY KEY, " +
				 "studenti_id INT NOT NULL, " +
				 "znamka tinyint NOT NULL CHECK (znamka between 1 AND 5), " +
				 "odbor varchar(50) NOT NULL, " +
				 "FOREIGN KEY (studenti_id) REFERENCES studenti(id))";
		
		try (Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement()) {
			stmt.execute(sql1);
			stmt.execute(sql2);
			return true;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	private static int aktualneMaxId = 0;

    
    public static void inicializaciaMaxId() {
        String sql = "SELECT MAX(id) AS max_id FROM studenti";
        
        try (Connection conn = DBManagement.dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                aktualneMaxId = rs.getInt("max_id"); 
            } else {
                aktualneMaxId = 0; 
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public static int getAktualneMaxId() {
		return aktualneMaxId;
	}
	
	
	private static HashMap<Integer, Student> studentiMap = new HashMap<>();
	
	public static void nacitajStudentovDoMapy() {
		String sqlStudenti = "SELECT * FROM studenti";
		
		try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmtStudenti = conn.prepareStatement(sqlStudenti);
	             ResultSet rsStudenti = pstmtStudenti.executeQuery()) {
			
			while (rsStudenti.next()) {
				int id = rsStudenti.getInt("id");
				String meno = rsStudenti.getString("meno");
				String priezvisko = rsStudenti.getString("priezvisko");
				Date sqlDatum = rsStudenti.getDate("dátum_narodenia");
				LocalDate datum = (sqlDatum != null)? sqlDatum.toLocalDate(): null;
				String odbor = rsStudenti.getString("odbor");
				
				pridatStudenta(id, meno, priezvisko, datum, odbor);
					
			}
			
			String sqlZnamky = "SELECT * FROM znamky";
			
			try (PreparedStatement pstmtZnamky = conn.prepareStatement(sqlZnamky);
					ResultSet rsZnamky = pstmtZnamky.executeQuery()){
				
				while (rsZnamky.next()) {
					int studentId = rsZnamky.getInt("studenti_id");
					int znamka = rsZnamky.getInt("znamka");
					
					Student student = studentiMap.get(studentId);
					if (student != null) {
						student.pridatZnamku(znamka);
					}
					
				}
			}
			
		}
				
		catch(SQLException e){
			e.printStackTrace();
		}
	            
	}
	
	
	
	public static Map<typyOdborov, List<Student>> getZoradenychStudentovPodlaOdboru() {
	    Map<typyOdborov, List<Student>> studentiPodlaOdboru = new HashMap<>();

	    for (Student student : studentiMap.values()) {
	        typyOdborov odbor = student.getOdbor();
	        studentiPodlaOdboru.computeIfAbsent(odbor, k -> new ArrayList<>()).add(student);
	    }


	    for (List<Student> studenti : studentiPodlaOdboru.values()) {
	        studenti.sort(Comparator.comparing(Student::getPriezvisko));
	    }

	    return studentiPodlaOdboru;
	}
		
	
	public static void vypisZoradenychStudentov () {
		Map <typyOdborov, List<Student>> studentiPodlaOdboru = getZoradenychStudentovPodlaOdboru();
		
		for (typyOdborov odbor : typyOdborov.values()) {
			List<Student> studenti = studentiPodlaOdboru.get(odbor);
			
			System.out.println("\n/---------------------------------------------------------------------\\");
			System.out.println("\033[1;35m" + "Odbor: "  + odbor + "\033[0m");
			for (Student student : studenti) {
				String rokNarodenia = String.valueOf(student.getDatumNarodenia().getYear());
				
				System.out.print("\033[1;32m" + "ID: " + "\033[0m" + student.getID());
				System.out.print("\t\033[1;32m" + "rok narodenia: " + "\033[0m" + rokNarodenia);
				System.out.println("\t\033[1;32m" + "Meno a priezvisko: " + "\033[0m" + student.getMeno() + " " + student.getPriezvisko());
				
	
				
				}
			System.out.println("\033[0;1m" + "\\---------------------------------------------------------------------/" + "\033[0m");
				
			}
		}
		
	

	
	
	
	
	
	public static boolean pridatStudenta(int id, String meno, String priezvisko, LocalDate datum, String odbor) {
		Student student;	
		if (odbor.equalsIgnoreCase(typyOdborov.IBE.toString()) ) {
				student = new BPC_IBE(id, meno, priezvisko, datum, typyOdborov.IBE);
			}
		else if (odbor.equalsIgnoreCase(typyOdborov.TLI.toString())) {
			student = new BPC_TLI(id, meno, priezvisko, datum, typyOdborov.TLI);
		}
		else return false;
		studentiMap.put(id, student);
		return true;
	}

	public static Student  najstStudenta(int id) {
		return studentiMap.get(id); 
	
		
	}
	
	
	public static boolean vymazatStudenta(int id) {
		if (studentiMap.get(id) == null) {
			return false;
		}
		studentiMap.remove(id);
		return true;
	}
	
	

	public static HashMap<Integer, Student> getStudentiMap() {
		return studentiMap;
	}
	
	
	
	}
