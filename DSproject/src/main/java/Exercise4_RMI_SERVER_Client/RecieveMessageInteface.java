/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/12/2020 inside the package - RMI_MODEL_EX4
 **
 */

package Exercise4_RMI_SERVER_Client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecieveMessageInteface extends Remote {
    void receiveMessage(String x) throws RemoteException;

}
