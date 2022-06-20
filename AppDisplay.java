/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * App Display is an observer in the distributed observer pattern, which is used to display the variables(parameters) 
 */

//import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AppDisplay extends UnicastRemoteObject implements Observer{

    private int currentLocationX;
    private int currentLocationY;
    private int dockLocationX;
    private int dockLocationY;
    private int batteryPercentage;

    private IRobotSystemDatabase rsd = null;

    public AppDisplay(IRobotSystemDatabase rsd) throws RemoteException {
        this.rsd = rsd;
        rsd.addObserver(this);
    }

    public void setValue(int value) throws RemoteException{
        rsd.setValue(value);
    }

    @Override
    public void update(IObservable o, Object arg) throws RemoteException {
        // TODO Auto-generated method stub
        if (o == rsd){
            //System.out.println("ov : " + arg + rsd.getValue());
            currentLocationX = rsd.getCurrentLocationX();
            currentLocationY = rsd.getCurrentLocationY();
            dockLocationX = rsd.getDockLocationX();
            dockLocationY = rsd.getDockLocationY();
            batteryPercentage = rsd.getBatteryPercentage();

        }
        else {
            //System.out.println("obs: " + arg + ((IRobotSystemDatabase)o).getValue());
            currentLocationX = ((IRobotSystemDatabase) o).getCurrentLocationX();
            currentLocationY = ((IRobotSystemDatabase) o).getCurrentLocationY();
            dockLocationX = ((IRobotSystemDatabase) o).getDockLocationX();
            dockLocationY = ((IRobotSystemDatabase) o).getDockLocationY();
            batteryPercentage = ((IRobotSystemDatabase) o).getBatteryPercentage();
        }
    }

    public void display(){
        System.out.println("Battery Percentage = " + batteryPercentage);
        System.out.println("CurrentLocationX = " + currentLocationX);
        System.out.println("CurrentLocationY = " + currentLocationY);
        System.out.println("DockLocationX = " + dockLocationX);
        System.out.println("DockLocationY = " + dockLocationY);
    }

    public IRobotSystemDatabase getRsd() {
        return rsd;
    }
    
}
