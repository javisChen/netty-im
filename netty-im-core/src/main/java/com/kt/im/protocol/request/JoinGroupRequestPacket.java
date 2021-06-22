package com.kt.im.protocol.request;


import com.kt.im.protocol.command.Packet;
import lombok.Data;

import static com.kt.im.protocol.command.Command.JOIN_GROUP_REQUEST;

@Data
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return JOIN_GROUP_REQUEST;
    }
}
