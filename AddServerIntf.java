package com.astoma.cs211e;

import java.rmi.*;
/**
 * Interface for RMI purposes
 * cs211e HomeWork # 5 
 * @author Andrei Stoma
 * Mar 22, 2017
 */

public interface AddServerIntf extends Remote
{
String[] getCapitals(String regex) throws RemoteException;
String[] getStates(String regex) throws RemoteException;
}
