package ua.javarush.encoder;

import ua.javarush.encoder.runner.Runner;

public class Application {
    public static void main(String[] args) {
        new Runner().run(args);
        System.out.println("Completed");
    }
}
