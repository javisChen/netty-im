package com.kt.im.protocol.response;


import com.kt.im.protocol.command.Packet;
import lombok.Data;

import static com.kt.im.protocol.command.Command.JOIN_GROUP_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_RESPONSE;
    }
}
