package org.example;
import ListOfProducts.ProductList;
import Services.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter space-separated pairs of values (e.g., key1 value1 key2 value2): ");
        String userInput = scanner.nextLine();

        String[] tokens = userInput.split("\\s+");


        System.out.println("Pairs of values:");
        for (int i = 0; i < tokens.length; i += 2) {
            String key = tokens[i];
            String value = tokens[i + 1];
            System.out.println("Key: " + key + ", Value: " + value);
        }

        // Close the scanner when you're done with it (optional, but good practice)
        scanner.close();
        System.out.println("Hello world!");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        ProductList Li = new ProductList();
        HashMap<String,Integer>ListOfAll = Li.ProductionLis1();
        PriceofProduct price = new PriceofProduct("Pen",ListOfAll);
        CollectTaxes taxes = new CollectTaxes();
        GstCollection gst = new GstCollection("Paper");

       Future<Integer>P = executor.submit(price);
        Future<Integer>T = executor.submit(taxes);
        Future<Integer>G = executor.submit(gst);

 try{

     Integer Price = P.get() ;
      if (Price == -1) {
          System.out.println("Product is Not Avaiable So break");
          System.exit(-1);
      }
     Integer Taxes = T.get();
     Integer GST = G.get();

     executor.shutdown();
     System.out.println(" Price " + Price);
     System.out.println(" Taxes " + Taxes);
     System.out.println(" GST " + GST);
     Integer sum = Price + Taxes + GST;
     System.out.println(sum);
 }catch (InterruptedException | ExecutionException e){
     e.printStackTrace();
 }



    }
}