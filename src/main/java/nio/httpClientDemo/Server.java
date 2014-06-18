package nio.httpClientDemo;

import java.io.IOException; 
import java.net.InetSocketAddress; 
import java.nio.ByteBuffer; 
import java.nio.channels.SelectionKey; 
import java.nio.channels.Selector; 
import java.nio.channels.ServerSocketChannel; 
import java.nio.channels.SocketChannel; 
import java.util.*;

public class Server { 
    private Selector selector; 
    private ByteBuffer readBuffer = ByteBuffer.allocate(2);//调整缓存的大小可以看到打印输出的变化

    public void start() throws IOException {

        //打开一个channel
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //设置为异步的
        ssc.configureBlocking(false);
        //绑定80端口
        ssc.socket().bind(new InetSocketAddress("localhost", 80));

        //创建一个selector
        selector = Selector.open();
        //告诉selector ssc这个channel 需要关心一个accept事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        Scanner scanner = new Scanner(System.in);

        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(1);
            //阻塞式的 获取已经准备好的通道
            //有准备好的立马返回
            int readyCount =  selector.select();

            System.out.println("readyCount:"+readyCount);

            System.out.println(2);
            //获取已经准备好的通道
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (!key.isValid()) { 
                    continue; 
                } 
                if (key.isAcceptable()) { 
                    accept(key); 
                } else if (key.isReadable()) {
                    System.out.println( "key is : " + key );
                    read(key);
                }else if (key.isWritable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    System.out.println("please input message");
                    String message = scanner.nextLine();
                    ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes());
                    sc.write(writeBuffer);

                }
                keyIterator.remove(); 
            } 
        } 
    } 
 
    private void read(SelectionKey key) throws IOException { 
        SocketChannel socketChannel = (SocketChannel) key.channel(); 
 
        // Clear out our read buffer so it's ready for new data 
        this.readBuffer.clear(); 
 
        // Attempt to read off the channel 
        int numRead; 
        while(true){
           numRead = 0;
           try {
               numRead = socketChannel.read(this.readBuffer);
               if(numRead == -1 || numRead == 0){  // 0是代表有东西，但是读不上来，对应于 limit == position的情况
                    return;
               }
           } catch (IOException e) {
               // The remote forcibly closed the connection, cancel
               // the selection key and close the channel.
               key.cancel();
               socketChannel.close();
               return;
           }

           if (numRead > 0) {
               System.out.print(new String(readBuffer.array()));
           }
           this.readBuffer.clear();

       }
    }
 
    private void accept(SelectionKey key) throws IOException { 
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel(); 
        SocketChannel clientChannel = ssc.accept(); 
        clientChannel.configureBlocking(false); 
        clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        System.out.println("a new client connected"); 
    } 
 
 
    public static void main(String[] args) throws IOException { 
        System.out.println("server started..."); 
        new Server().start(); 
    }

} 
