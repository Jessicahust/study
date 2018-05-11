package com.cainiao.work.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author wuchanming
 * @date 18/5/6
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //创建一个ServerBootstrap，下面开始配置
            ServerBootstrap b = new ServerBootstrap();
            //创建了两个EventLoopGroup(线程池和selector，reactor模型)一个main loop一个child loop通过ServerBoootstrap的group方法组合起来
            //接着通过option方法传递服务端NioServerSocketChannel(服务端套接字)设置它的backlog参数。
            //再通过handler设置服务端socket（ServerSocketChannel）的处理事件的handler是记录日志logger
            //最后childhandler设置每一个连接到服务端的socket(socketchannel)的handler(childhandler)，是创建一个EchoServerHandler类去处理。（利用channelhandler我们可以完成功能定制）
            //channelpipeline负责管理和执行channelhandler，可以向channelpipeline中add添加channelhandler
            //（注意ServerBootstrap部分方法来自父类）
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        //abstractBoostStrap中的Handler是个工厂类，它为每个新接入的客户端都创建一个新的Handler
                        //下面代码每新连接一个socket,都会创建一个EchoServerHandler
                        System.out.println("hello");
                        ch.pipeline().addLast(new EchoServerHandler());
                        //ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
            //绑定端口，netty中所有IO操作都是异步的，它会立即返回，但不能保证完成操作
            ChannelFuture f = b.bind(port).sync();
            System.out.println("bind....");
            f.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws Exception {
        int port;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }else{
            port = 10000;
        }
        new EchoServer(port).run();
    }
}
