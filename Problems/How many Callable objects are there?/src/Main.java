import java.util.concurrent.*;


class FutureUtils {
    public static int determineCallableDepth(Callable callable) throws Exception {
        // write your code here
        int count = 0;
        Object result = callable.call();
        if (result instanceof Callable) {
            return count += 1 + determineCallableDepth((Callable) result);
        } else {
            return 1;
        }
    }
}