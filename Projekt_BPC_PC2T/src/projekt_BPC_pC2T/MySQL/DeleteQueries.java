package projekt_BPC_pC2T.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteQueries {
    public static void deleteStudent(int id) {
        String sql1 = "DELETE FROM studenti WHERE id = ?";
        
        try (Connection conn = DBManagement.dataSource.getConnection(); 
             PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {
            
            pstmt1.setInt(1, id);
            int affectedRows = pstmt1.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println("Študent s ID " + id + " bol vymazaný.");
            } else {
                System.out.println("Študent s ID " + id + " neexistuje.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
