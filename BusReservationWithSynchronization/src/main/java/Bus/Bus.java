package Bus;

import java.util.ArrayList;
import java.util.List;
public class Bus {
 private final int totalSeats;
 private  int availableSeats;
 private List<String> bookedSeats = new ArrayList<>();
 public Bus(int totalSeats){
     this.totalSeats = totalSeats;
     this.availableSeats = totalSeats;
 }

 public synchronized boolean bookSeats(String passengerName,int numSeats) {
     if (numSeats <= availableSeats) {
         for (int i = 0; i < numSeats; i++) {
             String seat = "Seat" + (totalSeats - availableSeats + i + 1);
             bookedSeats.add(seat);
         }

           availableSeats -= numSeats;
           System.out.println(passengerName + "booked" + numSeats + "Seats(s)");
             return true;
      }else{
         System.out.println(passengerName + " could not book" + numSeats + "seats(s)");
       return false;
     }
 }
 public synchronized List<String> getBookedSeats(){
     return new ArrayList<>(bookedSeats);
 }
}
