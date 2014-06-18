//package xml;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//
//import com.sun.xml.internal.ws.util.StringUtils;
//import org.xml.sax.Attributes;
//import org.xml.sax.SAXException;
//import org.xml.sax.helpers.DefaultHandler;
//
//public class JDKBigXmlSaxParse extends DefaultHandler {
//
//    private int statmentSize = 6;
//    private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(statmentSize);
//    private Map<String, Object> dataMap;
//    private String currentTag = "";
//
//    public void test() throws Exception {
//        SAXParser sax = SAXParserFactory.newInstance().newSAXParser();
//        InputStream in = new FileInputStream("c:\\test.xml");
//        sax.parse(in, this);
//        in.close();
//    }
//
//    @Override
//    public void characters(char[] ch, int start, int length)throws SAXException {
//        String value = new String(ch, start, length);
//        dataMap.put(currentTag, value.trim());
//    }
//
//    @Override
//    public void endElement(String uri, String localName, String qName)
//            throws SAXException {
//        if("url".equals(qName)){
//            dataList.add(dataMap);
//            //dataMap.clear();
//        }
//
//        if(dataList.size() == statmentSize){
//            doSomeThing();
//            dataList.clear();
//        }
//
//        if("urlset".equals(qName) && dataList.size() != 0){
//            doSomeThing();
//            dataList.clear();
//        }
//
//    }
//
//    @Override
//    public void startElement(String uri, String localName, String qName,
//                             Attributes attributes) throws SAXException {
//        if ("urlset".equals(qName)) {
//            dataMap = new HashMap<String, Object>();
//            return;
//        }
//
//        currentTag = qName;
//    }
//
//    public static void main(String[] args) throws Exception {
//        long start = System.nanoTime();
//        JDKBigXmlSaxParse jdkBigXmlSaxParse =new JDKBigXmlSaxParse();
//        jdkBigXmlSaxParse.test();
//        long end = System.nanoTime();
//        System.out.println("耗时:"+(end-start)/1000000000.0+"秒");
//    }
//
//
//}
