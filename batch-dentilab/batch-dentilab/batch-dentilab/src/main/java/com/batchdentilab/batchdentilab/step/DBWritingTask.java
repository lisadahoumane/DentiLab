package com.batchdentilab.batchdentilab.step;

import com.batchdentilab.batchdentilab.context.DentilabContext;
import com.batchdentilab.batchdentilab.model.OrderEntity;
import com.batchdentilab.batchdentilab.repository.DentiLabRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DBWritingTask implements Tasklet {
    @Autowired
    private DentiLabRepository dentiLabRepository;
    @Autowired
    private DentilabContext dentilabContext;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Get factory ID
        List<Long> idFactory = dentiLabRepository.findFactoryId();
        log.info("Factory ID : " + String.valueOf(idFactory));

        // For each order save order entity in database (table = orders)
        log.info("Saving...");
        for (OrderEntity order : dentilabContext.getListOrders()) {
            order.setIdFactory(idFactory.get(0));

            log.info("- Order : " + order.toString());
            dentiLabRepository.save(order);
        }

        return RepeatStatus.FINISHED;
    }
}
