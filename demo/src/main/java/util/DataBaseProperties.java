package main.java.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2017/11/20.
 */
public class DataBaseProperties {
    private String driver;
    private String url;
    private String user;
    private String password;

    public String getDriver() {
        return getProperties("driver");
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProperties(String parm) {
        Properties properties = new Properties();
        InputStream inputStream = MailConfig.class.getClassLoader().getResourceAsStream("database.properties");
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(parm);
    }
}
