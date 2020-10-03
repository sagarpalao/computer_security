import java.util.*;
import java.security.*;
import java.net.*;
import java.io.*;


class Server{
	public static void main(String args[]) throws Exception{
		
		ServerSocket socket = new ServerSocket(6789);
		Socket clientSocket = socket.accept();
  		
		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
  		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  		
		String msg = in.readLine();
		
		String msgs[] = msg.split("@@@@");
			
		System.out.println("Message Received: "+msgs[0]);
		System.out.println("Signature Received: "+msgs[1]);
		
		msgs[0] = decrypt(msgs[0],3);
		
		System.out.println("\n\nDecrypted Message: "+msgs[0]);
		
		
		byte[] data = msgs[0].getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(data);
		
		String digest = new String(thedigest);
		System.out.println("Computed Signature: " + digest);
		
		int i;
		
		char[] x = digest.toCharArray();
		char[] y = msgs[1].toCharArray();
		
		for(i=0;i<x.length;i++){
			if(x[i] != y[i]){
				out.write("ack\n".getBytes());
				System.exit(0);
			}
		}
		out.write("ack\n".getBytes());

  		clientSocket.close();
    	}

        public static String decrypt(String x, int k1)throws Exception{
			StringBuffer cx=new StringBuffer();
			int i;
			for(i=0;i<x.length();i++){
				char a=x.charAt(i);
				char o=(char)(a - k1);
				cx.append(o);
			}
			String c=cx.toString();
			return c;
		}
}
