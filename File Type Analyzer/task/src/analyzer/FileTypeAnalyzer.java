package analyzer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class FileTypeAnalyzer {
    SearchAlgorithm algorithm;

    public FileTypeAnalyzer(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String processPatterns(String patternsFile, String filePath, String fileName) {
        List<List<String>> lists = new ArrayList<>();
        try (BufferedReader fis = new BufferedReader( new FileReader(patternsFile))) {
            String line;
            while((line = fis.readLine()) != null) {

                List<String> arr = new ArrayList<>();
                arr.addAll(
                        Arrays.asList(line.split(";"))
                );
                lists.add(arr);
            }
        } catch (Exception e) {
            System.out.println("problem reaading pattern files");
        }
        Collections.sort(lists, (t1, t2) -> t2.get(0).compareTo(t1.get(0)));
        String pattern;
        String fileType;
        boolean isMatch;
        for (List<String> list : lists) {
            pattern = list.get(1).replace("\"", "");
            fileType = list.get(2).replace("\"", "");

            isMatch = checkFileType(pattern, filePath);
            if (isMatch) {
                return fileName + ": " + fileType;
            }
        }
        return fileName + ": Unknown file type";
    }

    public boolean checkFileType(String pattern, String filePath) {
        byte[] unknownFileByteArray = null;

        try {
            unknownFileByteArray = Files.readAllBytes(Paths.get(filePath));

        } catch (IOException e) {
            System.out.println("unable to read file");
        }

        return algorithm.findPattern(pattern, unknownFileByteArray);
    }
}
