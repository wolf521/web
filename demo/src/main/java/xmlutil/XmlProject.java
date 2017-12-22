package main.java.xmlutil;

/**
 * Created by Administrator on 2017/12/21.
 */
public class XmlProject {
    private String id;
    private String projectName;
    private String projectVersion;
    private String projectYear;
    private String projectMonth;
    private String projectDay;
    private String projectDescription;
    private String docuAuthor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectMonth() {
        return projectMonth;
    }

    public void setProjectMonth(String projectMonth) {
        this.projectMonth = projectMonth;
    }

    public String getProjectDay() {
        return projectDay;
    }

    public void setProjectDay(String projectDay) {
        this.projectDay = projectDay;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getDocuAuthor() {
        return docuAuthor;
    }

    public void setDocuAuthor(String docuAuthor) {
        this.docuAuthor = docuAuthor;
    }

    @Override
    public String toString() {
        return "XmlProject{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectVersion='" + projectVersion + '\'' +
                ", projectYear='" + projectYear + '\'' +
                ", projectMonth='" + projectMonth + '\'' +
                ", projectDay='" + projectDay + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", docuAuthor='" + docuAuthor + '\'' +
                '}';
    }
}
