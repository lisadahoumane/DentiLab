package com.batchdentilab.batchdentilab.step;

import com.batchdentilab.batchdentilab.context.DentilabContext;
import com.batchdentilab.batchdentilab.model.MappingOrder;
import com.batchdentilab.batchdentilab.model.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class ItemReaderTask implements Tasklet{
    @Autowired
    private DentilabContext dentilabContext;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Fetching info for each order...");
        /* A COMPLETER ICI
        List<MappingOrder> listMappingOrders = null;
        for (OrderEntity order : dentilabContext.getListOrders())
        {
            // APPEL API VERS MEDIT /order POUR RECUPERER DESCRIPTION / UUID FILE / Nom de dossier = nomdupatient-dentiste-numCommande
            // ===> stocké dans une instance de mappingOrder
            // ADD TO LIST mappingOrders du context
            // --------------------------------------
            // /!\ ATTENTION : JE NE VEUX PAS ENCORE RECUPERER LE FICHIER DANS CE STEP,
            // CELA SE FERA AU PROCHAIN STEP
            // SOIT FILEWRITINGTASK /!\
            // --------------------------------------
        }
        dentilabContext.setListMappingOrders(listMappingOrders);
         */
        return RepeatStatus.FINISHED;
    }
}
