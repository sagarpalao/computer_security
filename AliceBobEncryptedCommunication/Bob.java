import java.io.*;
import java.util.*;
import java.net.*;
import java.math.*;

class Bob{

	static int p=199;
	static int g=197;
	
	public static void main(String...args)throws Exception{
		int x= new Double(Math.random()*(p-1)).intValue();
		
		BigInteger r1=new BigInteger(String.valueOf(g)).modPow(new BigInteger(String.valueOf(x)),new BigInteger(String.valueOf(p)));
		Socket s=new Socket(InetAddress.getLocalHost(),8888);
		
		DataOutputStream out=new DataOutputStream(s.getOutputStream());
		out.writeBytes(r1.toString()+"\n");

		BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		BigInteger r2=new BigInteger(in.readLine());
		BigInteger k=r2.modPow(new BigInteger(String.valueOf(x)),new BigInteger(String.valueOf(p)));

		System.out.println("Key: "+k.intValue());

		String c=encrypt("HELLO",k.intValue());

		System.out.println(c);

		
		out.writeBytes(c+"\n");

		s.close();
	}

	public static String encrypt(String p,int k1){
		
		StringBuffer cx=new StringBuffer();
		int i;
		for(i=0;i<p.length();i++){
			char a=p.charAt(i);
			char o=(char)((((a-65)+k1)%26)+65);
			cx.append(o);
		}

		return cx.toString();

	}

}
