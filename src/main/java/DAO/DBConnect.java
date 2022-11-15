package DAO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnect {
    public static Connection getConnection() throws SQLException {
        DataSource ds = null;
        Connection conn = null;

        try {
            Context context = new InitialContext();

            ds = (DataSource) context.lookup("java:comp/env/jdbc/pet");
            conn = ds.getConnection();
        } catch(NamingException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
