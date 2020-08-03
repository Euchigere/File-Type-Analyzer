package analyzer;

import java.nio.file.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Exactly 4 parameters required");
        }
        String algorithm = args[0];
        String filePath = args[1];
        String pattern = args[2];
        String fileType = args[3];

        FileTypeAnalyzer analyzeFile;
        long startTime = System.nanoTime();
        if ("--naive".equals(algorithm)) {
            analyzeFile = new FileTypeAnalyzer(new NaiveAlgorithm());
        } else {
            analyzeFile = new FileTypeAnalyzer(new KPMAlgorithm());
        }
        analyzeFile.checkFileType(pattern, filePath, fileType);
        long elapsedNanos = System.nanoTime() - startTime;
        System.out.println("it took " + elapsedNanos + " seconds");
    }
}

interface SearchAlgorithm{
    boolean findPattern(String pattern, byte[] unknownFileByteArray);
}

class NaiveAlgorithm implements SearchAlgorithm {
    @Override
    public boolean findPattern(String pattern, byte[] unknownFileByteArray) {
        int i = 0;
        int j = 0;

        while (i < unknownFileByteArray.length && j < pattern.length()) {
            if (unknownFileByteArray[i] == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length() - 1) {
                    return true;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return false;
    }
}

class KPMAlgorithm implements SearchAlgorithm {
    @Override
    public boolean findPattern(String pattern, byte[] unknownFileByteArray) {
        /* 1 */
        int[] prefixFunc = prefixFunction(pattern);
        int j = 0;
        /* 2 */
        int i;
        for (i = 0; i < unknownFileByteArray.length; i++) {
            /* 3 */
            while (j > 0 && unknownFileByteArray[i] != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            /* 4 */
            if (unknownFileByteArray[i] == pattern.charAt(j)) {
                j += 1;
            }
            /* 5 */
            if (j == pattern.length()) {
                return true;
            }
        }
        /* 6 */
        return false;
    }

    private int[] prefixFunction(String pattern) {
        /* 1 */
        int[] prefixFunc = new int[pattern.length()];

        /* 2 */
        for (int i = 1; i < pattern.length(); i++) {
            /* 3 */
            int j = prefixFunc[i - 1];

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }

            /* 4 */
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }

            /* 5 */
            prefixFunc[i] = j;
        }

        /* 6 */
        return prefixFunc;
    }
}

class FileTypeAnalyzer {
    SearchAlgorithm algorithm;

    public FileTypeAnalyzer(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public void checkFileType(String pattern, String filePath, String fileType) {
        byte[] unknownFileByteArray = null;

        try {
            unknownFileByteArray = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isMatch = algorithm.findPattern(pattern, unknownFileByteArray);

        if (isMatch) {
            System.out.println(fileType);
        } else {
            System.out.println("Unknown file type");
        }
    }
}
