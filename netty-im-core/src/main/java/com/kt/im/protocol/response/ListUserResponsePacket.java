package com.kt.im.protocol.response;

import com.kt.im.protocol.command.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import static com.kt.im.protocol.command.Command.LIST_USER_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListUserResponsePacket extends Packet {

    private List<String> users;

    @Override
    public Byte getCommand() {
        return LIST_USER_RESPONSE;
    }
}
