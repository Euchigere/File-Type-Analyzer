import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        List<Long> prefixHashValues = new ArrayList<>();
        String outIndex = "";
        prefixHashValues.add(0L);
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();

        for (int i = 1; i <= str.length(); i++) {
            long index = rabinKarp(str.substring(0, i));
            prefixHashValues.add(index);
            outIndex += index + (i == str.length() ? "\n" : " ");
        }
        long m = 1_000_000_000 + 9;
        int k = Integer.parseInt(scan.nextLine());
        for (int n = 0; n < k; n++) {
            int i = scan.nextInt();
            int j = scan.nextInt();

            outIndex += (m + prefixHashValues.get(j) - prefixHashValues.get(i)) % m  + (n == k - 1 ? "" : " ");
            scan.nextLine();
        }
        System.out.println(outIndex);
    }

    /* 1 */
    public static long charToLong(char ch) {
        return (long) (ch - 'A' + 1);
    }

    public static long rabinKarp(String text) {
        /* 2 */
        int a = 53;
        long m = 1_000_000_000 + 9;

        /* 3 */
        long textHash = 0;
        long pow = 1;

        for (int i = 0; i < text.length(); i++) {
            textHash += charToLong(text.charAt(i)) * pow;
            textHash %= m;

            if (i != text.length() - 1) {
                pow = pow * a % m;
            }
        }
        return textHash;
    }
}