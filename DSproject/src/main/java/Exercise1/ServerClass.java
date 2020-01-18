/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - Exercise1
 **
 */

package Exercise1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {

    public static void main(String[] args) throws Exception {
        //Creating a server socket that will wait for clients
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("ServerClass Started, waiting for clients...");
        //Accepting the messages from the server, 'serverSocket.accept()'
        // will block the execution until a client gets connected
        //Once the connection gets established the .accept method will return
        //an socket object, and the program will continue execution
        Socket socket = serverSocket.accept();
        //Sending message to the client
        PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
        pwOut.println("Hello client");
        //reading from the client
        BufferedReader bfrInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientInput = bfrInput.readLine();
        System.out.println(clientInput);
        //closing stream and socket
        bfrInput.close();
        pwOut.close();
        socket.close();

    }

}

