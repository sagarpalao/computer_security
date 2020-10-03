import java.io.*;
import java.util.*;

class AdditiveMultiplicativeEncryption{

	static String p;
	static String c;
	static int k1,k2,ik1,ik2;
	public static void main(String...args){
		
		Scanner scr=new Scanner(System.in);
		System.out.println("Enter string:");
		p=scr.next();
		System.out.println("Enter keys [Additive Multiplicative]");
		k1=scr.nextInt();
		k2=scr.nextInt();

		computeInverse();
		System.out.println("Inverse of keys: \nAdditive: "+ik1+"  Multiplicative: "+ik2);

		encrypt();
		System.out.println("Encrypted Text: "+c);

		decrypt();
	}

	public static void computeInverse(){
		ik1=0-k1;

		int r1=26,r2=k2,q=0,r=0,t1=0,t2=1,t;
		do{
			q=r1/r2;
			r=r1%r2;
			t=t1-q*t2;
			r1=r2;
			r2=r;
			t1=t2;
			t2=t;
		}while(r2!=0);
		if(r1==1){
			if(t1>0)
				ik2=t1;
			else
				ik2=26+t1;
		}
		else{
			System.out.println("Inverse of "+ik2+" doesn't exists.");
			System.exit(0);
		}
	}

	public static void encrypt(){
		
		StringBuffer cx=new StringBuffer();
		int i;
		for(i=0;i<p.length();i++){
			char a=p.charAt(i);
			char o=(char)(((((a-65)*k2)+k1)%26)+65);
			cx.append(o);
		}

		c=cx.toString();

	}

	public static void decrypt(){

		StringBuffer cx=new StringBuffer();
		int i;char o;
		
		for(i=0;i<c.length();i++){
			char a=c.charAt(i);
			
			if((((a-65)+ik1)*ik2)>=0)
				o=(char)(((((a-65)+ik1)*ik2)%26)+65);
			else{
				int x=(((a-65)+ik1)*ik2)%26;
				o=(char)(26+x+65);
			}
			cx.append(o);
		}

		System.out.println("Decrypted Text: "+cx.toString());

	}
}
