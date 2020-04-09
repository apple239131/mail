package com.lr.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@SpringBootTest
class MailApplicationTests {
    @Autowired
    JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        //简单邮件对象，不支持附件
        SimpleMailMessage mail = new SimpleMailMessage();
        //邮件主题
        mail.setSubject("SpringBoot整合邮件发送");
        mail.setText("邮件内容");
        //发送邮件的人，要与配置文件中的一致
        mail.setFrom("2391318676@qq.com");
        //发送日期
        mail.setSentDate(new Date());
        //发送给谁
        //如果某个邮件地址写错com.sun.mail.smtp.SMTPAddressFailedException: 550 Mailbox not found or acces
        mail.setTo("2860363914@qq.com", "lr05@inspur.com");
        //抄送给谁
        mail.setCc("2306710069@qq.com");
        javaMailSender.send(mail);
    }

    //发送带附件的邮件
    @Test
    public void mail() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setSubject("测试邮件带附件");
        helper.setText("邮件内容");
        helper.setFrom("2391318676@qq.com");
        helper.setSentDate(new Date());
        String[] ss = {"2860363914@qq.com", "lr05@inspur.com"};
        helper.setTo(ss);
        helper.addAttachment("steam.txt", new File("C:\\Users\\23913\\Desktop\\steam.txt"));
        javaMailSender.send(msg);
    }

    //邮件内容带图片
    @Test
    public void mail2() throws MessagingException {
        MimeMessage msg=javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(msg,true);
        helper.setSubject("测试邮件内容中带图片");
        helper.setText("内容带图片，第一张：<img src='cid:p1'/>,第二张图片:" +
                "<img src='cid:p2'/>",true);
        helper.setFrom("2391318676@qq.com");
        helper.setSentDate(new Date());
        String[] ss = {"2860363914@qq.com", "lr05@inspur.com"};
        helper.setTo(ss);
        helper.addInline("p1",new FileSystemResource(new File("C:\\Users\\23913\\Desktop\\p1.jpg")));
        helper.addInline("p2",new FileSystemResource(new File("C:\\Users\\23913\\Desktop\\p2.jpg")));
        javaMailSender.send(msg);
    }

}
