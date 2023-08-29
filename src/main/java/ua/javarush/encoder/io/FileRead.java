package ua.javarush.encoder.io;

import ua.javarush.encoder.exception.IORuntimeException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {

    public String[] readLines(String source) {
        ArrayList<String> array = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            while (reader.ready()) {
                array.add(reader.readLine());
            }
        } catch (IOException ex) {
            throw new IORuntimeException(ex);
        }
        return array.toArray(new String[0]);
    }
}
