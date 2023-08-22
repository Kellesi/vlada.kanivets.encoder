package ua.javarush.encoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CaesarCipher {
    private final LinkedHashMap<Integer, Character> encryptedAlphabet = new LinkedHashMap<>();

    public Integer[] encryptIntegerArray(Integer[] buffer, int key, EncryptorMode mode, List<Character> alphabet) {
        setEncryptedAlphabet(key, mode, alphabet);
        Integer[] encryptedArray = new Integer[buffer.length];
        int i = 0;
        for (int character : buffer) {
            encryptedArray[i++] = encryptChar(character);
        }
        return encryptedArray;
    }

    public String[] encryptStringArray(String[] buffer, int key, EncryptorMode mode, List<Character> alphabet) {
        setEncryptedAlphabet(key, mode, alphabet);
        String[] encryptedArray = new String[buffer.length];
        StringBuilder newLine = new StringBuilder();
        int i = 0;
        for (String line : buffer) {
            for (char character : line.toCharArray()) {
                newLine.append((char) encryptChar(character));
            }
            encryptedArray[i++] = newLine.toString();
        }
        return encryptedArray;
    }

    private int encryptChar(int character) {
        Character encrypted = null;
        if (Character.isUpperCase(character)) {
            if (encryptedAlphabet.containsKey(Character.toLowerCase(character))) {
                encrypted = Character.toUpperCase(encryptedAlphabet.get(Character.toLowerCase(character)));
            }
        } else {
            encrypted = encryptedAlphabet.get(character);
        }
        return encrypted != null ? encrypted : character;
    }

    private void setEncryptedAlphabet(int key, EncryptorMode mode, List<Character> alphabet) {
        for (char character : alphabet) {
            encryptedAlphabet.put((int) character, character);
        }
        if (mode == EncryptorMode.DECRYPT) {
            return;
        }
        ArrayList<Character> rolledChars = new ArrayList<>(encryptedAlphabet.values());
        Collections.rotate(rolledChars, key);
        int i = 0;
        for (Map.Entry<Integer, Character> map : encryptedAlphabet.entrySet()) {
            map.setValue(rolledChars.get(i++));
        }
    }
}
