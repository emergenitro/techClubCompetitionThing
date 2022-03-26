import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hangman {
    private static String arr[] = new String[]{"word", "hello", "test", "random"};
    private static String word = arr[(int)(Math.random() * arr.length)];
    private static char word_array[] = new char[word.length()];
    private static int numOfGuesses = 0;
    public static String guessedWord = new String();

    public static void start() {
        guessedWord = new String(word_array);
        while (numOfGuesses < 7 && guessedWord.contains("_")){
            for (int i=0; i < word_array.length; ++i){
                System.out.print(word_array[i] + " ");
            }

            Scanner Sc = new Scanner(System.in);
            System.out.print("Enter your guess: ");
        
            String str = Sc.next(); 
            for (int j=0;j < str.length(); ++j) {
                char[] chars = str.toCharArray();
                if (!Character.isLetter(chars[j])){
                    System.out.print("Kindly re-enter your guess\n\n");
                    break;
                } 
            }
            if (!checkIfWordContainsGuess(str.charAt(0))) {
                numOfGuesses++;
            } else {
                updateGuessedWord(str.charAt(0));
                guessedWord = new String(word_array);
            }
        }
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
}
