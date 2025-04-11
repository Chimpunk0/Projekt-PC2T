package projekt_BPC_pC2T;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private Connection conn;

    public boolean connect() {
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:myDB.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        System.out.println("databaza uspesne pripojena");
        return true;
    }

    public void disconnect() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}