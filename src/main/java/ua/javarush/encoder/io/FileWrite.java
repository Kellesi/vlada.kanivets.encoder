package ua.javarush.encoder.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public void write(Integer[] buffer,String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int character : buffer) {
                writer.write(character);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeLines(String[] buffer, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : buffer) {
                writer.write(line + System.lineSeparator());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
