package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.DAO;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.configs.SQLiteConnectionConfig;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignalDAO {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void createAndInsertMovimentsTable() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS movement (\n" +
            "name_file          VARCHAR(255)    PRIMARY KEY,\n" +
            "name               VARCHAR(255)    NOT NULL,\n" +
            "inserts_in_arff    INTEGER NOT     NULL,\n" +
            "active             VARCHAR(6)      NOT NULL\n" +
            ")";

        final Connection connection = SQLiteConnectionConfig.getConnection();

        Statement statement = connection.createStatement();
        statement.execute(createTable);
        statement.close();

        List<String> inserts = insertValues();

        inserts.forEach(insert -> {
            try {
                logger.info("Creating move: {}", insert);

                PreparedStatement preparedStatement = connection.prepareStatement(insert);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        connection.close();
    }

    public List<Signal> findAllWithActiveTrue() throws SQLException {
        String sql = "SELECT name_file, name, inserts_in_arff FROM movement WHERE active LIKE 'true'";
        List<Signal> signals = new ArrayList<>();

        Connection connection = SQLiteConnectionConfig.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Signal signal = new Signal();

            signal.setNameFile(resultSet.getString("name_file"));
            signal.setName(resultSet.getString("name"));
            signal.setInsertsInArff(resultSet.getInt("inserts_in_arff"));

            signals.add(signal);
        }

        return signals;
    }

    private List<String> insertValues() {
        List<String> inserts = new ArrayList<>();

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Sinaleiro', " +
            "'sinaleiro.mp4', " +
            "11, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Identificação de rampa', " +
            "'identificacao_rampa.mp4', " +
            "12, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Prosseguir para proximo sinaleiro à direita como orientado pela torre controle', " +
            "'prosseguir_para_proximo_sinaleiro_como_orientado_pela_torre_controle_direita.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Prosseguir para proximo sinaleiro à esquerda como orientado pela torre controle esquerda', " +
            "'prosseguir_para_proximo_sinaleiro_como_orientado_pela_torre_controle_esquerda.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Prosseguir em frente', " +
            "'prosseguir_em_frente.mp4', " +
            "12, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Girar para esquerda', " +
            "'girar_para_esquerda.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Girar para direita', " +
            "'girar_para_direita.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Parada normal', " +
            "'parada_normal.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Parada emergencial', " +
            "'parada_emergencial.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Acionar ou soltar freios', " +
            "'acionar_ou_soltar_freios.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Calços colocados ou retirados', " +
            "'calcos_colocados_ou_retirados.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Acionamento de motores', " +
            "'acionamento_de_motores.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Cortar motores', " +
            "'cortar_motores.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Reduzir velocidade', " +
            "'reduzir_velocidade.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Reduzir velocidade do motor da direita', " +
            "'reduzir_velocidade_do_motor_da_direita.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Reduzir velocidade do motor da esquerda', " +
            "'reduzir_velocidade_do_motor_da_esquerda.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Recuar', " +
            "'recuar.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Virar enquanto recuando para direita', " +
            "'virar_enquanto_recuando_direita.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Virar enquanto recuando para esquerda', " +
            "'virar_enquanto_recuando_esquerda.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Afirmativo', " +
            "'afirmativo.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Voo pairado', " +
            "'voo_pairado.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Subida', " +
            "'subida.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Descida', " +
            "'descida.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Deslocamento horizontal para direita', " +
            "'deslocamento_horizontal_para_direita.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Deslocamento horizontal para esquerda', " +
            "'deslocamento_horizontal_para_esquerda.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Pouso', " +
            "'pouso.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Fogo', " +
            "'fogo.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Manter posição', " +
            "'manter_posicao.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Despacho de aeronave', " +
            "'despacho_aeronave.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Não toque nos comandos', " +
            "'nao_toque_comandos.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Conectar alimentação elétrica no solo', " +
            "'conectar_alimentacao_elétrica_no_solo.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Desconectar alimentação elétrica no solo', " +
            "'desconectar_alimentacao_elétrica_no_solo.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Negativo', " +
            "'negativo.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Estabelecer comunicação via interfone', " +
            "'estabelecer_comunicacao_interfone.mp4', " +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, inserts_in_arff, active) VALUES (" +
            "'Abrir ou fechar escadas', " +
            "'abrir_fechar_escadas.mp4', " +
            "0, " +
            "'false')"
        );

        return inserts;
    }
}