import java.util.Scanner;

public class Hangman {
    private static String arr[] = new String[]{"word", "hello", "test", "random"};
    private static String word = arr[(int)(Math.random() * arr.length)];
    private static char word_array[] = new char[word.length()];
    private static char guessed_word[] = new char[word.length()];
    private static int numOfGuesses = 0;
    private static boolean wordGuessed = false;

    public static void start() {
        while (numOfGuesses < 7 && !wordGuessed){
            // Initializing the array "guessed_word" to all underscores
            for (int i=0; i < word_array.length; ++i){
                word_array[i] = '_';
                System.out.print(word_array[i] + " ");
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your guess: ");
        
            String str = sc.next(); 
            for (int j=0;j < str.length(); ++j) {
                char[] chars = str.toCharArray();
                if (!Character.isLetter(chars[j])){
                    System.out.print("Kindly re-enter your guess\n\n");
                    break;
                } 
            }
            System.out.println(checkIfWordContainsGuess(str.charAt(0)));
            if (!checkIfWordContainsGuess(str.charAt(0))) {
                numOfGuesses++;
            } else {
                updateGuessedWord(str.charAt(0));
            }
        }
    }
    public static void main(String[] args) throws Exception {
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
