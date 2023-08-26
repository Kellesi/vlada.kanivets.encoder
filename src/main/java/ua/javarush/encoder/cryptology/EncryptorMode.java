package ua.javarush.encoder.cryptology;

public enum EncryptorMode {
    ENCRYPT("e"), DECRYPT("d"), BRUTE_FORCE("b");
    final String abbreviation;

    EncryptorMode(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
