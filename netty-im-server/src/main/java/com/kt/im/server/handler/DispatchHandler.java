package com.kt.im.server.handler;

import com.kt.im.protocol.command.Packet;
import com.kt.im.server.handler.group.CreateGroupRequestHandler;
import com.kt.im.server.handler.group.JoinGroupRequestHandler;
import com.kt.im.server.handler.group.ListGroupMembersRequestHandler;
import com.kt.im.server.handler.group.QuitGroupRequestHandler;
import com.kt.im.server.handler.single.SingleMessageRequestHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.kt.im.protocol.command.Command.*;

@ChannelHandler.Sharable
public class DispatchHandler extends SimpleChannelInboundHandler<Packet> {

    public static final DispatchHandler INSTANCE = new DispatchHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private DispatchHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(MESSAGE_REQUEST, SingleMessageRequestHandler.INSTANCE);
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
//        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
//        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(ctx, packet);
    }
}
