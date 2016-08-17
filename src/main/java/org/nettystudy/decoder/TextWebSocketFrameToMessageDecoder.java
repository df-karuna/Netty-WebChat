package org.nettystudy.decoder;

import java.util.List;

import org.nettystudy.bean.Message;

import com.google.gson.Gson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextWebSocketFrameToMessageDecoder extends MessageToMessageDecoder<TextWebSocketFrame>{

	@Override
	protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg,
			List<Object> out) throws Exception {
		String text = msg.text();
		
		Gson g = new Gson();
		Message decoded = g.fromJson(text, Message.class);
		
		out.add(decoded);
	}
}
