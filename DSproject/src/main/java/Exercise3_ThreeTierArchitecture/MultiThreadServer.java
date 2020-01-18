/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - Exercise3_3TierArchitecture
 **
 */

package Exercise3_ThreeTierArchitecture;

import org.omg.CORBA.portable.UnknownException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
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
                clientThread.run();

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
            PrintWriter printOut = new PrintWriter(socket.getOutputStream(), true);
            //ServerClass writing to the client
            printOut.println("Hello ClientTier!!!");
            BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //read from client
            String inputFromClient = inputBufferedReader.readLine();
            System.out.println("ClientClass Says: "+inputFromClient);

            //Here it should contact the database class
            checkUser(inputFromClient);

            //closing services after finishing
            inputBufferedReader.close();
            printOut.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /*Connect to the database server to check the user information*/
    public void checkUser(String information) {
        System.out.println("Connecting to the Database server");
        try {

            InetAddress serverAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(serverAddress, 9091);//port of database server is different
            PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Printing message of the server
            System.out.println("DATABASE ServerClass says: " + input.readLine());
            //After receiving message , reply a message to the server again, sending our information to database server
            pwOut.println(information);
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
