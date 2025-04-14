package projekt_BPC_pC2T.MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBconnection {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/studentbaza"; 
	static final String USER = "Simon";
    static final String PASS = "mamRadJablka";
    private static MysqlDataSource dataSource;
   
	
	public static boolean connectDB() {

   		
    dataSource = new MysqlDataSource();
    dataSource.setURL("jdbc:mysql://localhost:3306/studentbaza");
    dataSource.setUser("Simon");
    dataSource.setPassword("mamRadJablka");

    try ( Connection conn = dataSource.getConnection()) {
        System.out.println("Connected via DataSource!");
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    
    return true;
	}
	
	public static boolean createTable() {
		String sql1 = "CREATE TABLE IF NOT EXISTS studenti (" +
					 "id INT AUTO_INCREMENT PRIMARY KEY, " +
					 "meno VARCHAR(50), " +
					 "priezvisko VARCHAR(50), " +
					 "dátum_narodenia DATE, " +
					 "odbor VARCHAR(20))";
		
		String sql2 = "CREATE TABLE IF NOT EXISTS znamky (" +
				 "id INT AUTO_INCREMENT PRIMARY KEY, " +
				 "studenti_id INT, " +
				 "znamka tinyint, " + 
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
	
	

}
