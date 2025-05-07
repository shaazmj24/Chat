package model;

import java.net.*;
import java.io.*; 

 
// client side 
public class Client extends User {   
    private Socket s;    
    private BufferedReader in; 
    private PrintWriter out; 
    private BufferedReader inputC; 

    // bulding connection like wire b/w client and server 
    public Client(String n, String ip, int port) { 
        super(n);
        try {  
            // connect to server on port no. 
            s = new Socket(ip, port);     
            System.out.println("Connected!");

            // read client message
            inputC = new BufferedReader(new InputStreamReader(System.in)); 
             
            // Take input from terminal (server's messsage only). s.getInputStream like a pipe and 
            // open mailbox and return raw data (binary) 
            // inputStreamerReader is like a trasnlator; decode bytes to meaningful string
            in = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            // connect output stream to the socket. think of it like wire 
            // printWriter let you send text to server intead of having to write raw data like 
            // a robot 
            out = new PrintWriter(s.getOutputStream(), true); 

            String line = " "; 
            while (!line.equals("bye")) { 
                line = inputC.readLine(); 
                out.println(line);
                System.out.println(in.readLine());
            }

            s.close(); // clear resources. help server know client is disconnected
            inputC.close(); 
            out.close(); 
        } catch(UnknownHostException e) {  
            System.out.println("Host Unknown: " + e.getMessage());
        } catch (IOException e) { 
            System.out.println("Unexpected exception: " + e.getMessage());
        } 



    }


}
