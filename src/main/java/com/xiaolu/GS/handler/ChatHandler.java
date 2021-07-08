package com.xiaolu.GS.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

public class ChatHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    // 用于记录和管理所有的客户端的channel
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        // 获取客户端传输过来的消息
        String content = msg.toString();
        System.out.println("接收到数据：" + content);

        for (Channel channel : clients) {
            channel.writeAndFlush(new TextWebSocketFrame("服务端在[" + LocalDateTime.now() + "]接收到" +
                    ctx.channel().id().asShortText() + "消息，消息为：" + content));
        }
//        msg.release();
    }


    /**
     * 当客户端连接服务端之后（打开链接）
     * 获取客户端的channel，并且放到channelGroup中去管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开，channel对应的长ID为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，channel对应的短ID为：" + ctx.channel().id().asShortText());
    }

}