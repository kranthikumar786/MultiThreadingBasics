package Bus;

import Passenger.Passenger;
import enumes.PaymentMethod;
import enumes.PaymentStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bus {
 private final int totalSeats;
 private  int availableSeats;
 private List<Passenger> bookedPassengers = new ArrayList<>();
 private boolean [] seatAvailability;

 private Map<Passenger,List<Integer>> bookingHistory = new HashMap<>();
 private Map<Passenger,Double>paymentHistory = new HashMap<>();
 private Map<Passenger,Integer>loyaltyPoints = new HashMap<>();
 private double seatPrice = 25.0;
 private double loyaltyDiscount = 5.0;
 public Bus(int totalSeats){
     this.totalSeats = totalSeats;
     this.availableSeats = totalSeats;
     this.seatAvailability =new boolean[totalSeats];
     for (int i = 0 ;i < totalSeats ; i++){
         seatAvailability[i] = true;
     }
 }

 public synchronized boolean bookSeats(Passenger passenger,int numSeats,List<Integer> selectedSeats) {
     if (numSeats <= availableSeats && selectedSeats.size() == numSeats) {
         double totalPrice = numSeats * seatPrice;
         for (int seatNumber : selectedSeats) {
             if (seatNumber >= 1 && seatNumber <= totalSeats && seatAvailability[seatNumber]) {
                 seatAvailability[seatNumber - 1] = false;
             } else {
                 System.out.println(passenger.getName() + "Selected an Invalid Seat");
                 return false;
             }
         }

         passenger.setBookedSeats(numSeats);
         bookedPassengers.add(passenger);
         availableSeats -= numSeats;
         System.out.println(passenger.getName() + "booked" + numSeats + "seat(s)");

         // Calculate the discounted price for loyal passenger
         double discountPrice = calculateDiscountedPrice(passenger,totalPrice);
          System.out.println("Total Price : " + totalPrice);
          System.out.println("Discounted Price" + discountPrice);
         // Store booking History
         bookingHistory.put(passenger, selectedSeats);
         // simulate sending a booking confimation email
         sendBookingConfirmationEmail(passenger);
         // Simulate Payment Processing
         PaymentStatus paymentStatus = simulatePaymentProcessingWithRetries(passenger,discountPrice,PaymentMethod.PAYPAL);
         if ( paymentStatus == PaymentStatus.SUCCESS) {
             System.out.println("Payment Processed Successful " + passenger.getName());
             // Update loyalty points for returning passengers
             updateLoyaltyPoints(passenger,numSeats);

             // Store payment history
             paymentHistory.put(passenger,discountPrice);

         } else if (paymentStatus == PaymentStatus.INSUFFICIENT_FUNDS) {
             System.out.println("Payment failed for" + passenger.getName());
         } else {
             System.out.println("Payment failed for" + passenger.getName());
         }


         return paymentStatus == PaymentStatus.SUCCESS;
     } else {
         System.out.println(passenger.getName() + "Could not book" + numSeats + "seat(s) ,Seats not available or invalid seat selection");
         return false;
     }
 }

 private double calculateDiscountedPrice(Passenger passenger , double totalPrice){
      // Apply a discount for loyal Passenger
     if ( loyaltyPoints.containsKey(passenger) ){
         int points = loyaltyPoints.get(passenger);
         if ( points >= 5) {
               // Apply a loyality discount for passengers with 5 or more points
          return  totalPrice - loyaltyDiscount;
         }
     }
     return totalPrice;
 }

 private PaymentStatus simulatePaymentProcessingWithRetries(Passenger passenger , double totalPrice , PaymentMethod paymentMethod){
      // Simulate payment processing with retires (i in a real system , you'd use payment gateway )

      int maxRetries = 3;
      for ( int retry = 1; retry <= maxRetries ; retry++) {

          PaymentStatus paymentStatus = processPayment(passenger,totalPrice,paymentMethod);

           if ( paymentStatus == PaymentStatus.SUCCESS){
               return  PaymentStatus.SUCCESS;
           } else if (retry < maxRetries) {
               System.out.println("Payment retry # " + retry + "for" + passenger.getName());
           } else {
                return paymentStatus;
           }
      }
      return PaymentStatus.PAYMENT_DECLINED;
 }
 private void updateLoyaltyPoints(Passenger passenger,int numSeats){

     // Update loyality points for returing passengers

     if ( loyaltyPoints.containsKey(passenger) ){
         int currentPoints = loyaltyPoints.get(passenger);
         int  newPoints = currentPoints + numSeats;
         loyaltyPoints.put(passenger,newPoints);
         System.out.println(passenger.getName() + "earned" + numSeats + "loyalty points");
     } else {
         loyaltyPoints.put(passenger,numSeats);
     }
 }
 public synchronized void cancelReservation(Passenger passenger){
      if ( bookedPassengers.contains(passenger) ){
          List<Integer> canceledSeats = bookingHistory.get(passenger);
        for ( int seatNumber : canceledSeats) {
            seatAvailability[seatNumber-1] = true;
        }
        bookedPassengers.remove(passenger);
        availableSeats += canceledSeats.size();
        System.out.println(passenger.getName() + "Canceled the resevation .");
      } else {
          System.out.println(passenger.getName() + "doed not have a reservation");

      }
 }

 public synchronized List<Passenger> getBookedPassengers(){
   return new ArrayList<>(bookedPassengers);
 }
 public synchronized boolean addToWaitlist(Passenger passenger) {
       if ( availableSeats == 0){
            // Bus is fully booked , add to waitlist
        System.out.println(passenger.getName() + "added to the waitlist");
        return true;
       } else {
        System.out.println(passenger.getName() + "Could not be added to the waitlist.seats are availBLE");
        return false;
       }
  }

 public synchronized int getAvailableSeats(){
     return availableSeats;
 }
 public synchronized boolean isSeatAvailable(int seatNumber){
     return seatNumber >= 1 && seatNumber <= totalSeats && seatAvailability[seatNumber-1] ;
 }

 public synchronized Map<Passenger,List<Integer>> getBookingHistory(){
     return new HashMap<>(bookingHistory);
 }
 public synchronized Map<Passenger,Double> getPaymentHistory(){
      return  new HashMap<>(paymentHistory);
 }
 public synchronized Map<Passenger,Integer> getLoyaltyPoints(){
     return new HashMap<>(loyaltyPoints);
 }

 private void sendBookingConfirmationEmail(Passenger passenger){
     System.out.println("Sending booing confirmation email to " + passenger.getName());
  // Simulate sending an email
     System.out.println("Booking confirmtion email sent to " + passenger.getName());
 }

 private PaymentStatus processPayment(Passenger passenger,double totalPrice, PaymentMethod paymentMethod){

      // Simulate Payment Processing (in a real System , you 'd  use a payment gateway)
    switch (paymentMethod) {
        case CREDIT_CARD:
             if (Math.random() < 0.9) {
                 return PaymentStatus.SUCCESS;
             } else {
                 return PaymentStatus.PAYMENT_DECLINED;
             }
        case PAYPAL:
            if (Math.random() < 0.95){
              return PaymentStatus.SUCCESS;
            } else {
             return PaymentStatus.INSUFFICIENT_FUNDS;
            }

        default:
            System.out.println("Invalid Payment Method");
            return PaymentStatus.PAYMENT_DECLINED;
    }
 }
}
