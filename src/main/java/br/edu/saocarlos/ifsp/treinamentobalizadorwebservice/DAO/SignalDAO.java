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

        return signal;
    }

    public void updateInsertsInArff(Signal signal) throws SQLException {
        String sql = "UPDATE movement SET inserts_in_arff = ? WHERE name_file LIKE ?";

        Connection connection = SQLiteConnectionConfig.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, signal.getInsertsInArff());
        preparedStatement.setString(2, signal.getNameFile());

        preparedStatement.executeQuery();

        preparedStatement.close();
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

        return signals;
    }

    private List<String> insertValues() {
        List<String> inserts = new ArrayList<>();

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'sinaleiro.mp4', " +
            "'Sinaleiro', " +
            "'Sinaleiro'," +
            "11, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'identificacao_rampa.mp4', " +
            "'Identificação de rampa', " +
            "'IdentificacaoRampa'," +
            "12, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'prosseguir_para_proximo_sinaleiro_como_orientado_pela_torre_controle_direita.mp4', " +
            "'Prosseguir para proximo sinaleiro à direita como orientado pela torre controle', " +
            "'ProsseguirParaProximoSinaleiroComoOrientadoPelaTorreControleDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'prosseguir_para_proximo_sinaleiro_como_orientado_pela_torre_controle_esquerda.mp4', " +
            "'Prosseguir para proximo sinaleiro à esquerda como orientado pela torre controle esquerda', " +
            "'ProsseguirParaProximoSinaleiroComoOrientadoPelaTorreControleEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'prosseguir_em_frente.mp4', " +
            "'Prosseguir em frente', " +
            "'ProsseguirFrente'," +
            "12, " +
            "'true')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'girar_para_esquerda.mp4', " +
            "'Girar para esquerda', " +
            "'GirarParaEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'girar_para_direita.mp4', " +
            "'Girar para direita', " +
            "'GirarParaDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'parada_normal.mp4', " +
            "'Parada normal', " +
            "'ParadaNormal'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'parada_emergencial.mp4', " +
            "'Parada emergencial', " +
            "'ParadaEmergencia'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'acionar_ou_soltar_freios.mp4', " +
            "'Acionar ou soltar freios', " +
            "'AcionarOuSoltarFreios'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'calcos_colocados_ou_retirados.mp4', " +
            "'Calços colocados ou retirados', " +
            "'CalcosColocadosOuRetirados'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'acionamento_de_motores.mp4', " +
            "'Acionamento de motores', " +
            "'AcionamentoMotores'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
             "'cortar_motores.mp4', " +
             "'Cortar motores', " +
             "'CortarMotores'," +
             "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'reduzir_velocidade.mp4', " +
            "'Reduzir velocidade', " +
            "'ReduzirVelocidade'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
             "'reduzir_velocidade_do_motor_da_direita.mp4', " +
             "'Reduzir velocidade do motor da direita', " +
            "'ReduzirVelocidadeMotorDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'reduzir_velocidade_do_motor_da_esquerda.mp4', " +
            "'Reduzir velocidade do motor da esquerda', " +
            "'ReduzirVelocidadeMotorEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'recuar.mp4', " +
            "'Recuar', " +
            "'Recuar'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'virar_enquanto_recuando_direita.mp4', " +
            "'Virar enquanto recuando para direita', " +
            "'VirarEnquantoRecuandoDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'virar_enquanto_recuando_esquerda.mp4', " +
            "'Virar enquanto recuando para esquerda', " +
            "'VirarEnquantoRecuandoEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'afirmativo.mp4', " +
            "'Afirmativo', " +
            "'Afirmativo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'voo_pairado.mp4', " +
            "'Voo pairado', " +
            "'VooPairado'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'subida.mp4', " +
            "'Subida', " +
            "'Subida'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'descida.mp4', " +
            "'Descida', " +
            "'Descida'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'deslocamento_horizontal_para_direita.mp4', " +
            "'Deslocamento horizontal para direita', " +
            "'DeslocamentoHorizontalParaDireita'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'deslocamento_horizontal_para_esquerda.mp4', " +
            "'Deslocamento horizontal para esquerda', " +
            "'DeslocamentoHorizontalParaEsquerda'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'pouso.mp4', " +
            "'Pouso', " +
            "'Pouso'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'fogo.mp4', " +
            "'Fogo', " +
            "'Fogo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'manter_posicao.mp4', " +
            "'Manter posição', " +
            "'ManterPosicao'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'despacho_aeronave.mp4', " +
            "'Despacho de aeronave', " +
            "'DespachoAeronave'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'nao_toque_comandos.mp4', " +
            "'Não toque nos comandos', " +
            "'NaoToqueComandos'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'conectar_alimentacao_elétrica_no_solo.mp4', " +
            "'Conectar alimentação elétrica no solo', " +
            "'ConectarAlimentacaoEletricaSolo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'desconectar_alimentacao_elétrica_no_solo.mp4', " +
            "'Desconectar alimentação elétrica no solo', " +
            "'DesconectarAlimentacaoEletrica'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'negativo.mp4', " +
            "'Negativo', " +
            "'Negativo'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'estabelecer_comunicacao_interfone.mp4', " +
            "'Estabelecer comunicação via interfone', " +
            "'EstabelecerComunicacaoInterfone'," +
            "0, " +
            "'false')"
        );

        inserts.add("INSERT INTO movement (name_file, name, key, inserts_in_arff, active) VALUES (" +
            "'abrir_fechar_escadas.mp4', " +
            "'Abrir ou fechar escadas', " +
            "'AbrirFecharEscadas'," +
            "0, " +
            "'false')"
        );

        return inserts;
    }
}