package ua.javarush.encoder.Cryptology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaesarCipher {

    public Integer[] encryptIntegerArray(Integer[] buffer, int key, EncryptorMode mode, List<Character> alphabet) {
        Integer[] encryptedArray = new Integer[buffer.length];
        int i = 0;
        for (int character : buffer) {
            encryptedArray[i++] = encryptChar((char) character, key, mode, alphabet);
        }
        return encryptedArray;
    }

    public String[] encryptStringArray(String[] buffer, int key, EncryptorMode mode, List<Character> alphabet) {
        String[] encryptedArray = new String[buffer.length];
        StringBuilder newLine = new StringBuilder();
        int i = 0;
        for (String line : buffer) {
            for (char character : line.toCharArray()) {
                newLine.append((char) encryptChar(character, key, mode, alphabet));
            }
            encryptedArray[i++] = newLine.toString();
        }
        return encryptedArray;
    }

    public int encryptChar(char character, int key, EncryptorMode mode, List<Character> alphabet) {
        ArrayList<Integer> encryptedCharIndexes = rollIndex(key, alphabet.size(), mode);
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

    private ArrayList<Integer> rollIndex(int key, int size, EncryptorMode mode) {
        ArrayList<Integer> encryptedCharIndexes = new ArrayList<>();
        key = key % size;
        if (mode == EncryptorMode.DECRYPT) {
            key = -key;
        }
        for (int i = 0; i < size; i++) {
            encryptedCharIndexes.add(i);
        }
        Collections.rotate(encryptedCharIndexes, key);
        return encryptedCharIndexes;
    }
}
