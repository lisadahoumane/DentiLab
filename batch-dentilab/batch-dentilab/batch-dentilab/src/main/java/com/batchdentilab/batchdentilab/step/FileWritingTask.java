package com.batchdentilab.batchdentilab.step;

import com.batchdentilab.batchdentilab.Utils.TranslatorAPI;
import com.batchdentilab.batchdentilab.context.DentilabContext;
import com.batchdentilab.batchdentilab.model.OrderEntity;
import com.batchdentilab.batchdentilab.repository.DentiLabRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileWritingTask implements Tasklet {
    @Autowired
    private DentiLabRepository dentiLabRepository;
    @Autowired
    private DentilabContext dentilabContext;
    @Autowired
    private TranslatorAPI translatorAPI;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws IOException {
        final String fileNameFr = "Description_fr";
        final String fileNameEn = "Description_en";
        /*
        POUR CHAQUE ORDERMAPPING DANS LA LISTE FROM CONTEXT :
        RECUPERER LE FICHIER ET LE STOCKER DANS LE REPERTOIRE CREE POUR LE PATIENT
        + RECUPERER LE CHEMIN DU REPERTOIRE ET STOCKER DANS UNE LISTE "OrderEntity"
        AVEC DESCRIPTION / FILEPATH
         */
        // TEST
        OrderEntity orderEntity = new OrderEntity();
        String description =
                "Dans une forêt lointaine, au milieu d'un océan de verdure luxuriante," +
                        "se trouve une clairière enchantée où les lucioles dansent sous le clair de lune." +
                        "Les arbres que" +
                        "les rivières chantent des mélodies oubliées depuis longtemps.";

        orderEntity.setFilePath("C:\\Users\\SAM-YIN-YANG Alex\\Desktop");
        orderEntity.setDescription(description);

        List<OrderEntity> listOrders = new ArrayList<>();
        listOrders.add(orderEntity);
        dentilabContext.setListOrders(listOrders);
        // TEST

        // Create directory for each order
        String name_folder = "alexsamyinyang_dentilab";
        String outputDirectory = System.getProperty("user.home") + File.separator +"Desktop\\" + name_folder ;

        log.info("Creating directory...");
        File dir = new File(outputDirectory);
        if (dir.mkdir()){
            log.info("Directory created !");
        }else{
            log.info("Error creating directory.");
        }

        // Translate description to English
        String translatedDesc = translatorAPI.translate("en", description);

        // Create french description .txt file
        writeDescriptionTxtFile(description, fileNameFr, outputDirectory);

        // Create english description .txt file
        writeDescriptionTxtFile(translatedDesc, fileNameEn, outputDirectory);

        return RepeatStatus.FINISHED;
    }

    private StringBuilder writeDescriptionTxtFile(String description, String fileName, String outputDirectory) {
        StringBuilder sb = new StringBuilder();
        sb.append(description);

        // Write description in .txt file
        try(FileWriter fileWriter = new FileWriter(outputDirectory + File.separator + fileName + ".txt")){
            fileWriter.write(sb.toString());
            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
