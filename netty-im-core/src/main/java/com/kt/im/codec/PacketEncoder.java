package com.kt.im.codec;


import com.kt.im.protocol.command.Packet;
import com.kt.im.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
