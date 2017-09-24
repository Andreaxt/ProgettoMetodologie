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
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Db_Farmacia", "postgres", "$Postgres22.");
        }
        return conn;
    }

    public static void close() throws SQLException {
        conn.close();
        conn=null;

    }
}
