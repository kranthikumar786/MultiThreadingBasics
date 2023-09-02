package org.example;

import Bus.*;
import Passenger.*;

import java.util.List;
public class Main {
    public static void main(String[] args) {

        Bus bus = new Bus(7); // creating a bus with 20 seats
        Passenger passenger1 = new Passenger("Passenger 1", 3, bus);
        Passenger passenger2 = new Passenger("Passenger 2", 3, bus);
        Passenger passenger3 = new Passenger("Passenger 3", 3, bus);

        passenger1.start();
        passenger2.start();
        passenger3.start();

        try {

            passenger1.join();
            passenger2.join();
            passenger3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display Booked seats
        List<String> bookedSeats = bus.getBookedSeats();
        System.out.println("Booked Seats : " + bookedSeats);
    }
}
/***
 *
 * Input :
 *        Number of Buses 3 , 3 different passengers are trying to 3 for each .
 *        When No Synchronization  , which is not correct , data inconsistency we can observe.
 * Output :
 * Passenger 3booked3Seats(s)
 * Passenger 2booked3Seats(s)
 * Passenger 1booked3Seats(s)
 * Booked Seats : [Seat1, Seat2, Seat2, Seat3, Seat3, Seat1, Seat8, Seat9]
 *
 *    Input :
 *            Number of Buses 3 , 3 different passengers are trying to 3 for each .
 *  *        When With Synchronization  , which is correct , data consistency we can observe.
 *
 *
 * Output :
 * Passenger 1booked3Seats(s)
 * Passenger 3booked3Seats(s)
 * Passenger 2 could not book3seats(s)
 * Booked Seats : [Seat1, Seat2, Seat3, Seat4, Seat5, Seat6]
 *
 */