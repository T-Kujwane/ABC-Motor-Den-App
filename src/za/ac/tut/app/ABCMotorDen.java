/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package za.ac.tut.app;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import za.ac.tut.car.Car;
import za.ac.tut.manager.CarManager;

/**
 *
 * @author Thato Keith Kujwane
 */
public class ABCMotorDen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here: Build a databse driven application that handles a car inventory and sales
        
        String root = "root";
        try {
            CarManager mgr = new CarManager("jdbc:mysql://localhost:7000/motor_den_db", root, root);
            Scanner scanner = new Scanner(System.in);

            int userOption = getMainMenuOption(new Scanner(System.in));
            
            while (userOption != 9) {

                if (userOption == 1) {
                    String manufact = getUserInput(scanner, "car manufacturer");
                    String model = getUserInput(scanner, "car model");
                    double price = Double.parseDouble(getUserInput(scanner, "car price"));
                    String registration = getUserInput(scanner, "car registration");

                    Car newCar = mgr.createCar(manufact, model, price, registration);

                    mgr.add(newCar);

                } else if (userOption == 2) {
                    String reg = getUserInput(scanner, "search car registration");

                    Car returnedCar = mgr.getCar(reg);

                    if (returnedCar != null) {
                        System.out.println("A car with registration " + reg.toUpperCase() + " was found. Printing car details....\n");
                        mgr.displayCar(returnedCar);
                    } else {
                        System.out.println("A car with registration " + reg.toUpperCase()
                                + " was not found.\n");
                    }
                } else if (userOption == 3) {
                    List<Car> carsList = mgr.getAll();

                    for (Car listCar : carsList) {
                        mgr.displayCar(listCar);
                    }

                } else if (userOption == 4) {
                    Car cheapestCar = mgr.getCheapestCar();

                    if (cheapestCar != null) {
                        System.out.println("Printing cheapest car details.");
                        mgr.displayCar(cheapestCar);
                    }
                } else if (userOption == 5) {
                    Car expensiveCar = mgr.getMostExpensiveCar();

                    if (expensiveCar != null) {
                        System.out.println("Expensive car found...printing car details");
                        mgr.displayCar(expensiveCar);
                    }
                } else if (userOption == 6) {
                    int changeMenuOption;
                    boolean changeMenuOptionIsValid;

                    String registration = getUserInput(scanner, "car registration");

                    Car oldCar = mgr.getCar(registration);
                    Car newCar = oldCar;

                    if (oldCar != null) {
                        do {
                            System.out.print("Choose what would you like to change from the options given below.\n"
                                    + "--------------------------------------------------------------\n"
                                    + "1 - Price\n"
                                    + "2 - Manufacturer\n"
                                    + "3 - Model\n"
                                    + "4 - Cancel\n"
                                    + "Enter you option here: ");

                            changeMenuOption = scanner.nextInt();

                            changeMenuOptionIsValid = validateChangeMenuOption(changeMenuOption);

                            if (!changeMenuOptionIsValid) {
                                System.out.println("The option " + changeMenuOption + " is not allowed here. Try again.");
                            }
                        } while (!changeMenuOptionIsValid);

                        switch (changeMenuOption) {
                            case 1:
                                double newPrice = Double.parseDouble(getUserInput(scanner, "new price"));

                                newCar.setPrice(newPrice);

                                mgr.replace(oldCar, newCar);
                                break;

                            case 2:
                                String newManufacturer = getUserInput(scanner, "new manufacturer");
                                newCar.setManufacturer(newManufacturer);

                                mgr.replace(oldCar, newCar);
                                break;

                            case 3:
                                String newModel = getUserInput(scanner, "new model");
                                newCar.setModel(newModel);

                                mgr.replace(oldCar, newCar);
                                break;
                        }
                    } else {
                        System.out.println("Cannot change details for car with registration " + registration
                                + " since it does not exist.\n");
                    }
                }else if (userOption == 7){
                    String registration = getUserInput(scanner, "car registration");
                    
                    Car car = mgr.getCar(registration);
                    
                    if (car != null){
                        mgr.delete(car);
                    }else {
                        System.out.println("Cannot delete car with registration " + registration + " since it does not exist.");
                    }
                }else if (userOption == 8){
                    List<Car> carsList = mgr.getAll();
                    
                    if (! carsList.isEmpty()){
                        String manufact = getUserInput(scanner, "manufacturer");
                        System.out.println("Displaying cars made by " + manufact.toUpperCase());
                        for (Car listCar : carsList){
                            if (listCar.getManufacturer().equalsIgnoreCase(manufact)){
                                mgr.displayCar(listCar);
                            }
                        }
                    }
                }
                userOption = getMainMenuOption(new Scanner(System.in));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            displayExceptionInformation(ex, "instantiate CarManager");
        }
    }

    private static String getUserInput(Scanner sc, String inputDesc) {
        System.out.print("Enter " + inputDesc + ": ");
        return sc.nextLine();
    }

    private static int displayCarsProcessingMenu(Scanner sc) {
        System.out.print("Choose a menu option\n"
                + "------------------------------------------------\n"
                + "1 - Add car\n"
                + "2 - Search car\n"
                + "3 - View all cars\n"
                + "4 - View cheapest car\n"
                + "5 - View most expensive car\n"
                + "6 - Change car details\n"
                + "7 - Delete car\n"
                + "8 - View cars by manufacturer\n"
                + "9 - Exit\n"
                + "Enter your option here: ");
        return sc.nextInt();
    }

    private static void displayExceptionInformation(Exception ex, String causeAction) {
        System.out.println("An / a " + ex + " while trying to " + causeAction + "\n"
                + "See exception message below\n" + ex.getLocalizedMessage() + "\n"
                + "See exception stack trace below.\n");
        ex.printStackTrace(System.out);
    }
    
    private static int getMainMenuOption(Scanner sc){
        System.out.print("Choose main menu option below\n" + 
                "----------------------------------------------" + "\n" + 
                "1 - Process car information\n" + 
                "2 - Process customer information\n" + 
                "3 - Sell car" + "\n" + 
                "4 - Exit\n" + 
                "Enter your choice here: ");
        return sc.nextInt();
    }

    private static boolean validateChangeMenuOption(int changeMenuOption) {
        switch (changeMenuOption) {
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }
}
