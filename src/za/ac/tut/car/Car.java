package za.ac.tut.car;

/**
 *
 * @author Student
 */
public class Car {
    private String manufacturer, model, registrationNumber;
    private double price;

    public Car(String manufacturer, String model, String registrationNumber, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }   
}