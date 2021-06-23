package com.kt.im.client.console;

import com.kt.im.protocol.request.ListUserRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class GetOnlineUsersCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        channel.writeAndFlush(new ListUserRequestPacket());
    }
}
