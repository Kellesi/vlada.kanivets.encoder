package ua.javarush.encoder;

import ua.javarush.encoder.Cryptology.CaesarCipher;
import ua.javarush.encoder.Cryptology.EncryptorMode;
import ua.javarush.encoder.IO.FileWrite;
import ua.javarush.encoder.LanguageService.AlphabetRepository;
import ua.javarush.encoder.LanguageService.LanguageAnalyzer;
import ua.javarush.encoder.IO.FileRead;

public class Runner {
    public static void run(String[] args) {  // [mode  path  key]
        if (args.length ==3) {
            EncryptorMode encryptionMode=EncryptorMode.valueOf(args[0]);
            String filePath=args[1];
            int encryptionKey=Integer.parseInt(args[2]);

            FileRead fileRead = new FileRead();
            Integer[] text = fileRead.read(filePath);
            String language = LanguageAnalyzer.getLanguage(text);
            CaesarCipher caesarCipher = new CaesarCipher();
            Integer[] ciphered = caesarCipher.encryptIntegerArray(text, encryptionKey, encryptionMode, AlphabetRepository.getLang(language));
            FileWrite fileWrite = new FileWrite();
            fileWrite.write(ciphered, filePath);
        } else {
            Runner.run(new ClientConsole().start());
        }
    }
}
