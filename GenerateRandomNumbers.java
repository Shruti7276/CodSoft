import java.util.Scanner;

public class GenerateRandomNumbers {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        GenerateRandom();
    }

    private static void GenerateRandom() {
        int number = (int) (Math.random() * 100) + 1;
        int totalrounds = 0;
        int numberofAttempts = 1;
        boolean wonorlost = true;
        int roundwon = 0;
        char playagain = 'Y';
        System.out.print("Enter your name:- ");
        String playerName = scanner.nextLine();
        while (playagain == 'Y') {
            while (numberofAttempts <= 5) {
                System.out.print("Guess your Number in (" + numberofAttempts + "/5) Attempt:- ");
                int guessedNumber = scanner.nextInt();
                if (guessedNumber > number) {
                    if (numberofAttempts == 5) {
                        wonorlost = false;
                    }
                    System.out.println("Guessed Number is Too High");
                } else if (guessedNumber < number) {
                    if (numberofAttempts == 5) {
                        wonorlost = false;
                    }
                    System.out.println("Guessed Number is Too Low");
                } else {
                    number = (int) (Math.random() * 100) + 1;
                    System.out.println("You Have Guessed The Correct Number");
                    wonorlost = true;
                    break;
                }
                numberofAttempts++;
            }
            if (wonorlost) {
                roundwon++;
            }
            System.out.print("Do you want to play again (Y/N):- ");
            playagain = scanner.next().charAt(0);
            totalrounds++;
            numberofAttempts = 1;
        }
        System.out.println(playerName + " has won " + roundwon + " out of " + totalrounds + " rounds. ");
    }
}