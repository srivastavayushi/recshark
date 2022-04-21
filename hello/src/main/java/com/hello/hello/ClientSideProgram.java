package com.hello.hello;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSideProgram {

  //here we simply initialize socket, input & output streams

  private Socket socket = null;
  private DataInputStream input = null;
  private DataOutputStream output = null;

  //Parameterized constructor for CilentSideProgram
  @SuppressWarnings("deprecation")
public ClientSideProgram(String address, Integer port) {

    //code to establish a connection
    try {
      socket = new Socket(address, port);
      input = new DataInputStream(System.in);

      // sends output to the socket
      output = new DataOutputStream(socket.getOutputStream());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String line = "";
    //below line is to read message from input
    while (!(line.equals("Done"))) {
      try {
        line = input.readLine();
        output.writeUTF(line);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    //below code to close the connection
    try {
      input.close();
      output.close();
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    ClientSideProgram clientProgram = new ClientSideProgram("localhost", 5000);
  }
}