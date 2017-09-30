package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.*;

@Service
public class CreateWekaModelService {

    @Value("${weka.model.name}")
    private String model;

    @Value("${weka.arff.name}")
    private String arff;


    public void createModel() throws Exception {
        File modelFile = new File(model);

        if(modelFile.exists() && !modelFile.isDirectory()) {
            modelFile.delete();
        }

        Classifier classifier = new SMO();

        Instances instances = new Instances(new BufferedReader(new FileReader(arff)));
        instances.setClassIndex(instances.numAttributes() - 1);

        classifier.buildClassifier(instances);

        SerializationHelper.write(model, classifier);
    }

}
