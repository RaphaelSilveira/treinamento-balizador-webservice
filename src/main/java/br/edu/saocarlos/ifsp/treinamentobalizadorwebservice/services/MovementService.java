package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils.AttributesUtils;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.SerializationHelper;

@Service
public class MovementService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${weka.model.name}")
    private String model;

    @Value("${weka.model.accept.value}")
    private Double accept;

    @Value("${weka.arff.name}")
    private String arff;

    public Boolean verifyMovement(Movement movement) throws Exception {
        Boolean correct = Boolean.FALSE;

        Classifier classifier = (Classifier) SerializationHelper.read(model);

        double[] probabilities = classifier.distributionForInstance(getInstance(movement));

        for (int i = 0; i < probabilities.length; i++) {
            logger.info("Index: {} Accept: {}", i, probabilities[i]);

            if(movement.getMovement().equals(AttributesUtils.getAttribute(i)) && probabilities[i] >= accept) {
                correct = Boolean.TRUE;
            }
        }

        logger.info("Is correct: {}", correct);

        return correct;
    }

    private Instance getInstance(Movement movement) throws Exception {
        Instance instance = new Instance(movement.getCoordinates().size());

        for (int i = 0; i < movement.getCoordinates().size(); i++) {
            instance.setValue(i, Double.parseDouble(movement.getCoordinates().get(i)));
        }

        return instance;
    }

}
