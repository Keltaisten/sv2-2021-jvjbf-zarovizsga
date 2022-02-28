package sentences;

import java.util.Locale;

public class SentenceTransformer {
    public String shortenSentence(String sentence) {
        validateSentence(sentence);
        int spaces = (int) sentence.chars().mapToObj(c -> (char) c).filter(k -> k.equals(' ')).count();
        if (spaces >= 4) {
            String[] fields = sentence.split(" ");
            return fields[0] + " ... " + fields[spaces];
        } else {
            return sentence;
        }
    }

    private void validateSentence(String sentence) {
        if (sentence.charAt(0) != sentence.toUpperCase(Locale.ROOT).charAt(0)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        } else if (sentence.charAt(sentence.length() - 1) != '.' && sentence.charAt(sentence.length() - 1) != '!' &&
                sentence.charAt(sentence.length() - 1) != '?') {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
