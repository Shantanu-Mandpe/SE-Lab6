/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Class runs the client side and starts the simulation
 */

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;


public class Main {
    public static void main (String args[]) throws MalformedURLException, RemoteException, NotBoundException{
        IRobotSystemDatabase rsd = (IRobotSystemDatabase) Naming.lookup("rmi://localhost:7777/rsd");
        IHardwareController hc = (IHardwareController) Naming.lookup("rmi://localhost:7777/hc");
        
        //Panel.registerStub(rsd, new Panel(rsd));
        System.out.println("Connected to Server...");
        int a;
        Scanner s = new Scanner(System.in); 
        System.out.println("Enter a number between 1-3: ");
        a = s.nextInt();
        AppDisplay app = new AppDisplay(rsd);
        hc.setUserCommand(a);
        // app.setValue(100);
        SimPanel.registerStub(rsd, new SimPanel(rsd,hc,a));
        new SimPanel();
        
    }
}
