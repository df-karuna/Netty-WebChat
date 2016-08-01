package org.nettystudy.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class WebChatHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		if(msg instanceof String){
			
			ctx.write(msg);
			ctx.flush();
		} else {
			super.channelRead(ctx, msg);
		}
	}

}
