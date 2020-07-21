package analyzer;

import java.nio.file.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    static byte[] unknownFileByteArray;
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Exactly 3 parameters required");
        }
        String unknownFile = args[0];
        byte[] pattern = args[1].getBytes();
        String isType = args[2];

        try {
            unknownFileByteArray = Files.readAllBytes(Paths.get(unknownFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        int j = 0;

        while (i < unknownFileByteArray.length && j < pattern.length) {
            if (unknownFileByteArray[i] == pattern[j]) {
                i++;
                j++;

                if (j == pattern.length - 1) {
                    System.out.println(isType);
                    return;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        System.out.println("Unknown file type");
    }
}
