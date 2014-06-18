package nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Future;

public class Main {
	
	public static void read( BufferedInputStream bin , byte[] buffer ) throws IOException{
		String content;
//		while( bin.read(buffer) != -1 ){
			bin.read(buffer);
			content = new String(buffer);
			System.out.println( content );
//		}
		
	}
	
	public static String getReponseContent(){
		
		String content ="<html><body><div style='background:#FFCC80'>sdfasdfasdfasdf</div></body></html>";
		return content;
	}
	
	/**
	 * @author liukunyang
	 * @date 2013-9-2	
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] buffer = new  byte[500];
		ServerSocket ss = new ServerSocket(80);
//		while(true){
			Socket socket = ss.accept();
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
			Main.read(bin, buffer);
			
			BufferedOutputStream bon = new BufferedOutputStream(socket.getOutputStream());
			bon.write( Main.getReponseContent().getBytes("UTF-8") );
			bon.flush();
			bon.close();
//		}
		
		
	}

}
