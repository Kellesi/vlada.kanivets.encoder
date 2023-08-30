package ua.javarush.encoder.cryptology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaesarCipher {
    private final List<Character> alphabet;

    public CaesarCipher(List<Character> alphabet) {
        this.alphabet = alphabet;
    }

    public String[] encryptArray(String[] buffer, int key) {
        if (key == 0) {
            return buffer;
        }
        String[] encryptedArray = new String[buffer.length];
        int i = 0;
        for (String line : buffer) {
            StringBuilder newLine = new StringBuilder();
            for (char character : line.toCharArray()) {
                newLine.append((char) encryptChar(character, key));
            }
            encryptedArray[i++] = newLine.toString();
        }
        return encryptedArray;
    }

    public String[] decryptArray(String[] buffer, int key) {
        key = -key;
        return encryptArray(buffer, key);
    }

    public int encryptChar(char character, int key) {
        ArrayList<Integer> encryptedCharIndexes = rollIndex(key, alphabet.size());
        int encrypted = -1;
        if (alphabet.contains(character) || alphabet.contains(Character.toLowerCase(character))) {
            if (Character.isUpperCase(character)) {
                int charPosition = alphabet.indexOf(Character.toLowerCase(character));
                encrypted = Character.toUpperCase(alphabet.get(encryptedCharIndexes.get(charPosition)));
            } else {
                encrypted = alphabet.get(encryptedCharIndexes.get(alphabet.indexOf(character)));
            }
        }
        return encrypted != -1 ? encrypted : character;
    }

    private ArrayList<Integer> rollIndex(int key, int size) {
        ArrayList<Integer> encryptedCharIndexes = new ArrayList<>();
        key = key % size;
        for (int i = 0; i < size; i++) {
            encryptedCharIndexes.add(i);
        }
        Collections.rotate(encryptedCharIndexes, key);
        return encryptedCharIndexes;
    }
}
