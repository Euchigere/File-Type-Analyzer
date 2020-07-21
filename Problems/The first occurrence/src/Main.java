import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String pattern = in.nextLine();
        String text = in.nextLine();

        System.out.println(containsPattern(text, pattern));
    }

    public static int containsPattern(String text, String pattern) {
        if (text.length() < pattern.length()) {
            return -1;
        }

        if (pattern.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            boolean patternIsFound = true;

            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    patternIsFound = false;
                    break;
                }
            }

            if (patternIsFound) {
                return i;
            }
        }

        return -1;
    }
}