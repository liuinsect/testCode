package digester.rule;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import digester.Catalog;
import digester.DigesterDriver;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;


/**
 * 测试一下degister的rule变量，可以看到 degister是基于压栈的方式处理xml的，每次匹配到一个'动作'
 * 比如标签开始，这调用一下当前rule的begin方法
 * 依次body
 * 然后是end
 * 最后是finsh
* @Package digestertest.rule 
* @author liukunyang
* @date 2013-9-26 下午02:16:27 
* @version V1.0
 */
public class Main {
	
	public static void main( String[] args ) throws IOException, SAXException {
		 Digester digester = new Digester();
         digester.setValidating( false );
         
         digester.addRule("catalog", new CatalogRule() );


         File input = new File("E:\\workspace\\allTest\\testCode\\src\\main\\java\\digester\\catalog.xml");
         Catalog c = (Catalog)digester.parse( input );
	}
	
}
