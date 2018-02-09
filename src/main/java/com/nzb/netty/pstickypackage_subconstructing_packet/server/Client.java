package com.nzb.netty.pstickypackage_subconstructing_packet.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Client {

	public static void main(String... args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 10101);

		String message = "hello";

		byte[] bytes = message.getBytes();

		ByteBuffer buffer = ByteBuffer.allocate(4 + bytes.length);
		buffer.putInt(bytes.length);
		buffer.put(bytes);

		byte[] array = buffer.array();

		for (int i = 0; i < 1000; i++) {
			socket.getOutputStream().write(array);
		}

		socket.close();
	}

}
