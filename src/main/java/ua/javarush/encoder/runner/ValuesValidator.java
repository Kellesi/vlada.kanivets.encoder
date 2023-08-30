package ua.javarush.encoder.runner;

import ua.javarush.encoder.cryptology.EncryptorMode;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class ValuesValidator {
    public String validateMode(String mode) {
        for (EncryptorMode emode : EncryptorMode.values()) {
            if (mode.equalsIgnoreCase(emode.name()) || mode.equalsIgnoreCase(emode.getAbbreviation())) {
                return emode.name();
            }
        }
        System.out.println("Wrong encryption mode. Please, try again:");
        return null;
    }

    public String validateKey(String key) {
        try {
            Integer.parseInt(key);
            return key;
        } catch (NumberFormatException ex) {
            System.out.println("Only numbers are allowed. Please, try again:");
            return null;
        }
    }

    public String validatePath(String path) {
        Path pathFromString;
        try {
            pathFromString = Path.of(path);
            if (Files.isRegularFile(pathFromString)) {
                return path;
            } else {
                System.out.println("Wrong path. Please, try again:");
                return null;
            }
        } catch (InvalidPathException ex) {
            System.out.println("Wrong path. Please, try again:");
            return null;
        }
    }
}
