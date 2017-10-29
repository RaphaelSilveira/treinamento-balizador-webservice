package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.DAO;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.configs.SQLiteConnectionConfig;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Signal;
import com.sun.org.apache.bcel.internal.generic.SIPUSH;
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
            "key                VARCHAR(255)    NOT NULL,\n" +
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

    public Signal findOneByKey(String key) throws SQLException {
        String sql = "SELECT name_file, name, key, inserts_in_arff FROM movement WHERE key LIKE ?";
        Signal signal = new Signal();

        Connection connection = SQLiteConnectionConfig.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, key);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            signal.setNameFile(resultSet.getString("name_file"));
            signal.setName(resultSet.getString("name"));
            signal.setKey(resultSet.getString("key"));
            signal.setInsertsInArff(resultSet.getInt("inserts_in_arff"));

            break;
        }

        preparedStatement.close();
        connection.close();

        return signal;
    }

    public void updateInsertsInArff(Signal signal) throws SQLException {
        String sql = "UPDATE movement SET inserts_in_arff = ? WHERE name_file LIKE ?";

        Connection connection = SQLiteConnectionConfig.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, signal.getInsertsInArff());
        preparedStatement.setString(2, signal.getNameFile());

        preparedStatement.executeQuery();
    }

    public List<Signal> findAllWithActiveTrue() throws SQLException {
        String sql = "SELECT name_file, name, key, inserts_in_arff FROM movement WHERE active LIKE 'true'";
        List<Signal> signals = new ArrayList<>();

        Connection connection = SQLiteConnectionConfig.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Signal signal = new Signal();

            signal.setNameFile(resultSet.getString("name_file"));
            signal.setName(resultSet.getString("name"));
            signal.setKey(resultSet.getString("key"));
            signal.setInsertsInArff(resultSet.getInt("inserts_in_arff"));

            signals.add(signal);
        }

        statement.close();
        connection.close();

        return signals;
    }

    private List<String> insertValues() {
        List<String> inserts = new ArrayList<>();

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Sinaleiro', " +
            "'sinaleiro.mp4', " +
            "'Sinaleiro'," +
            "11, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Identificação de rampa', " +
            "'identificacao_rampa.mp4', " +
            "'IdentificacaoRampa'," +
            "12, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Prosseguir para proximo sinaleiro à direita como orientado pela torre controle', " +
            "'prosseguir_para_proximo_sinaleiro_como_orientado_pela_torre_controle_direita.mp4', " +
            "'ProsseguirParaProximoSinaleiroComoOrientadoPelaTorreControleDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Prosseguir para proximo sinaleiro à esquerda como orientado pela torre controle esquerda', " +
            "'prosseguir_para_proximo_sinaleiro_como_orientado_pela_torre_controle_esquerda.mp4', " +
            "'ProsseguirParaProximoSinaleiroComoOrientadoPelaTorreControleEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Prosseguir em frente', " +
            "'prosseguir_em_frente.mp4', " +
            "'ProsseguirFrente'," +
            "12, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Girar para esquerda', " +
            "'girar_para_esquerda.mp4', " +
            "'GirarParaEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Girar para direita', " +
            "'girar_para_direita.mp4', " +
            "'GirarParaDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Parada normal', " +
            "'parada_normal.mp4', " +
            "'ParadaNormal'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Parada emergencial', " +
            "'parada_emergencial.mp4', " +
            "'ParadaEmergencia'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Acionar ou soltar freios', " +
            "'acionar_ou_soltar_freios.mp4', " +
            "'AcionarOuSoltarFreios'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Calços colocados ou retirados', " +
            "'calcos_colocados_ou_retirados.mp4', " +
            "'CalcosColocadosOuRetirados'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Acionamento de motores', " +
            "'acionamento_de_motores.mp4', " +
            "'AcionamentoMotores'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Cortar motores', " +
            "'cortar_motores.mp4', " +
             "'CortarMotores'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Reduzir velocidade', " +
            "'reduzir_velocidade.mp4', " +
            "'ReduzirVelocidade'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Reduzir velocidade do motor da direita', " +
            "'reduzir_velocidade_do_motor_da_direita.mp4', " +
            "'ReduzirVelocidadeMotorDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Reduzir velocidade do motor da esquerda', " +
            "'reduzir_velocidade_do_motor_da_esquerda.mp4', " +
            "'ReduzirVelocidadeMotorEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Recuar', " +
            "'recuar.mp4', " +
            "'Recuar'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Virar enquanto recuando para direita', " +
            "'virar_enquanto_recuando_direita.mp4', " +
            "'VirarEnquantoRecuandoDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Virar enquanto recuando para esquerda', " +
            "'virar_enquanto_recuando_esquerda.mp4', " +
            "'VirarEnquantoRecuandoEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Afirmativo', " +
            "'afirmativo.mp4', " +
            "'Afirmativo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Voo pairado', " +
            "'voo_pairado.mp4', " +
            "'VooPairado'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Subida', " +
            "'subida.mp4', " +
            "'Subida'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Descida', " +
            "'descida.mp4', " +
            "'Descida'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Deslocamento horizontal para direita', " +
            "'deslocamento_horizontal_para_direita.mp4', " +
            "'DeslocamentoHorizontalParaDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Deslocamento horizontal para esquerda', " +
            "'deslocamento_horizontal_para_esquerda.mp4', " +
            "'DeslocamentoHorizontalParaEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Pouso', " +
            "'pouso.mp4', " +
            "'Pouso'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Fogo', " +
            "'fogo.mp4', " +
            "'Fogo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Manter posição', " +
            "'manter_posicao.mp4', " +
            "'ManterPosicao'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Despacho de aeronave', " +
            "'despacho_aeronave.mp4', " +
            "'DespachoAeronave'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Não toque nos comandos', " +
            "'nao_toque_comandos.mp4', " +
            "'NaoToqueComandos'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Conectar alimentação elétrica no solo', " +
            "'conectar_alimentacao_elétrica_no_solo.mp4', " +
            "'ConectarAlimentacaoEletricaSolo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Desconectar alimentação elétrica no solo', " +
            "'desconectar_alimentacao_elétrica_no_solo.mp4', " +
            "'DesconectarAlimentacaoEletrica'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Negativo', " +
            "'negativo.mp4', " +
            "'Negativo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Estabelecer comunicação via interfone', " +
            "'estabelecer_comunicacao_interfone.mp4', " +
            "'EstabelecerComunicacaoInterfone'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'Abrir ou fechar escadas', " +
            "'abrir_fechar_escadas.mp4', " +
            "'AbrirFecharEscadas'," +
            "0, " +
            "'false')"
        );

        return inserts;
    }
}