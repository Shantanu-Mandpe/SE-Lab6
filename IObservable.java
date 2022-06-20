/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Standard interface for distributed observer pattern
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservable extends Remote{
    public void addObserver(Observer o) throws RemoteException;

    public void deleteObserver(Observer o) throws RemoteException;

    public int countObservers() throws RemoteException;
}
