package httpURLConnection;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-1-26
 * Time: 上午11:38
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://gemini.jd.net/index.html");

        URLConnection conn = url.openConnection();
        // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
        // http正文内，因此需要设为true, 默认情况下是false;
        conn.setDoOutput(true);

        // 设置是否从httpUrlConnection读入，默认情况下是true;
        conn.setDoInput(true);

        // Post 请求不能使用缓存
        conn.setUseCaches(false);


        // 设定传送的内容类型是可序列化的java对象
        // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
//        connn.setRequestProperty("Content-type", "application/x-java-serialized-object");

        // 设定请求的方法为"POST"，默认是GET
        ((HttpURLConnection) conn).setRequestMethod("GET");

        // 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
        conn.connect();

        BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
        byte[] byteArray = new byte[1024];
        int pos = -1;
        while( ( pos = bin.read(byteArray) ) > -1 ){
            System.out.println(new String(byteArray,0,pos));
        }
//        System.out.println(new String(byteArray));
    }



}
