package ua.javarush.encoder.cryptology;

import ua.javarush.encoder.languageservice.AlphabetRepository;
import ua.javarush.encoder.languageservice.TextInfo;
import java.util.List;

public class BruteForce {
    private final String[] text;
    private final TextInfo textInfo;
    private TextInfo textInfo2;
    private static final int DEFAULT_NUMBER_OF_GENERATED_KEYS = 3;

    public BruteForce(String[] text) {
        this.text = text;
        textInfo = new TextInfo(text);
    }

    public BruteForce(String[] text, String[] textSample) {
        this.text = text;
        textInfo = new TextInfo(text);
        textInfo2 = new TextInfo(textSample);
    }

    private List<Character> getReferenceFrequency() {
        List<Character> referenceFrequency;
        if (textInfo2 != null && textInfo.getTextLanguage().equals(textInfo2.getTextLanguage())) {
            referenceFrequency = textInfo2.getCharFrequency();
        } else {
            referenceFrequency = textInfo.getAlphabetCharFrequency();
        }
        return referenceFrequency;
    }

    public int[] getPossibleKeys(int keysToGenerate, boolean charsEncrypted) {
        List<Character> alphabet = textInfo.getAlphabet();
        if (charsEncrypted) {
            alphabet.addAll(AlphabetRepository.getRepository().getAlphabet("Symbols"));
        }
        List<Character> encryptedFrequency = textInfo.getCharFrequency();
        List<Character> referenceFrequency = getReferenceFrequency();
        int[] possibleKeys = new int[keysToGenerate];

        for (int i = 0; i < keysToGenerate; i++) {
            int key = (alphabet.size() - alphabet.indexOf(encryptedFrequency.get(i)) + alphabet.indexOf(referenceFrequency.get(0))) % alphabet.size();
            possibleKeys[i] = key;
        }
        return possibleKeys;
    }

    public int[] getPossibleKeys(int keysToGenerate) {
        return getPossibleKeys(keysToGenerate, false);
    }

    public int[] getPossibleKeys(boolean charsEncrypted) {
        return getPossibleKeys(DEFAULT_NUMBER_OF_GENERATED_KEYS, charsEncrypted);
    }

    public int[] getPossibleKeys() {
        return getPossibleKeys(DEFAULT_NUMBER_OF_GENERATED_KEYS, false);
    }

    public void printSampleOfText(int[] possibleKey, int numberOfLines) {
        List<Character> alphabet = textInfo.getAlphabet();
        CaesarCipher cipher = new CaesarCipher(alphabet);
        for (int key : possibleKey) {
            String[] decryptedText = cipher.decryptArray(text, key);
            System.out.println("Possible key: " + key);
            for (int i = 0; i < numberOfLines; i++) {
                System.out.println(decryptedText[i]);
            }
        }
    }

    public void printSampleOfText(int[] possibleKey) {
        int defaultNumberOfLines = 2;
        printSampleOfText(possibleKey, defaultNumberOfLines);
    }
}
