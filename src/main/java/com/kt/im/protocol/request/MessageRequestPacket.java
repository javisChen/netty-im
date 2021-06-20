package com.kt.im.protocol.request;


import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.kt.im.protocol.command.Command.MESSAGE_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
