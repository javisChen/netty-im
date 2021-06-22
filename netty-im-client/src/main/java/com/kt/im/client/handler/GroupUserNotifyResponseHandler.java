package com.kt.im.client.handler;

import com.kt.im.protocol.response.GroupUserNotifyResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupUserNotifyResponseHandler extends SimpleChannelInboundHandler<GroupUserNotifyResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupUserNotifyResponsePacket groupUserNotifyResponsePacket) {
        System.out.print(groupUserNotifyResponsePacket.getUserId() + "已加入群" + groupUserNotifyResponsePacket.getGroupId());
    }
}
