/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - Exercise1
 **
 */

package Exercise1;

import org.omg.CORBA.portable.UnknownException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    public static void main(String[] args) {
        try {
            //We assign a socked of server and an port no. with ipd address
            //in this case 'localhost' ip address
            ServerSocket serverSocket = new ServerSocket(9090);
            while (true) {
                System.out.println("Waiting for clients request...");
                //it accept request from clients
                Socket clientSocket = serverSocket.accept();
                //confirm client connection
                System.out.println("ClientTier is connected successfully!");
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();

            }
            //avoiding errors when creating the sockets
        } catch (Exception ex) {
            System.out.println("Other exceptions " + ex.toString());
        }
    }
}

//client thread class
class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
            //ServerClass writing to the client
            pwOut.println("Hello ClientTier!!!");
            BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //read from client
            String clientInput = inputBufferedReader.readLine();
            System.out.println(clientInput);
            //closing services after finishing
            inputBufferedReader.close();
            pwOut.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}

