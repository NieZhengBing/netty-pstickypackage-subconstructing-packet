package com.nzb.netty.pstickypackage_subconstructing_packet.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class MyDecoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
		if (buffer.readableBytes() > 4) {
			if (buffer.readableBytes() > 2048) {
				buffer.skipBytes(buffer.readableBytes());
			}

			buffer.markReaderIndex();

			int length = buffer.readInt();

			if (buffer.readableBytes() < length) {
				buffer.resetReaderIndex();
				return null;
			}

			byte[] bytes = new byte[length];
			buffer.readBytes(bytes);
			return new String(bytes);
		}
		return null;
	}

}
