package com.kt.im.protocol.request;


import com.kt.im.protocol.command.Packet;
import lombok.Data;

import static com.kt.im.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

@Data
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
