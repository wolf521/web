package main.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by cuizhixiang on 2017/5/31.
 * 获取资源文件数据
 **/
public class MailConfig {
    private static final String PROPERTIES_DEFAULT = "mailConfig.properties";
    public static String host;
    public static Integer port;
    public static String userName;
    public static String passWord;
    public static String emailForm;
    public static String timeout;
    // 发送标志
    public static String personal;
    // 邮件主题
    public static String subject;
    public static Properties properties;

    static {
        init();
    }

    /**
     * 初始化
     */
    public static void init() {
        properties = new Properties();
        try {
            InputStream inputStream = MailConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_DEFAULT);
            properties.load(inputStream);
            inputStream.close();
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
            userName = properties.getProperty("username");
            passWord = properties.getProperty("password");
            emailForm = properties.getProperty("from");
            timeout = properties.getProperty("timeout");
            personal = "墨裔";
            subject = "测试邮件";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        MailConfig.host = host;
    }

    public static Integer getPort() {
        return port;
    }

    public static void setPort(Integer port) {
        MailConfig.port = port;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        MailConfig.userName = userName;
    }

    public static String getPassWord() {
        return passWord;
    }

    public static void setPassWord(String passWord) {
        MailConfig.passWord = passWord;
    }

    public static String getEmailForm() {
        return emailForm;
    }

    public static void setEmailForm(String emailForm) {
        MailConfig.emailForm = emailForm;
    }

    public static String getTimeout() {
        return timeout;
    }

    public static void setTimeout(String timeout) {
        MailConfig.timeout = timeout;
    }

    public static String getPersonal() {
        return personal;
    }

    public static void setPersonal(String personal) {
        MailConfig.personal = personal;
    }

    public static String getSubject() {
        return subject;
    }

    public static void setSubject(String subject) {
        MailConfig.subject = subject;
    }

    public static Properties getProperties() {
        return properties;
    }
}
