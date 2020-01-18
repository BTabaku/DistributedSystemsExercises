package Exercise10_ReservationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Project Name: DSproject
 * Package Name: Exercise10_ReservationSystem
 * Created With: IntelliJ IDEA.
 * Author: Baftjar TABAKU & Erli REÇI
 * Created on: 1/18/2020, Time: 2:17 AM
 **/
public class CinemaTicketManagement {
    private static Scanner sc = new Scanner(System.in);
    private static List<Integer> numberOfPlacesList = new ArrayList<>();//10 rows with 20 seats 200 persons
    private static int spectatorCntID = 0, limitCnt = 0;
    private static final int SPECTATOR_LIMIT = 5;
//    private static final int ADDING_LIMIT = 1;


    //for synchronisation used an inner class
    static class CostumerServicePlace {
        private final Object lock = new Object();

        //method of client service 1
        public void costumerService1() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (numberOfPlacesList.size() == SPECTATOR_LIMIT || limitCnt == 1) {
                        System.out.println("Costumer service 1 center, waiting of center 2 to add client...");
                        lock.wait();
                    } else {
                        System.out.println("Costumer service 1 center adding client: " + spectatorCntID);
                        numberOfPlacesList.add(spectatorCntID);
                        spectatorCntID++;
                        limitCnt++;
                        lock.notify();
                    }
                    Thread.sleep(1500);
                }
            }
        }

        //method of client service 2
        public void costumerService2() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (numberOfPlacesList.size() == SPECTATOR_LIMIT || limitCnt == 0) {
                        System.out.println("Costumer service 2 center, waiting of center 1 to add client...");
                        lock.wait();
                    } else {
                        System.out.println("Costumer service 2 center adding client: " + spectatorCntID);
                        numberOfPlacesList.add(spectatorCntID);
                        spectatorCntID++;
                        limitCnt -= 1;
                        lock.notify();
                    }
                    Thread.sleep(1500);
                }
            }
        }
    }

    public static void main(String[] args) {
        //Begin of initialization
        System.out.println("Entering public in  the cinema using two service rooms \n" +
                "that will allocate each " + SPECTATOR_LIMIT + " person/s:\n");

        CostumerServicePlace costumerServicePlaceSync = new CostumerServicePlace();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    costumerServicePlaceSync.costumerService1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    costumerServicePlaceSync.costumerService2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }

    public static void addSpectator(int spectatorOrder, int spectatorId) {
        //Adding person on the seat
//        numberOfPlaces[spectatorOrder] = spectatorId;
    }
//Todo printing array with thread if the array of places get updates, but is not necessary in this case
}
