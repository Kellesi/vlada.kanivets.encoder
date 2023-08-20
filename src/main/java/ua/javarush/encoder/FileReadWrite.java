package ua.javarush.encoder;

import java.io.*;

public class FileReadWrite {
    private final String source;

    FileReadWrite(String source) {
        this.source = source;
    }

    public void useCryptography() {

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new CreateName().createNewName()))) {

            while (reader.ready()) {
                writer.write(CaesarCipher.encryptIt(reader.read()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class CreateName {
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
