import java.util.Arrays;
import java.util.Scanner;

public class GuessTheArtist {
    public static void main(String[] args) {
        String artist = "Adam Mickiewicz";
        char[] artistChar = artist.toCharArray();
        char[] guess = new char[artist.length()];
        // TODO: how to hide space with '-' but allow guessing it as well as char (or winning without guessing it at all)
        for (int i = 0; i < artist.length(); i++) {
            if (!Character.isWhitespace(artist.charAt(i))) {
                guess[i] = '-';
            } else {
                guess[i] = ' ';
            }
        }
        int guessCounter = 10;
        boolean won = false;

        System.out.println("Welcome in the game \"Guess the Artist\"! \nYou've got 10 guesses. Press enter when you are ready.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println();

        do {
            for (int j = 0; j < artist.length(); j++) {
                System.out.print(guess[j]);
            }
            System.out.println(" ");
            System.out.println("Guess the letter.");
            char letter = scanner.next().charAt(0);
            // TODO: allow the user to type only one letter / ensure it's only one letter / let him know we take the first char
            System.out.println("Your letter: " + letter);

            int goodGuesses = 0;
            for (int k = 0; k < artist.length(); k++) {
                if (Character.toUpperCase(letter) == artistChar[k] || Character.toLowerCase(letter) == artistChar[k]) {
                    guess[k] = artistChar[k];
                    goodGuesses++;
                    if (Arrays.toString(guess).equals(Arrays.toString(artistChar).toLowerCase())) {
                        won = true;
                        break;
                    }
                }
            }
            if (goodGuesses == 0) {
                System.out.println("There is no \"" + letter + "\" in the artist name.");
                guessCounter--;
                if (guessCounter == 0) {
                    break;
                }
                System.out.println("You still have " + guessCounter + " guesses.");
            }
        } while (!(Arrays.toString(guess).equals(Arrays.toString(artistChar).toLowerCase())));
        scanner.close();

        if (won) {
            System.out.println("Congratulations! You got it! The answer is \"" + artist + "\".");
        } else {
            System.out.println("You've lost. The artist we're asking for was " + artist + ".");
        }
    }
}