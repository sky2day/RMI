package com.astoma.cs211e;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

/**
 * Facilitates communication with database using JDBC
 * 
 * @author Andrei Stoma Mar 1, 2017
 */
public class DatabaseManager
{
  private Connection con;
  private Statement stmt;
  private ResultSet result;
  private Properties jdbcProp;
  private FileInputStream fis;
  private String userid;
  private String password;
  private String server;
  private String port;
  private String driver;
  private String database;
  private String url;

  // default constructor uses property file
  public DatabaseManager()
  {
    jdbcProp = new Properties();

    try
    {
      fis = new FileInputStream("database.prop");
    } catch (FileNotFoundException e)
    {
      System.out.println("Property file cannot be found");
      e.printStackTrace();
    }

    try
    {
      jdbcProp.load(fis);
    } catch (IOException e)
    {
      System.out.println("Property file cannot be loaded");
      e.printStackTrace();
    }

    driver = jdbcProp.getProperty("jdbc.driver");
    userid = jdbcProp.getProperty("jdbc.userid");
    password = jdbcProp.getProperty("jdbc.password");
    server = jdbcProp.getProperty("jdbc.server");
    port = jdbcProp.getProperty("jdbc.port");
    database = jdbcProp.getProperty("jdbc.database");
    url = driver + ":" + server + ":" + port + ":" + database;

    try
    {
      con = getConnection();
      stmt = con.createStatement();
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
  }// end default constructor

  // constructor uses arguments and hardcoded values
  public DatabaseManager(String userid, String password)
  {
    this.userid = userid;
    this.password = password;
    driver = "jdbc:oracle:thin";
    server = "@hills.ccsf.edu";
    port = "1521";
    database = "cs12";
    url = driver + ":" + server + ":" + port + ":" + database;
    try
    {
      con = getConnection();
      stmt = con.createStatement();
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
  }// end constructor

  public Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection(url, userid, password);
  }

  // handles SQL commands not returning anything
  public void execute(String query)
  {
    try
    {
      stmt.execute(query);
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  // handles SQL queries
  public ResultSet executeQuery(String query)
  {
    try
    {
      result = stmt.executeQuery(query);
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    return result;
  }

  public void closeConnection()
  {
    try
    {
      
      //result.close();
      stmt.close();
      con.close();
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
