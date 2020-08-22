package analyzer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Exactly 2 parameters required");
        }
        String fileFolder = args[0];
        String patterns = args[1];

        File file = new File(fileFolder);
        File[] files = file.listFiles();
        // create array list of callables
        List<Callable<String>> callables = new ArrayList<>();
        for (File filePath : files) {
            // add pattern matcher executables to the list
            callables.add(
                    () -> {
                        FileTypeAnalyzer analyzeFile = new FileTypeAnalyzer(new RabinKarpAlgorithm());
                        return analyzeFile.processPatterns(patterns, filePath.getPath(), filePath.getName());
                    }
            );
        }

        // create executor service with maximum number of thread
        ExecutorService executor = Executors.newFixedThreadPool(10);
        try {
            // invoke all the callable
            List<Future<String>> futures = executor.invokeAll(callables);
            for (Future<String> future : futures) {
                // get the results and print
                String text = future.get();
                System.out.println(text);
            }
            executor.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}