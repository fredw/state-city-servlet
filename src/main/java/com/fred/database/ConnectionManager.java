package com.fred.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException {
        String DATASOURCE_CONTEXT = "java:comp/env/jdbc/senac-state-city";
        Connection conn;
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup(DATASOURCE_CONTEXT);
            if (ds == null) {
                throw new SQLException("Failed to lookup Data Source.");
            }
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            throw new SQLException("Problem with JNDI: " + e.getMessage());
        }
        return conn;
    }
}
