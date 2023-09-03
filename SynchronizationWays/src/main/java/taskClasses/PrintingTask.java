package taskClasses;

import objectClasses.Printer;

public class PrintingTask  implements Runnable{

    private final Printer printer;

    private final String document;
    public PrintingTask(Printer printer , String document){
        this.printer = printer;
        this.document =document;
    }
    @Override
    public void run(){
        Thread currentThread = Thread.currentThread();
        String threadName = currentThread.getName();
        printer.printDocument(document,threadName);
    }
}
