package com.kt.im.protocol.command;

import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    public abstract Byte getCommand();
}