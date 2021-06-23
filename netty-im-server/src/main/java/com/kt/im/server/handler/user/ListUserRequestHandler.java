package com.kt.im.server.handler.user;

import com.kt.im.protocol.request.ListUserRequestPacket;
import com.kt.im.protocol.response.ListUserResponsePacket;
import com.kt.im.session.Session;
import com.kt.im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ChannelHandler.Sharable
public class ListUserRequestHandler extends SimpleChannelInboundHandler<ListUserRequestPacket> {

    public static ListUserRequestHandler INSTANCE = new ListUserRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListUserRequestPacket messageRequestPacket) {

        Map<String, Channel> users = SessionUtil.getOnlineUsers();

        ListUserResponsePacket responsePacket = new ListUserResponsePacket();
        List<String> list = new ArrayList<>();
        responsePacket.setUsers(list);
        users.forEach((key, value) -> {
            Session session = SessionUtil.getSession(SessionUtil.getChannel(key));
            list.add(session.getUserName() + "[" + session.getUserId() + "]");
        });

        ctx.writeAndFlush(responsePacket);

    }
}
