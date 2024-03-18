import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileWriterThread extends Thread {
    private List<String> lines;
    private int startIndex;
    private int linesPerFile;
    private String outputFilePath;

    public FileWriterThread(List<String> lines, int startIndex, int linesPerFile, String outputFilePath) {
        this.lines = lines;
        this.startIndex = startIndex;
        this.linesPerFile = linesPerFile;
        this.outputFilePath = outputFilePath;
    }

    @Override
    public void run() {
        try (FileWriter outputFileWriter = new FileWriter(outputFilePath)) {
            for (int k = startIndex; k < startIndex + linesPerFile && k < lines.size(); k++) {
                outputFileWriter.write(lines.get(k) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to " + outputFilePath + ": " + e.getMessage());
        }
    }
}

class Loadbalancing {
    public static void main(String[] args) {
        // Specify the input file path
        String inputFilePath = "/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/sample2.txt";

        // Prompt user for the number of output files
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of output files: ");
        int numberOfOutputFiles = scanner.nextInt();

        // Read the content of the input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Calculate the number of lines per file
            int totalLines = lines.size();
            int linesPerFile = totalLines * numberOfOutputFiles;

            // Create threads for each output file
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < numberOfOutputFiles; i++) {
                int startIndex = i * linesPerFile;
                String outputFilePath = "/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/out" + (i + 1) + ".txt";
                Thread thread = new FileWriterThread(lines, startIndex, linesPerFile, outputFilePath);
                threads.add(thread);
            }
            
            // Start all threads
            for (Thread thread : threads) {
                thread.start();
            }

            // Wait for all threads to finish
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Files written successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
