package Services;

import java.util.concurrent.Callable;

import java.util.HashMap;
import ListOfProducts.*;
public class PriceofProduct  implements Callable<Integer> {

     String Product ;
     HashMap<String,Integer> ListOfAll;
    public PriceofProduct(String Product, HashMap<String,Integer>ListOfAll){
        this.Product = Product;
        this.ListOfAll = ListOfAll;
    }
@Override
    public Integer call() throws Exception{

          /**
           * HashMap<String,Integer>ProductPrice = new HashMap<>();
          ProductPrice.put("Pen", 100);
          ProductPrice.put("Ball" , 200);
           */

          if (ListOfAll.containsKey(Product)) {
              return ListOfAll.get(Product);
          }
         return -1;
    }
}
