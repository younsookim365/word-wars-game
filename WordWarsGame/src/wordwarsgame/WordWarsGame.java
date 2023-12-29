package wordwarsgame;

import java.util.Scanner;

/*------------------------------------------------------start------------------------------------------------------*/
//Java application prototype based on the game Scrabble
public class WordWarsGame {
    // Sentinel value to exit the game
    private static final String SENTINEL_VALUE = "???";
    // String representing the alphabet letters
    private static final String ALPHABET_LIST = "a b c d e f g h i j k l m n o p q r s t u v w x y z";

    // Scanner for user input
    private Scanner scanner = new Scanner(System.in);
    // Player names
    private String firstPlayer;
    private String secondPlayer;
    // Currently used word in the game
    private String usedWord = "";
    // String representing the remaining alphabet letters
    private String alphabetList = ALPHABET_LIST;
    // String representing the letters already used
    private String usedLetters = "";
    // Player currently taking their turn
    private String mainPlayer;
    // Variable to alternate between players
    private int playerOrder = 1;
    // Flag to track remaining alphabet letters
    private boolean remainingAlphabets = true;
    // Scores for each player
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;

    /*------------------------------------------main method start------------------------------------------*/
    // Entry point of the program
    public static void main(String[] args) {
        WordWarsGame wordWarsGame = new WordWarsGame();
        wordWarsGame.startGame();
    }
    /*-------------------------------------------main method end-------------------------------------------*/ 

    // Start the game
    private void startGame() {
        System.out.println("Welcome to the WORD WARS game.");
        System.out.println("\nPress (1) To start the game.");
        System.out.println("\nPress any other key to exit the game");
        System.out.print("Enter your selection: ");

        if (scanner.next().equalsIgnoreCase("1")) {
            System.out.println("**************************************");
            System.out.print("Enter player 1 name: ");
            firstPlayer = scanner.next();
            System.out.print("Enter player 2 name: ");
            secondPlayer = scanner.next();
            System.out.print("\nLET'S PLAY WORD WARS!!!");
            liftOff();
        } else {
            System.exit(0); // Ending game
        }
    }

    // Main game loop
    private void liftOff() {
        while (!usedWord.equals(SENTINEL_VALUE)) {
            mainPlayer();
            System.out.print("\nAlphabet letters left: " + remainingAlphabets());

            do {
                System.out.print("\n" + mainPlayer + " enter your word: ");
                usedWord = scanner.next();

                if (usedWord.equals(SENTINEL_VALUE)) {
                    break; // Exit the loop if sentinel value is entered
                }
            } while (!isValidWord(usedWord, alphabetList));

            if (!usedWord.equals(SENTINEL_VALUE)) {
                validOrInvalidWord();
            }
        }
        winnerScore();
    }

    // Check if the entered word is valid
    private boolean isValidWord(String word, String availableLetters) {
        for (char letter : word.toCharArray()) {
            if (!availableLetters.contains(String.valueOf(letter))) {
                System.out.println("YOU ENTERED A WORD THAT CONTAINS A LETTER THAT IS USED OR IS NOT VALID. PLEASE ENTER ANOTHER WORD!");
                return false;
            }
        }
        return true;
    }

    // Update remaining alphabet letters based on the used word
    private String remainingAlphabets() {
        if (remainingAlphabets) {
            for (char letter : usedWord.toCharArray()) {
                if (alphabetList.contains(String.valueOf(letter))) {
                    usedLetters += String.valueOf(letter);
                    alphabetList = alphabetList.replace(String.valueOf(letter), "");
                }
            }
        } else {
            System.out.print("YOU ENTERED A WORD THAT CONTAINS A LETTER THAT IS USED OR IS NOT VALID. PLEASE ENTER ANOTHER WORD!\n");
            usedWord = "";
            remainingAlphabets = true;
        }
        return alphabetList;
    }

    // Determine if the word is valid, and update scores accordingly
    private void validOrInvalidWord() {
        System.out.print("Enter (y) yes if both players agree on the word\n");
        if (scanner.next().equalsIgnoreCase("y")) {
            searchUsedLetters();
            leaveVowels();
            calculatingScore();
        } else {
            usedWord = "";
        }
    }

    // Check if the used letters are present in the entered word
    public void searchUsedLetters() {
        for (char letter : usedLetters.toCharArray()) {
            if (usedWord.contains(String.valueOf(letter))) {
                remainingAlphabets = false;
            }
        }
    }

    // Remove vowels from the used word
    private void leaveVowels() {
        String vowels = "a e i o u";
        for (char letter : usedWord.toCharArray()) {
            if (vowels.contains(String.valueOf(letter))) {
                usedWord = usedWord.replace(String.valueOf(letter), "");
            }
        }
    }

    // Switch between players taking turns
    private void mainPlayer() {
        if (playerOrder == 1) {
            mainPlayer = firstPlayer;
            playerOrder += 1;
        } else {
            mainPlayer = secondPlayer;
            playerOrder -= 1;
        }
    }

    // Update scores based on the current player
    private void calculatingScore() {
        if (mainPlayer.equals(firstPlayer) && !usedWord.equals(SENTINEL_VALUE)) {
            firstPlayerScore += 1;
        }
        if (mainPlayer.equals(secondPlayer) && !usedWord.equals(SENTINEL_VALUE)) {
            secondPlayerScore += 1;
        }
    }

    // Display the winner and scores at the end of the game
    private void winnerScore() {
        System.out.println("\nGAME OVER!");
        if (firstPlayerScore > secondPlayerScore) {
            System.out.println("WINNER OF THE GAME IS: " + firstPlayer + " with a score of: " + firstPlayerScore);
        } else if (secondPlayerScore > firstPlayerScore) {
            System.out.println("WINNER OF THE GAME IS: " + secondPlayer + " with a score of: " + secondPlayerScore);
        } else {
            System.out.println("THE GAME IS TIED!");
        }
        System.out.println("THANK YOU FOR PLAYING WORD WARS!!!");
    }
}
/*-------------------------------------------------------end-------------------------------------------------------*/