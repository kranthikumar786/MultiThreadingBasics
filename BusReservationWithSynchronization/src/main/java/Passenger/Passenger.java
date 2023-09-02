package Passenger;

import Bus.Bus;

public class Passenger  extends  Thread{

    private  String name;
    private int numSeats;
    private Bus bus;

    public Passenger(String name, int numSeats,Bus bus){
        this.name = name;
        this.numSeats=numSeats;
        this.bus=bus;
    }
    @Override
    public void run() {
        bus.bookSeats(name, numSeats);
    }
}
