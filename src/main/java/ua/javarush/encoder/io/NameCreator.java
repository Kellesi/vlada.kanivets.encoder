package ua.javarush.encoder.io;

public class NameCreator extends FileWrite {
    private FileWrite fileWriter;
    private static final String ENCRYPTED = "[ENCRYPTED]";
    private static final String DECRYPTED = "[DECRYPTED]";

    public NameCreator(FileWrite fileWrite) {
        this.fileWriter = fileWrite;
    }

    @Override
    public void writeLines(String[] buffer, String filePath) {
        filePath = createNewName(filePath);
        fileWriter.writeLines(buffer, filePath);
    }

    private String createNewName(String fromPath) {
        String name;
        String extension = fromPath.substring(fromPath.lastIndexOf("."));
        if (fromPath.contains(ENCRYPTED)) {
            name = fromPath.replace(ENCRYPTED, DECRYPTED);
        } else if (fromPath.contains(DECRYPTED)) {
            name = fromPath.replace(DECRYPTED, ENCRYPTED);
        } else {
            name = fromPath.replace(extension, ENCRYPTED + extension);
        }
        return name;
    }
}
