package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

import java.io.File;

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

        ConverterUtils.DataSource dataSource = new ConverterUtils.DataSource(arff);

        Instances instances = dataSource.getDataSet();

        instances.setClassIndex(instances.numAttributes() - 1);

        IBk ibk = new IBk();
        ibk.buildClassifier(instances);

        SerializationHelper.write(model, ibk);
    }

}
