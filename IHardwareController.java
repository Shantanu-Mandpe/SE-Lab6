/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Remote Interface used to communicate between the server and client side
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHardwareController extends Remote{
    public int controller (int player ) throws RemoteException;

    public void setUserCommand(int a) throws RemoteException;
}
