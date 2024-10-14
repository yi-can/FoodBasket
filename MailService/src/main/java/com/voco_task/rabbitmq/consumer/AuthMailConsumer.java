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
public class AuthMailConsumer {
    private final MailService mailService;
    private final JavaMailSender javaMailSender;
    private final JwtTokenManager jwtTokenManager;

    @RabbitListener(queues = "queueMail")
    public void createUser(AuthMailModel model){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setFrom("moco.yemek@gmail.com");
            helper.setTo(model.getEmail());
            helper.setSubject("AKTIVASYON MAİLİ : ");
            String content = "<div style='text-align: center;'><h2 style='font-size: 24px;'>"
                    + model.getFirstname() + "</h2>" +
                    "<p style='font-size: 18px;'>Başarıyla kayıt oldunuz.</p>" +
                    "<p style='font-size: 14px;'>Bu kodu saklayınız!:</p>" + model.getActivationCode() +
                    "<p><a href='http://localhost:9090/auth/activate/status?token="
                    + model.getToken() + "'>" +
                    "<button style='background-color: #FFA500; border: none; color: white; padding: 12px 24px; text-align: center; text-decoration: "
                    + "none; display: inline-block; font-size: 16px; border-radius: 12px;'>Üyeliğini Aktive Et</button></a></p></div>";

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new MailManagerException(ErrorType.MAİL_NOT_SEND);
        }
    }


}
