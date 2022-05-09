package projectFiles;

// The Abstract class projectFiles.Cipher will notify the user
// what kind of cipher they choose.
public abstract class Cipher {

    // Will be overridden by projectFiles.SubstitutionCipher
    // and projectFiles.ShuffleCipher
    protected abstract String cipherType();
}
