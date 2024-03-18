
//Give Question: Implement reading of a large file 100K lines
// and writing to multiple files of n lines
// (n will be passed as argument)
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Files {
    public static void main(String[] args) {
        // Specify the input file path
        String inputFilePath = "/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/sample2.txt";

        // Prompt user for the number of lines per file
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of lines per file: ");
        int linesPerFile = scanner.nextInt();

        // Prompt user for the number of output files
        System.out.print("Enter the number of output files: ");
        int numberOfOutputFiles = scanner.nextInt();

        // Read the content of the input file
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            // Calculate the number of lines per chunk
            int totalLines = lines.size();
            int linesPerChunk = linesPerFile * numberOfOutputFiles;
            int chunks = (int) Math.ceil((double) totalLines / linesPerChunk);
            // Ensure the number of chunks does not exceed the number of output files
            chunks = Math.min(chunks, numberOfOutputFiles);

            // Write content to output files

            for (int i = 0; i < chunks; i++) {
                int startIndex = i * linesPerChunk;
                int endIndex = Math.min(startIndex + linesPerChunk, totalLines);
                List<String> chunkLines = lines.subList(startIndex, endIndex);
                
                for (int j = 0; j < numberOfOutputFiles && i * numberOfOutputFiles + j < chunks; j++) {
                    int fileIndex = i * numberOfOutputFiles + j;
                    String outputFilePath = "/Users/ts-sathi.reddy/Desktop/java_examples/SpringBoot/example/src/out" + (fileIndex + 1) + ".txt";

                    try (FileWriter outputFileWriter = new FileWriter(outputFilePath)) {
                        for (int k = j * linesPerFile; k < (j + 1) * linesPerFile && k < chunkLines.size(); k++) {
                            outputFileWriter.write(chunkLines.get(k) + "\n");
                        }
                    } catch (IOException e) {
                        System.out.println("Error writing to " + outputFilePath + ": " + e.getMessage());
                    }
                }
            }

            System.out.println("Files written successfully.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
