package ua.javarush.encoder.cryptology;

import ua.javarush.encoder.languageservice.AlphabetRepository;
import ua.javarush.encoder.languageservice.TextAnalyzer;
import java.util.List;

public class BruteForce {
    String [] buffer;
    public BruteForce(String [] buffer){
        this.buffer=buffer;
    }

    public int[] getPossibleKeys(int keysToGenerate) {
        String language = TextAnalyzer.getLanguage(buffer);
        List<Character> alphabet = AlphabetRepository.getAlphabet(language);
        List<Character> encryptedFrequency = new TextAnalyzer().getSortedByFreqChars(buffer).subList(0, keysToGenerate);
        List<Character> alphabetFrequency = AlphabetRepository.getCharFrequency(language).subList(0, keysToGenerate);
        int[] possibleKeys = new int[keysToGenerate];

        for (int i = 0; i < keysToGenerate; i++) {
            int key = (alphabet.size() - alphabet.indexOf(encryptedFrequency.get(i)) + alphabet.indexOf(alphabetFrequency.get(i))) % alphabet.size();
            possibleKeys[i] = key;
        }
        return possibleKeys;
    }

    public void getSampleOfText(int [] possibleKey){
        String language = TextAnalyzer.getLanguage(buffer);
        List<Character> alphabet = AlphabetRepository.getAlphabet(language);
        CaesarCipher cipher = new CaesarCipher(alphabet);
        for (int key: possibleKey) {
            String [] decryptedText=cipher.encryptArray(buffer,key,EncryptorMode.DECRYPT);
            System.out.println(decryptedText[0]);
        }
    }
}
