package com.andy.net;

/**
 * @author HP
 * @Description TODO
 * @date 2020/9/20-21:40
 */
public abstract class Msg {

    public abstract void handle();
    public abstract byte[] toBytes();
    public abstract void parse(byte[] bytes);
    public abstract MsgType getMsgType();
}
