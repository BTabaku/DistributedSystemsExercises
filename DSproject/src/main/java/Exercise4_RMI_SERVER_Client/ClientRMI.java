/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/12/2020 inside the package - RMI_MODEL_EX4
 **
 */

package Exercise4_RMI_SERVER_Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    public static void main(String[] args) {
        ClientRMI clientRMI = new ClientRMI();
        clientRMI.connectRemote();
    }

    private void connectRemote() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 4444);
            RecieveMessageInteface recieveMessageInteface = (RecieveMessageInteface) reg.lookup("hi_server");
            recieveMessageInteface.receiveMessage("CEN I EPOKA ");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}

