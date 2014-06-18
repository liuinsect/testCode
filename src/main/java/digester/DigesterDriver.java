package digester;

import java.io.File;
import java.net.URL;

import org.apache.commons.digester.Digester;

 

public class DigesterDriver {

   public static void main( String[] args ) {

      try {
         Digester digester = new Digester();
         digester.setValidating( false );
         
         /*ObjectCreateRule: 利用默认构造函数创建类实例并且压到堆栈, 
          *元素结束的时候会从堆栈pop出来.  
          */  
         digester.addObjectCreate( "catalog", Catalog.class );
         digester.addSetProperties("catalog", "library", "library");
         
         
         digester.addObjectCreate( "catalog/book", Book.class );
         
         /* 
          * BeanPropertySetterRule: 将名字属性赋值给栈顶的元素 
          * (Example: <page>10</page>.) 
          */  
         digester.addBeanPropertySetter( "catalog/book/author", "author" );
         digester.addBeanPropertySetter( "catalog/book/title", "title" );
         
         /* 
          * SetNextRule: pop栈顶实例并且利用定义的方法传递给下个对象实例, 通常用来将一个完整的bean插到父对象上. 
          */  
         digester.addSetNext( "catalog/book", "addBook" );

         digester.addObjectCreate( "catalog/magazine", Magazine.class );
         digester.addBeanPropertySetter( "catalog/magazine/name", "name" );

         digester.addObjectCreate( "catalog/magazine/article", Article.class );
         
         /* 
          * SetPropertiesRule: 将名字属性的值赋给栈顶对象. 
          * (Typically used to handle XML constructs like <article page="10">.) 
          */  
         digester.addSetProperties( "catalog/magazine/article", "page", "page" );
         digester.addBeanPropertySetter( "catalog/magazine/article/headline" ); 
         digester.addSetNext( "catalog/magazine/article", "addArticle" );

         digester.addSetNext( "catalog/magazine", "addMagazine" );
         
//         System.out.println(DigesterDriver.class.getResource(""));
//         System.out.println(DigesterDriver.class.getResource("catalog.xml"));
//         System.out.println(DigesterDriver.class.getResource("/"));
//         System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
//         System.out.println(DigesterDriver.class.getClassLoader().getResource(""));
//         System.out.println(ClassLoader.getSystemResource(""));
         
         URL fileURL = DigesterDriver.class.getResource("catalog.xml");
         File input = new File(fileURL.getFile());
         Catalog c = (Catalog)digester.parse( input );

         System.out.println( c.toString() );

      } catch( Exception exc ) {
         exc.printStackTrace();
      }
   }
}