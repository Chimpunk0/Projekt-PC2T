package projekt_BPC_pC2T.MySQL;

import com.mysql.cj.jdbc.MysqlDataSource;

import projekt_BPC_pC2T.BPC_IBE;
import projekt_BPC_pC2T.BPC_TLI;
import projekt_BPC_pC2T.Databaza;
import projekt_BPC_pC2T.Student;
import projekt_BPC_pC2T.typyOdborov;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class DBManagement {
    static final String DB_URL = "jdbc:mysql://localhost:3306/studentbaza"; 
    static final String USER = "Simon";
    static final String PASS = "mamRadJablka";
    
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
		String sql = "SELECT * FROM studenti";
		
		try (Connection conn = dataSource.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql);
	             ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String meno = rs.getString("meno");
				String priezvisko = rs.getString("priezvisko");
				Date sqlDatum = rs.getDate("dátum_narodenia");
				LocalDate datum = (sqlDatum != null)? sqlDatum.toLocalDate(): null;
				String odbor = rs.getString("odbor");
				if (DBManagement.pridatStudenta(id, meno, priezvisko, datum, odbor)) {
					System.out.println("Student s ID " + id + " bol pridany." );
				}
				else {
					System.out.println("Studenta sa nepodarilo pridat.");
				}
			}
		}
				
		catch(SQLException e){
			e.printStackTrace();
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
