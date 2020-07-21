import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String pattern = in.nextLine();
        String text = in.nextLine();

        containsPattern(text, pattern);
    }

    public static void containsPattern(String text, String pattern) {
        if (text.length() < pattern.length()) {
            System.out.println("0");
            return;
        }

        if (pattern.isEmpty()) {
            System.out.println("1\n0");
            return;
        }

        List<Integer> indexOfOccurrence;
        indexOfOccurrence = new ArrayList<>();
        int i = 0;
        while (i < text.length() - pattern.length() + 1) {
            boolean patternIsFound = true;

            int j = 0;
            for (j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    patternIsFound = false;
                    break;
                }
            }

            if (patternIsFound) {
                indexOfOccurrence.add(i);
                i += j - 1;
            }
            i++;
        }

        System.out.println(indexOfOccurrence.size());
        indexOfOccurrence.forEach(e -> System.out.print(e + " "));
    }
}