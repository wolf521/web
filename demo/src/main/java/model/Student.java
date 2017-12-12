package main.java.model;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/12.
 */
public class Student implements HttpSessionActivationListener,Serializable{

    private String name;

    private Integer age;

    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {

    }
}
