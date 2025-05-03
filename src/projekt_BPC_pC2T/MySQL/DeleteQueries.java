package projekt_BPC_pC2T.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQueries {
	
    public static void deleteStudent(int id) {
    	
    	deleteVsetkyZnamky(id);
    	
        String sql = "DELETE FROM studenti WHERE id = ?";
        
        try (Connection conn = DBManagement.dataSource.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int ovplyvneneRiadky = pstmt.executeUpdate();
            
            
            if (ovplyvneneRiadky > 0) {
                System.out.println("Student s ID " + id + " bol vymazany z SQL.");
            } else {
                System.out.println("Student s ID " + id + " v SQL neexistuje.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteVsetkyZnamky(int studentId) {
    	String sql = "DELETE FROM znamky WHERE studenti_id = ?";
    	
    	try (Connection conn = DBManagement.dataSource.getConnection();
    			PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		
    		pstmt.setInt(1, studentId);
    		int ovplyvneneRiadky = pstmt.executeUpdate();
    		
    		if (ovplyvneneRiadky > 0) {
    			System.out.println("Znamky boli vymazane.");
    		} 
    		else {
    			System.out.println("Znamky studenta sa nenasli.");
    		}
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
}

