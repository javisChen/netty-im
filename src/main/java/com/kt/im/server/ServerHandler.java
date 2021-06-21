//package com.kt.im.server;
//
//import com.kt.im.protocol.command.Packet;
//import com.kt.im.protocol.command.PacketCodeC;
//import com.kt.im.protocol.request.LoginRequestPacket;
//import com.kt.im.protocol.request.MessageRequestPacket;
//import com.kt.im.protocol.response.LoginResponsePacket;
//import com.kt.im.protocol.response.MessageResponsePacket;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import lombok.SneakyThrows;
//
//import java.util.Date;
//
///**
// * @author chao.yu
// * chao.yu@dianping.com
// * @date 2018/08/04 06:21.
// */
//public class ServerHandler extends ChannelInboundHandlerAdapter {
//
//    @SneakyThrows
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        System.out.println(new Date() + ": 客户端开始登录……");
//        ByteBuf requestByteBuf = (ByteBuf) msg;
//
//        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
//
//        if (packet instanceof LoginRequestPacket) {
//            // 登录流程
//            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//
//            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//            loginResponsePacket.setVersion(packet.getVersion());
//            if (valid(loginRequestPacket)) {
//                loginResponsePacket.setSuccess(true);
//                System.out.println(new Date() + ": 登录成功!");
//            } else {
//                loginResponsePacket.setReason("账号密码校验失败");
//                loginResponsePacket.setSuccess(false);
//                System.out.println(new Date() + ": 登录失败!");
//            }
//            // 登录响应
//            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        } else if (packet instanceof MessageRequestPacket) {
//            // 处理消息
//            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
//            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//
//            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//            messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
//            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
//            ctx.channel().writeAndFlush(responseByteBuf);
//        }
//    }
//
//    private boolean valid(LoginRequestPacket loginRequestPacket) {
//        return true;
//    }
//}
