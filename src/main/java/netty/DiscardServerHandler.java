package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

/**
 * Created with IntelliJ IDEA.
 * User: liukunyang
 * Date: 14-3-31
 * Time: 下午1:27
 * To change this template use File | Settings | File Templates.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }


            ctx.write("hello this is server"); // (1)
            ctx.flush(); // (2)

        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }


    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.write("hello this is server"); // (1)
        ctx.flush(); // (2)
    }

    @Override
    public void channelActive( final  ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(50); // (2)

        time.writeBytes("this is server".getBytes());
        ctx.write(time);
        ctx.flush();
//        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) {
//                assert f == future;
//                ctx.close();
//            }
//        }); // (4)
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
