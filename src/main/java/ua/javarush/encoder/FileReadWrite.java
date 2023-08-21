package ua.javarush.encoder;

import java.io.*;

public class FileReadWrite {
    private final String source;

    FileReadWrite(String source) {
        this.source = source;
    }

    public void useCryptography(Cipher cipher) {

        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new CreateName().createNewName()))) {

            while (reader.ready()) {
                writer.write(cipher.encryptIt(reader.read()));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getLanguage() {
        int engCharCounter = 0;
        int ukrCharCounter = 0;
        try (FileReader fileReader = new FileReader(source)) {
            char[] chars = new char[10];
            int amountInBuffer = fileReader.read(chars);
            for (int i = 0; i < amountInBuffer; i++) {
                if (chars[i] >= (int) 'a' & chars[i] <= (int) 'z') {
                    engCharCounter++;
                } else if (chars[i] >= (int) 'а' & chars[i] <= (int) 'я') {
                    ukrCharCounter++;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return engCharCounter>ukrCharCounter?"eng":"ukr";
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
