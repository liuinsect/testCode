package digester;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;

public class CatalogRuleSet implements RuleSet {

	@Override
	public void addRuleInstances(Digester digester) {
         digester.addObjectCreate( "catalog", Catalog.class );
         digester.addSetProperties("catalog", "library", "library");
         
         digester.addObjectCreate( "catalog/book", Book.class );
         
         digester.addBeanPropertySetter( "catalog/book/author", "author" );
         digester.addBeanPropertySetter( "catalog/book/title", "title" );
         
         digester.addSetNext( "catalog/book", "addBook" );

         digester.addObjectCreate( "catalog/magazine", Magazine.class );
         digester.addBeanPropertySetter( "catalog/magazine/name", "name" );

         digester.addObjectCreate( "catalog/magazine/article", Article.class );
         
         digester.addSetProperties( "catalog/magazine/article", "page", "page" );
         digester.addBeanPropertySetter( "catalog/magazine/article/headline" ); 
         digester.addSetNext( "catalog/magazine/article", "addArticle" );

         digester.addSetNext( "catalog/magazine", "addMagazine" );
	}

	@Override
	public String getNamespaceURI() {
		System.out.println(" this is getNamespaceURI ");
		return null;
	}

}
