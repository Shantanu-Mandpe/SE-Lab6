/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Standard Remote Interface for the distributed observer pattern
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote{
    void update(IObservable o, Object arg) throws RemoteException;
}
