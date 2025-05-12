package model;

import java.net.*;
import java.io.*; 
import java.util.ArrayList; 
import java.util.List; 

// server side 
public class Server extends User { 
    private ServerSocket ss;  
    private ArrayList<ClientHandler> clients; 

    public Server(String name, int port) {
        super(name);       
       try {  
        ss = new ServerSocket(port); // starts server and waits for connection 
       } catch (IOException e) { 
       }
        System.out.println("Server started");  
        System.out.println("Waiting for connection..."); // pause for connection  
            
        while (true) {   
            try {  
                Socket clientS = ss.accept();  // like a receptionist and accept client 
                System.out.println("Client connected: " + clientS); 
                ClientHandler clientT = new ClientHandler(clientS, clients);   
                clients.add(clientT);    
                // allow mutiple clients to send message at the same time  
                // otherwise next client will only work if prevous client is disconnected  
                Thread t1 = new Thread(clientT);  
                t1.start();  // Runs clientT.run() in parallel
            } catch (IOException e) {  
            } 
        } 
    }

    private class ClientHandler implements Runnable { 
        private Socket clientS;
        private BufferedReader in; 
        private BufferedReader inputC; 
        private PrintWriter out;   
        private List<ClientHandler> clients; 

        public ClientHandler(Socket cs, List<ClientHandler> clients) {    
            this.clientS = cs; 
            this.clients = clients; 
        }

        @Override 
        public void run() {     
            try {  
                inputC = new BufferedReader(new InputStreamReader(System.in)); 
                in = new BufferedReader(new InputStreamReader(clientS.getInputStream()));    
                out = new PrintWriter(clientS.getOutputStream(), true); 

                String line = ""; 
                while (!line.equals("bye")) { 
                    line = inputC.readLine(); 
                    out.println(line);  // send message to client 
                    System.out.println(in.readLine());
                }
            } catch (IOException e) { 
            }
        }

    }

}



