import java.util.*;

import java.security.*;

import java.net.*;

import java.io.*;

class BOB{

public static void main(String args[]) throws Exception{

ServerSocket socket = new ServerSocket(6791);

Socket clientSocket = socket.accept();

DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

String msg = in.readLine();

String msgs[] = msg.split("@@@@");
System.out.println(msg);

if(msgs[0].equals("99786")){
	out.write((msgs[1]+" granted access\n").getBytes());
}
else{
	out.write("INVALID@@@@INVALID".getBytes());

}
clientSocket.close();

}



}
