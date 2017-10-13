package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.Utils.AttributesUtils;
import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.models.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.SerializationHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class MovementService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${weka.model.name}")
    private String model;

    @Value("${weka.model.accept.value}")
    private Double accept;

    @Value("${weka.arff.name}")
    private String arff;

    @Autowired
    private CreateWekaModelService createWekaModelService;

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
        logger.info("Size: {}", movement.getCoordinates().size());

        Instance instance = new DenseInstance(1261);

        for (int i = 0; i < movement.getCoordinates().size(); i++) {
            String value = movement.getCoordinates().get(i).replace(",", ".");
            instance.setValue(i, Double.parseDouble(value));
        }

        return instance;
    }

    public void save(Movement movement) throws Exception {
        logger.info("Size: {}", movement.getCoordinates().size());

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < movement.getCoordinates().size(); i++) {
            String value = movement.getCoordinates().get(i).replace(",", ".");
            builder.append(value).append(",");
        }

        builder.append(movement.getMovement()).append("\n");

        Files.write(Paths.get(arff), builder.toString().getBytes(), StandardOpenOption.APPEND);
        createWekaModelService.createModel();
    }

}
