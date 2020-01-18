/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - Exercise1
 **
 */

package Exercise1;

import org.omg.CORBA.portable.UnknownException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientClass {

    public static void main(String[] args) {
        try {
            //We assign a socked of server and an port no. with ipd address
            //in this case 'localhost' ip address
            //Passing the ip address of the server as the first parameter
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(serverAddress, 9090);

            //input buffer reader for messages between client and server
            //In this case client is waiting for the message from the server
            //after sending the request connection
            PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Printing message of the server
            System.out.println("ServerClass says: "+input.readLine());
            //After receiving message , reply a message to the server again
            pwOut.println("ClientTier Says: Hello ServerClass!");
            //closing all the services after finishing
            input.close();
            pwOut.close();
            socket.close();

            //avoiding errors when creating the sockets
        } catch (UnknownException e1) {
            System.out.println("Unknown host exception " + e1.toString());
        } catch (IllegalArgumentException e3) {
            System.out.println("Illegal Argument Exception " + e3.toString());
        } catch (IOException e2) {
            System.out.println("IOException " + e2.toString());
        } catch (Exception e4) {
            System.out.println("Other exceptions " + e4.toString());
        }
    }
}

