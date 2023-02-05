/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;
import java.util.concurrent.Semaphore;

public class t5 extends Thread{
    public static Semaphore s = new Semaphore(0);
    public static int planeNo;

    public t5(){

    }

    public t5(int planeNo){
        this.planeNo = planeNo;
    }
    @Override
    public void run() {
        while (true){
            t1 adas = new t1();
            t4 aaa = new t4();
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(currentThread().getName()+"-"+"Plane "+ planeNo+ " finished refuelling");
            t4.sem2.release();

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.sem.release();
        }
    }
}
