package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.configs;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils.ConstantsUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnectionConfig {

    private static Connection CONNECTION;

    private SQLiteConnectionConfig() throws SQLException {
        CONNECTION = DriverManager.getConnection(ConstantsUtils.DATABASE.value());
    }

    public static synchronized Connection getConnection() throws SQLException {
        if(CONNECTION == null) {
            new SQLiteConnectionConfig();
        }

        return CONNECTION;
    }
}
