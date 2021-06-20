package com.kt.im.protocol.response;

import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.kt.im.protocol.command.Command.MESSAGE_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
