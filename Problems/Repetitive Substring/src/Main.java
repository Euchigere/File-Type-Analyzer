import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
         // put your code here
        Scanner scan = new Scanner(System.in);
        String inputText = scan.nextLine();
        int patternLength = scan.nextInt();
        String outputText = rabinKarp(inputText, patternLength);
        System.out.println(outputText);
    }

    public static long charToLong(char ch) {
        return ch - 'A' + 1;
    }

    public static String rabinKarp(String text, int patternLength) {
        int a = 887;
        long m = 1_000_000_016_531L;

        StringBuffer s = new StringBuffer(text);

        long currSubstrHash = 0;
        long pow = 1;

        for (int i = 0; i < patternLength; i++) {
            currSubstrHash += charToLong(s.charAt(text.length() - patternLength + i)) * pow;
            currSubstrHash %= m;

            if (i != patternLength - 1) {
                pow = pow * a % m;
            }
        }

        Set<Long> occurrences = new HashSet<>();

        for (int i = text.length(); i >= patternLength; i--) {

            if (occurrences.contains(currSubstrHash)) {
                return s.substring(i - patternLength, i);
            }


            occurrences.add(currSubstrHash);

            if (i > patternLength) {
                currSubstrHash = (currSubstrHash - charToLong(text.charAt(i - 1)) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong(text.charAt(i - patternLength - 1))) % m;
            }
        }

        return "does not contain";
    }
}