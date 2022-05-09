package projectFiles;

// projectFiles.ShuffleCipher extends projectFiles.Cipher and implements both
// projectFiles.MessageEncoder and projectFiles.MessageDecoder
public class ShuffleCipher extends Cipher
    implements MessageEncoder, MessageDecoder {
    private int NoShuffles; // Number of times to shuffle text

    // Constructor will initialize NoShuffles member and oddString
    public ShuffleCipher(int k) {
        NoShuffles = k;
    }

    // projectFiles.ShuffleCipher will override cipherType to return
    // the string "projectFiles.ShuffleCipher"
    @Override
    protected String cipherType() {
        return "projectFiles.ShuffleCipher";
    }


    // This method will take a string and shuffle it a number of times
    // based on NoShuffles. It will utilize a private method
    // to do the shuffling
    @Override
    public String encode(String plainText) {
        boolean oddString = false;
        String newCipherText = plainText; // Result variable

        if(newCipherText.length() % 2 != 0)
            oddString = true; // Keeping track if string has an odd length

        // The following for loop will split the input string
        // into two halves, and then it will alternate placing a letter
        // from each half into a new string (the shuffle), and assign that new string
        // to the result variable. The number of iterations is the value of NoShuffles.
        for(int i = 0; i < NoShuffles; ++i) {
            StringBuilder result = new StringBuilder(); // StringBuilder for append method
            int mid = newCipherText.length()/2; // Mid-index for the input string

            // Use substring method to get first half and second half with mid-index
            String firstHalf = newCipherText.substring(0,mid);
            String secondHalf = newCipherText.substring(mid);

            // For loop will add each character from each half to the result
            // string builder simultaneously using the append method.
            for(int j = 0; j < mid; ++j) {
                result.append(firstHalf.charAt(j));
                result.append(secondHalf.charAt(j));
            }

            // If we have an odd length string, then after the for loop,
            // there will still be one more character in secondHalf that we must add to the result.
            if (oddString)
                result.append(secondHalf.charAt(secondHalf.length() - 1));

            newCipherText = result.toString(); // convert string builder back to string
        }

        return newCipherText;

    }

    // "decode" will decode messages that were encoded
    // with the shuffle cipher.
    @Override
    public String decode(String cipherText) {
        boolean oddString = false;
        String newDecodedText = cipherText; // result variable

        if(newDecodedText.length() % 2 != 0)
            oddString = true; // Keeping track if string has an odd length

        // This for loop will take the input string and
        // decode it by reforming the first and second halves. It will
        // then concatenate both halves into one string and set it to
        // the result variable. The number of iterations is value of NoShuffles.
        for(int i = 0; i < NoShuffles; ++i) {
            StringBuilder firstHalf = new StringBuilder(); // StringBuilder for append method
            StringBuilder secondHalf = new StringBuilder();

            // First for loop will reconstruct the first half of the
            // encoded string. First half starts at index 0 of encoded text
            for(int j = 0; j < newDecodedText.length(); j+=2) { // step is 2 since characters alternate

                // For odd-length strings, we cannot add in the last character of the cipher text
                // in the firstHalf since it is originally in the second half. We will prevent this
                // with this conditional statement.
                if(oddString && j == newDecodedText.length() - 1)
                    continue;
                firstHalf.append(newDecodedText.charAt(j));
            }

            for(int k = 1; k < newDecodedText.length(); k+=2) // second half characters start at index 1
                secondHalf.append(newDecodedText.charAt(k));

            if(oddString) // if odd-length text, append last character of cipher text to secondHalf
                secondHalf.append(newDecodedText.charAt(newDecodedText.length() - 1));

            newDecodedText = firstHalf.toString() + secondHalf.toString(); // Convert StringBuilders to string
        }

        return newDecodedText;
    }

}
