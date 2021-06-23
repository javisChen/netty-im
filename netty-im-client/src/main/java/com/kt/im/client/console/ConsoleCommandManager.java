package com.kt.im.client.console;


import com.kt.im.attribute.Attributes;
import com.kt.im.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private Map<Integer, ConsoleCommand> consoleCommandMap;
    private Map<Integer, ConsoleCommand> privateChatCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put(1, new GetOnlineUsersCommand());
        consoleCommandMap.put(2, new ChoiceUserCommand());
//        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put(3, new CreateGroupConsoleCommand());
        consoleCommandMap.put(5, new JoinGroupConsoleCommand());

    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        String attr = channel.attr(Attributes.CURRENT_USER).get();
        if (attr != null) {
            new SendToUserCommand().exec(scanner, channel);
            return;
        } else {
            System.out.println("============== 聊天室 ==============");
            System.out.println("1.查看用户列表");
            System.out.println("2.私聊");
            System.out.println("3.创建群");
            System.out.println("4.查看群聊");
            System.out.println("5.加入群聊");
            System.out.println("0.退出聊天室");
            System.out.println("===================================");
        }
        //  获取第一个指令
        String command = scanner.next();

        if (!SessionUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(Integer.parseInt(command));

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
