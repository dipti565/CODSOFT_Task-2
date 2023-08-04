import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class task_two {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Word Counter!");

        // Step 1: Prompt the user to enter a text or provide a file
        System.out.print("Enter 'text' to input text or 'file' to provide a file: ");
        String inputChoice = scanner.next().toLowerCase();

        String inputText = "";
        if (inputChoice.equals("text")) {
            // Read input text
            System.out.print("Enter your text: ");
            scanner.nextLine(); // Consume the newline character left by next() or nextInt()
            inputText = scanner.nextLine();
        } else if (inputChoice.equals("file")) {
            // Read input from a file
            System.out.print("Enter the file path: ");
            String filePath = scanner.next();
            try {
                inputText = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
                System.exit(1);
            }
        } else {
            System.out.println("Invalid input choice. Exiting the program.");
            System.exit(1);
        }

        // Step 3: Split the string into an array of words using space or punctuation as delimiters
        String[] words = inputText.split("[\\p{Punct}\\s]+");

        // Step 4: Initialize a counter variable to keep track of the number of words
        int totalWordCount = 0;

        // Step 7: Define common words or stop words to ignore
        String[] stopWords = { "the", "a", "an", "is", "of", "in", "and", "to", "on" };
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        // Step 5: Iterate through the array of words and increment the counter for each word encountered
        for (String word : words) {
            word = word.toLowerCase(); // Convert word to lowercase for case-insensitivity
            if (!word.isEmpty() && !isStopWord(word, stopWords)) {
                totalWordCount++;
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        // Step 6: Display the total count of words to the user
        System.out.println("Total number of words: " + totalWordCount);

        // Step 8: Provide statistics like the number of unique words and word frequency
        System.out.println("Number of unique words: " + wordFrequencyMap.size());
        System.out.println("Word Frequency:");
        for (String word : wordFrequencyMap.keySet()) {
            int frequency = wordFrequencyMap.get(word);
            System.out.println(word + ": " + frequency);
        }

        // Step 10: GUI can be implemented using Java Swing or JavaFX, which is beyond the scope of this code snippet.
    }

    // Helper method to read the content of a file
    private static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes);
    }

    // Helper method to check if a word is a stop word
    private static boolean isStopWord(String word, String[] stopWords) {
        for (String stopWord : stopWords) {
            if (word.equals(stopWord)) {
                return true;
            }
        }
        return false;
    }
}
