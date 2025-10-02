package com.tp;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyRemoteService extends Remote {

    String sayHello() throws RemoteException;
}
