import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {
    private static String arr[] = new String[]{"word", "hello", "test", "random", "never", "gonna", "give", "you", "up"}; // List of words
    private static String img[] = new String[]{"----------", "|", "|", "|", "|", "|\n|\n----------"}; // Image lines for the hangman
    private static String word = arr[(int)(Math.random() * arr.length)];; // The random word
    private static char word_array[] = new char[word.length()]; // Characters of the word (will be intialized with _ _ _ _...)
    private static ArrayList<Character> guessed_letters = new ArrayList<Character>(); // List of letters that are already guessed, for bonus challenge
    private static int numOfGuesses; // Number of guesses - Self Explanatory
    private static String guessedWord = new String(); // Create new string for the guessed word using word_array later
    private static boolean accepted = true; // Used to check if input is accepted 
    static Scanner Sc = new Scanner(System.in); // Input scanner

    public static void restart() {
        // Re-initializing all variables to the original, required values
        numOfGuesses = 0;
        word = arr[(int)(Math.random() * arr.length)];
        word_array = new char[word.length()];
        guessedWord = new String(word_array);
        guessed_letters.clear();
        for (int i=0; i < word_array.length; ++i){
            word_array[i] = '_';
        }
        img = new String[]{"----------", "|", "|", "|", "|", "|\n|\n----------"};

        // Restarting with the above values
        start();
    }

    public static void start() {
        // Update final word
        guessedWord = new String(word_array);
        
        // While loop used to costantly take inputs 
        while (numOfGuesses < 6 && guessedWord.contains("_")){
            // Creating some space before printing
            // Option 1: System.out.print("\n\n\n\n")
            // ----------------------------------------
            // Option 2:
            System.out.println();
            System.out.println();
            sendText();
            System.out.print("Enter your guess: ");
        
            String str = Sc.next(); 

            // Deny if the guess has more than one character
            if (str.length() != 1){
                System.out.print("\n\nKindly re-enter your guess. Please keep it a single character\n\n");
                accepted = false;
            } 
            
            // Check if any of the characters of the input are special
            for (int j=0;j < str.length(); ++j) {
                char[] chars = str.toCharArray();
                if (!Character.isLetter(chars[j])){
                    System.out.println("\n\nKindly re-enter your guess. Don't use special chars.\n\n");
                    accepted = false; // Check if input is accepted or not, if loop used ahead so that points are not deducted
                    break;
                } 
            }

            // Check if user has inputted letter
            if (guessed_letters.contains(str.charAt(0))){
                System.out.println("\n\nYou have already entered this letter! Kindly re-enter\n\n"); // Deny inputs which have already been used
                accepted = false;
            }

            // Using "accepted" from the above checks
            if (accepted){
                if (!checkIfWordContainsGuess(str.charAt(0))) {
                    numOfGuesses++;
                    updateImage(numOfGuesses);
                } 
                else if (checkIfWordContainsGuess(str.charAt(0))) {
                    updateGuessedWord(str.charAt(0));
                    guessedWord = new String(word_array);
                }
                guessed_letters.add(str.charAt(0));                
            }
            accepted = true;
        }
        sendText(); // Sends final hangman and word

        // Winning-Losing messages (uses shorthand if-else statement)
        String winloseMessage = (numOfGuesses>=6) ? "\n\nYou have lost! The answer was %s\n " : "\n\nYou have won! Woohoo! The answer is %s\n";
        System.out.print(String.format(winloseMessage, word));

        // Restarting the game
        System.out.print("\nPlay again (Y/N): ");
        String a = Sc.next();
        if (a.equals("Y")) restart();
    }

    public static void main(String[] args) throws Exception {
        // Initializing the array "guessed_word" to all underscores
        for (int i=0; i < word_array.length; ++i){
            word_array[i] = '_';
        }
        start();
    }

    // Checks if the word contains the guessed character
    public static boolean checkIfWordContainsGuess(char c){
        // Loops through the required word, and checks if any of them is the character argument. If false is returned, number of tries is incremented
        for (int i=0; i<word.length();++i){
            if (word.charAt(i) == c) return true;
        }
        return false;
    }

    // Update the word in case that the character is seen in the word
    public static void updateGuessedWord(char c){
        for (int i=0; i < word.length();++i){
            if (word.charAt(i) == c) word_array[i] = c;
        }
    }
    // Updates but doesnt send the image or thingie of the hangman
    public static void updateImage(int count){
        if (count == 1) img[1] = "|        0";
        if (count == 2) img[1] = "|      \\ 0";
        if (count == 3) img[1] = "|      \\ 0 /";
        if (count == 4) img[2] += "        |";
        if (count == 5) img[3] += "      _/";
        if (count == 6) img[3] += " \\_";
    }

    public static void sendText(){
        String thing = String.join("\n", img); // This and line below are for printing the image of the hangman
        System.out.println(thing);
        System.out.println(String.format("\nRemaining lives: %d", (6 - numOfGuesses))); // Prints remaining lives
        for (int i=0; i < word_array.length; ++i){
            System.out.print(word_array[i] + " "); // The blanks/underscores for seen under the hangman
        }
    }
}
