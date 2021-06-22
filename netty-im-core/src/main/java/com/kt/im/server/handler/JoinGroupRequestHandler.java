package com.kt.im.server.handler;


import com.kt.im.protocol.request.JoinGroupRequestPacket;
import com.kt.im.protocol.response.GroupUserNotifyResponsePacket;
import com.kt.im.protocol.response.JoinGroupResponsePacket;
import com.kt.im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 通知其他客户端当前用户已加入群聊
        GroupUserNotifyResponsePacket notifyResponsePacket = new GroupUserNotifyResponsePacket();
        notifyResponsePacket.setSuccess(true);
        notifyResponsePacket.setGroupId(groupId);
        notifyResponsePacket.setUserId(SessionUtil.getSession(ctx.channel()).getUserName());
        notifyResponsePacket.setReason("有新用户加入群聊");
        channelGroup.writeAndFlush(notifyResponsePacket);


        channelGroup.add(ctx.channel());
        // 3. 构造加群响应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
