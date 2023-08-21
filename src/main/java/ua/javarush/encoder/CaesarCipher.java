package ua.javarush.encoder;

import java.util.*;

public class CaesarCipher implements Cipher {
    final int key;
    private EncryptorMode mode;
    private final String language;
    private static final String DEFAULT_LANGUAGE = "eng";

    private static final char[] SYMBOLS = {'.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static final char[] UKR_ALPHABET = {'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й',
            'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'};
    private final Map<Integer, Character> alphabet = new LinkedHashMap<>();

    CaesarCipher(int keyValue) {
        key = keyValue;
        language = DEFAULT_LANGUAGE;
        initAlphabet();
    }

    CaesarCipher(int keyValue, String textLanguage) {
        key = keyValue;
        language = textLanguage;
        initAlphabet();
    }

    @Override
    public int encryptIt(int character) {
        boolean isUpperCase = false;

        if (Character.isUpperCase(character)) {
            character = Character.toLowerCase(character);
            isUpperCase = true;
        }
        Character encrypted = alphabet.get(character);
        return encrypted != null ? (isUpperCase ? Character.toUpperCase(encrypted) : encrypted)
                : (isUpperCase ? Character.toUpperCase(character) : character);
    }

    private void encryptAlphabet(int key) {
        if (mode==EncryptorMode.DECRYPT) {
            key=-key;
        }
        ArrayList<Character> rolledChars = new ArrayList<>(alphabet.values());
        Collections.rotate(rolledChars, key);
        int i = 0;
        for (Map.Entry<Integer, Character> map : alphabet.entrySet()) {
            map.setValue(rolledChars.get(i++));
        }
    }

    public void setMode(String mode) {

    }

    private void initAlphabet() {
        if (language.equals("eng")) {
            for (char character = 'a'; character <= 'z'; character++) {
                alphabet.put((int) character, character);
            }
        } else if (language.equals("ukr")) {
            for (char character : UKR_ALPHABET) {
                alphabet.put((int) character, character);
            }
        }
        for (char symb : SYMBOLS) {
            alphabet.put((int) symb, symb);
        }
        encryptAlphabet(key);
    }
}
