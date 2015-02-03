package digester.rule;

import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

public class CatalogRule extends Rule{
	
    public void begin(Attributes attributes) throws Exception {

       int length = attributes.getLength();
       for( int i = 0 ; i < length ; i++ ){
    	   System.out.println( attributes.getQName(i) );
       }
    }


    public void begin(String namespace, String name, Attributes attributes)
        throws Exception {
    	System.out.println("this is begin's name : "+ name);
        begin(attributes);

    }


    public void body(String text) throws Exception {

        System.out.println( "this is text : " + text );

    }


    public void body(String namespace, String name, String text)
        throws Exception {
    	System.out.println("this is body's name : "+ name);
        body(text);

    }


    public void end() throws Exception {

    	System.out.println( "this is end");

    }

    public void end(String namespace, String name)
        throws Exception {
    	System.out.println("this is end's name : "+ name);
        end();

    }

    public void finish() throws Exception {

    	System.out.println( "this is finish");

    }

}
