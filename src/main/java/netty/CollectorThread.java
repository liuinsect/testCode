package netty;

import io.netty.channel.ChannelHandlerAdapter;

/**
 * Created by liukunyang on 2015/10/28.
 */
public class CollectorThread implements Runnable {

    private ChannelHandlerAdapter channelHandlerAdapter = new ChannelHandlerAdapter();


    public void run() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("sleep over");


    }
}
