import java.util.Scanner;
import java.util.ArrayList;

public class Hangman {
    private static String arr[] = new String[]{"word", "hello", "test", "random"};
    private static String img[] = new String[]{"----------", "|", "|", "|", "|", "|\n|\n----------"};
    private static String word = arr[(int)(Math.random() * arr.length)];;
    private static char word_array[] = new char[word.length()];
    private static ArrayList<Character> guessed_letters = new ArrayList<Character>();
    private static int numOfGuesses;
    private static String guessedWord = new String();
    private static boolean accepted = true;
    static Scanner Sc = new Scanner(System.in);

    public static void restart() {
        // Re-initializing all variables to original, required values
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
        guessedWord = new String(word_array);
        
        while (numOfGuesses < 6 && guessedWord.contains("_")){
            // Creating some space before printing
            System.out.println();
            System.out.println();
            sendText();
            System.out.print("Enter your guess: ");
        
            String str = Sc.next(); 
            for (int j=0;j < str.length(); ++j) {
                char[] chars = str.toCharArray();
                if (!Character.isLetter(chars[j])){
                    System.out.println("\n\nKindly re-enter your guess. Don't use special chars.\n\n");
                    accepted = false; // Check if input is accepted or not, if loop used ahead so that points are not deducted
                    break;
                } 
            }
            if (accepted){
                if (!guessed_letters.contains(str.charAt(0))){
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
                else{
                    System.out.println("\n\nYou have already entered this letter! Kindly re-enter\n\n");
                }
            }
            accepted = true;
        }
        sendText();

        // Winning-Losing messages


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


    public static boolean checkIfWordContainsGuess(char c){
        for (int i=0; i<word.length();++i){
            if (word.charAt(i) == c) return true;
        }
        return false;
    }


    public static void updateGuessedWord(char c){
        for (int i=0; i < word.length();++i){
            if (word.charAt(i) == c) word_array[i] = c;
        }
    }

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

        for (int i=0; i < word_array.length; ++i){
            System.out.print(word_array[i] + " "); // The blanks/underscores for seen under the hangman
        }
    }
}
