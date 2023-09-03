package org.example;

import Bus.*;
import Passenger.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Bus bus = new Bus(20); // creating a bus with 20 seats
        Passenger passenger1 = new Passenger("Passenger 1");
        passenger1.setBookedSeats(3);

        Passenger passenger2 = new Passenger("Passenger 2");
        passenger2.setBookedSeats(2);

        Passenger passenger3 = new Passenger("Passenger 3");
        passenger3.setBookedSeats(5);
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

        // Display Seat Availability chart
        System.out.println("Seat Available Chart");
        for (int i = 1 ; i <= 20 ; i++){
            System.out.println("Seat" + i + " : " + (bus.isSeatAvailable(i) ? "Available" : "Booked"));
        }
         // Display booked passengers
        List<Passenger> bookedPassengers = bus.getBookedPassengers();
        System.out.println("\n Booked Passengers");

         for (Passenger passenger : bookedPassengers){
             System.out.println("Name" + passenger.getName1() + " , Seats Booked" + passenger.getBookedSeats());

         }

         // Simulate passenger Cancellations
         bus.cancelReservation(passenger1);
         bus.cancelReservation(passenger2);
         // Display Updated seat Availabliti chart andavailable seats
        System.out.println("\n Updated Seats Availbility Charts : ");
        for ( int i = 1 ; i <= 20 ; i++){
            System.out.println("Seat" + i + " : " + (bus.isSeatAvailable(i) ? "Available" : "Booked"));
        }
        System.out.println("Available Seats" + bus.getAvailableSeats());

         // Display booikng History
        Map<Passenger,List<Integer>> bookingHistory = bus.getBookingHistory();
        System.out.println("\n Booking History");
        for ( Map.Entry<Passenger,List<Integer>> entry : bookingHistory.entrySet()) {
            Passenger passenger = entry.getKey();
            List<Integer> bookedSeats = entry.getValue();
            System.out.println("Name" + passenger.getName() + ",Boooked Seats" + bookedSeats);
        }

        // Display Payment History
        Map<Passenger, Double>paymentHistory = bus.getPaymentHistory();
        System.out.println("\n Payment History");
        for ( Map.Entry<Passenger,Double> entry : paymentHistory.entrySet() ){

            Passenger passenger = entry.getKey();
            Double paymentAmount = entry.getValue();
            System.out.println("Name :" + passenger.getName() + ", Payment Amount : " + paymentAmount);
        }

        // Display Loyalty points
        Map<Passenger,Integer>loyaltyPoints =bus.getLoyaltyPoints();
        System.out.println("\n Loyalty Points :");
        for(Map.Entry<Passenger,Integer> entry : loyaltyPoints.entrySet() ) {
            Passenger passenger = entry.getKey();
            Integer points = entry.getValue();
            System.out.println("Name : " + passenger.getName() + ",Loyalty Points" + points);
        }
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