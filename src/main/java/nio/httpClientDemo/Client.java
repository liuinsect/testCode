package nio.httpClientDemo;

import java.io.BufferedOutputStream;
import java.io.IOException; 
import java.net.InetSocketAddress; 
import java.net.Socket;
import java.nio.ByteBuffer; 
import java.nio.channels.SelectionKey; 
import java.nio.channels.Selector; 
import java.nio.channels.SocketChannel; 
import java.util.Iterator; 
import java.util.Scanner; 
import java.util.Set;

/**
 * 客户端服务端通信的例子
 */
public class Client {

    private ByteBuffer readBuffer = ByteBuffer.allocate(8);//调整缓存的大小可以看到打印输出的变化

    public void start() throws IOException {

        SocketChannel sc = SocketChannel.open(); 
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("localhost",8989));

        Selector selector = Selector.open(); 
        sc.register(selector, SelectionKey.OP_CONNECT); 
        Scanner scanner = new Scanner(System.in); 
        while (true) { 
            selector.select(); 
            Set<SelectionKey> keys = selector.selectedKeys(); 
            System.out.println("keys=" + keys.size()); 
            Iterator<SelectionKey> keyIterator = keys.iterator(); 
            while (keyIterator.hasNext()) { 
                SelectionKey key = keyIterator.next(); 

                if (key.isConnectable() && sc.finishConnect()) {
//                    sc.finishConnect();
                    sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE );
                    System.out.println("server connected..."); 
                    break; 
                } else if(key.isReadable()){
                    read(key);
                } else if (key.isWritable()) {
 
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
    
    public static void start2() throws IOException{
    	Socket socket = new Socket();
    	socket.bind(new InetSocketAddress(8001));
    	BufferedOutputStream bo = new BufferedOutputStream(socket.getOutputStream());
    	bo.write( new String("this is client").getBytes());
    }
 
    public static void main(String[] args) throws IOException { 
        new Client().start();
    } 
} 
