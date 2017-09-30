package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.controllers;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Movement;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MovementController {

    @Autowired
    private MovementService movementService;

    @RequestMapping(value = "/verify-moviment", method = RequestMethod.POST)
    public Boolean verifyMovement(@RequestBody Movement movement) throws Exception {
        return movementService.verifyMovement(movement);
    }

}
