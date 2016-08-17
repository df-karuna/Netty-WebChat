package org.nettystudy.handler;

import java.util.ArrayList;
import java.util.List;

import org.nettystudy.bean.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;

public class WebChatInboundHandler extends ChannelInboundHandlerAdapter{
	private static final Logger logger = LoggerFactory.getLogger(WebChatInboundHandler.class); // 로깅 퍼사드
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelActive(ctx);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
	}
	
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
	}
	
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelUnregistered(ctx);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/*
		 * {userName : 'minque', message : 'hello', method:'send|accept', roomkey:'12345' }
		 */
		if(msg instanceof Message){
			Message m = (Message)msg;
			
			
			logger.trace("m.userName = {}", m.userName);
			logger.trace("m.message = {}", m.message);
			
//			List<ChannelHandlerContext> ctxlists;
//			...
//			for(ChannelHandlerContext c : ctxlists){
//				ctx.write(m.message);
//				ctx.flush();
//				
//			}
			//Set : 집합, 중복허용을 하지않는 ... 
			ChannelGroup cg = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
			cg.add(ctx.channel());
			cg.writeAndFlush(m.message);
			
		} else {
			super.channelRead(ctx, msg);
		}
	}
	

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
	}
}
