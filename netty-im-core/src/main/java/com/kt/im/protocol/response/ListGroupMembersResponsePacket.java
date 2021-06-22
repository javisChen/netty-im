package com.kt.im.protocol.response;


import com.kt.im.protocol.command.Packet;
import com.kt.im.session.Session;
import lombok.Data;

import java.util.List;

import static com.kt.im.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
