package com.batchdentilab.batchdentilab.step;

import com.batchdentilab.batchdentilab.context.DentilabContext;
import com.batchdentilab.batchdentilab.model.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class InitDentilabTask implements Tasklet {
    @Autowired
    private DentilabContext dentilabContext;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Fetching all orders...");

        /* A COMPLETER ICI
        List<OrderEntity> orderEntityList = null;
        // APPEL API POUR AVOIR TOUTES LES ORDERS DANS LA LISTE INITIALISEE
        // + LES STOCKER DANS LISTE CONTEXT
        // dentilabContext.set(LISTE DES COMMANDES) => CREER DANS LE MODEL,
        // UNE CLASS COMMANDE CONFORME A LA REPONSE JSON
         */

        return RepeatStatus.FINISHED;
    }
}
