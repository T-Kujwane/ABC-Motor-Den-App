/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package za.ac.tut.manager;

import java.util.List;
import java.sql.*;

/**
 *
 * @author Student
 */
public interface ManagerInterface {
    public void add(Object newObject) throws SQLException;
    public void replace(Object oldObject, Object newObject)throws SQLException;
    public List<Object> getAll()throws SQLException;
    public void delete(Object object)throws SQLException;
}
