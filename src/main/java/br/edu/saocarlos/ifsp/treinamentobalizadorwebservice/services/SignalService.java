package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.DAO.SignalDAO;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Signal;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SignalService {

    public List<Signal> findAllWithActiveTrue() throws SQLException {
        SignalDAO signalDAO = new SignalDAO();
        return signalDAO.findAllWithActiveTrue();
    }

}
