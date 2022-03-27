import java.util.Scanner;

public class Hangman {
    private static String arr[] = new String[]{"word", "hello", "test", "random"};
    private static String img[] = new String[]{"----------", "|", "|", "|", "|", "|\n|\n----------"};
    private static String word = arr[(int)(Math.random() * arr.length)];
    private static char word_array[] = new char[word.length()];
    private static char guessed_letters[] = new char[5];
    private static int numOfGuesses = 0;
    private static String guessedWord = new String();
    private static boolean accepted = true;

    public static void start() {
        guessedWord = new String(word_array);
        while (numOfGuesses < 6 && guessedWord.contains("_")){
            String thing = String.join("\n", img); // This and line below are for printing the image of the hangman
            System.out.println(thing);

            for (int i=0; i < word_array.length; ++i){
                System.out.print(word_array[i] + " "); // The blanks/underscores for seen under the hangman
            }

            Scanner Sc = new Scanner(System.in);
            System.out.print("Enter your guess: ");
        
            String str = Sc.next(); 
            for (int j=0;j < str.length(); ++j) {
                char[] chars = str.toCharArray();
                if (!Character.isLetter(chars[j])){
                    System.out.print("Kindly re-enter your guess\n\n");
                    accepted = false;
                    break;
                } 
            }
            if (accepted){
                if (new String(guessed_letters).indexOf(str.charAt(0)) == -1){
                    if (!checkIfWordContainsGuess(str.charAt(0))) {
                        numOfGuesses++;
                        sendImage(numOfGuesses);
                    } 
                    else if (checkIfWordContainsGuess(str.charAt(0))) {
                        updateGuessedWord(str.charAt(0));
                        guessedWord = new String(word_array);
                    }
                }
                guessed_letters[numOfGuesses-1] = str.charAt(0);
            }
            accepted = true;
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

    public static void sendImage(int count){
        if (count == 1) img[1] = "|        0";
        if (count == 2) img[2] = "|      \\";
        if (count == 3) img[2] = "|      \\  /";
        if (count == 4) img[3] = "|        |";
        if (count == 5) img[4] = "|      _/";
        if (count == 6) img[4] = "|      _/\\_";
    }
}
