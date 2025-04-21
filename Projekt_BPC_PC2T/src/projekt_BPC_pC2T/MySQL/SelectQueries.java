package projekt_BPC_pC2T.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQueries {
	
	public static void vypisDatabazy() {
	    String sql = "SELECT * FROM studenti";

	    try (Connection conn = DBManagement.dataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	           
	             
	           try (ResultSet rs = pstmt.executeQuery()) {  
	               
	               System.out.println("/--------------------------------------------------\\");
	               System.out.println("| ID | Meno | Priezvisko | Datum narodenia | Odbor |");
	               System.out.println("+--------------------------------------------------+");
	               while (rs.next()) {
	                   System.out.printf(
	                       "| %d   %s	%s	%s	%s",
	                       rs.getInt("id"),
	                       rs.getString("meno"),
	                       rs.getString("priezvisko"),
	                       rs.getDate("dátum_narodenia"),
	                       rs.getString("odbor"));
	                   		System.out.println("");
	               }
	               
	               System.out.println("\\--------------------------------------------------/");
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	}
	
	public static float celkovyPriemer(String odbor) {
		String sql = "SELECT znamka FROM znamky WHERE odbor = ?";
		
		try (Connection conn = DBManagement.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, odbor);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				float hodnotaZnamok = 0;
				int pocitadlo = 0;
				
				while (rs.next()) {
					hodnotaZnamok += rs.getInt("znamka");
					++pocitadlo;
				}
				return hodnotaZnamok / pocitadlo;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int pocetStudentov(String odbor) {
		
		String sql = "SELECT COUNT(*) AS celkovyPocet FROM studenti WHERE odbor = ?";
		int pocetStudentov = 0;
		
		try (Connection conn = DBManagement.dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, odbor);
			
			try (ResultSet rs = pstmt.executeQuery()){
			while (rs.next()) {
				pocetStudentov = rs.getInt("celkovyPocet");
			}
			return pocetStudentov;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
