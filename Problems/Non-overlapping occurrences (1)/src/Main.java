import java.util.*;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String pattern = in.nextLine();
        String text = in.nextLine();

        if (pattern.isEmpty()) {
            System.out.println("1\n0");
            return;
        }

        if (pattern.length() > text.length()) {
            System.out.println(0);
            return;
        }
        List <Integer> occurrences;
        occurrences = KMPSearch(text, pattern);
        System.out.println(occurrences.size());
        occurrences.forEach(e -> System.out.print(e + " "));
    }

    public static int[] prefixFunction(String str) {
        /* 1 */
        int[] prefixFunc = new int[str.length()];

        /* 2 */
        for (int i = 1; i < str.length(); i++) {
            /* 3 */
            int j = prefixFunc[i - 1];

            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }

            /* 4 */
            if (str.charAt(i) == str.charAt(j)) {
                j += 1;
            }

            /* 5 */
            prefixFunc[i] = j;
        }

        /* 6 */
        return prefixFunc;
    }

    public static List<Integer> KMPSearch(String text, String pattern) {
        /* 1 */
        int[] prefixFunc = prefixFunction(pattern);
        ArrayList<Integer> occurrences = new ArrayList<>();
        int j = 0;
        /* 2 */
        int i;
        for (i = 0; i < text.length(); i++) {
            /* 3 */
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            /* 4 */
            if (text.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }
            /* 5 */
            if (j == pattern.length()) {
                occurrences.add(i - j + 1);
                j = 0;
            }
        }
        /* 6 */
        return occurrences;
    }
}