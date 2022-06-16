import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote{
    void update(IObservable o, Object arg) throws RemoteException;
}
