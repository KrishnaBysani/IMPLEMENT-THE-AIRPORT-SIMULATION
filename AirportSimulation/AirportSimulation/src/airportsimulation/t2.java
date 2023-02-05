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

public class t2 extends Thread {
    private static final int noOfPlanes = 6;
    public static boolean semRelease = true;
//    List<Integer> taxi = new ArrayList<>();
    List<Integer> takeOff = new ArrayList<>();

    public t2(List<Integer> takeoff){
        this.takeOff = takeoff;
    }

    public t2() {

    }


    @Override
    public void run() {
        List<Integer> landingQueue = new ArrayList<>();
        List<Integer> landingWaitQueue = new ArrayList<>();
        Random r = new Random();
        t1 land = new t1(landingQueue,landingWaitQueue);

        for (int i = 0; i < noOfPlanes; i++) {
//            System.out.println(currentThread().getName() +(i+1)+ " Plane No " + (i + 1) + ": waiting to land");


//            if (t1.runwayCheck()) {
//                land.runwayFree = false;
                landingQueue.add(i+1);

                    land.sem.release();

//                System.out.println(currentThread().getName()+(i+1) + " Plane No " + (i + 1) + ": landing");
//            } else {
//                landingWaitQueue.add(i + 1);
//                land.sem.release();
//            }


            try {
                Thread.sleep(r.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        land.sem.release();
    }
}
