import java.io.*;
import java.util.*;
import java.net.*;
import java.math.*;

class Alice{

	static int p=199;
	static int g=197;
	
	public static void main(String...args) throws Exception{
		int y= new Double(Math.random()*(p-1)).intValue();
		
		BigInteger r2=new BigInteger(String.valueOf(g)).modPow(new BigInteger(String.valueOf(y)),new BigInteger(String.valueOf(p)));
		ServerSocket ss=new ServerSocket(8888);
		Socket s=ss.accept();
		
		BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		BigInteger r1=new BigInteger(in.readLine());

		DataOutputStream out=new DataOutputStream(s.getOutputStream());
		out.writeBytes(r2.toString()+"\n");

		BigInteger k=r1.modPow(new BigInteger(String.valueOf(y)),new BigInteger(String.valueOf(p)));


		System.out.println("Key: "+k);

		String c=in.readLine();

		System.out.println(c);

		String p=decrypt(c,k.intValue());

		System.out.println(p);

		s.close();
	}

	public static String decrypt(String c,int k1){

		StringBuffer cx=new StringBuffer();
		int i;char o;
		
		for(i=0;i<c.length();i++){
			char a=c.charAt(i);
			if(a-65-k1>=0)
				o=(char)((((a-65)-k1)%26)+65);
			else
				o=(char)(26-(Math.abs(a-65-k1)%26)+65);
			cx.append(o);		
		}

		return cx.toString();

	}
}
