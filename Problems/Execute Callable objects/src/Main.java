import java.util.List;
import java.util.concurrent.*;


class FutureUtils {

    public static int executeCallableObjects(List<Future<Callable<Integer>>> items) {
        // write your code here
        Integer item = 0;
        for (int i = items.size() - 1; i >= 0; i--) {
            Future futureItem = (Future) items.get(i);
            try {
                Callable<Integer>  callableItem = (Callable) futureItem.get();
                item += callableItem.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return item;
    }

}