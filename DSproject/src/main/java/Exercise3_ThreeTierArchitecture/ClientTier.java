/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - ThreeTierArchitecture
 **
 */

package Exercise3_ThreeTierArchitecture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTier {
    public static void main(String[] args) {
        try {

            InetAddress serverAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(serverAddress, 9090);

            PrintWriter pwOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Printing message of the server
            System.out.println("ServerClass says: " + input.readLine());
            //After receiving message , reply a message to the server again
            pwOut.println("Epoka");

            input.close();
            pwOut.close();
            socket.close();
        } catch (Exception e4) {
            System.out.println("Other exceptions " + e4.toString());
        }
    }

}

