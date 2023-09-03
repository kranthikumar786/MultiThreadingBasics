package objectClasses;

import java.util.concurrent.TimeUnit;

public class Printer {

    // lock object for synchronization
    private final Object lock = new Object();

    public void printDocument(String document , String threadName){

        synchronized (lock) {

            System.out.println(threadName + "  is Printing" + document);
            // simulTE THE TIME IT TAKES TO PRINT
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "finished printing" + document);
        }
    }
}
