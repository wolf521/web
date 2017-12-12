package main.java.model;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/17.
 */
public class User implements HttpSessionBindingListener,HttpSessionActivationListener,Serializable{
    private String id;
    private String companyId;
    private String name;
    private String pass;

    public User() {
    }

    public User(String id, String companyId, String name, String pass) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * 绑定
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.print("session绑定了值"+httpSessionBindingEvent.getName());
    }

    /**
     * 解绑
     *
     * @param httpSessionBindingEvent
     */
    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.print("session解绑了值"+httpSessionBindingEvent.getName());
    }

    /**
     * 钝化
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
        System.out.print("钝化"+httpSessionEvent.getSource().toString());
    }

    /**
     * 活化
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
        System.out.print("活化"+httpSessionEvent.getSource().toString());
    }
}
