package main.java.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Administrator on 2017/12/7.
 */
public class MyAuthenticator extends Authenticator {
    private String name;
    private String password;

    public MyAuthenticator() {}

    public MyAuthenticator(String name,String password) {
        this.name = name;
        this.password = password;
        getPasswordAuthentication();
    }

    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(name,password);
    }
}
