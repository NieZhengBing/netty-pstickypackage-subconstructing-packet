package com.nzb.netty.pstickypackage_subconstructing_packet.server;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class Server {
	public static void main(String... args) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		ExecutorService boss = Executors.newCachedThreadPool();
		ExecutorService worker = Executors.newCachedThreadPool();

		bootstrap.setFactory(new NioServerSocketChannelFactory(boss, worker));

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new MyDecoder());
				pipeline.addLast("handler1", new HelloHandler());
				return pipeline;
			}
		});

		bootstrap.bind(new InetSocketAddress(10101));

		System.out.println("start!!!");
	}

}
