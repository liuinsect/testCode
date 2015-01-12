//package digester;
//
//import java.io.File;
//import java.net.URL;
//
//import org.apache.commons.digester.Digester;
//
//public class Main {
//	public static void main( String[] args ) {
//
//	      try {
//	         Digester digester = new Digester();
//	         digester.setValidating( false );
//	         digester.addRuleSet(new CatalogRuleSet());
//
//
//	         URL fileURL = DigesterDriver.class.getResource("catalog.xml");
//	         File input = new File(fileURL.getFile());
//	         Catalog c = (Catalog)digester.parse( input );
//
//	         System.out.println( c.toString() );
//
//	      } catch( Exception exc ) {
//	         exc.printStackTrace();
//	      }
//	   }
//}
