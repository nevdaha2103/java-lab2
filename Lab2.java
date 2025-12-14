import java.util.*;

public class Lab2 {
    public static void main(String[] args) {
        try {
            StringBuilder text = new StringBuilder(
                "How are you? This is the first sentence. What are you doing today? " +
                "This is the second sentence! Were you at the store? Why do you ask? " +
                "This is the last sentence without a question mark."
            );
            
            System.out.println("=== LABORATORY WORK #2 ===");
            System.out.println("Text processing using StringBuilder");
            System.out.println("Task: Find unique words of specific length in questions");
            System.out.println("\nOriginal text:");
            System.out.println(text.toString());
            System.out.println();

            int wordLength = 3;
            System.out.println("Searching for words with length: " + wordLength);
            System.out.println();
            
            Set<String> resultWords = findWordsInQuestions(text, wordLength);
            
            System.out.println("=== RESULT ===");
            if (resultWords.isEmpty()) {
                System.out.println("No words of length " + wordLength + " found in questions.");
            } else {
                System.out.println("Unique words with length " + wordLength + " from questions:");
                int count = 1;
                for (String word : resultWords) {
                    System.out.println(count + ". " + word);
                    count++;
                }
                System.out.println("\nTotal unique words found: " + resultWords.size());
            }
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static Set<String> findWordsInQuestions(StringBuilder text, int length) {
        Set<String> result = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        
        if (text == null || text.length() == 0 || length <= 0) {
            return result;
        }
        
        List<StringBuilder> sentences = splitIntoSentences(text);
        
        System.out.println("Processing " + sentences.size() + " sentences...");
        
        for (StringBuilder sentence : sentences) {
            if (isQuestion(sentence)) {
                List<StringBuilder> words = extractWords(sentence);
                
                for (StringBuilder word : words) {
                    if (word.length() == length) {
                        result.add(word.toString().toLowerCase());
                    }
                }
            }
        }
        
        return result;
    }
    
    private static List<StringBuilder> splitIntoSentences(StringBuilder text) {
        List<StringBuilder> sentences = new ArrayList<>();
        StringBuilder currentSentence = new StringBuilder();
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            currentSentence.append(c);
            
            if (c == '.' || c == '!' || c == '?') {
                String sentenceStr = currentSentence.toString().trim();
                if (!sentenceStr.isEmpty()) {
                    sentences.add(new StringBuilder(sentenceStr));
                }
                currentSentence = new StringBuilder();
            }
        }

        String lastSentence = currentSentence.toString().trim();
        if (!lastSentence.isEmpty()) {
            sentences.add(new StringBuilder(lastSentence));
        }
        
        return sentences;
    }
    
    private static boolean isQuestion(StringBuilder sentence) {
        if (sentence.length() == 0) {
            return false;
        }
        
        String trimmed = sentence.toString().trim();
        return trimmed.endsWith("?");
    }
    
    private static List<StringBuilder> extractWords(StringBuilder sentence) {
        List<StringBuilder> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            
            if (Character.isLetter(c) || c == '\'' || c == '-') {
                currentWord.append(c);
            } else {
                if (currentWord.length() > 0) {
                    words.add(new StringBuilder(currentWord));
                    currentWord = new StringBuilder();
                }
            }
        }
        
        if (currentWord.length() > 0) {
            words.add(new StringBuilder(currentWord));
        }
        
        return words;
    }
}