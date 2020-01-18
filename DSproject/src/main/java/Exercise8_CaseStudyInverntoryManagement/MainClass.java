package Exercise8_CaseStudyInverntoryManagement;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Project Name: DSproject
 * Created With: IntelliJ IDEA.
 * Author: Baftjar TABAKU & Erli REÇI
 * Date Created: 1/16/2020
 * Time Created: 3:06 PM
 **/

//Threads
//Adding products

public class MainClass {
    Scanner sc = new Scanner(System.in);
    public static Product[] products;

    //Main calling class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Simply array implementation for an construction shop
        //Products nd anything is pre defined for asy usage,
        String[] productNames = {"Brick", "Steel", "Lumber", "Wood", "CementUnits"};
        int[] materialQuantity = {100, 30, 100, 230, 566, 360};

        //Initialisation thread
        Thread initialiseThread = new Thread() {
            public void run() {
                products = new Product[productNames.length];
                System.out.println("Initializer thread started initialising the database !!! ");
                for (int i = 0; i < productNames.length; i++) {
                    products[i] = new Product(materialQuantity[i], productNames[i], i);
                    System.out.println("Adding to database: " + products[i].toString());
                }
            }
        };
        initialiseThread.run();

        // Checker thread that will always check the database
        Thread checkerThread = new Thread() {
            public void run() {
                //to avoid notify the same product again and again
                System.out.println("Checker thread started and will always check the database !!! ");
                ArrayList previousExistingElementsID = new ArrayList();

                while (true) {
                    for (int i = 0; i < productNames.length; i++) {
                        if (products[i].getQuantity() <= 0 && !isElementOfTheList(previousExistingElementsID, i)) {
                            System.out.println("Product : " + products[i].getName() + " appears to be empty!!!");
                            //it may be negative but we set it to 0
                            products[i].setQuantity(0);
                            previousExistingElementsID.add(i);
                        }
                    }
                }
            }
        };
        checkerThread.start();

        while (true) {
            System.out.println("Removing Product");
            System.out.println("Enter product id:");
            int id = scanner.nextInt();
            System.out.println("Enter product Quantity To remove");
            int quantity = scanner.nextInt();
            removeproduct(id, quantity);
            //starting thread to check again

        }

    }

    public static boolean isElementOfTheList(ArrayList<Integer> elements, int element) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) == element) {
                return true;
            }
        }
        return false;
    }

    private static void removeproduct(int id, int quantity) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getId() == id) {
                products[i].setQuantity(products[i].getQuantity() - quantity);
            }
        }
    }

    private static void printProducts() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].toString());
        }
    }
}


