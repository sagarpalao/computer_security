import java.util.*;

import java.security.*;

import java.net.*;

import java.io.*;

class TGS{

public static void main(String args[]) throws Exception{

ServerSocket socket = new ServerSocket(6790);

Socket clientSocket = socket.accept();

DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

String msg = in.readLine();

String msgs[] = msg.split("@@@@");

System.out.println("Ticket Recieved Received: "+msgs[0]);

System.out.println("Requested from "+msgs[1]);

System.out.println("User to be Connected: "+msgs[2]);



if(msgs[0].equals("113578888")){
	out.write("VALID@@@@99786".getBytes());
}
else{
	out.write("INVALID@@@@INVALID".getBytes());

}
clientSocket.close();

}



}
