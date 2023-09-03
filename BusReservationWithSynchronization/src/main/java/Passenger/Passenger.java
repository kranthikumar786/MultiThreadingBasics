package Passenger;

import Bus.*;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Thread {

    private  String name;
    private int bookedSeats;
    public Bus bus;
    public Passenger(String name){
        this.name = name;
    }
    public int getBookedSeats() {
        return bookedSeats;
    }
    public String getName1(){
        return name;
    }
    public void setBookedSeats(int bookedSeats){
        this.bookedSeats = bookedSeats;
    }
    @Override
     public void run(){
        // Simulate passsenger booking behaviour here
        List<Integer> selectedSeats = new ArrayList<>();
        for ( int i = 1 ;i <= bookedSeats ; i++){
            selectedSeats.add(i);
        }
         if (bus != null) {
             bus.bookSeats(this, bookedSeats, selectedSeats);
          }
    }

}
