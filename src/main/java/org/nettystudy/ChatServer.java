package org.nettystudy;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ChatServer {
	private static Logger logger = LoggerFactory.getLogger(ChatServer.class);
	public static HashMap<String, String> testUsers = null;
	protected static String index = "resources/html/index.html";

	public static AtomicLong aChatDataSeq;
	public static AtomicLong chatDataSeq;
	public static AtomicLong bChatDataSeq;

	public static void main(String[] args) {
		int port = 9001;


		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGruop = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();

			b.group(bossGroup, workerGruop)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.DEBUG))
					.childHandler(new ChatChannelInitializer());

			ChannelFuture f;

			try {
				f = b.bind(port).sync();
				f.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} finally {
			workerGruop.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

}
