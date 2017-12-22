package main.java.xmlutil;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.EscapeStrategy;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 * JDom解析xml
 */
public class XmlJdom {
    public static void main(String[] agrs) {
        try {
            new XmlJdom().xmlParser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        try {
            new XmlJdom().createXml();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析xml
     *
     * @throws IOException
     * @throws JDOMException
     */
    public void xmlParser() throws IOException, JDOMException {
        // 创建SAXBuilder对象
        SAXBuilder builder = new SAXBuilder();
        // 以utf-8编码加载文件
        InputStreamReader inputStreamReader = new InputStreamReader(XmlJdom.class.getClassLoader().getResourceAsStream("project.xml"), "utf-8");
        // 引入xml文件
        Document document = builder.build(inputStreamReader);
        // 获得根节点
        Element rootElement = document.getRootElement();
        // 获取子节点集合
        List<Element> elements = rootElement.getChildren();
        for (Element element : elements) {
            for (Attribute attribute : element.getAttributes()) {
                System.out.println(attribute.getName());
                System.out.println(attribute.getValue());
            }
            for (Element element1 : element.getChildren()) {
                System.out.println(element1.getName());
                System.out.println(element1.getValue());
            }
        }
    }

    public void createXml() throws IOException {
        // 生成rss节点
        Element rss = new Element("rss");
        // 设置属性
        rss.setAttribute("version", "2.0");
        // 生成Document对象
        Document document = new Document(rss);
        // 生成channel节点
        Element channel = new Element("channel");
        // 将channel节点添加到rss中
        rss.addContent(channel);
        // 生成title节点
        Element title = new Element("title");
        // 设置title节点文本内容
        title.setText("国内最新新闻");
        // 将title节点添加到channel中
        channel.addContent(title);
        // 生成content节点
        Element content = new Element("content");
        // 设置content节点文本内容
        content.setText("<![CDATA[上海移动互联网产业促进中心正式揭牌]]>");
        // 将content节点添加到channel中
        channel.addContent(content);
        // 生成cdata节点
        Element cdata = new Element("content");
        // 设置cdata节点文本内容(不转义)
        cdata.addContent(new CDATA("上海移动互联网产业促进中心正式揭牌"));
        // 将cdata节点添加到channel中
        channel.addContent(cdata);
        // 生成Format对象
        Format format = Format.getCompactFormat();
        // 设置换行
        format.setIndent("");
        // 设置编码格式
        format.setEncoding("GBK");
        format.setEscapeStrategy(new EscapeStrategy() {
            // 是否应该将字符转为对应的码文
            @Override
            public boolean shouldEscape(char c) {
                return false;
            }
        });
        XMLOutputter outputter = new XMLOutputter(format);
        outputter.output(document, new FileOutputStream(new File("rss.xml")));
    }
}
