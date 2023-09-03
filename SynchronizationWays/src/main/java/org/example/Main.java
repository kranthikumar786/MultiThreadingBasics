package org.example;

import objectClasses.Printer;
import taskClasses.PrintingTask;

public class Main {
    public static void main(String[] args) {

        Printer sharedPrinter = new Printer();
        PrintingTask p1 = new PrintingTask(sharedPrinter,"Document1");
        Thread thread1 = new Thread(p1);
        PrintingTask p2 = new PrintingTask(sharedPrinter,"Document2");
        Thread thread2 = new Thread(p2);
         thread1.start();
         thread2.start();
         thread1.stop();
         thread2.stop();

         /**
          * OutPut :
          * Thread-0  is PrintingDocument1
          * Thread-0finished printingDocument1
          * Thread-1  is PrintingDocument2
          * Thread-1finished printingDocument2
          * */
        }
}
