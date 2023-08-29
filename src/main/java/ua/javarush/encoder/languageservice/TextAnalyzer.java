package ua.javarush.encoder.languageservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextAnalyzer {
    private final String[] buffer;

    public TextAnalyzer(String[] buffer) {
        this.buffer = buffer;
    }

    public String getLanguage() {
        AlphabetRepository alphabetRepository = AlphabetRepository.getRepository();
        String langCode = "";
        int mostMatches = 0;
        for (Map.Entry<String, List<Character>> currentLang : alphabetRepository.getLangMap().entrySet()) {
            int numberOfMatches = countCharContain(currentLang.getValue());
            if (numberOfMatches == buffer.length) {
                return currentLang.getKey();
            }
            if (numberOfMatches > mostMatches) {
                mostMatches = numberOfMatches;
                langCode = currentLang.getKey();
            }
        }
        return langCode;
    }

    private int countCharContain(List<Character> currentLang) {
        int numberOfMatches = 0;
        for (String line : buffer) {
            for (char character : line.toCharArray()) {
                if (currentLang.contains(character)) {
                    numberOfMatches++;
                }
            }
        }
        return numberOfMatches;
    }

    public Map<Character, Integer> analyzeCharFrequency() {
        AlphabetRepository alphabetRepository = AlphabetRepository.getRepository();
        String language = getLanguage();
        List<Character> alphabet = alphabetRepository.getAlphabetWithSymbols(language);
        HashMap<Character, Integer> charFrequency = new HashMap<>();
        for (char alphabetChar : alphabet) {
            int freq = countCharMatches(alphabetChar);
            charFrequency.put(alphabetChar, freq);
        }
        return charFrequency;
    }

    private int countCharMatches(char alphabetChar) {
        int freq = 0;
        for (String line : buffer) {
            for (char currentChar : line.toCharArray()) {
                if (currentChar == alphabetChar) {
                    freq++;
                }
            }
        }
        return freq;
    }

    public List<Character> getSortedByFreqChars() {
        List<Character> sortedChars = new ArrayList<>();
        HashMap<Character, Integer> charFrequency = new HashMap<>(analyzeCharFrequency());
        List<Map.Entry<Character, Integer>> map = new ArrayList<>(charFrequency.entrySet());
        map.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        for (Map.Entry<Character, Integer> m : map) {
            sortedChars.add(m.getKey());
        }
        return sortedChars;
    }
}
