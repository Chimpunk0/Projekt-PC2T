package projekt_BPC_pC2T.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQueries {
	
	/*public static void queryData() {
	    String sql = "SELECT * FROM studenti";

	    try (Connection conn = DBManagement.dataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	           
	           pstmt.setInt(1, id);  
	           try (ResultSet rs = pstmt.executeQuery()) {  
	               
	               System.out.println("\nŠtudent:");
	               if (rs.next()) {
	                   System.out.printf(
	                       "ID: %d, Meno: %s, Priezvisko: %s, Dátum narodenia: %s, Odbor: %s\n",
	                       rs.getInt("id"),
	                       rs.getString("meno"),
	                       rs.getString("priezvisko"),
	                       rs.getDate("dátum_narodenia"),
	                       rs.getString("odbor"));
	               } else {
	                   System.out.println("Študent s ID " + id + " nebol nájdený.");
	               }
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	}*/

}
