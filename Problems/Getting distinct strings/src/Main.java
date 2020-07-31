import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


class Operator {

    public static UnaryOperator<List<String>> unaryOperator = list -> {
        return list.stream().distinct().collect(Collectors.toList());
    }; // Write yor code here
}