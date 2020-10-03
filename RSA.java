import java.util.*;
import java.math.*;

class KeyPair{
	int e,d;
	KeyPair(int e,int d){
		this.e=e;
		this.d=d;
	}
}

class RSA{
	static int n;
	public static void main(String...args){

		Scanner scr=new Scanner(System.in);
		System.out.print("\n\nEnter p and q: ");
		int p=scr.nextInt();
		int q=scr.nextInt();
		int i;

		KeyPair keys=generateKey(p,q);

		System.out.println("\nComputed: e: "+keys.e+" d:"+keys.d+" n:"+n);

		System.out.println("\nEnter Plain Text: ");
		String ptext=scr.next();
		ArrayList<BigInteger> pint=new ArrayList<BigInteger>();
		ArrayList<BigInteger> cint=new ArrayList<BigInteger>();
		StringBuffer ctext=new StringBuffer();
		
		System.out.println("\n\nPlain Text(Integral): ");
		for(i=0;i<ptext.length();i++){
			pint.add(new BigInteger(String.valueOf((int)ptext.charAt(i))));
			System.out.print(pint.get(i).intValue());
		}
		
		System.out.println("\n\nAfter Encryption\nCipher Text(Integral): ");
		for(i=0;i<ptext.length();i++){
			cint.add(new BigInteger(encrypt(pint.get(i),keys.e)));
			System.out.print(cint.get(i).intValue());
		}
		pint.clear();
		
		System.out.println("\n\nAfter Decryption\nPlain Text(Integral): ");
		for(i=0;i<ptext.length();i++){
			pint.add(new BigInteger(decrypt(cint.get(i),keys.d)));
			System.out.print(pint.get(i).intValue());
		}
		
		System.out.println("\nPlain Text: ");
		for(i=0;i<ptext.length();i++){
			pint.add(new BigInteger(decrypt(cint.get(i),keys.d)));
			System.out.print((char)pint.get(i).intValue());
		}
		System.out.println();
		
	}

	public static KeyPair generateKey(int p,int q){
		int e=0,i,d;
		n=p*q;

		int phin=(p-1)*(q-1);

		for(i=2;i<=phin;i++){
			if(isPrime(i)){
				if(phin%i!=0){
					e=i;
					break;
				}
			}
		}
		
		d=multiplicativeInverse(e,phin);

		KeyPair keys=new KeyPair(e,d);
		return keys;
	}

	public static boolean isPrime(int n){
		for(int i=2;i<n;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}

	public static int multiplicativeInverse(int k2,int phin){

		int r1=phin,r2=k2,q=0,r=0,t1=0,t2=1,t,ik2=0;
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
				ik2=phin+t1;
			return ik2;
		}
		else{
			System.out.println("Inverse of "+ik2+" doesn't exists.");
			System.exit(0);
		}
		return 0;
	}

	static String encrypt(BigInteger plain,int e){
		BigInteger c=plain.modPow(new BigInteger(String.valueOf(e)),new BigInteger(String.valueOf(n)));
		return String.valueOf(c);
	}

	static String decrypt(BigInteger cipher,int d){
		BigInteger p=cipher.modPow(new BigInteger(String.valueOf(d)),new BigInteger(String.valueOf(n)));
		return p.toString();

	}


}
