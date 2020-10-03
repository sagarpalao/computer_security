import java.util.*;

import java.security.*;

import java.net.*;

import java.io.*;

class AS{

public static void main(String args[]) throws Exception{



ServerSocket socket = new ServerSocket(6789);

Socket clientSocket = socket.accept();

DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


String msg = in.readLine();

String msgs[] = msg.split("@@@@");

System.out.println("Username Received: "+msgs[0]);

System.out.println("Password Received: "+msgs[1]);

if(msgs[0].equals("dangerzone") && msgs[1].equals("gameboy")){
	out.write("VALID@@@@113578888".getBytes());
}
else{
	out.write("INVALID@@@@INVALID".getBytes());

}
clientSocket.close();

}



}
