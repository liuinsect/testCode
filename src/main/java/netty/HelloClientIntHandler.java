package netty;

/**
 * Created by liukunyang on 2015/10/28.
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class HelloClientIntHandler extends ChannelHandlerAdapter {

    // ����server�˵���Ϣ������ӡ����
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1));
        result.release();


    }

    // ���ӳɹ�����server������Ϣ
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "Are you ok?";
        ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
        encoded.writeBytes(msg.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}

