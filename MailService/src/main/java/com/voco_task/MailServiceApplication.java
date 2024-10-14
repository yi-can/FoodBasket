package com.voco_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class MailServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

//    @Autowired
//    private JavaMailSender javaMailSender;
//    @EventListener(ApplicationReadyEvent.class)
//    public void senMail(){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("abayyigitcan@gmail.com");
//        mailMessage.setTo("abayyigitcan@gmail.com");
//        mailMessage.setSubject("Aktivasyon linki");
//        mailMessage.setText("sfdsfdsfsdfsdfsdfsdfsdfsfdsfd");
//        javaMailSender.send(mailMessage);
//    }
}