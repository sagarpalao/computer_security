import java.io.*;
import java.util.*;

class CipherDecipher{

	
	public static void main(String...args){

		int k,col,row,i,j;
		StringBuffer p;
		String plain;
		StringBuffer ctext1=new StringBuffer();
		StringBuffer ctext2=new StringBuffer();
		StringBuffer ptext1=new StringBuffer();
		StringBuffer ptext2=new StringBuffer();
		Scanner scr=new Scanner(System.in);
		int bogus=0;

		System.out.println("Enter column length: ");
		col=scr.nextInt();
		System.out.println("Enter plain text: ");
		plain=scr.next();
		p=new StringBuffer(plain);

		if(plain.length()%col!=0){
			row=plain.length()/col + 1;
			bogus=col-plain.length()%col;
			for(i=0;i<bogus;i++){
				p.append('z');
			}
		}
		else{
			row=plain.length()/col;
		}

		int key[]=new int[col];
		char trans[][]=new char[row][col];

		System.out.println("Enter key: ");

		for(i=0;i<col;i++){
			key[i]=scr.nextInt();
		}

		k=0;
		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				trans[i][j]=p.charAt(k);
				k++;
			}
		}

		for(i=0;i<col;i++){
			int colno=key[i]-1;
			for(j=0;j<row;j++){
				ctext1.append(trans[j][colno]);
			}
		}

		System.out.println("Cipher at first transposition: "+ctext1);

		k=0;
		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				trans[i][j]=ctext1.charAt(k);
				k++;
			}
		}

		for(i=0;i<col;i++){
			int colno=key[i]-1;
			for(j=0;j<row;j++){
				ctext2.append(trans[j][colno]);
			}
		}

		System.out.println("Cipher at second transposition: "+ctext2);

		k=0;
		for(i=0;i<col;i++){
			int colno=key[i]-1;
			for(j=0;j<row;j++){
				trans[j][colno]=ctext2.charAt(k);
				k++;
			}
		}

		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				ptext1.append(trans[i][j]);
			}
		}

		System.out.println("Plain text at first decryption: "+ptext1);

		k=0;
		for(i=0;i<col;i++){
			int colno=key[i]-1;
			for(j=0;j<row;j++){
				trans[j][colno]=ptext1.charAt(k);
				k++;
			}
		}

		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				ptext2.append(trans[i][j]);
			}
		}

		
		ptext2.delete(ptext2.length()-bogus,ptext2.length());

		System.out.println("Plain text at second decryption: "+ptext2);

	}
}
