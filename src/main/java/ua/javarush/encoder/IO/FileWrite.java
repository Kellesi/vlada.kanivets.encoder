package ua.javarush.encoder.IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public void write(Integer[] buffer,String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new CreateNewFileName().createNewName(filePath)))) {
            for (int character : buffer) {
                writer.write(character);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeLines(String[] buffer, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new CreateNewFileName().createNewName(filePath)))) {
            for (String line : buffer) {
                writer.write(line + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class CreateNewFileName {
        private static final String ENCRYPTED = " [ENCRYPTED]";
        private static final String DECRYPTED = " [DECRYPTED]";

        private String createNewName(String fromPath) {
            String name;
            String extension = fromPath.substring(fromPath.lastIndexOf("."));

            if (fromPath.contains(ENCRYPTED)) {
                name = fromPath.replace(ENCRYPTED, DECRYPTED);
            } else if (fromPath.contains(DECRYPTED)) {
                name = fromPath.replace(DECRYPTED, ENCRYPTED);
            } else {
                name = fromPath.replace(extension, ENCRYPTED + extension);
            }
            return name;
        }
    }
}
