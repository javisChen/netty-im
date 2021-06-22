package com.kt.im.protocol.request;


import com.kt.im.protocol.command.Packet;
import lombok.Data;

import static com.kt.im.protocol.command.Command.QUIT_GROUP_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }
}
