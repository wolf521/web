package main.java.model;

/**
 * Created by Administrator on 2017/11/17.
 */
public class User {
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
}
