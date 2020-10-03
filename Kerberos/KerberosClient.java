import java.util.*;
import java.security.*;
import java.net.*;
import java.io.*;

class KerberosClient{
	
     public static void main(String args[]) throws Exception{
		
        Scanner scr=new Scanner(System.in);
        String fromTGS="";
  		Socket clientSocket = new Socket(InetAddress.getLocalHost(), 6789);
  		DataOutputStream outToServerAS = new DataOutputStream(clientSocket.getOutputStream());
  		BufferedReader inFromServerAS = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.print("Enter userid: ");
        String userid=scr.next();
        System.out.print("Enter password: ");
        String pass=scr.next();

        String cred=userid+"@@@@"+pass+"\n";

		outToServerAS.write(cred.getBytes());
  		
        String fromAS = inFromServerAS.readLine();
  		System.out.println("FROM SERVER AS: " + fromAS);

  		clientSocket.close();

        Socket clientSocket2 = new Socket(InetAddress.getLocalHost(), 6790);
        DataOutputStream outToServerTGS = new DataOutputStream(clientSocket2.getOutputStream());
        BufferedReader inFromServerTGS = new BufferedReader(new InputStreamReader(clientSocket2.getInputStream()));

        String ticket[] = fromAS.split("@@@@");
        if(ticket[0].equals("VALID")){
            
            cred = ticket[1] + "@@@@" + userid + "@@@@" + "nirbhay" + "\n"; 
            outToServerTGS.write(cred.getBytes());

            fromTGS = inFromServerTGS.readLine();
            System.out.println("FROM SERVER TGS: " + fromTGS);

        }

        clientSocket2.close();

        Socket clientSocket3 = new Socket(InetAddress.getLocalHost(), 6791);
        DataOutputStream outToServerSS = new DataOutputStream(clientSocket3.getOutputStream());
        BufferedReader inFromServerSS = new BufferedReader(new InputStreamReader(clientSocket3.getInputStream()));

        ticket = fromTGS.split("@@@@");
        if(ticket[0].equals("VALID")){
            
            cred = ticket[1] + "@@@@" + userid + "\n"; 
            outToServerSS.write(cred.getBytes());

            String fromSS = inFromServerSS.readLine();
            System.out.println("FROM SERVER SS: " + fromSS);

        }
    
    }
}
