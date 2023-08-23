package ua.javarush.encoder.LanguageService;

import java.util.List;
import java.util.Map;

public class LanguageAnalyzer {
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

    public static String getLanguage(String[] buffer) {
        String langCode = "";
        int mostMatches = 0;
        for (Map.Entry<String, List<Character>> currentLang : AlphabetRepository.getLangMap().entrySet()) {
            int containingNumber = 0;
            for (String line : buffer) {
                for (char character:line.toCharArray()) {
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
}
