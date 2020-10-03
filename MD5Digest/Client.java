import java.util.*;
import java.security.*;
import java.net.*;
import java.io.*;

class Client{
	public static void main(String args[]) throws Exception{
		
		System.out.print("Enter Message to send: ");
        Scanner scr=new Scanner(System.in);
		
  		Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6789);  		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
  		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  		
		String msg = scr.next();
		
  		byte[] bytesOfMessage = msg.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		String digest = new String(thedigest);
		
		System.out.println("Message: "+msg);
		System.out.println("Signature: "+digest);
		
		msg = encrypt(msg,3);
		
		System.out.println("\n\nEncrypted Message: "+msg);
		System.out.println("Signature: "+digest);
		
		String toSend = msg + "@@@@" + digest + "\n";
		
		outToServer.write((toSend + "\n").getBytes());
  		String modifiedSentence = inFromServer.readLine();
  		System.out.println("\n\nFrom Server: " + modifiedSentence);
  		clientSocket.close();
    	
	}
	public static String encrypt(String x, int k1)throws Exception{
			StringBuffer cx=new StringBuffer();
			int i;
			for(i=0;i<x.length();i++){
				char a=x.charAt(i);
				char o=(char)(a + k1);
				cx.append(o);
			}
			String c=cx.toString();
			return c;
	}

}
