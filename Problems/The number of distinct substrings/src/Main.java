import java.util.*;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine().trim();

        if (str.isEmpty()) {
            System.out.println(1);
            System.exit(1);
        }

        int patternCount = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            patternCount += rabinKarp(str, i);
        }

        System.out.println(patternCount + 1);
    }

    public static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    public static int rabinKarp(String text, int patternLength) {
        int a = 53;
        long m = 1_000_000_000 + 9;

        long currSubstrHash = 0;
        long pow = 1;
        int len = text.length() - patternLength;

        for (int i = 0; i < len; i++) {
            currSubstrHash += charToLong(text.charAt(text.length() - len + i)) * pow;
            currSubstrHash %= m;

            if (i != len - 1) {
                pow = pow * a % m;
            }
        }

        Set<Long> occurrences = new HashSet<>();

        for (int i = text.length(); i >= len; i--) {
            occurrences.add(currSubstrHash);

            if (i > len) {
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - len - 1))) % m;
            }
        }

        return occurrences.size();
    }
}