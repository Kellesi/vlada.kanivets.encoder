package ua.javarush.encoder;
public class Runner {
    public static void run(String[]args){  // [mode  path  key]
        if (args.length!=0){
            FileReadWrite file = new FileReadWrite(args[1]);
            CaesarCipher cipher = new CaesarCipher(Integer.parseInt(args[2]), file.getLanguage(),args[0]);
            file.useCryptography(cipher);
        } else {
            Runner.run(ClientConsole.start());
        }
    }
}
