package com.kt.im.protocol.response;

import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.kt.im.protocol.command.Command.LOGIN_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
