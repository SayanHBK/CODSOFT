
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = "";

        System.out.println("Enter text or provide the path to a file:");

        // Read user input or file
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.toLowerCase().endsWith(".txt")) {
                try {
                    File file = new File(input);
                    Scanner fileScanner = new Scanner(file);
                    while (fileScanner.hasNextLine()) {
                        inputText += fileScanner.nextLine() + " ";
                    }
                    fileScanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found.");
                    scanner.close();
                    return;
                }
            } else {
                inputText = input;
            }
        }

        // Split the string into an array of words using space or punctuation as delimiters
        String[] words = inputText.split("[\\s.,?!:;()\"']+");

        // Initialize variables
        int totalWords = words.length;
        int uniqueWords = 0;
        Map<String, Integer> wordFrequency = new HashMap<>();
        Set<String> stopWords = new HashSet<>(Arrays.asList("a", "an", "the", "in", "on", "of", "and", "or", "is", "are"));

        // Count words and calculate statistics
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) {
                wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
                if (wordFrequency.get(word.toLowerCase()) == 1) {
                    uniqueWords++;
                }
            }
        }

        // Display results
        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }
}

