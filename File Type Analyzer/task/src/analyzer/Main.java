package analyzer;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Exactly 4 parameters required");
        }
        String folder = args[0];
        String pattern = args[1];
        String fileType = args[2];


        File file = new File(folder);
        String[] files = file.list();
        List<Callable<String>> callables = new ArrayList<>();
        for (String fileName : files) {

            callables.add(
                    () -> {
                        FileTypeAnalyzer analyzeFile = new FileTypeAnalyzer(new KPMAlgorithm());
                        return analyzeFile.checkFileType(pattern, folder +"/" + fileName, fileName, fileType);
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