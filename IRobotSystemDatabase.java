/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Remote Interface for communication between the server and client side, transfer values of the variable
 */

//import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRobotSystemDatabase extends IObservable {
    public void setValue(int nValue) throws RemoteException;

    public int getValue() throws RemoteException;

    // getter and setter for the variables
    public void setBatteryPercentage(int batteryPercentage) throws RemoteException;

    public void setDockLocationY(int dockLocationY) throws RemoteException;

    public void setDockLocationX(int dockLocationX) throws RemoteException;

    public void setCurrentLocationY(int currentLocationY) throws RemoteException;

    public void setCurrentLocationX(int currentLocationX) throws RemoteException;

    public int getBatteryPercentage() throws RemoteException;

    public int getCurrentLocationY() throws RemoteException;

    public int getCurrentLocationX() throws RemoteException;

    public int getDockLocationX() throws RemoteException;

    public int getDockLocationY() throws RemoteException;

    /*public int getLowerBound() throws RemoteException;

    public int getHigherBound() throws RemoteException;*/
}