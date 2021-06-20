package com.kt.im.protocol.request;

import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.kt.im.protocol.command.Command.LOGIN_REQUEST;


@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }
}