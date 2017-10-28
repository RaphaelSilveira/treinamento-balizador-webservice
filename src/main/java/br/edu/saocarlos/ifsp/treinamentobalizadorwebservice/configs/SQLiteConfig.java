package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.configs;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils.ConstantsUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConfig {

    public static void createDatabase() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");

        try(Connection connection = DriverManager.getConnection(ConstantsUtils.DATABASE.value())) {
            if(connection != null) {
                DatabaseMetaData metaData = connection.getMetaData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
