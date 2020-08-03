import java.util.*;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        List<Integer> array = Arrays
                .stream(in.nextLine().split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 0; i < array.size(); i++) {
            array.remove(i);
        }
        for (int i = array.size() - 1; i >= 0; i--) {
            System.out.print(array.get(i) + " ");
        }
    }
}