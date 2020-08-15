package analyzer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly 4 parameters required");
        }
        String fileFolder = args[0];
        String patterns = args[1];

        File file = new File(fileFolder);
        File[] files = file.listFiles();
        List<Callable<String>> callables = new ArrayList<>();
        for (File filePath : files) {

            callables.add(
                    () -> {
                        FileTypeAnalyzer analyzeFile = new FileTypeAnalyzer(new KPMAlgorithm());
                        return analyzeFile.processPatterns(patterns, filePath.getPath(), filePath.getName());
                    }
            );
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            List<Future<String>> futures = executor.invokeAll(callables);
            for (Future<String> future : futures) {
                String text = future.get(10, TimeUnit.SECONDS);
                System.out.println(text);
            }
            executor.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}