package ua.javarush.encoder.languageservice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextAnalyzer {
    public static String getLanguage(Integer[] buffer) {
        String langCode = "";
        int mostMatches = 0;
        for (Map.Entry<String, List<Character>> currentLang : AlphabetRepository.getLangMap().entrySet()) {
            int containingNumber = 0;
            for (int bufferedChar : buffer) {
                if (currentLang.getValue().contains((char) bufferedChar)) {
                    containingNumber++;
                }
            }
            if (containingNumber == buffer.length) {
                return currentLang.getKey();
            }
            if (containingNumber > mostMatches) {
                mostMatches = containingNumber;
                langCode = currentLang.getKey();
            }
        }
        return langCode;
    }

    //TODO: too much for
    public static String getLanguage(String[] buffer) {
        String langCode = "";
        int mostMatches = 0;
        for (Map.Entry<String, List<Character>> currentLang : AlphabetRepository.getLangMap().entrySet()) {
            int containingNumber = 0;
            for (String line : buffer) {
                for (char character : line.toCharArray()) {
                    if (currentLang.getValue().contains(character)) {
                        containingNumber++;
                    }
                }
            }
            if (containingNumber == buffer.length) {
                return currentLang.getKey();
            }
            if (containingNumber > mostMatches) {
                mostMatches = containingNumber;
                langCode = currentLang.getKey();
            }
        }
        return langCode;
    }

    private HashMap<Character, Integer> analyzeCharFrequency(String[] buffer) {
        String language = TextAnalyzer.getLanguage(buffer);
        List<Character> alphabet = AlphabetRepository.getAlphabet(language);
        HashMap<Character, Integer> charFrequency = new HashMap<>();
        for (char alphabetChar : alphabet) {
            int freq = 0;
            for (String line : buffer) {
                for (char currentChar : line.toCharArray()) {
                    if (currentChar == alphabetChar) {
                        freq++;
                    }
                }
            }
            charFrequency.put(alphabetChar, freq);
        }
        return charFrequency;
    }

    public List<Character> getSortedByFreqChars(String[] buffer){
        List<Character> sortedChars = new ArrayList<>();
        HashMap<Character, Integer> charFrequency = analyzeCharFrequency(buffer);
        List<Map.Entry<Character,Integer>> map= new ArrayList<>(charFrequency.entrySet());
        map.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue()-entry1.getValue();
            }
        });
        for (Map.Entry<Character,Integer> m: map) {
            sortedChars.add(m.getKey());
        }
        return sortedChars;
    }
}

