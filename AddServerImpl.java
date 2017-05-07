package com.astoma.cs211e;

import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/**
 * RMI server program cs211e HomeWork # 5
 * 
 * @author Andrei Stoma Mar 22, 2017
 */

public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf
{
  public AddServerImpl() throws RemoteException // throws RemoteException
  {
    super();
  }

  @Override
  public String[] getCapitals(String regex)
  {

    DatabaseManager dbm = new DatabaseManager();
    ResultSet rs = dbm
	.executeQuery("select capital from fiftystates_astoma where REGEXP_LIKE(capital,'" + regex + "')");
    ArrayList<String> capitals = new ArrayList<>();

    try
    {
      while (rs.next())
      {
	capitals.add(rs.getString(1));
      }
    } catch (SQLException e)
    {
      e.printStackTrace();

    }
    return capitals.toArray(new String[capitals.size()]);
  }

  @Override
  public String[] getStates(String regex)
  {
    DatabaseManager dbm = new DatabaseManager();
    ResultSet rs = dbm.executeQuery("select state from fiftystates_astoma where REGEXP_LIKE(state,'" + regex + "')");
    ArrayList<String> states = new ArrayList<>();

    try
    {
      while (rs.next())
      {
	states.add(rs.getString(1));
      }
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    return states.toArray(new String[states.size()]);
  }

  public static void main(String[]... args)
  {
    try
    {
      AddServerImpl asi = new AddServerImpl();
    } catch (RemoteException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
