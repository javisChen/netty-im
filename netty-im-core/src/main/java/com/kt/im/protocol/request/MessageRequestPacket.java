package com.kt.im.protocol.request;


import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.kt.im.protocol.command.Command.MESSAGE_REQUEST;

@Data
@NoArgsConstructor

public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
