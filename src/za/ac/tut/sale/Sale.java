/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.tut.sale;
import java.sql.Date;

/**
 *
 * @author Student
 */
public class Sale {
    private String carRegistration, customerID;
    private Date saleDate;

    public Sale(String carRegistration, String customerID, Date saleDate) {
        this.carRegistration = carRegistration;
        this.customerID = customerID;
        this.saleDate = saleDate;
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
