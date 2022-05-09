package projectFiles;

// This interface represents general behavior for the cipher
// classes.
public interface MessageDecoder {
    String decode(String cipherText); // Returns the decoded message
}
