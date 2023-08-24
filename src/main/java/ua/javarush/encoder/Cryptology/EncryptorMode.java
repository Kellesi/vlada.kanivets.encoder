package ua.javarush.encoder.Cryptology;

public enum EncryptorMode {
    ENCRYPT("e"), DECRYPT("d"), BRUTE_FORCE("b");
    String abbreviation;

    EncryptorMode(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
