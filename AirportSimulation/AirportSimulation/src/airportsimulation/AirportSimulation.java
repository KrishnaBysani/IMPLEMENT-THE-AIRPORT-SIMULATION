/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class AirportSimulation{

//    int num1,num2;
//    int choice;
//    int calc=0;
//    Scanner sc = new Scanner(System.in);
//    void input(){
//        System.out.println("Enter the Numbers: \n");
//        num1 = sc.nextInt();
//        num2 = sc.nextInt();
//    }
//
//    void choiceOfSign(){
//        System.out.println("Type 1 for Addition or 2 for Subtraction\n");
//        choice = sc.nextInt();
//    }
//
//    void calculation(){
//        if(choice==1){
//            calc= num1+num2;
//        }
//        if(choice == 2){
//            calc = num1-num2;
//        }
//    }
//    public static void main(String[] args) {
//
//	Airport t = new Airport();
//	t.start(t.input());
//
//    }

    public static void main(String[] args) {

        System.out.println("#####Airport Simulation#####");

//        for (int i=0; i<6; i++){
//            Thread pilot = new t2();
//            pilot.setName("Pilot: ");
//            pilot.start();
//        }

        Thread atc = new t1();
        Thread plane = new t2();
        Thread pilot = new t3();
        Thread passengers = new t4();
        Thread fuelTank = new t5();


        atc.setName("Thread-ATC");
        pilot.setName("Thread-Pilot");
        passengers.setName("Thread-Passenger");
        fuelTank.setName("Thread- Fuel Tank");


        atc.start();
        plane.start();
        pilot.start();
        passengers.start();
        fuelTank.start();
//        oilTanker.start();


    }
}