package ua.javarush.encoder.cryptology;

import ua.javarush.encoder.languageservice.TextInfo;
import java.util.Arrays;
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

    public int[] getPossibleKeys(int keysToGenerate) {
        List<Character> alphabet = textInfo.getAlphabet();
        List<Character> encryptedFrequency = textInfo.getCharFrequency().subList(0, keysToGenerate);
        List<Character> referenceFrequency = getReferenceFrequency().subList(0, keysToGenerate);
        int[] possibleKeys = new int[keysToGenerate];

        for (int i = 0; i < keysToGenerate; i++) {
            int key = (alphabet.size() - alphabet.indexOf(encryptedFrequency.get(0)) + alphabet.indexOf(referenceFrequency.get(i))) % alphabet.size();
            possibleKeys[i] = key;
        }
        return possibleKeys;
    }

    public int[] getPossibleKeys() {
        return getPossibleKeys(DEFAULT_NUMBER_OF_GENERATED_KEYS);
    }

    public void printSampleOfText(int[] possibleKey) {
        List<Character> alphabet = textInfo.getAlphabet();
        CaesarCipher cipher = new CaesarCipher(alphabet);
        for (int key : possibleKey) {
            String[] decryptedText = cipher.decryptArray(text, key);
            System.out.println("Possible key: "+key);
            System.out.println(Arrays.toString(decryptedText));
        }
    }
}
