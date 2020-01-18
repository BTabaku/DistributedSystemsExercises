/*
 ** Author: Baftjar Tabaku & Erli Reçi
 ** created on 1/10/2020 inside the package - Exercise2
 **
 */

package Exercise2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DeadLock {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int[][] alloc = new int[10][10];
        int[][] req = new int[10][10];
        int[] ins = new int[10];
        int[] avail = new int[10];
        boolean flag = false;
        int totalProcesses, totalResources, i, j, count;
        int[] tmp = new int[10];
        //Boolean array initialised with false
        boolean[] finish = new boolean[10];
        Arrays.fill(finish, Boolean.FALSE);
//      inputting from user
        //total number of processes
        System.out.println("Enter the total number of processes");
        totalProcesses = sc.nextInt();
        //total number of resources
        System.out.println("Enter the total number of resources");
        totalResources = sc.nextInt();

        //matrix initialised with random values
        System.out.println("Randomly Generated Allocation Matrix:");

        for (i = 0; i < totalProcesses; i++) {
            for (j = 0; j < totalResources; j++) {
                alloc[i][j] = random.nextInt(5);
                System.out.print(alloc[i][j] + "\t");
            }
            System.out.println();
        }

        //Randomly Generated Resource Instance Vector
        System.out.println("Randomly Generated Resource Instance Vector:");

        for (i = 0; i < totalResources; i++) {
            ins[i] = random.nextInt(10 + totalResources + totalProcesses);
            System.out.print(ins[i] + "\t");
        }

        //To calculate the resource availability vector
        System.out.println("\nCalculating the availability vector:");
        for (i = 0; i < totalProcesses; i++) {
            for (j = 0; j < totalResources; j++) {
                //sum calculated column wise for allocation matrix
                tmp[i] += alloc[j][i];
            }
        }
        //printing result of availability vector
        for (i = 0; i < totalResources; i++) {
            avail[i] = ins[i] - tmp[i];
            System.out.print(avail[i] + "\t");
        }
        System.out.println();

        //Main logic part
        count = 10;
        while (count > 0) {
            //if finish array has all true's (all processes to running state)
            //deadlock not detected and loop stops!
            for (i = 0; i < totalProcesses; i++) {
                count = 0;
                //to see whenever proccesses can be allocated to any blocked  process
                if (finish[i] == false) {
                    for (j = 0; j < totalResources; j++) {

                        if (req[i][j] <= avail[j]) {
                            count++;
                        }
                        flag = false;
                        if (count == totalResources) {

                            for (j = 0; j < totalResources; j++) {
                                avail[j] += alloc[i][j];
                                //Allocated resources are released and added to available!
                            }
                            finish[i] = true;
                            System.out.println("Process " + (i + 1) + " is transferred to running state and assumed finished, " + (i + 1));
                        } else {
                            flag = true;
                        }
                    }
                    count = 0;
                    for (j = 0; j < totalResources; j++) {
                        if (finish[j]) {
                            count++;
                        }
                    }
                }
            }

            for (i = 0; i < totalProcesses; i++) {
                if (!finish[i]) {
                    System.out.println("Oops! Deadlock detected an causing process is: proccess " + (i + 1));
                    break;
                }
            }
        }

        if (i - 1 >= 0) {
            i = i - 1;
        }
        if ((finish[i])) {
            System.out.println("\nHurray! Deadlock not detected:~\n");
        }

    }
}

