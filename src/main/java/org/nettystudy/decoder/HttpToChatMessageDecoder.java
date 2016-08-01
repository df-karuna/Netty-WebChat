package org.nettystudy.decoder;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class HttpToChatMessageDecoder extends MessageToMessageDecoder<TextWebSocketFrame>{

	@Override
	protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg,
			List<Object> out) throws Exception {
		String msgText = msg.text();
		
		out.add(msgText);
	}
	
}
