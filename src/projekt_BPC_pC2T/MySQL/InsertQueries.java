package projekt_BPC_pC2T.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InsertQueries {
	
	

	
	public static void insertStudent(int id, String meno, String priezvisko, LocalDate dátum_narodenia, String odbor ) {
	    String sql = "INSERT INTO studenti (id, meno, priezvisko, dátum_narodenia, odbor) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DBManagement.dataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	    	pstmt.setInt(1, id);
	        pstmt.setString(2, meno);
	        pstmt.setString(3, priezvisko);
	        pstmt.setDate(4, java.sql.Date.valueOf(dátum_narodenia));
	        pstmt.setString(5, odbor);
	        pstmt.executeUpdate();
	        System.out.println("data vlozene do SQL!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}
	    
	public static void insertZnamka(int studentiId, int znamka, String odbor)
	    {
	    	String sql = "INSERT INTO znamky (studenti_id, znamka, odbor) VALUES (?, ?, ?)";

	    	try (Connection conn = DBManagement.dataSource.getConnection();
	    	     PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	    
	    		pstmt.setInt(1, studentiId);      
	    	    pstmt.setInt(2, znamka);      
	    	    pstmt.setString(3, odbor);
	    	    pstmt.executeUpdate();
	    	}
	    	catch (SQLException e) {
	    		e.printStackTrace();
	    	}
	    
	}

}
