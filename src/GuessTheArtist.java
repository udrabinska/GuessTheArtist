import java.util.Arrays;
import java.util.Scanner;

public class GuessTheArtist {
    public static void main(String[] args) {
        String artist = "Adam Mickiewicz";
        char[] guess = new char[artist.length()];
        for (int i = 0; i < artist.length(); i++) {
            guess[i] = '-';
        }
        int guessCounter = 10;
        boolean won = false;

        System.out.println("Welcome in the game \"Guess the Artist\"! \nYou've got 10 guesses. Press enter when you are ready.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println();

        do {
            for (int i = 0; i < artist.length(); i++) {
                System.out.print(guess[i]);
            }
            System.out.println(" ");
            System.out.println("Guess the letter.");
            String letter = scanner.nextLine();
            // TODO: allow the user to type only one letter / ensure it's only one letter
            System.out.println("Your letter: " + letter);

            // TODO: reveal all occurrences of the letter, do not show the first one "A" when just pressed enter
            if (artist.toLowerCase().contains(letter)) {
                int index = artist.indexOf(letter);
                guess[index] = artist.charAt(index);
                if (Arrays.toString(guess).equals(artist.toLowerCase())) {
                    won = true;
                    break;
                }
            } else {
                System.out.println("There is no \"" + letter +"\" in the artist name.");
                guessCounter--;
                if (guessCounter == 0) {
                    break;
                }
                System.out.println("You still have " + guessCounter + " guesses.");
            }
        } while (!(Arrays.toString(guess).equals(artist.toLowerCase())));
        scanner.close();

        if (won) {
            System.out.println("Congratulations! You got it!");
        } else {
            System.out.println("You've lost. The artist we're asking for was " + artist + ".");
        }
    }
}
