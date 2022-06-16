import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHardwareController extends Remote{
    public int controller (int player ) throws RemoteException;

    public void setUserCommand(int a) throws RemoteException;
}
