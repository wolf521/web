package main.java.xmlutil;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/12/21.
 * DOM4j解析xml
 */
public class XmlDom4j {
    public static void main(String[] agrs) {
        try {
            new XmlDom4j().xmlParser();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            new XmlDom4j().createXml();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析xml
     *
     * @throws UnsupportedEncodingException
     * @throws DocumentException
     */
    public void xmlParser() throws UnsupportedEncodingException, DocumentException {
        // 创建SAXReader对象
        SAXReader saxReader = new SAXReader();
        // 以utf-8编码加载文件
        InputStreamReader inputStreamReader = new InputStreamReader(XmlJdom.class.getClassLoader().getResourceAsStream("project.xml"), "utf-8");
        Document document = saxReader.read(inputStreamReader);
        // 获得根节点
        Element element = document.getRootElement();
        // 获取迭代器
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element element1 = (Element) iterator.next();
            for (Attribute attribute : element1.attributes()) {
                System.out.println(attribute.getName());
                System.out.println(attribute.getValue());
            }
            // 获得子元素迭代器
            Iterator iterator1 = element1.elementIterator();
            while (iterator1.hasNext()) {
                Element projectElement = (Element) iterator1.next();
                System.out.println(projectElement.getName());
                System.out.println(projectElement.getStringValue());
            }
        }
    }

    /**
     * 生成rss xml文件
     *
     * @throws IOException
     */
    public void createXml() throws IOException {
        // 生成Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element rss = document.addElement("rss");
        // 向rss中添加版本信息
        rss.addAttribute("version","2,0");
        // 向根节点中添加channel
        Element channel = rss.addElement("channel");
        // 向channel中添加title
        Element title = channel.addElement("title");
        // 向title添加文本
        title.setText("国内最新新闻");
        // 向channel中添加content
        Element content = channel.addElement("content");
        // 向title添加文本
        content.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌]]>");
        // 创建文件格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码格式
        format.setEncoding("GBK");
        // 生成xml文件
        File file = new File("rss.xml");
        XMLWriter writer = new XMLWriter(new FileOutputStream(file),format);
        // 设置是否转义，默认true（转义）
        writer.setEscapeText(false);
        writer.write(document);
        writer.close();
    }
}
