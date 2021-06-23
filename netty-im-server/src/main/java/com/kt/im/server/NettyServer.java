package com.kt.im.server;

import com.kt.im.codec.Spliter;
import com.kt.im.server.handler.PacketCodecHandler;
import com.kt.im.server.handler.auth.AuthHandler;
import com.kt.im.server.handler.auth.LoginRequestHandler;
import com.kt.im.server.handler.group.CreateGroupRequestHandler;
import com.kt.im.server.handler.group.JoinGroupRequestHandler;
import com.kt.im.server.handler.group.ListGroupMembersRequestHandler;
import com.kt.im.server.handler.single.SingleMessageRequestHandler;
import com.kt.im.server.handler.user.ListUserRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 接收线程
        NioEventLoopGroup boss = new NioEventLoopGroup();

        //处理线程
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                // 指定io模型
                .channel(NioServerSocketChannel.class)
                // 方法可以给服务端的 channel，也就是NioServerSocketChannel指定一些自定义属性,通常情况下用不上
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 用于指定在服务端启动过程中的一些逻辑，通常情况下用不到
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        System.out.println("服务端启动中");
                    }
                })
                // childOption()可以给每条连接设置一些TCP底层相关的属性
                // 表示是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 定义每条连接的数据读写，业务逻辑处理
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline()
                                // in
                                .addLast(new Spliter())
                                .addLast(PacketCodecHandler.INSTANCE)
                                .addLast(LoginRequestHandler.INSTANCE)
                                .addLast(AuthHandler.INSTANCE)
                                .addLast(SingleMessageRequestHandler.INSTANCE)
                                .addLast(CreateGroupRequestHandler.INSTANCE)
                                .addLast(JoinGroupRequestHandler.INSTANCE)
                                .addLast(ListGroupMembersRequestHandler.INSTANCE)
                                .addLast(ListUserRequestHandler.INSTANCE);
                    }
                })
                .bind(8000);
    }
}
