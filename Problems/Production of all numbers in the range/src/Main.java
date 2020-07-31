import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (x, y) -> {
        long product = x;
        for (long i = x + 1; i <= y; i++) {
            product *= i;
        }
        return product;
    }; // Write your code here
}