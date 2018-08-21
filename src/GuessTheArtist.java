import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GuessTheArtist {
    public static void main(String[] args) {
        // TODO: let the user choose what he wants to guess: writers, musician, politicians, all...
        File artistsFile = new File("artists.txt");
        ArrayList<String> artistList = readFile(artistsFile);
        int index = (int) (Math.random() * artistList.size());
        String artist = artistList.get(index);

        char[] artistChar = artist.toCharArray();
        char[] guess = new char[artist.length()];
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

        ArrayList<Character> wrongLetters = new ArrayList<>();
        ArrayList<Character> goodLetters = new ArrayList<>();

        do {
            for (int j = 0; j < artist.length(); j++) {
                System.out.print(guess[j]);
            }
            System.out.println(" ");
            System.out.println("Guess the letter.");
            char letter = scanner.next().charAt(0);
            // TODO: allow the user to type only one letter / ensure it's only one letter / let him know we take the first char
            // TODO if int typed - let know something is wrong?

            int goodGuesses = 0;
            for (int k = 0; k < artist.length(); k++) {
                if (Character.toUpperCase(letter) == artistChar[k] || Character.toLowerCase(letter) == artistChar[k]) {
                    guess[k] = artistChar[k];
                    goodGuesses++;
                    if (Arrays.toString(guess).equals(Arrays.toString(artistChar))) {
                        won = true;
                        break;
                    }
                }
            }
            if (goodGuesses != 0) {
                if (goodLetters.contains(letter)) {
                    System.out.println("You've already typed  \"" + letter + "\" and I showed you all I have.");
                    System.out.println("Try another letter.");
                } else {
                    System.out.println("Good shot! " + goodGuesses + " \"" + Character.toLowerCase(letter) + "\" in the artist's name.");
                    goodLetters.add(letter);
                }
            } else {
                System.out.println("There is no \"" + letter + "\" in the artist name.");
                if (wrongLetters.contains(letter)) {
                    System.out.println("You've already typed \"" + letter + "\"! I'm not counting this guess.");
                } else {
                    guessCounter--;
                    wrongLetters.add(letter);
                }
                if (guessCounter == 0) {
                    break;
                } else if (guessCounter == 1) {
                    System.out.println("You've got ONE last try. Be careful!");
                }
                System.out.println("You still have " + guessCounter + " guesses.");
            }
        } while (!(Arrays.toString(guess).equals(Arrays.toString(artistChar))));
        scanner.close();

        if (won) {
            System.out.println("Congratulations, you got it! The answer is \"" + artist + "\".");
            // TODO: in the future: short bio about artist.
        } else {
            System.out.println("You've lost. The artist we're asking for was " + artist + ".");
        }
        // TODO: want to play again?
    }

    private static ArrayList<String> readFile(File file) {
        ArrayList<String> artistList = new ArrayList<>();
        try {
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                String artist = fileScan.nextLine();
                artistList.add(artist);
            }
            fileScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ups, we didn't upload the file with artists (or did it wrong). See you later!");
            System.exit(0);
        }
        return artistList;
    }
}