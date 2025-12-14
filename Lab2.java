import java.util.*;

public class Lab2 {
    public static void main(String[] args) {
        try {
            StringBuilder text = new StringBuilder();
            text.append("How are you? This is the first sentence. ");
            text.append("What are you doing today? This is the second sentence! ");
            text.append("Were you at the store? Why do you ask? ");
            text.append("This is the last sentence without a question mark.");
            
            System.out.println("=== LABORATORY WORK #2 ===");
            System.out.println("Task: Find unique words of specific length in questions");
            System.out.println("\nOriginal text:");
            System.out.println(text.toString());
            System.out.println();
            
            int wordLength = 3;
            System.out.println("Searching for words with length: " + wordLength);
            System.out.println();

            Set<StringBuilder> resultWords = findWordsInQuestions(text, wordLength);
            
            System.out.println("=== RESULT ===");
            if (resultWords.isEmpty()) {
                System.out.println("No words of length " + wordLength + " found in questions.");
            } else {
                System.out.println("Unique words with length " + wordLength + " from questions:");
                int count = 1;
                for (StringBuilder word : resultWords) {
                    System.out.println(count + ". " + word.toString());
                    count++;
                }
                System.out.println("\nTotal unique words found: " + resultWords.size());
            }
            
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static Set<StringBuilder> findWordsInQuestions(StringBuilder text, int length) {
        Set<StringBuilder> result = new TreeSet<>((sb1, sb2) -> {
            String s1 = sb1.toString().toLowerCase();
            String s2 = sb2.toString().toLowerCase();
            return s1.compareTo(s2);
        });
        
        if (text == null || text.length() == 0 || length <= 0) {
            return result;
        }
        
        List<StringBuilder> sentences = splitIntoSentences(text);
        
        for (StringBuilder sentence : sentences) {
            if (isQuestion(sentence)) {
                List<StringBuilder> words = extractWords(sentence);
                
                for (StringBuilder word : words) {
                    if (word.length() == length) {
                        StringBuilder wordCopy = new StringBuilder(word);
                        toLowerCase(wordCopy);
                        result.add(wordCopy);
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
                sentences.add(new StringBuilder(currentSentence));
                currentSentence = new StringBuilder();
            }
        }
        
        if (currentSentence.length() > 0) {
            sentences.add(currentSentence);
        }
        
        return sentences;
    }
    
    private static boolean isQuestion(StringBuilder sentence) {
        if (sentence.length() == 0) {
            return false;
        }
        
        for (int i = sentence.length() - 1; i >= 0; i--) {
            char c = sentence.charAt(i);
            if (!Character.isWhitespace(c)) {
                return c == '?';
            }
        }
        
        return false;
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
            words.add(currentWord);
        }
        
        return words;
    }
    
    private static void toLowerCase(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.setCharAt(i, Character.toLowerCase(c));
            }
        }
    }
}