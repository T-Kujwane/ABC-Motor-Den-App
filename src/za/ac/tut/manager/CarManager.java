/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.manager;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import za.ac.tut.car.Car;

/**
 *
 * @author Student
 */
public class CarManager extends AbstractManager {
    
    public CarManager(String url, String userName, String password) throws ClassNotFoundException, SQLException {
        super(url, userName, password);
    }

    @Override
    public void add(Object newObject) throws SQLException {
        if (newObject instanceof Car) {
            Car tempCar = (Car) newObject;

            String reg = tempCar.getRegistrationNumber();
            String model = tempCar.getModel();
            double price = tempCar.getPrice();
            String manufact = tempCar.getManufacturer();

            String query = "INSERT INTO car VALUES(?,?,?,?)";
            PreparedStatement statement = super.getConnection().prepareStatement(query);

            statement.setString(1, reg);
            statement.setDouble(2, price);
            statement.setString(3, manufact);
            statement.setString(4, model);

            statement.executeUpdate();

            System.out.println("Car added successfully");

        }
    }

    @Override
    public void replace(Object oldObject, Object newObject) throws SQLException {

    }

    @Override
    public List getAll() throws SQLException {
        String query = "SELECT * FROM car";
        ArrayList<Car> carsList = new ArrayList<>();
        
        PreparedStatement statement = this.getConnection().prepareStatement(query);
        
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Car listCar = getCar(rs);
            
            carsList.add(listCar);
        }

        return carsList;
    }

    @Override
    public void delete(Object object) throws SQLException {

    }

    public Car getCar(String reg) throws SQLException {
        String query = "SELECT * FROM car WHERE reg_nr = ?";
        PreparedStatement statement = getConnection().prepareStatement(query);
        
        statement.setString(1, reg);
        
        ResultSet rs = statement.executeQuery();
        
        if (rs.next()) {
            Car car = getCar(rs);
            return car;
        }
        
        return null;
    }
    
    //8905161012085
    public Car getCheapestCar() throws SQLException {
        String query = "SELECT * FROM car c, car cr GROUP BY c.price, c.manufacturer, c.model_name HAVING c.price = MIN(cr.price)";
        ResultSet rs = getConnection().prepareStatement(query).executeQuery();

        if (rs.next()) {
            Car car = getCar(rs);
            
            return car;
        }
        
        return null;
    }

    public Car createCar(String manufacturer, String model, double price, String reg) {
        Car newCar = new Car(manufacturer, model, reg, price);
        return newCar;
    }

    private Car getCar(ResultSet rs) throws SQLException {
        String reg = rs.getString("REG_NR");
        String model = rs.getString("MODEL_NAME");
        String manufact = rs.getString("MANUFACTURER");
        double price = rs.getDouble("PRICE");

        return createCar(manufact, model, price, reg);
    }
    
    public void displayCar(Car car) {
        System.out.println("Registration: " + car.getRegistrationNumber() + "\n"
                + "Manufacturer: " + car.getManufacturer() + "\n"
                + "Model: " + car.getModel() + "\n"
                + "Price: R" + new DecimalFormat("000,000.00").format(car.getPrice()) + "\n");
    }
    
    public Car getMostExpensiveCar() throws SQLException{
        String query = "SELECT * FROM car c, car cr GROUP BY c.price, c.manufacturer, c.model_name HAVING c.price = MAX(cr.price)";
        ResultSet rs = getConnection().prepareStatement(query).executeQuery();
        
        if (rs.next()){
            Car mostExpensive = getCar(rs);
            
            return mostExpensive;
        }
        
        return null;
    }
}
