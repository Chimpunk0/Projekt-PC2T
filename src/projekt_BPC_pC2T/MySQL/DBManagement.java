package projekt_BPC_pC2T.MySQL;

import com.mysql.cj.jdbc.MysqlDataSource;

import projekt_BPC_pC2T.Student;
import projekt_BPC_pC2T.StudentManagement;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class DBManagement {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentbaza"; 
    private static final String USER = "root";
    private static final String PASS = "Fjy35d4en2_SQL";
    
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
	
	
	public static HashMap<Integer, Student> studentiMap = new HashMap<>();
	
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
				
				StudentManagement.pridatStudenta(id, meno, priezvisko, datum, odbor);
					
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
	
	
	}
