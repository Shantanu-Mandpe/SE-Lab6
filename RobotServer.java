/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Class starts and runs the server for the RMI project
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.nio.channels.AlreadyBoundException;

public class RobotServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        try {
            Registry registry = LocateRegistry.createRegistry(7777);
            RemoteServer.setLog(System.err);
            IRobotSystemDatabase rsd = new RobotSystemDatabase();
            registry.bind("rsd", rsd);

            IHardwareController hc = new HardwareController();
            registry.bind("hc", hc);

            System.out.println("THE SERVER IS Ready");
        } catch (java.rmi.AlreadyBoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Server is not ready");
            e.printStackTrace();
        }
        // Naming.rebind("subject", subject);
    }
}
