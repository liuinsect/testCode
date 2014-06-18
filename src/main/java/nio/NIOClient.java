package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NIOClient {

	
	/**
	 * @author liukunyang
	 * @date 2013-9-27	
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket;
		BufferedReader in;
		PrintWriter out;
		socket = new Socket("127.0.0.1", 80);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
		out.println(line.readLine());
		line.close();
		out.close();
		in.close();
		socket.close();
	}

}
