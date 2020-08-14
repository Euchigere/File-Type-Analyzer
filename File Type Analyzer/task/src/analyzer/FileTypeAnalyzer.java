package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
