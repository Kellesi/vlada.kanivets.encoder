package ua.javarush.encoder;

import ua.javarush.encoder.Cryptology.EncryptorMode;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class ClientConsole {
    private final String[] args = new String[3];

    public String[] start() {
        Scanner console = new Scanner(System.in);
        args[0] = initMode(console);
        args[1] = initPath(console);
        args[2] = initKey(console);
        console.close();
        return args;
    }

    private String initKey(Scanner console) {
        System.out.println("Enter the key:");
        String key = "";
        boolean correctInput = false;
        while (!correctInput) {
            key = console.nextLine();
            try {
                Integer.parseInt(key);
                correctInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Only numbers are allowed. Please, try again:");
            }
        }
        return key;
    }

    private String initPath(Scanner console) {
        System.out.println("Enter the path to the file you want to encrypt:");
        Path path = null;
        boolean correctPath = false;

        while (!correctPath) {
            try {
                path = Path.of(console.nextLine());
            } catch (InvalidPathException ex) {
                System.out.println("Wrong path. Please, try again:");
                continue;
            }
            if (Files.isRegularFile(path)) {
                correctPath = true;
            } else {
                System.out.println("Wrong path. Please, try again:");
            }
        }
        return path.toString();
    }

    private String initMode(Scanner console) {
        System.out.println("Choose and input an encryption mode:");
        System.out.println("ENCRYPT/DECRYPT/BRUTE_FORCE");
        String mode = "";
        boolean correctMode = false;
        while (!correctMode) {
            mode = console.nextLine();
            for (EncryptorMode emode : EncryptorMode.values()) {
                if (mode.equalsIgnoreCase(emode.name()) || mode.equalsIgnoreCase(emode.getAbbreviation())) {
                    mode = emode.toString();
                    correctMode = true;
                    break;
                }
            }
            if (!correctMode) {
                System.out.println("Wrong encryption mode. Please, try again:");
            }
        }
        return mode;
    }
}
