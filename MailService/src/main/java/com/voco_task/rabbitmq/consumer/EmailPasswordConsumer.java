package com.voco_task.rabbitmq.consumer;

import com.voco_task.exception.ErrorType;
import com.voco_task.exception.MailManagerException;
import com.voco_task.rabbitmq.model.AuthMailModel;
import com.voco_task.service.MailService;
import com.voco_task.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailPasswordConsumer {
    private final JavaMailSender javaMailSender;
    private final JwtTokenManager jwtTokenManager;

    @RabbitListener(queues = "queueMailPassword")
    public void userPassword(AuthMailModel model){
//        mailService.userPassword(model);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        String password = jwtTokenManager.getEmailPasswordToken(model.getToken()).orElseThrow(() -> new MailManagerException(ErrorType.INVALID_TOKEN));
        try {
            helper.setFrom("moco.yemek@gmail.com");
            helper.setTo(model.getEmail());
            helper.setSubject("PASSWORD HATIRLATMA MAİLİ : ");
            String content = "<div style='text-align: center;'><h2 style='font-size: 24px;'>" +
                    "<p style='font-size: 18px;'>PAROLANIZ : </p>" + password;

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailManagerException(ErrorType.MAİL_NOT_SEND);
        }
    }
}
