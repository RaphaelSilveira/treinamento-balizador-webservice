package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.configs;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils.ConstantsUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnectionConfig {

    private static Connection CONNECTION;

    private SQLiteConnectionConfig() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        CONNECTION = DriverManager.getConnection(ConstantsUtils.DATABASE.value());
    }

    public static synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
        if(CONNECTION == null) {
            new SQLiteConnectionConfig();
        }

        return CONNECTION;
    }
}
