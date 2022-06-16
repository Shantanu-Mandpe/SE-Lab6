import java.io.Serializable;
import java.rmi.RemoteException;

public class HardwareController implements IHardwareController , Serializable{

    public AlgorithmManager am;
    private int userCommand;

    public HardwareController() {
        
    }

    

    @Override
    public int controller(int player) throws RemoteException {
        // TODO Auto-generated method stub
        /*if (userCommand == 1) {
            newPosition = am.startAlgorithm(player);
        } else if (userCommand == 2) {
            newPosition = am1.startAlgorithm(player);
        } else if (userCommand == 3) {
            newPosition = am2.startAlgorithm(player);
        }*/
        return am.startAlgorithm(player);
    }

    // changes the algorithm dynamically
    public void setAlgorithm(AlgorithmManager manager) {
        am = manager;
    }

    @Override
    public void setUserCommand(int a) throws RemoteException {
        // TODO Auto-generated method stub
        this.userCommand = a;
        if (userCommand == 1) {
            am = new AStarSearchAlgorithm();
        } else if (userCommand == 2) {
            am = new MazeSolvingAlgorithm();
        } else if (userCommand == 3) {
            am = new SLAMAlgorithm();
        } else {
            System.out.println("ERROR : Please select an option from 1-3");
        }
    }
    
}
