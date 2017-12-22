package main.java.xmlutil;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 * Sax解析xml(边加载边解析)
 */
public class XmlSax {
    public static void main(String[] agrs) {
        try {
            List<XmlProject> projects = new XmlSax().xmlParser();
            for (int i = 0; i < projects.size(); i++) {
                System.out.println("第" + (i + 1) + "本书：" + projects.get(i));
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            new XmlSax().createXml();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析xml
     *
     * @return
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public List<XmlProject> xmlParser() throws SAXException, ParserConfigurationException, IOException {
        // 获得一个SAXParserFactory对象
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 获得SAXParser对象
        SAXParser parser = factory.newSAXParser();
        String xmlFile = XmlSax.class.getClassLoader().getResource("project.xml").getFile();
        SaxHandler handler = new SaxHandler();
        parser.parse(xmlFile, handler);
        return handler.getList();
    }

    public void createXml() throws TransformerConfigurationException, IOException, SAXException {
        // 创建SAXTransformerFactory对象
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        // 创建TransformerHandler对象
        TransformerHandler handler = factory.newTransformerHandler();
        // 创建Transformer对象
        Transformer transformer = handler.getTransformer();
        // 设置编码
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        // 设置换行
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        File file = new File("book.xml");
        file.createNewFile();
        // 创建Result对象
        Result result = new StreamResult(file);
        // 将handler与result关联
        handler.setResult(result);
        // 打卡document
        handler.startDocument();
        AttributesImpl attribute = new AttributesImpl();
        // 利用handler对xml进行编写
        handler.startElement("", "", "bookstore", attribute);
        attribute.clear();
        attribute.addAttribute("","","id","","1");
        handler.startElement("", "", "book", attribute);
        attribute.clear();
        handler.startElement("", "", "name", attribute);
        handler.characters("sax解析初级".toCharArray(),0,"sax解析初级".length());
        handler.endElement("","","name");
        attribute.clear();
        handler.startElement("", "", "year", attribute);
        handler.characters("2017-12-12".toCharArray(),0,"2017-12-12".length());
        handler.endElement("","","year");
        attribute.clear();
        handler.startElement("", "", "price", attribute);
        handler.characters("36.00".toCharArray(),0,"36.00".length());
        handler.endElement("","","price");
        attribute.clear();
        handler.startElement("", "", "author", attribute);
        handler.characters("老毕".toCharArray(),0,"老毕".length());
        handler.endElement("","","author");
        handler.endElement("","","book");
        attribute.addAttribute("","","id","","2");
        handler.startElement("", "", "book", attribute);
        attribute.clear();
        handler.startElement("", "", "name", attribute);
        handler.characters("sax解析高级".toCharArray(),0,"sax解析高级".length());
        handler.endElement("","","name");
        attribute.clear();
        handler.startElement("", "", "year", attribute);
        handler.characters("2017-12-20".toCharArray(),0,"2017-12-20".length());
        handler.endElement("","","year");
        attribute.clear();
        handler.startElement("", "", "price", attribute);
        handler.characters("66.00".toCharArray(),0,"66.00".length());
        handler.endElement("","","price");
        attribute.clear();
        handler.startElement("", "", "author", attribute);
        handler.characters("老毕老爷".toCharArray(),0,"老毕老爷".length());
        handler.endElement("","","author");
        handler.endElement("","","book");
        handler.endElement("","","bookstore");
        // 关闭document
        handler.endDocument();
    }
}
