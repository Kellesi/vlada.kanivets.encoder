package ua.javarush.encoder.runner;

import ua.javarush.encoder.cryptology.EncryptorMode;
import java.util.Arrays;
import java.util.Scanner;

public class ClientConsole {
    private ValuesValidator validator = new ValuesValidator();


    public String[] start() {
        int maxArgumentsAmount =3;
        int minArgumentsAmount =2;
        String[] args = new String[minArgumentsAmount];

        try(Scanner console = new Scanner(System.in)) {
            System.out.println("Choose and input an encryption mode:");
            args[0] = initMode(console);
            System.out.println("Enter the path to the file you want to encrypt:");
            args[1] = initPath(console);
            if (!(args[0].equalsIgnoreCase(EncryptorMode.BRUTE_FORCE.name()))) {
                System.out.println("Enter the key:");
                args = Arrays.copyOf(args, maxArgumentsAmount);
                args[2] = initKey(console);
            }
        }
        return args;
    }

    private String initKey(Scanner console) {
        String key = console.nextLine();
        if ((key = validator.validateKey(key)) == null) {
            key = initKey(console);
        }
        return key;
    }

    private String initPath(Scanner console) {
        String path = console.nextLine();
        if (validator.validatePath(path) == null) {
            path = initPath(console);
        }
        return path;
    }

    private String initMode(Scanner console) {
        System.out.println("ENCRYPT/DECRYPT/BRUTE_FORCE");
        String mode = console.nextLine();
        if ((mode = validator.validateMode(mode)) == null) {
            mode = initMode(console);
        }
        return mode;
    }
}
