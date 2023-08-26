package ua.javarush.encoder.languageservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AlphabetRepository {
    private static HashMap<String, List<Character>> languageToAlphabet = new HashMap<>();
    private static HashMap<String, List<Character>> freq = new HashMap<>();

    static {
        languageToAlphabet=RepoFileService.readMap(RepoFileService.repoFile);
        freq=RepoFileService.readMap(RepoFileService.charFrequencyStat);
    }

    public static HashMap<String, List<Character>> getLangMap() {
        return new HashMap<>(languageToAlphabet);
    }

    public static ArrayList<Character> getAlphabet(String langCode) {
        return new ArrayList<>(languageToAlphabet.get(langCode));
    }
    public static ArrayList<Character> getCharFrequency(String langCode) {
        return new ArrayList<>(freq.get(langCode));
    }
    public static void addFreq(String languageName, List<Character> languageCharacters) {
        freq.put(languageName, languageCharacters);
        RepoFileService.writeMap(RepoFileService.charFrequencyStat, freq);
    }
    public static void addLanguage(String languageName, List<Character> languageCharacters) {
        languageToAlphabet.put(languageName, languageCharacters);
        RepoFileService.writeMap(RepoFileService.repoFile, languageToAlphabet);
    }
    public static void removeLanguage(String languageName){
        for (DefaultLanguages lang: DefaultLanguages.values()) {
            if (languageName.equalsIgnoreCase(lang.name())){
               throw new IllegalArgumentException("You can't delete default language");
            }
        }
        languageToAlphabet.remove(languageName);
        RepoFileService.writeMap(RepoFileService.repoFile, languageToAlphabet);
    }
    public static HashSet<String> availableLanguages(){
        return new HashSet<>(languageToAlphabet.keySet());
    }

    private static class RepoFileService {
        private static final File repoFile = new File("src/main/resources/LangRepo.txt");
        private static final File charFrequencyStat = new File("src/main/resources/charFrequencyStat.txt");

        static HashMap<String, List<Character>> readMap(File fileName) {
            HashMap<String, List<Character>> map = new HashMap<>();
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))) {
                map = (HashMap<String, List<Character>>) reader.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            return map;
        }

        static void writeMap(File fileName, HashMap<String, List<Character>> mapToWrite) {
            HashMap<String, List<Character>> map = new HashMap<>(mapToWrite);
            try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
                writer.writeObject(map);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
