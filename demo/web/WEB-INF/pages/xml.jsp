<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/21
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>xml操作</title>
    <script type="text/javascript">
        function loadXMLText(text) {
            try {
                //Internet Explorer
                var xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
                xmlDoc.async="false";
                xmlDoc.loadXML(text);
                return xmlDoc;
            } catch(e) {
                try {
                    //Firefox, Mozilla, Opera, etc.
                    var parser=new DOMParser();
                    xmlDoc=parser.parseFromString(text,"text/xml");
                    return xmlDoc;
                } catch(e) {
                    alert(e.message)
                }
            }
        }

        function loadXMLDoc(dname){
            try {
                //Internet Explorer
                xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
            } catch(e) {
                try {
                    //Firefox, Mozilla, Opera, etc.
                    xmlDoc=document.implementation.createDocument("","",null);
                } catch(e) {
                    alert(e.message)
                }
            } try {
                xmlDoc.async=false;
                xmlDoc.load(dname);
                return(xmlDoc);
            } catch(e) {
                alert(e.message)
            }
            return(null);
        }
        //loadXMLDoc("common/project.xml");
        text="<bookstore>"
        text=text+"<book>";
        text=text+"<title>Harry Potter</title>";
        text=text+"<author>J K. Rowling</author>";
        text=text+"<year>2005</year>";
        text=text+"</book>";
        text=text+"</bookstore>";
        xmlDoc= loadXMLText(text);

        document.write(xmlDoc.getElementsByTagName("title")[0].childNodes[0].nodeValue);
        document.write("<br />");
        document.write(xmlDoc.getElementsByTagName("author")[0].childNodes[0].nodeValue);
        document.write("<br />");
        document.write(xmlDoc.getElementsByTagName("year")[0].childNodes[0].nodeValue);
    </script>

</head>
<body>
    <form action="" method="post">
        请输入属性名：<input id="name" type="text" /><br/>
        请输入属性值：<input id="value" type="text" /><br/>
        <button onclick="createXml()">添加节点</button><br/>
        xml文档内容：<br/>
        <div id="result">

        </div>
    </form>
</body>
</html>
