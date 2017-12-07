package main.java.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

/**
 * Created by cuizhixiang on 2017/5/27.
 * 发送邮件
 **/

public class EmailHelper {

    private MailConfig mailConfig = new MailConfig();

    /**
     * 邮件发送器
     *
     * @return 配置好的工具
     */
    private JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(mailConfig.getHost());
        sender.setPort(mailConfig.getPort());
        sender.setUsername(mailConfig.getUserName());
        sender.setPassword(mailConfig.getPassWord());
        sender.setDefaultEncoding("Utf-8");
        Properties prop = new Properties();
        // 链接超时
        prop.setProperty("mail.smtp.connectiontimeout", "50000");
        // 读超时
        prop.setProperty("mail.smtp.timeout", "50000");
        prop.setProperty("mail.smtp.auth", "true");
        sender.setJavaMailProperties(prop);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param mailMap 邮件信息:key为账号，value为发送内容
     * @throws MessagingException 异常
     */
    public void sendHtmlMail(Map<String, String> mailMap) throws MessagingException, UnsupportedEncodingException {

        JavaMailSenderImpl mailSender = createMailSender();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8编码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        // 设置发送人
        messageHelper.setFrom(mailConfig.getUserName(), mailConfig.getPersonal());
        // 获得接收人及内容
        for (Map.Entry<String, String> entry : mailMap.entrySet()) {
            messageHelper.setTo(entry.getKey());
            messageHelper.setSubject(mailConfig.getSubject());
            messageHelper.setText(entry.getValue(), true);
            mailSender.send(mimeMessage);
        }
    }
}

