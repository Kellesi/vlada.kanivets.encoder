package ua.javarush.encoder;

import java.util.*;

public class CaesarCipher {
    final int key;
    CaesarCipher(int keyValue){
        key = keyValue;
        initAlphabet();
    }
    private String mode;
    private static final char[] symbols ={'.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static Map<Integer, Character> engAlphabet = new LinkedHashMap<>();
    private static Map<Integer, Character> ukrAlphabet = new HashMap<>();

    public static int encryptIt(int character) {
        boolean isBig=false;
        // TODO: think about it
        if (character>=((int)'A') && character<=((int)'Z')){
            isBig=true;
            character-=32;

        }
        Character encrypted = engAlphabet.get(character);
        return encrypted!=null? (isBig? encrypted+32: encrypted):character;
    }

    public void setMode(String mode) {
        this.mode=mode;
    }
    private void initAlphabet(){
        for (char i ='a'; i<='z';i++){
            engAlphabet.put((int)i,i);
        }
        for (char symb:symbols) {
            engAlphabet.put((int) symb,symb);
        }
//        for (Map.Entry<Integer,Character> ch: engAlphabet.entrySet()) {
//            System.out.println(ch);
//        }
        encryptAlphabet(key);
//        for (Map.Entry<Integer,Character> ch: engAlphabet.entrySet()) {
//           System.out.println(ch);
//        }
    }
    private void encryptAlphabet(int key){
        ArrayList<Character> rolledChars = new ArrayList<>(engAlphabet.values());
        Collections.rotate(rolledChars,key);
        int i =0;
        for (Map.Entry<Integer,Character> map:engAlphabet.entrySet()) {
            map.setValue(rolledChars.get(i++));
        }
    }

}
