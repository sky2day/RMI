package com.astoma.cs211e;

import java.rmi.Naming;
/**
 * RMI client program 
 * cs211e HomeWork # 5 
 * @author Andrei Stoma
 * Mar 22, 2017
 */

public class AddClient
{
  public static void main(String... args)
  {
    try
    {
      String url = "rmi://" + args[0] + "/AddServer";
      AddServerIntf asi = (AddServerIntf) Naming.lookup(url); // checking if "AddServer" is in registry
      String regex = args[1];
      String[] capitals = asi.getCapitals(regex);
      String[] states = asi.getStates(regex);

      for (int i = 0; i <= capitals.length; i++)
        System.out.println("Capitals: " + capitals[i]);

      for (int i = 0; i <= capitals.length; i++)
        System.out.println("States: " + states[i]);
    } catch (Exception e)
    {
    }

  }
}
