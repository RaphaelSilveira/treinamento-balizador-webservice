package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.DAO.SignalDAO;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Movement;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Signal;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SignalService {

    public List<Signal> findAllWithActiveTrue() throws SQLException, ClassNotFoundException {
        SignalDAO signalDAO = new SignalDAO();
        return signalDAO.findAllWithActiveTrue();
    }

    public void addInsertsOnArff(Movement movement) throws SQLException, ClassNotFoundException {
        SignalDAO signalDAO = new SignalDAO();
        Signal signal = signalDAO.findOneByKey(movement.getMovement());

        Integer insertsInArff = signal.getInsertsInArff() + 1;
        signal.setInsertsInArff(insertsInArff);

        signalDAO.updateInsertsInArff(signal);
    }

}
