package ua.javarush.encoder.LanguageService;

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

    static {
        RepoFileService.readMap();
    }

    public static HashMap<String, List<Character>> getLangMap() {
        return new HashMap<>(languageToAlphabet);
    }

    public static ArrayList<Character> getLang(String langCode) {
        return new ArrayList<>(languageToAlphabet.get(langCode));
    }

    public static void addLanguage(String languageName, List<Character> languageCharacters) {
        languageToAlphabet.put(languageName, languageCharacters);
        RepoFileService.writeMap();
    }
    public static void removeLanguage(String languageName){
        for (DefaultLanguages lang: DefaultLanguages.values()) {
            if (languageName.equalsIgnoreCase(lang.name())){
               throw new IllegalArgumentException("You can't delete default language");
            }
        }
        languageToAlphabet.remove(languageName);
        RepoFileService.writeMap();
    }
    public static HashSet<String> availableLanguages(){
        return new HashSet<>(languageToAlphabet.keySet());
    }

    private static class RepoFileService {
        private static final File repoFile = new File("src/main/resources/LangRepo.txt");

        static HashMap<String, List<Character>> readMap() {
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(repoFile))) {
                languageToAlphabet = (HashMap<String, List<Character>>) reader.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            return languageToAlphabet;
        }

        static void writeMap() {
            try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(repoFile))) {
                writer.writeObject(languageToAlphabet);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
