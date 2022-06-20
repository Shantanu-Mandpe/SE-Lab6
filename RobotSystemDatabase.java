/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Class is the subject which is observed by observers for the distributed observer pattern
 */

import java.rmi.RemoteException;

public class RobotSystemDatabase extends Observable implements IRobotSystemDatabase{

    private int nValue = 0;
    private int currentLocationX = 100;
    private int currentLocationY = 100;
    private int dockLocationX = 600;
    private int dockLocationY = 600;
    private int batteryPercentage =100;

    public RobotSystemDatabase(int nValue, int currentLocationX, int currentLocationY, int dockLocationX, int dockLocationY, int batteryPercentage) throws RemoteException {
        this.nValue = nValue;
        this.currentLocationX = currentLocationX;
        this.currentLocationY = currentLocationY;
        this.dockLocationX = dockLocationX;
        this.dockLocationY = dockLocationY;
        this.batteryPercentage = batteryPercentage;
        //TODO Auto-generated constructor stub
    }


    public RobotSystemDatabase() throws RemoteException{
        this (100,100,100,600,600,100);
    }


    @Override
    public void setValue(int nValue) throws RemoteException {
        // TODO Auto-generated method stub
        this.nValue = nValue;

        setChanged();
        notifyObservers("New value: ");
    }

    @Override
    public int getValue() throws RemoteException {
        // TODO Auto-generated method stub
        return nValue;
    }


    @Override
    public void setBatteryPercentage(int batteryPercentage) throws RemoteException {
        // TODO Auto-generated method stub
        this.batteryPercentage = batteryPercentage;

        setChanged();
        notifyObservers("New value: ");
        
    }


    @Override
    public void setDockLocationY(int dockLocationY) throws RemoteException {
        // TODO Auto-generated method stub
        this.dockLocationY = dockLocationY;

        setChanged();
        notifyObservers("New value: ");
        
    }


    @Override
    public void setDockLocationX(int dockLocationX) throws RemoteException {
        // TODO Auto-generated method stub
        this.dockLocationX = dockLocationX;

        setChanged();
        notifyObservers("New value: ");
        
    }


    @Override
    public void setCurrentLocationY(int currentLocationY) throws RemoteException {
        // TODO Auto-generated method stub
        this.currentLocationY = currentLocationY;

        setChanged();
        notifyObservers("New value: ");
        
    }


    @Override
    public void setCurrentLocationX(int currentLocationX) throws RemoteException {
        // TODO Auto-generated method stub
        this.currentLocationX = currentLocationX;

        setChanged();
        notifyObservers("New value: ");
        
    }


    @Override
    public int getBatteryPercentage() throws RemoteException {
        // TODO Auto-generated method stub
        return batteryPercentage;
    }


    @Override
    public int getCurrentLocationY() throws RemoteException {
        // TODO Auto-generated method stub
        return currentLocationY;
    }


    @Override
    public int getCurrentLocationX() throws RemoteException {
        // TODO Auto-generated method stub
        return currentLocationX;
    }


    @Override
    public int getDockLocationX() throws RemoteException {
        // TODO Auto-generated method stub
        return dockLocationX;
    }


    @Override
    public int getDockLocationY() throws RemoteException {
        // TODO Auto-generated method stub
        return dockLocationY;
    }
    
}
