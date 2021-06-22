package com.kt.im.protocol.request;


import com.kt.im.protocol.command.Packet;
import lombok.Data;

import java.util.List;

import static com.kt.im.protocol.command.Command.CREATE_GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }
}
