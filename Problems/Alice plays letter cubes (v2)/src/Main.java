import java.util.*;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String newStr = "";
        int noOfSubstrings = 1;
        int index = 0;
        int[] prefix;

        while (index < str.length()) {
            int len = newStr.length();
            newStr = str.charAt(index) + newStr;
            prefix = prefixFunction(newStr);
            noOfSubstrings += (len + 1) - Arrays.stream(prefix).max().getAsInt();
            index++;
        }
        System.out.println(noOfSubstrings);
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
}