import java.util.*;
import java.util.concurrent.*;


class FutureUtils {

    public static int howManyIsDone(List<Future> items) {
        // write your code here
        int count = 0;
        for (Future future : items) {
            if (future.isDone()) count++;
        }
        return count;
    }

}