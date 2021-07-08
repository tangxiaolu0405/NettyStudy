package com.xiaolu.GS.decoder;

import com.xiaolu.GS.bean.BaseBean;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class BaseDecoder extends ByteToMessageCodec<BaseBean<String>> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byte[] bytes = new byte[1024];
        byteBuf.readBytes(bytes);
        System.out.println(new String(bytes));
        list.add(byteBuf);
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseBean<String> stringBaseBean, ByteBuf byteBuf) throws Exception {

    }
}
