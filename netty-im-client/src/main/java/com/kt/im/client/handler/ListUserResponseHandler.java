package com.kt.im.client.handler;


import com.kt.im.protocol.response.ListUserResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class ListUserResponseHandler extends SimpleChannelInboundHandler<ListUserResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListUserResponsePacket responsePacket) {
        System.out.println("=========== 当前在线用户 =========== ");
        List<String> users = responsePacket.getUsers();
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            System.out.println((i+1) + "." + user);
        }
    }
}
