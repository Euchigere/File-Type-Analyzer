package analyzer;

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