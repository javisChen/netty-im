package com.kt.im.protocol.request;

import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.kt.im.protocol.command.Command.LIST_USER_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListUserRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LIST_USER_REQUEST;
    }
}
