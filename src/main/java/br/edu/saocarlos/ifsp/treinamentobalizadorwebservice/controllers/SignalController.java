package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.controllers;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Signal;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
public class SignalController {

    @Autowired
    private SignalService signalService;

    @RequestMapping(value = "/get-movements", method = RequestMethod.GET)
    public List<Signal> getMoviments() throws SQLException {
        return signalService.findAllWithActiveTrue();
    }

}
