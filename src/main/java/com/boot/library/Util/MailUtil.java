package com.boot.library.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Component
@Slf4j
public class MailUtil {

    @Value("${spring.mail.username}")
    String From;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    Jedis jedis;

    public boolean checkCode(String mail, String code) {
        String s = jedis.get(mail);
        return s!=null && s.equals(code);
    }

    public void sendMailCode(String userMail) {
        StringBuilder code=new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(new Random().nextInt(10));
        }
        jedis.set(userMail, code.toString());
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(From);
        msg.setTo(userMail);
        msg.setSubject("MyLibrary");
        msg.setText("你的验证码是："+code);
        javaMailSender.send(msg);
    }

    public void sendMailWithFile(File file,String mail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message =javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=null;
        try
        {
            // 设置编码utf-8
            messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(From);
            messageHelper.setTo(mail);
            messageHelper.setSubject(file.getName());
            messageHelper.setText("this is you download book:"+file.getName(),true);

            // 附件
            FileSystemResource resourse = new FileSystemResource(file);
            // 附件名称和路径
            messageHelper.addAttachment(file.getName(), resourse);
            javaMailSender.send(message);
        }
        catch (MessagingException e)
        {
            log.error("发送邮件失败");
            throw new RuntimeException("发送邮件失败",e);
        }
        log.info("发送完毕");
    }

}
