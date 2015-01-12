//package netty;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//import io.netty.util.CharsetUtil;
//
///**
// * Created with IntelliJ IDEA.
// * User: liukunyang
// * Date: 14-3-31
// * Time: 下午1:31
// * To change this template use File | Settings | File Templates.
// */
//public class TimeClient {
//    public static void main(String[] args) throws Exception {
//        String host = "127.0.0.1";
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            Bootstrap b = new Bootstrap(); // (1)
//            b.group(workerGroup); // (2)
//            b.channel(NioSocketChannel.class); // (3)
//            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
//            b.handler(new ChannelInitializer<SocketChannel>() {
//                @Override
//                public void initChannel(SocketChannel ch) throws Exception {
//                    ChannelPipeline pipeline = ch.pipeline();
//                    pipeline.addLast(new TimeClientHandler());
//                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
//
//                }
//            });
//
//            // Start the client.
//            ChannelFuture f = b.connect(host, 8999).sync(); // (5)
//
////            f.channel().write("hello this is client");
//            // Wait until the connection is closed.
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//        }
//    }
//}