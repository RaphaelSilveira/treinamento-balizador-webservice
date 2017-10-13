package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.controllers;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Movement;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services.CreateWekaModelService;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class MovementController {

    @Autowired
    private MovementService movementService;

    @Autowired
    private CreateWekaModelService createWekaModelService;

    @RequestMapping(value = "/verify-movement", method = RequestMethod.POST)
    public Boolean verifyMovement(@RequestBody Movement movement) throws Exception {
        return movementService.verifyMovement(movement);
    }

    @RequestMapping(value = "/save-movement", method = RequestMethod.POST)
    public ResponseEntity saveMovement(@RequestBody Movement movement) throws Exception {
        movementService.save(movement);
        return new ResponseEntity(HttpStatus.OK);
    }
}
