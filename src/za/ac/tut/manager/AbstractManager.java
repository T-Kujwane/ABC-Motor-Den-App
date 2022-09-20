/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.manager;
import java.sql.*;

/**
 *
 * @author Student
 */
public abstract class AbstractManager implements ManagerInterface{
    private final Connection connection;
    
    protected AbstractManager(String url, String userName, String password) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url, userName, password);
    }
    
    protected Connection getConnection(){
        return this.connection;
    }
    
}
