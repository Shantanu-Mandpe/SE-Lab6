/*
 * Author : Shantanu Mandpe
 * Date : 17/06/2022
 * Lab : IE-SEL
 * Standard Remote Interface for the distributed observer pattern
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class Observable extends UnicastRemoteObject implements IObservable{
    private boolean changed = false;
    private Vector<Observer> obs;

    public Observable() throws RemoteException {
        obs = new Vector<>();
    }

    @Override
    // public synchronized void addObserver(Observer o) {
    public void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    @Override
    // public synchronized void deleteObserver(Observer o) {
    public void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    public void notifyObservers() throws RemoteException {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) throws RemoteException
    { 
        /* 
         * a temporary array buffer, used as a snapshot of the state of 
         * current Observers. 
         */ 
        Object[] arrLocal;
        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }

        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((Observer) arrLocal[i]).update(this, arg);
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    @Override
    public synchronized int countObservers() {
        return obs.size();
    }
    
}
