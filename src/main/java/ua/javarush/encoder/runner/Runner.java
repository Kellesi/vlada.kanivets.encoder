package ua.javarush.encoder.runner;

import ua.javarush.encoder.cryptology.BruteForce;
import ua.javarush.encoder.cryptology.CaesarCipher;
import ua.javarush.encoder.cryptology.EncryptorMode;
import ua.javarush.encoder.io.FileWrite;
import ua.javarush.encoder.io.NameCreator;
import ua.javarush.encoder.languageservice.AlphabetRepository;
import ua.javarush.encoder.languageservice.TextAnalyzer;
import ua.javarush.encoder.io.FileRead;

public class Runner {
    public void run(String[] args) {
        ValuesValidator validator = new ValuesValidator();
        EncryptorMode encryptionMode;
        String filePath;
        switch (args.length) {
            case 2 -> {
                try {
                    encryptionMode = EncryptorMode.valueOf(validator.validateMode(args[0]));
                    filePath = validator.validatePath(args[1]);
                    if (encryptionMode != EncryptorMode.BRUTE_FORCE) {
                        System.out.println("Unexpected arguments");
                        run(new ClientConsole().start());
                    } else {
                        runBruteForce(filePath);
                    }
                } catch (NullPointerException ex) {
                    run(new ClientConsole().start());
                }
            }
            case 3 -> {
                try {
                    encryptionMode = EncryptorMode.valueOf(validator.validateMode(args[0]));
                    filePath = validator.validatePath(args[1]);
                    if (encryptionMode != EncryptorMode.BRUTE_FORCE) {
                        int encryptionKey = Integer.parseInt(validator.validateKey(args[2]));
                        runEncryption(encryptionMode, filePath, encryptionKey);
                    } else {
                        String filePathOfReferenceText = validator.validatePath(args[2]);
                        runBruteForce(filePath, filePathOfReferenceText);
                    }
                } catch (NullPointerException ex) {
                    run(new ClientConsole().start());
                }
            }
            default -> run(new ClientConsole().start());
        }
    }

    private void runBruteForce(String filePath) {
        FileRead fileRead = new FileRead();
        String[] text = fileRead.readLines(filePath);
        BruteForce bruteForce = new BruteForce(text);
        int[] possibleKeys = bruteForce.getPossibleKeys(true);
        bruteForce.printSampleOfText(possibleKeys);
    }

    private void runBruteForce(String filePath, String filePathOfReferenceText) {
        FileRead fileRead = new FileRead();
        String[] text = fileRead.readLines(filePath);
        String[] referencedText = fileRead.readLines(filePathOfReferenceText);
        BruteForce bruteForce = new BruteForce(text, referencedText);
        int[] possibleKeys = bruteForce.getPossibleKeys(true);
        bruteForce.printSampleOfText(possibleKeys);
    }

    private void runEncryption(EncryptorMode encryptionMode, String filePath, int encryptionKey) {
        FileRead fileRead = new FileRead();
        String[] text = fileRead.readLines(filePath);
        String textLanguage = new TextAnalyzer(text).getLanguage();
        CaesarCipher caesarCipher = new CaesarCipher(AlphabetRepository.getRepository().getAlphabetWithSymbols(textLanguage));
        String[] ciphered = switch (encryptionMode) {
            case ENCRYPT -> caesarCipher.encryptArray(text, encryptionKey);
            case DECRYPT -> caesarCipher.decryptArray(text, encryptionKey);
            default -> new String[0];
        };
        FileWrite fileWrite = new NameCreator(new FileWrite());
        fileWrite.writeLines(ciphered, filePath);
    }
}

