package Utility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Andrea on 21/09/2017.
 */
public class StaticConn {

    private static Connection conn = null;

    public static Connection getConn() throws Exception {
        if (conn == null) {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/facst/Desktop/ProgettoEsame/database/farmaciareg.sqlite");
        }
        return conn;
    }

    public static void close() throws SQLException {
        conn.close();
        conn=null;

    }
}
