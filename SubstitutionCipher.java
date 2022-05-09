package projectFiles;

// projectFiles.SubstitutionCipher extends projectFiles.Cipher and implements both
// projectFiles.MessageEncoder and projectFiles.MessageDecoder
public class SubstitutionCipher extends Cipher
    implements MessageEncoder, MessageDecoder {

    private int shift;

    // the constructor should only have parameter
    // to initialize the shift member
    public SubstitutionCipher(int shift) {
        // Dealing with letters, keep shift to 26
        this.shift = shift;
    }

    // projectFiles.SubstitutionCipher will override cipherType to return
    // the string "projectFiles.SubstitutionCipher"
    @Override
    protected String cipherType() {
        return "projectFiles.SubstitutionCipher";
    }

    // The encode method will take the input string and shift each
    // letter to the right based on the user's shift cipher.
    @Override
    public String encode(String plainText) {
        StringBuilder newCipherText = new StringBuilder(); // new string variable

        // This for loop will loop through each character in the input
        // string and shift it according to their ascii value.
        for(int i = 0; i < plainText.length(); ++i) {
            char ch = plainText.charAt(i); // get character
            ch += shift; // Shift using ascii value
            newCipherText.append(ch); // concatenate shifted character to new string
        }
        return newCipherText.toString();
    }

    // The decode method will take the input string and shift each
    // letter to the lift based on the user's shift cipher. It is meant
    // to decode a message that was encoded with a shift cipher.
    @Override
    public String decode(String cipherText) {
        StringBuilder newDecodedText = new StringBuilder(); // new string variable

        // This for loop will loop through each encoded character in the input
        // string and shift it back according to their ascii value.
        for(int i = 0; i < cipherText.length(); ++i) {
            char ch = cipherText.charAt(i); // get encoded character
            ch -= shift; // Shift back to original character
            newDecodedText.append(ch); // concatenate shifted character to new string

        }
        return newDecodedText.toString();
    }


}
