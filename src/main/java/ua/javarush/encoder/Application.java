package ua.javarush.encoder;

public class Application {
    public static void main(String[] args) {
        String path = "C:\\Users\\kaniv\\Desktop\\file1.txt";
        FileReadWrite go = new FileReadWrite(path);

        go.useCryptography();
    }
}
