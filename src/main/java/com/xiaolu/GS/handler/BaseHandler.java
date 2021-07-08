package com.xiaolu.GS.handler;

import com.xiaolu.GS.bean.BaseBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class BaseHandler extends SimpleChannelInboundHandler<BaseBean<String>> {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseBean<String> stringBaseBean) throws Exception {
        System.out.println(stringBaseBean);
    }
}
