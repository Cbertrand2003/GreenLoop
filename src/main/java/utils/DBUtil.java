package utils;

import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static DataSource dataSource;

    static {
        try {
            // Look up the JNDI DataSource (must match context.xml and web.xml)
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/GreenLoopDB");
            if (dataSource == null) {
                throw new RuntimeException("JNDI lookup for DataSource returned null.");
            }
        } catch (NamingException e) {
            System.err.println("JNDI lookup failed: " + e.getMessage());
            throw new RuntimeException("Failed to initialize DataSource", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new IllegalStateException("DataSource not initialized.");
        }
        return dataSource.getConnection();
    }
}
