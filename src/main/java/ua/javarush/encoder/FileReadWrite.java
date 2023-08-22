package ua.javarush.encoder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileReadWrite {
    private final String source;

    FileReadWrite(String source) {
        this.source = source;
    }

    public Integer[] read() {
        ArrayList<Integer> array = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while (reader.ready()) {
                array.add(reader.read());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return array.toArray(new Integer[0]);
    }

    public String[] readLines() {
        ArrayList<String> array = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while (reader.ready()) {
                array.add(reader.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return array.toArray(new String[0]);
    }

    public void write(Integer[] buffer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new CreateNewFileName().createNewName()))) {
            for (int character : buffer) {
                writer.write(character);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeLines(String[] buffer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new CreateNewFileName().createNewName()))) {
            for (String line : buffer) {
                writer.write(line+"\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class CreateNewFileName {
        private static final String ENCRYPTED = " [ENCRYPTED]";
        private static final String DECRYPTED = " [DECRYPTED]";

        private String createNewName() {
            String name;
            String extension = source.substring(source.lastIndexOf("."));

            if (source.contains(ENCRYPTED)) {
                name = source.replace(ENCRYPTED, DECRYPTED);
            } else if (source.contains(DECRYPTED)) {
                name = source.replace(DECRYPTED, ENCRYPTED);
            } else {
                name = source.replace(extension, ENCRYPTED + extension);
            }
            return name;
        }
    }
}
