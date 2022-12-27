package scenarios;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbers implements Callable {
    int result = 0;
    int max;

    public RandomNumbers(int max) {
	this.max = max;
    }


    @Override
    public Integer call() throws Exception {
	try {
	    int bound = max;
	    if (bound < 2) {
		return bound;
	    }
	    result = ThreadLocalRandom.current().nextInt(1, bound);

	} catch (Exception e) {
	    System.out.println("Exception");
	}
	return result;
    }
}
