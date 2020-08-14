package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class FileTypeAnalyzer {
    SearchAlgorithm algorithm;

    public FileTypeAnalyzer(SearchAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String checkFileType(String pattern, String filePath, String file, String fileType) {
        byte[] unknownFileByteArray = null;

        try {
            unknownFileByteArray = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("unable to read file");
        }

        boolean isMatch = algorithm.findPattern(pattern, unknownFileByteArray);

        if (isMatch) {
            return file + ": " + fileType;
        } else {
            return file + ": Unknown file type";
        }
    }
}
