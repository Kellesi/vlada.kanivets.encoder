package ua.javarush.encoder.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {
    public Integer[] read(String source) {
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

    public String[] readLines(String source) {
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
}
