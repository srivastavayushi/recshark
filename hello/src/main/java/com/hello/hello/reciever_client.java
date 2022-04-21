package com.hello.hello;
import java.io.*;
import java.net.*;
import java.util.*;




  
// Client class
class reciever_client{
    
    // driver code
    public static void main(String[] args) throws ClassNotFoundException
    {
        // establish a connection by providing host and port
        // number
        try (Socket socket = new Socket("localhost", 3001)) 
        {
            
            // writing to server
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
            ObjectInputStream oos=new ObjectInputStream(socket.getInputStream());
 
  
            // object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;
            int n=0;
            while (!"exit".equalsIgnoreCase(line)) {
                
                // reading from user
            	line = sc.nextLine();
            	
                // sending the user input to server
            	if (!"exit".equalsIgnoreCase(line))
            	{
                out.println(line);
                out.flush();
  
                // displaying server reply
                ArrayList<entryinfo> ans;
                
                    ans=(ArrayList<entryinfo>)oos.readObject();
                    for(int i=0;i<5;i++)
            		{
            			//System.out.println(ans.get(i).timestamp);
                    	System.out.println("hello");
            		}
            	}
                }
                
            
            // closing the scanner object
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}