import java.util.Random;
import java.util.Scanner;
class Game{
    public int number;
    public int guessed_Number;
    public int noOfGuesses = 0;
    public int scores = 0;
    Game(){
        Random rand = new Random();
        this.number = rand.nextInt(100);
    }
    void takeUserInput(){
        System.out.println("Guess the number");
        Scanner sc = new Scanner(System.in);
        guessed_Number = sc.nextInt();
    }
    int isCorrectNumber(){
        noOfGuesses++;
        if (guessed_Number == number){
            scores += 11-noOfGuesses;
            System.out.format("Yes you guessed it right, it was %d.\nYour score is %d.\n\n", number, scores);
            return scores;
        }
        else if(guessed_Number<number){
            System.out.println("Guessed number is too low than actual number\n");
        }
        else if(guessed_Number>number){
            System.out.println("Guessed number is too high than actual number\n");
        }
        if(noOfGuesses == 10){
            return 11;
        }
        return 0;
    }
}
public class guess_number {
    public static void main(String[] args) {
        int scores = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rounds to play");
        int no_of_rounds = sc.nextInt();
        Game g;
        for(int i=1;i<=no_of_rounds;i++){
            g = new Game();
            int b = 0;
            System.out.println("------------ Round "+i+" ------------");
            while(b==0){
                g.takeUserInput();
                b = g.isCorrectNumber();
                if(b>0 && b<=10) scores+=b;
            }
            if(b==11){
                System.out.println("Game Over!!! You have exhausted all your attempts.");
            }
        }
        System.out.println("------------ Results ------------");
        System.out.println("Total Score : "+scores);
    }
}
