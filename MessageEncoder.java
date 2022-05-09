package projectFiles;

// This interface represents general behavior for the cipher
// classes.
public interface MessageEncoder {
    String encode(String plainText); // Returns the encoded message
}
