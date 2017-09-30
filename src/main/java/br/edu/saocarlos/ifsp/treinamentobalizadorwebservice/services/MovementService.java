package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils.AttributesUtils;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Movement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.SerializationHelper;

@Service
public class MovementService {

    @Value("${weka.model.name}")
    private String model;

    @Value("${weka.model.accept.value}")
    private Double accept;

    public Boolean verifyMovement(Movement movement) throws Exception {
        Boolean correct = Boolean.FALSE;

        Classifier classifier = (Classifier) SerializationHelper.read(model);

        double[] probabilities = classifier.distributionForInstance(getInstance(movement));

        for (int i = 0; i < probabilities.length; i++) {
            if(movement.getMovement().equals(AttributesUtils.getAttribute(i)) && probabilities[i] <= accept) {
                correct = Boolean.TRUE;
            }
        }

        return correct;
    }

    private Instance getInstance(Movement movement) {
        Instance instance = new Instance(movement.getCoordinates().size());

        for (int i = 0; i <= movement.getCoordinates().size() - 1; i++) {
            instance.setValue(i, Double.parseDouble(movement.getCoordinates().get(i)));
        }

        return instance;
    }

}
