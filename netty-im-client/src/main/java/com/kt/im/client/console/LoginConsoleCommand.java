package com.kt.im.client.console;

import com.kt.im.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.println("=====================================");
        System.out.println("================ 登录 ================");
        System.out.print("用户名: ");
        loginRequestPacket.setUsername(scanner.nextLine());
        System.out.print("密码: ");
        loginRequestPacket.setPassword(scanner.nextLine());
        System.out.println("=====================================");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
