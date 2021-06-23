package com.kt.im.client.console;


import com.kt.im.attribute.Attributes;
import com.kt.im.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToUserCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String message = scanner.next();
        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
        messageRequestPacket.setMessage(message);
        messageRequestPacket.setToUserId(channel.attr(Attributes.CURRENT_USER).get());
        channel.writeAndFlush(messageRequestPacket);

    }
}
