package Services;

import java.util.concurrent.Callable;

public class CollectTaxes  implements Callable<Integer> {
    @Override
    public Integer call() throws Exception{
        return 10;
    }
}
