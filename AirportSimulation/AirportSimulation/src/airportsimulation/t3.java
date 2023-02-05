/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class t3 extends Thread {
    public static boolean canTaxi = false;
    public static boolean canTakeOff = false;
    public static Semaphore sem1 = new Semaphore(0);
    public static List<Integer> pilot = new ArrayList<>();
    public static List<Integer> taxi = new ArrayList<>();
    public static List<Integer> boardQueue = new ArrayList<>();
    public static List<Integer> takeOff = new ArrayList<>();

    public t3(List<Integer> pilot, List<Integer> taxi, List<Integer> takeOff) {
        this.pilot = pilot;
        this.taxi = taxi;
        this.takeOff = takeOff;

    }

    public t3() {

    }

    @Override
    public void run() {
        while (true) {

            try {
                sem1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            t1 atc = new t1();
//            try {
//                t1.sem.acquire();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


            if (!pilot.isEmpty()) {
                int pilotNo = pilot.get(0);


                System.out.println(currentThread().getName() + "-" + (pilotNo) + ": Plane " + pilotNo + " has landed. Waiting for taxi to gate.");
                pilot.remove(pilot.get(0));
//                t1.sem.release();
//                t1.sem.release();
                try {
                    sem1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            else {
//                t1.sem.release();
//            }

            if (canTaxi) {
                if (!taxi.isEmpty()) {
                    int taxiNo = taxi.get(0);
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(currentThread().getName() + "-" + taxi.get(0) + " has finished taxing to the assigned gate");

//                    t1 atc = new t1(boardQueue);
                    taxi.remove(taxi.get(0));
                    System.out.println(currentThread().getName() + "-" + taxiNo + " initiating to dock to the gate");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    boardQueue.add(taxiNo);
                    t1 atc = new t1(boardQueue);
                    t1.sem.release();
//                    t1.sem.release();


                }
            }
            canTaxi = false;
            try {
                sem1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if (canTakeOff) {
                if(!takeOff.isEmpty()){
                    int takeOffNo = takeOff.get(0);
                    System.out.println(currentThread().getName() + "-" + takeOffNo + " has finished undocking from the gate");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(currentThread().getName() + "-" + takeOffNo + " has departed and take-off completed");
                    takeOff.remove(takeOff.get(0));
                    t1.runwayFree=true;
                    t1.sem.release();
                }

            }
            canTakeOff=false;
        }
    }
}