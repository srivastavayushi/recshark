package com.hello.hello;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;

public class ServerSideProgram {

  //initialize socket and input stream
  private int no_of_threads=5;
  private Socket socket = null;
  private ServerSocket server = null;
  private DataInputStream in = null;

  // constructor with port
  public ServerSideProgram(int port) throws SQLException {
    // starts server and waits for a connection
    try {
      server = new ServerSocket(port);
      System.out.println("Server started::");

      System.out.println("Waiting for a client ........");

      socket = server.accept();
      System.out.println("Client accepted.");

      // takes input from the client socket
      in = new DataInputStream(
          new BufferedInputStream(socket.getInputStream()));

      String line = "";
ExecutorService executorService= Executors.newFixedThreadPool(no_of_threads);
      // reads message from client until "Done" is sent
      while (!line.equals("Done")) {
        try {
          line = in.readUTF();
          Runnable worker=new mythread(line);
          executorService.execute(worker);

        } catch (IOException i) {
          System.out.println(i);
        }
      }
      while(!executorService.isTerminated()) {}
      System.out.println("Closing connection");

      // close connection
      socket.close();
      in.close();
    } catch (IOException i) {
      System.out.println(i);
    }
  }

  public static void main(String args[]) throws SQLException {
    ServerSideProgram serverSideProgram = new ServerSideProgram(5000);
  }

}