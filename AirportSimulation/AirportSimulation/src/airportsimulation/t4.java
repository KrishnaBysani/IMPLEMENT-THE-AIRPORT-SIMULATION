/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class t4 extends Thread {
    public static Semaphore sem2 = new Semaphore(0);
    public static List<Integer> passengerListDisembark = new ArrayList<>();
    public static List<Integer> passengerListEmbark = new ArrayList<>();
    public static List<Integer> plane = new ArrayList<>();
    public static List<Integer> undock = new ArrayList<>();
    public static List<Double> disembarkTime = new ArrayList<>();
    public static List<Double> embarkTime = new ArrayList<>();

    public static int planeNo;
    public static int noOfPassengers;
    public static int noOfPassengers1;
    public static boolean disembark=false;
    public static boolean embark=false;

    public t4( int planeNo, int noOfPassengers, int noOfPassengers1) {
        this.planeNo = planeNo;
        this.noOfPassengers = noOfPassengers;
        this.noOfPassengers1 = noOfPassengers1;
    }

    public t4() {

    }

    @Override
    public void run() {
        while (true) {
            t1 atc1 = new t1(undock,disembarkTime,embarkTime,passengerListEmbark,passengerListDisembark);
            try {
                sem2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atc1.board = false;
            if (disembark) {
                for (int i = 1; i <= noOfPassengers; i++) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currentThread().getName() + "-" + i + " exiting plane " + planeNo);
                }
                disembarkTime.add(noOfPassengers*0.02);
                passengerListDisembark.add(noOfPassengers);
                atc1.disembark=true;
                atc1.sem.release();
                t5 fuel = new t5(planeNo);
                fuel.s.release();



            }

            try {
                sem2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if(embark){
                for(int i=1; i<= noOfPassengers1; i++){
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currentThread().getName()+"-"+i+ " entering plane "+planeNo);
                }
                embarkTime.add(noOfPassengers1*0.02);
                passengerListEmbark.add(noOfPassengers1);
                atc1.embark = true;
                atc1.board = true;
                atc1.sem.release();
                undock.add(planeNo);
                t1.sem.release();
            }
        }
    }
}
