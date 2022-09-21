/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            
            String query = "INSERT INTO customer VALUES(?, ?, ?, ?)";
            
            setPreparedStatement(getConnection().prepareStatement(query));
            
            PreparedStatement st = getPreparedStatement();
            
            st.setString(1, newCustomer.getIdNumber());
            st.setString(2, newCustomer.getName());
            st.setString(3, newCustomer.getSurname());
            
            st.execute();
            
            System.out.println("Customer added successfully.\n");
        }
    }

    @Override
    public void replace(Object oldObject, Object newObject) throws SQLException {
        
    }

    @Override
    public List<Object> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Customer getCustomer(String idNumber) throws SQLException{
        String query = "SELECT * FROM customer WHERE ID_NO = ?";
        setPreparedStatement(getConnection().prepareStatement(query));
        
        PreparedStatement st = getPreparedStatement();
        st.setString(1, idNumber);
        
        ResultSet rs = st.executeQuery();
        
        if (rs.next()){
            Customer client = createCustomer(rs);
            return client;
        }
        return null;
    }
    
    public Customer createCustomer(ResultSet rs) throws SQLException{
        String idNumber = rs.getString("ID_NO");
        String name = rs.getString("CUSTOMER_NAME");
        String surname = rs.getString("CUSTOMER_SURNAME");
        
        Customer customer = new Customer(name, idNumber, surname);
        
        return customer;
    }
    
    public Customer createCustomer(final String ID, String name, String surname){
        return new Customer(name, ID, surname);
    }
}
