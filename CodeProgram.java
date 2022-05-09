package projectFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

// Menu-driven Program that will allow the users to encode and decode
// messages read from a file and written to another file
public class CodeProgram {

    // private member is a reference variable of projectFiles.Cipher data type
    private Cipher userCipher;

    // Constructor which initializes a projectFiles.CodeProgram object
    public CodeProgram() {}

    // Main method for user to try out cipher program
    public static void main(String[] args) {
        CodeProgram test = new CodeProgram();
        Scanner input = new Scanner(System.in);
        boolean exitMain = false;

        while(!exitMain) {
            test.showMenu();
            switch(input.next().charAt(0)) {
                case '1':
                    test.enactCipher1(); // Substitution projectFiles.Cipher
                    break;

                case '2':
                    test.enactCipher2(); // Shuffle projectFiles.Cipher
                    break;
            }

            System.out.println("Would you like to do another message? (Y/y = yes)");
            if(Character.toUpperCase(input.next().charAt(0)) != 'Y')
                exitMain = true;
        }

        System.out.println("\nExiting projectFiles.Cipher Program!");
    }

    // showMenu method that displays all options to the user
    public void showMenu() {
        System.out.println("Welcome to the projectFiles.Cipher Program!");
        System.out.println("------------------------------");
        System.out.println("Type 1 for the Substitution projectFiles.Cipher.");
        System.out.println("Type 2 for the Shuffle projectFiles.Cipher.");
    }

    // This method validates the user's input file
    private File validateInputFile() {
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        File inputFile = null;

        // While loop will validate that user's I/O files does exist.
        while(!exit) {
            System.out.println("Enter input file name:");
            inputFile = new File(input.next());
            if(inputFile.exists())
                exit = true;
            else
                System.out.println("Error: the file does not exist! Try again.");
        }

        return inputFile;
    }

    // This method validates the user's output file
    private File validateOutputFile() {
        Scanner input = new Scanner(System.in);
        String name = null;
        boolean exit = false;

        while(!exit) {
            System.out.println("Enter output file name:");
            name = input.next();
            if(name.matches("[\\S]+\\.txt"))
                exit = true;
            else
                System.out.println("Error: Your file must be a .txt file! Try again.");
        }

        return new File(name);
    }

    // This method will allow the user to enter the file name that will be read by the
    // system. Then, depending on the cipher object passed into the function, it will
    // encode/decode the message using the cipher parameter and store it into
    // another file determined by the user.
    private void writeToFiles(Cipher cipherType) {
        Scanner userInput = new Scanner(System.in);

        // Make file objects to read message and display encoded/decoded message
        File userInputFile = validateInputFile();
        File userOutputFile = validateOutputFile();

        // Exception handling: We want to be able to write to open and write to our files
        // during encoding/decoding
        // This while loop, in addition to making sure we can open and read files, will
        // Read each line in the input file and encode/decode the message.
        boolean exit = false;
        Scanner fileReader = null; // Scanner object to read input file
        PrintWriter fileWriter = null; // PrintWriter object to write to output file
        while(!exit) {

            try {
                // PrintWriter object is used to write lines to our output file
                // Scanner object that reads in the input file
                fileReader = new Scanner(userInputFile);
                fileWriter = new PrintWriter(userOutputFile.getName());

                System.out.println("Encode (E/e) or Decode (D/d)");
                switch(Character.toUpperCase(userInput.next().charAt(0))) {
                    case 'E':
                        while(fileReader.hasNextLine()) {
                            String encodeLine = ((MessageEncoder)cipherType).encode(fileReader.nextLine());
                            fileWriter.println(encodeLine);
                        }
                        exit = true;
                        break;

                    case 'D':
                        while(fileReader.hasNextLine()) {
                            String decodeLine = ((MessageDecoder)cipherType).decode(fileReader.nextLine());
                            fileWriter.println(decodeLine);
                        }
                        exit = true;
                        break;

                }
            }
            catch(FileNotFoundException ex) {
                System.out.println("Sorry, but the system could not open or write to files!");
            }
        }

        // Close files for both Scanner object that reads file
        // and PrintWriter object that writes to file
        fileReader.close();
        fileWriter.close();
    }

    // enactCipher1 (Substitution) will ask the user for various inputs regarding
    // the shift key they want to use and whether they want to Encode or Decode.
    public void enactCipher1() {
        Scanner userInput = new Scanner(System.in);
        int userShift = -1;

        // try block will handle user entering invalid input for shift key
        while (userShift == -1) {
            try {
                System.out.println("What is the key (shift amount) for your code?");
                userShift = Math.abs(userInput.nextInt());
            } catch (InputMismatchException ex) {
                userInput.nextLine();
            }
        }

        // Initialize projectFiles.Cipher reference variable with an instance of projectFiles.SubstitutionCipher and the user's
        // shift key
        userCipher = new SubstitutionCipher(userShift);
        System.out.println(userCipher.cipherType()+"-shift amount = " + userShift);
        writeToFiles(userCipher);
    }

    // enactCipher1 (Shuffle) will ask the user for various inputs regarding
    // the number of times they want to shuffle their message
    // and whether they want to Encode or Decode.
    public void enactCipher2() {
        Scanner userInput = new Scanner(System.in);
        int userShuffles = -1;

        // try block will handle user entering invalid input for shift key
        while (userShuffles == -1) {
            try {
                System.out.println("What is the key (shuffle amount) for your code?");
                userShuffles = Math.abs(userInput.nextInt());
            } catch (InputMismatchException ex) {
                userInput.nextLine();
            }
        }

        // Initialize projectFiles.Cipher reference variable with an instance of projectFiles.ShuffleCipher and the user's
        // number of shuffles
        userCipher = new ShuffleCipher(userShuffles);
        System.out.println(userCipher.cipherType() + "-shuffle amount = " + userShuffles);

        writeToFiles(userCipher); // Call writeToFiles()
    }

}
