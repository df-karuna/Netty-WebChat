package org.nettystudy;

import org.nettystudy.handler.WebChatHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ChatChannelInitializer extends ChannelInitializer<SocketChannel> {
	Logger logger = LoggerFactory.getLogger(ChatChannelInitializer.class); //log4j 가 아니라 , logback을 사용. slf4j : facade pattern
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();

		p.addLast(new HttpServerCodec())
				.addLast(new HttpObjectAggregator(65536))
				.addLast( new LoggingHandler(LogLevel.INFO))
				/**
				 * 넘어온 http패킷을 업그레이드를 할거. 
				 */
				.addLast(new WebSocketServerProtocolHandler("/chat")) // http://localhost:9001/chat
				.addLast(new WebChatHandler());
	}
}
