/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.manager;

import java.sql.SQLException;
import java.util.List;
import za.ac.tut.customer.Customer;

/**
 *
 * @author devel
 */
public class CustomerManager extends AbstractManager{
    
    public CustomerManager(String url, String userName, String password) throws ClassNotFoundException, SQLException{
        super(url, userName, password);
    }
    
    @Override
    public void add(Object newObject) throws SQLException {
        if (newObject instanceof Customer){
            Customer newCustomer = (Customer) newObject;
        }
    }

    @Override
    public void replace(Object oldObject, Object newObject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
