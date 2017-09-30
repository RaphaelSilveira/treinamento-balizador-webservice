package br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.configs;

import br.edu.saocarlos.ifsp.treinamentobalizadorwebservice.services.CreateWekaModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CreateWekaModelService createWekaModelService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            createWekaModelService.createModel();
        } catch (Exception e) {
            logger.error("Exception", e);
        }
    }

}
