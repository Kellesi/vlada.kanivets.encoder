package ua.javarush.encoder.io;

import ua.javarush.encoder.exception.IORuntimeException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public void writeLines(String[] buffer, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : buffer) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException ex) {
            throw new IORuntimeException(ex);
        }
    }
}
