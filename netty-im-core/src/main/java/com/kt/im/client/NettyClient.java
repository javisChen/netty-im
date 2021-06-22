package com.kt.im.client;

import com.kt.im.client.console.ConsoleCommandManager;
import com.kt.im.client.console.LoginConsoleCommand;
import com.kt.im.client.handler.*;
import com.kt.im.codec.PacketDecoder;
import com.kt.im.codec.PacketEncoder;
import com.kt.im.codec.Spliter;
import com.kt.im.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                // 1.指定线程模型
                .group(group)
                // option() 方法可以给连接设置一些 TCP 底层相关的属性
                // 表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                // 表示是否开启 TCP 底层心跳机制，true 为开启
                .option(ChannelOption.SO_KEEPALIVE, true)
                // 表示是否开始 Nagle 算法，true 表示关闭，false 表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，
                // 就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启
                .option(ChannelOption.TCP_NODELAY, true)
                // 指定IO类型
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline()
                                .addLast(new Spliter())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginResponseHandler())
                                .addLast(new MessageResponseHandler())
                                .addLast(new CreateGroupResponseHandler())
                                .addLast(new JoinGroupResponseHandler())
                                .addLast(new ListGroupMembersResponseHandler())
                                .addLast(new GroupUserNotifyResponseHandler())

                                .addLast(new PacketEncoder());
                    }
                });

        bootstrap.connect("127.0.0.1", 8000)
                .addListener(channelFuture -> {
                    if (channelFuture.isSuccess()) {
                        System.out.println("连接成功");
                        Channel channel = ((ChannelFuture) channelFuture).channel();
                        // 连接成功之后，启动控制台线程
                        startConsoleThread(channel);
                    } else {
                        System.out.println("连接失败");
                    }
                });
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }
}