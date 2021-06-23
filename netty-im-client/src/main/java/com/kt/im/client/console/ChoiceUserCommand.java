package com.kt.im.client.console;


import com.kt.im.attribute.Attributes;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ChoiceUserCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入私聊的用户：");
        String toUserId = scanner.next();
        channel.attr(Attributes.CURRENT_USER).set(toUserId);
        System.out.printf("============== 正在和[%s] 聊天==============%n", toUserId);

    }
}
