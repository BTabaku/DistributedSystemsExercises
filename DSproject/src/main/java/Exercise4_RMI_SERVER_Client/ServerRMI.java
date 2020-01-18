/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/12/2020 inside the package - RMI_MODEL_EX4
 **
 */

package Exercise4_RMI_SERVER_Client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI extends UnicastRemoteObject implements RecieveMessageInteface {

    public ServerRMI() throws Exception {
        super();
    }

    @Override
    public void receiveMessage(String x) throws RemoteException {
        System.out.println("Server printing the message: " + x);
    }

    public static void main(String[] args) throws RemoteException {

        Registry reg = LocateRegistry.createRegistry(4444);
        try {
            reg.rebind("hi_server", new ServerRMI());
            System.out.println("Server is ready..");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception"+e);
        }

    }
}

