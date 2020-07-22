import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int noOfCharacters = Integer.parseInt(in.nextLine());
        String[] symbols = in.nextLine().split("\\s+");

        if (noOfCharacters < 1) {
            System.out.println(0);
            return;
        }

        Set<String> strSubstring = distinctSubstring(str);
        Set<String> newSubstring;
        for (String symbol : symbols) {
            newSubstring = distinctSubstring(symbol + str);
            boolean isNewSubstring = newSubstring.removeAll(strSubstring);
            System.out.print((isNewSubstring ? newSubstring.size() : 0) + " ");
        }
    }

    static Set<String> distinctSubstring(String str) {
        Set<String> subString = new HashSet<>();

        for (int i = 0; i <= str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                subString.add(str.substring(i, j));
            }
        }
        return subString;
    }
}