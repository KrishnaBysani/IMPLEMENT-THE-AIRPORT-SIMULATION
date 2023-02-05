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

public class t1 extends Thread {
    public static boolean runwayFree = true;
    public static boolean interrupt = false;
    public static boolean gate1 = true;
    public static boolean gate2 = true;
    public static boolean embark = false;
    public static boolean disembark = false;
    public static boolean board = true;
    public static boolean taxiRemove = false;

    public static boolean semAcquire = true;
    public static Semaphore sem = new Semaphore(0);
    int id;

    public static List<Integer> landingQueue = new ArrayList<>();
    public static List<Integer> landingWaitQueue = new ArrayList<>();
    public static List<Integer> taxi = new ArrayList<>();
    public static List<Integer> takeOff = new ArrayList<>();
    public static List<Integer> gateQueue = new ArrayList<>();
    public static List<Integer> pilot = new ArrayList<>();
    public static List<Integer> passengerListEmbark = new ArrayList<>();
    public static List<Integer> passengerListDisembark = new ArrayList<>();
    public static List<Integer> gateNumber = new ArrayList<>();
    public static List<Integer> boardQueue = new ArrayList<>();
    public static List<Integer> landedInRunway = new ArrayList<>();
    public static List<Integer> undock= new ArrayList<>();
    public static List<Double> disembarkTime = new ArrayList<>();
    public static List<Double> embarkTime = new ArrayList<>();

    public t1(List<Integer> landingQueue, List<Integer> landingWaitQueue) {
        this.landingQueue = landingQueue;
        this.landingWaitQueue = landingWaitQueue;
    }

    public t1(List<Integer> boardQueue) {
        this.boardQueue = boardQueue;
    }

    public t1() {

    }

    public t1(List<Integer> undock, List<Double> disembarkTime,List<Double> embarkTime,List<Integer> passengerListEmbark,List<Integer> passengerListDisembark) {
        this.undock= undock;
        this.disembarkTime = disembarkTime;
        this.embarkTime = embarkTime;
        this.passengerListEmbark = passengerListEmbark;
        this.passengerListDisembark = passengerListDisembark;
    }



    static boolean runwayCheck() {
        if (runwayFree) {
            return true;
        } else {
            return false;
        }
    }

    static boolean gateCheck() {
        if (!gate1 && !gate2) {
            return false;
        } else {
            return true;
        }
    }

    static int gateNo() {
        if (!gate2 && gate1) {
            return 1;
        } else if (!gate1 && gate2) {
            return 2;
        }
        return 1;
    }

    @Override
    public void run() {
        System.out.println(currentThread().getName() + ":" + "starting");
        AirportSimulation a = new AirportSimulation();
        t2 p = new t2();
        t3 pilotComs = new t3(pilot, taxi,takeOff);
//        try {
//            sem.acquire();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {

            if(!landingQueue.isEmpty()) {
                if (gateCheck()) {
                    if (runwayCheck()) {

                        System.out.println(currentThread().getName() + ": " + "runway clear");


                        if (!landingQueue.isEmpty()) {
                            runwayFree = false;

                            int landed = landingQueue.get(0);
                            System.out.println(currentThread().getName() + ": " + "Plane No " + landed + ": given permission to land.");


                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runwayFree = true;
                            landedInRunway.add(landingQueue.get(0));
                            landingQueue.remove(landingQueue.get(0));
                            pilot.add(landed);

                            pilotComs.sem1.release();

                            System.out.println(currentThread().getName() + ": " + "Plane No " + landed + " can taxi. Checking if any gate is empty...");
                        } else {
                            System.out.println(currentThread().getName() + ": " + " No plane is waiting to land");
                        }
                    } else {
                        System.out.println(currentThread().getName() + "runway in use");
                    }
                    if (gateCheck()) {
                        if (!landedInRunway.isEmpty()) {

                            int gt = gateNo();
                            gateNumber.add(gt);
                            if (gt == 1) {
                                gate1 = false;
                            }
                            if (gt == 2) {
                                gate2 = false;
                            }

                            taxi.add(landedInRunway.get(0));
                            landedInRunway.remove(landedInRunway.get(0));
                            int taxiPlane = taxi.get(0);
                            System.out.println(currentThread().getName() + ":" + "Plane no " + taxiPlane + " is assigned to gate: " + gt + ". Please coast to the assigned gate");

                            pilotComs.canTaxi = true;
                            pilotComs.sem1.release();
                        }

                    }


                } else {
                    System.out.println(currentThread().getName() + ": " + "Gates are not empty at the moment, Plane "+landingQueue.get(0)+  " please wait to land");
                }
            }
//                if (gateCheck()) {
//                    if (!taxi.isEmpty()) {
//
//                        int gt = gateNo();
//                        gateNumber.add(gt);
//                        if (gt == 1) {
//                            gate1 = false;
//                        }
//                        if (gt == 2) {
//                            gate2 = false;
//                        }
//
//                        int taxiPlane = taxi.get(0);
//                        System.out.println(currentThread().getName() + ":" + "Plane no " + taxiPlane + " is assigned to gate: " + gt + ". Please coast to the assigned gate");
////
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }

            Random r = new Random();
            int noOfPassengers = r.nextInt(51 - 30) + 30;
            int noOfPassengers1 = r.nextInt(51 - 30) + 30;
//                        for (int i = 1; i <= noOfPassengers; i++) {
//                            passengerList.add(i);
//                        }
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

            if (board) {
                if (!boardQueue.isEmpty()) {
                    board = false;
                    t4 passenger = new t4(boardQueue.get(0), noOfPassengers,noOfPassengers1);
                    passenger.disembark = true;
                    passenger.embark = true;
                    passenger.sem2.release();

                    boardQueue.remove(boardQueue.get(0)); //remove1
                }
            }
//                            passenger.embark = true;
//                            passenger.sem2.release();





//                    if(disembark){
//                t4.embark=true;
//
//                 }


            if(!undock.isEmpty()){
                if(runwayCheck()) {
                    int gtRemove = gateNumber.get(0);
                    gateNumber.remove(gateNumber.get(0));
                    if (gtRemove == 1) {
                        gate1 = true;
                    }
                    if (gtRemove == 2) {
                        gate2 = true;
                    }
                    takeOff.add(undock.get(0));
                    undock.remove(0);
                    System.out.println(currentThread().getName()+"- Plane "+takeOff.get(0)+" can now undock and coast towards the runway for takeoff");
                    runwayFree=false;
                    pilotComs.canTakeOff = true;
                    pilotComs.sem1.release();
                    pilotComs.sem1.release();
                    pilotComs.sem1.release();
                    pilotComs.sem1.release();

                }
                else {
                    System.out.println(currentThread().getName()+": Runway currently in use plane "+undock.get(0)+" please wait.");
                }


//                    if (!gateQueue.isEmpty()) {
//                        taxi.add(gateQueue.get(0));
//                        gateQueue.remove(gateQueue.get(0));
//                    }
            }
            else {
                System.out.println(currentThread().getName()+": No plane waiting to undock ");
            }






//                    embark=false;
//            if(!taxi.isEmpty()) {
//                for (int i = 0; i < 3; i++) {
//                    System.out.println(taxi.get(0));
//                }
//            }
            if(landingQueue.isEmpty() &&takeOff.isEmpty()){
                double max =-9999999;
                for(int i=0; i<disembarkTime.size();i++){
                    if(disembarkTime.get(i)>max){
                        max=disembarkTime.get(i);
                    }
                }
                double min =99999;
                for(int i=0; i<disembarkTime.size();i++){
                    if(disembarkTime.get(i)<min){
                        min=disembarkTime.get(i);
                    }
                }



                double max1 =-9999999;
                for(int i=0; i<embarkTime.size();i++){
                    if(embarkTime.get(i)>max1){
                        max1=embarkTime.get(i);
                    }
                }
                double min1 =99999;
                for(int i=0; i<embarkTime.size();i++){
                    if(embarkTime.get(i)<min1){
                        min1=embarkTime.get(i);
                    }
                }
                int sum=0;
                for(int i=0; i<passengerListDisembark.size(); i++){
                    sum+=passengerListDisembark.get(i)+passengerListEmbark.get(i);
                }

                int sumDisembark=0;
                for(int i=0; i<passengerListDisembark.size();i++){
                    sumDisembark+=passengerListDisembark.get(i);
                }

                int sumEmbark =0;
                for(int i=0; i<passengerListEmbark.size(); i++){
                    sumEmbark+= passengerListEmbark.get(i);
                }
                System.out.println("######STATISTICS######");
                System.out.println(currentThread().getName()+": Maximum disembark time was: "+max+"s");
                System.out.println(currentThread().getName()+": Minimum disembark time was: "+min+"s");
                System.out.println(currentThread().getName()+": Maximum embark time was: "+max1+"s");
                System.out.println(currentThread().getName()+": Minimum embark time was: "+min1+"s");
                System.out.println(currentThread().getName()+": Total 6 planes were served");
                System.out.println(currentThread().getName()+": Total "+sum+" passengers were served ");
                System.out.println(currentThread().getName()+": Total "+sumDisembark+" passengers disembarked");
                System.out.println(currentThread().getName()+": Total "+sumEmbark+" passengers embarked");
                return;


            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
