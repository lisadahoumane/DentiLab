package com.batchdentilab.batchdentilab.step;

import com.batchdentilab.batchdentilab.Utils.EmailProperties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.batchdentilab.batchdentilab.context.DentilabContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SendEmailTask implements Tasklet {
    @Value("${email.username}")
    private String fromEmail;
    @Value("${email.toEmail}")
    private String toEmail;
    @Autowired
    private DentilabContext dentilabContext;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        EmailProperties emailProperties = new EmailProperties();
        Message message = new MimeMessage(emailProperties.initSession());
        message.setFrom(new InternetAddress("tmp.test.oneshot@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("asamyinyang@gmail.com"));
        message.setSubject("TEST");

        log.info(dentilabContext.toString());
        String msg = dentilabContext.getListOrders().get(0).getDescription();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        return RepeatStatus.FINISHED;
    }
}
