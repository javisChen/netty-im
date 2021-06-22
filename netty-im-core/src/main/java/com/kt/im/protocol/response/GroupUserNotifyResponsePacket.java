package com.kt.im.protocol.response;


import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.kt.im.protocol.command.Command.GROUP_USER_NOTIFY_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserNotifyResponsePacket extends Packet {

    private String userId;

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return GROUP_USER_NOTIFY_RESPONSE;
    }
}
