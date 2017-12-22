package main.java.xmlutil;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/20.
 * Dom解析xml(将xml文件加载到内存中，依次解析)
 */
public class XmlDom {
    public static void main(String[] agrs) {
        try {
            new XmlDom().xmlParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            new XmlDom().createXml();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析xml
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void xmlParser() throws ParserConfigurationException, IOException, SAXException {
        String xmlFile = XmlDom.class.getClassLoader().getResource("project.xml").getFile();
        Document document = getDocumentBuilder().parse(xmlFile);
        NodeList nodeList = document.getElementsByTagName("project");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap nodeMap = node.getAttributes();
            for (int j = 0; j < nodeMap.getLength(); j++) {
                System.out.println("*********属性值**********");
                System.out.println(nodeMap.item(j).getNodeName());
                System.out.println(nodeMap.item(j).getNodeValue());
                System.out.println("*************************");
            }
            NodeList nodes = node.getChildNodes();
            for (int k = 1; k < nodes.getLength(); k++) {
                if (nodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println("*************************");
                    System.out.println(nodes.item(k).getNodeName());
                    System.out.println(nodes.item(k).getTextContent());
                    System.out.println("*************************");
                }
            }
        }
    }

    /**
     * 解析xml
     *
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void xmlParser1() throws ParserConfigurationException, IOException, SAXException {
        String xmlFile = XmlDom.class.getClassLoader().getResource("project.xml").getFile();
        Document document = getDocumentBuilder().parse(xmlFile);
        NodeList nodeList = document.getElementsByTagName("project");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            System.out.println(element.getAttribute("id"));
        }
    }

    /**
     * 创建xml文件
     *
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public void createXml() throws ParserConfigurationException, TransformerException {
        Document document = getDocumentBuilder().newDocument();
        // 将standalone设置为true并不显示
        document.setXmlStandalone(true);
        // 创建根节点
        Element bookstore = document.createElement("bookstore");
        Element book = document.createElement("book");
        book.setAttribute("id", "1");
        Element name = document.createElement("name");
        name.setTextContent("xml入门简介");
        book.appendChild(name);
        Element year = document.createElement("year");
        year.setTextContent("2017-12-12");
        book.appendChild(year);
        Element author = document.createElement("author");
        author.setTextContent("DJH");
        book.appendChild(author);
        Element book1 = document.createElement("book");
        book1.setAttribute("id", "2");
        Element name1 = document.createElement("name");
        name1.setTextContent("xml高级");
        book1.appendChild(name1);
        Element year1 = document.createElement("year");
        year1.setTextContent("2017-12-22");
        book1.appendChild(year1);
        Element author1 = document.createElement("author");
        author1.setTextContent("HK");
        book1.appendChild(author1);
        //在根节点下添加book节点
        bookstore.appendChild(book);
        bookstore.appendChild(book1);
        //document中添加根节点
        document.appendChild(bookstore);
        //创建TransformerFactory对象
        TransformerFactory factory = TransformerFactory.newInstance();
        //创建Transformer对象
        Transformer transformer = factory.newTransformer();
        // 文件是否换行
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        //生成xml文件
        transformer.transform(new DOMSource(document), new StreamResult(new File("book.xml")));
    }

    /**
     * 获得DocumentBuilder对象
     *
     * @return
     * @throws ParserConfigurationException
     */
    private DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        // 创建一个DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建一个DocumentBuilder对象
        return factory.newDocumentBuilder();
    }
}
