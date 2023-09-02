package Services;

import java.util.concurrent.Callable;

public class GstCollection implements Callable<Integer> {

    String productType;
    public GstCollection(String productType){
        this.productType = productType;
    }

    @Override
    public Integer call() throws Exception {


       if ( productType.equals("Paper"))
            return  12;
        return 10;
    }
}
