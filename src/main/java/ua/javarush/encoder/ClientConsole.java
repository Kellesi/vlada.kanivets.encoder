package ua.javarush.encoder;

import java.util.Scanner;
// [mode  path  key] BRUTE_FORCE
public class ClientConsole {
    public static String [] start() {
        String [] args = new String[3];
        Scanner console=new Scanner(System.in);
        System.out.println("Welcome!");
        System.out.println("Choose and input an encryption mode:");
        System.out.println("ENCRYPT/DECRYPT/BRUTE_FORCE");
        args[0]=console.nextLine();
        System.out.println("Enter the path to the file you want to encrypt:");
        args[1]=console.nextLine();
        System.out.println("Enter the key:");
        args[2]=console.nextLine();
        return args;
    }
}
