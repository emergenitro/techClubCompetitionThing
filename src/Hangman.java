import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) throws Exception {
        String arr[] = new String[]{"word", "hello", "test", "random", "help", "arizona", "cut", "somebody", "never", "gonna", "give", "you", "up"};
        String word = arr[(int)(Math.random() * arr.length)];
        int wordLength = word.length() - 1;
        char word_array[] = new char[wordLength];
        char guessed_word[] = new char[wordLength];
        int numOfGuesses = 0;

        // Setting the array "guessed_word" to all underscores
        for (int i=0; i < word.length(); ++i){
            word_array[i] = '_';
        }
        for (int j=0; j < word_array.length; ++ j){
            System.out.print(word_array[j]);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your guess: ");
    
        String str = sc.next(); 
        for (int j=0;j < str.length(); ++j) {
            char[] chars = str.toCharArray();
            if (!Character.isLetter(chars[j])) System.out.print("UNveliaven;le sutpdi");
        }
        
    }
}
