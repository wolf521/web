package main.java.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Administrator on 2017/12/7.
 */
public class TextMail {
    private MimeMessage mimeMessage;
    private Properties properties;
    private Session session;
    private String host = "smpt.163.com";
    private String name = "15953185712@163.com";
    private String password = "czxsqm521";

    /**
     * 完成邮件发送初始化工作
     *
     * @param to      收信人
     * @param from    发信人
     * @param subject 主题
     * @param content 内容
     */
    public TextMail(String to, String from, String subject, String content) {
        properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        // 设置smtp验证属性
        properties.put("mail.smtp.auth", "true");
        // 链接超时
        properties.put("mail.smtp.connectiontimeout", "500000");
        // 读超时
        properties.put("mail.smtp.timeout", "500000");
        MyAuthenticator myAuthenticator = new MyAuthenticator(name,password);
        // 创建邮件回话对象
        session = Session.getDefaultInstance(properties,myAuthenticator);
        // 创建mime邮件对象
        mimeMessage = new MimeMessage(session);
        try {
            // 设置邮件发送人
            mimeMessage.setFrom(new InternetAddress(name));
            // 设置邮件接受人
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // 设置邮件主题
            mimeMessage.setSubject(subject);
            // 设置邮件正文
            mimeMessage.setText(content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件(暂时不可行)
     *
     * @return
     */
    public boolean send() {
        try {
            // 创建smtp邮件协议发送对象
            Transport transport = session.getTransport("smtp");
            // 取得与邮件服务器的链接
            transport.connect(host, name, password);
            // 通过邮件服务器发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
            transport.close();
            return true;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
