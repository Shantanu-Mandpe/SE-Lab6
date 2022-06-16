import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservable extends Remote{
    public void addObserver(Observer o) throws RemoteException;

    public void deleteObserver(Observer o) throws RemoteException;

    public int countObservers() throws RemoteException;
}
