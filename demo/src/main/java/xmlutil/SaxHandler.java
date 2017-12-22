package main.java.xmlutil;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 * sax解析xml的handler
 */
public class SaxHandler extends DefaultHandler {
    private int index = 0;
    private List<XmlProject> list = new ArrayList<XmlProject>();
    private XmlProject xmlProject = null;
    private String value = null;

    public List<XmlProject> getList() {
        return list;
    }

    /**
     * 遍历xml文件开始标签
     *
     * @param uri
     * @param localName
     * @param qName 节点名称
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //  判断是否开始
        if("project".equals(qName)){
            xmlProject = new XmlProject();
            index++;
            System.out.println("**************第"+index+"本书开始*****************");
            for (int i = 0; i < attributes.getLength(); i++) {
                xmlProject.setId(attributes.getValue(i));
            }
        }
    }

    /**
     * 遍历xml文件结束标签
     *
     * @param uri
     * @param localName
     * @param qName 节点名称
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        // 判断是否结束
        if ("project".equals(qName)) {
            list.add(xmlProject);
            xmlProject = null;
            System.out.println("**************第"+index+"本书结束*****************");
        } else {
            switch (qName){
                case "project_name":xmlProject.setProjectName(value);break;
                case "project_version":xmlProject.setProjectVersion(value);break;
                case "project_year":xmlProject.setProjectYear(value);break;
                case "project_month":xmlProject.setProjectMonth(value);break;
                case "project_day":xmlProject.setProjectDay(value);break;
                case "project_description":xmlProject.setProjectDescription(value);break;
                case "docu_author":xmlProject.setDocuAuthor(value);
            }
        }

    }

    /**
     * 标识解析开始
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("**************解析开始*************");
    }

    /**
     * 标识解析结束
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("**************解析结束*************");
    }

    /**
     *
     *
     * @param ch xml文档
     * @param start 节点内容开始下表
     * @param length 节点长度
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String value = new String(ch,start,length);
        if(!"".equals(value.trim())){
           this.value = value;
        }
    }
}
