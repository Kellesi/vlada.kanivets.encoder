package ua.javarush.encoder.languageservice;

import java.util.List;

public class TextInfo {
    private final String[] text;
    private String language;
    private List<Character> alphabet;
    private List<Character> alphabetCharFrequency;
    private List<Character> textCharsFrequency;

    public TextInfo(String[] text) {
        this.text = text;
    }

    public String getTextLanguage() {
        if (language == null) {
            TextAnalyzer analyzer = new TextAnalyzer(text);
            language = analyzer.getLanguage();
        }
        return language;
    }

    public String[] getText() {
        return text;
    }

    public List<Character> getCharFrequency() {
        if (textCharsFrequency == null) {
            TextAnalyzer analyzer = new TextAnalyzer(text);
            textCharsFrequency = analyzer.getSortedByFreqChars();
        }
        return textCharsFrequency;
    }

    public List<Character> getAlphabet() {
        language = getTextLanguage();
        if (alphabet == null) {
            AlphabetRepository alphabetRepository = AlphabetRepository.getRepository();
            alphabet = alphabetRepository.getAlphabet(language);
        }
        return alphabet;
    }

    public List<Character> getAlphabetCharFrequency() {
        language = getTextLanguage();
        if (alphabetCharFrequency == null) {
            AlphabetRepository alphabetRepository = AlphabetRepository.getRepository();
            alphabetCharFrequency = alphabetRepository.getCharFrequency(language);
        }
        return alphabetCharFrequency;
    }
}
