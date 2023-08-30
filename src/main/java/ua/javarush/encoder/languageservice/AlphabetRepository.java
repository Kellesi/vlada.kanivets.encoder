package ua.javarush.encoder.languageservice;

import ua.javarush.encoder.exception.IORuntimeException;
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
import java.util.Map;
import java.util.Set;

public class AlphabetRepository {
    private static final HashMap<String, List<Character>> languageToAlphabet = RepoFileService.readMap(RepoFileService.ALPHABET_CHARS);
    private static final HashMap<String, List<Character>> freq = RepoFileService.readMap(RepoFileService.CHAR_FREQUENCY_STAT);
    private static AlphabetRepository repository;

    private AlphabetRepository() {
    }

    public static AlphabetRepository getRepository() {
        if (repository == null) {
            repository = new AlphabetRepository();
        }
        return repository;
    }

    public Map<String, List<Character>> getLangMap() {
        return new HashMap<>(languageToAlphabet);
    }

    public List<Character> getAlphabet(String langCode) {
        return new ArrayList<>(languageToAlphabet.get(langCode));
    }

    public List<Character> getAlphabetWithSymbols(String langCode) {
        List<Character> alphabetWithSymbols = new ArrayList<>();
        alphabetWithSymbols.addAll(languageToAlphabet.get(langCode));
        alphabetWithSymbols.addAll(languageToAlphabet.get("Symbols"));
        return alphabetWithSymbols;
    }

    public List<Character> getCharFrequency(String langCode) {
        return new ArrayList<>(freq.get(langCode));
    }

    public void addFreq(String languageName, List<Character> languageCharacters) {
        freq.put(languageName, languageCharacters);
        RepoFileService.writeMap(RepoFileService.CHAR_FREQUENCY_STAT, freq);
    }

    public void addLanguage(String languageName, List<Character> languageCharacters) {
        languageToAlphabet.put(languageName, languageCharacters);
        RepoFileService.writeMap(RepoFileService.ALPHABET_CHARS, languageToAlphabet);
    }

    public void removeLanguage(String languageName) {
        for (DefaultLanguages lang : DefaultLanguages.values()) {
            if (languageName.equalsIgnoreCase(lang.name())) {
                throw new IllegalArgumentException("You can't delete default language");
            }
        }
        languageToAlphabet.remove(languageName);
        RepoFileService.writeMap(RepoFileService.ALPHABET_CHARS, languageToAlphabet);
    }

    public Set<String> availableLanguages() {
        return new HashSet<>(languageToAlphabet.keySet());
    }

    private static class RepoFileService {
        private static final File ALPHABET_CHARS = new File("src/main/resources/LangRepo.txt");
        private static final File CHAR_FREQUENCY_STAT = new File("src/main/resources/charFrequencyStat.txt");

        static HashMap<String, List<Character>> readMap(File fileName) {
            HashMap<String, List<Character>> map = new HashMap<>();
            try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName))) {
                map = (HashMap<String, List<Character>>) reader.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                throw new IORuntimeException(ex);
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
