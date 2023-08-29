Encryptor has 3 options for working with text: ENCRYPT, DECRYPT, BRUTE_FORCE.
ENCRYPT / DECRYPT
 - The program can be run from the console with arguments: mode, filepath, key.
 - Modes may be entered as a full name uppercase/lowercase or as the first letter.
 - The text of the file are encoded/decoded using the Caesar cipher (uppercase, lowercase letters and such symbols as '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ' are encoded).
 - After decoding, the text has the original formatting.
 - The result of the encoding/decoding is saved in a file at the same folder with the same name, but marked [ENCRYPTED] / [DECRYPTED].
 - The program uses the same key to encode and decode the file.

BRUTE_FORCE
 - The program has a brute-force mode for automatically selecting a key for encrypted text and decrypting it.
 - This mode can be run from the console with arguments: mode, filepath or mode, filepath, path to a file for frequency analysis.
 - The result of the brute force will be printed to the console in a view as three possible keys and a sample of first two lines of decoded texts.
 - The result of decoding with reference text may have inaccuracies if symbols are encoded.

Project features:
Encoder works with English, Ukrainian and French languages.
It automatically determines which language is used for text.
The program also can be run without any arguments, in this case it will bу run in a user mode.