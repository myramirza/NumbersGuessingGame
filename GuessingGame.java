
package guessing.game;
import java.util.*;

/**
 *
 * @author Myra Mirza
 */

// Guess class
class Guess{
    
    // Guess is made up of 3 fields of ints
    private int a;
    private int b;
    private int c;
    
    // Constructor for creating a guess object
    public Guess(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;        
    }
    
    // Getter, to access private field int a
    public int getA() {
        return a;
    }

    // Getter, to access private field int b
    public int getB() {
        return b;
    }
    
    // Getter, to access private field int c
    public int getC() {
        return c;
    }  
    
    // True or false value of whether a guess follows the rule I have set for this game
    boolean followsRule;
    
    // Method to make the Guess into a String, for printing purposes
    public String toString(){
    return a + " " + b + " " + c;
    }    
}

// Class where we create the actual Guessing Game
public class GuessingGame {
    // Each game contains it's own Array List of guesses that the user makes
    private ArrayList<Guess> guesses;
    
    // Constructor for creating a GuessingGame object, the Array List for the game is also initialised
    public GuessingGame(){
        this.guesses = new ArrayList<>();
    }
    
    // Method to convert user input into a guess (Guess object)
    public Guess changeUserInputToGuess(String [] arrayOfDigits){
        
        int a = Integer.parseInt(arrayOfDigits[0]);
        int b = Integer.parseInt(arrayOfDigits[1]);
        int c = Integer.parseInt(arrayOfDigits[2]);
        
        Guess userGuess = new Guess(a, b, c);    
        
        return userGuess;
    }
    
    // Method checks to see if a particular guess follows the rule that I have set for this game
    public void followsRule(Guess g){
        
        // This is testing if every integer after the first is greater than the one preceding it, which is the rule I have set for this game
        // If the guess follows my rule, the method changes this particular guess' followsRule field to TRUE
        if ((g.getC() > g.getB()) && (g.getC() > g.getA()) && (g.getB() > g.getA())){
            g.followsRule = true;
        }
        
        // If the guess follows the rule, a "Yes" is printed, "No" if not
        if (g.followsRule){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        } 
    }
     
    public static void main(String[] args) {
        // Initialising an instance of the Guessing Game
        GuessingGame gameOne = new GuessingGame();
        // Stating the game is not done as of now
        boolean done = false;
        
        // This loop will keep running until the game is not done
        while(!done){
            // Getting a user to input three digits as a guess, or previous to see the preceding guesses, 
            // or answer to input what the user thinks is the answer and end the game
            Scanner scan = new Scanner (System.in);
            String input = "";
            System.out.println("Enter 3 numbers, \"answer\", or \'previoius\": ");            
            input = scan.nextLine();
            
            // Checks user input to perform the appropriate action
            if(input.equalsIgnoreCase("answer")){
                System.out.println("Enter what you think the rule is: ");
                Scanner answerScan = new Scanner(System.in);
                String userAnswer = answerScan.nextLine();
                System.out.println("The rule was : Every number is greater than the previous one.");
                done = true;
            } else if (input.equalsIgnoreCase("previous")){
                // Prints all previous guesses made by the user
                for(int i = 0; i < gameOne.guesses.size(); i++){
                System.out.println(gameOne.guesses.get(i));
                }
            } else {
                try {
                    // Splits the users input into an array of three integers, if more or less than 3
                    // integers, throws an exception
                    String [] arrayOfDigits = input.split(" ");
                    if (arrayOfDigits.length > 3 || arrayOfDigits.length < 3){
                        throw new Exception();
                    }
                    // Creates a guess object from the array of three integers retrieved from user's input
                    gameOne.guesses.add(gameOne.changeUserInputToGuess(arrayOfDigits));  
                    // Checks and tells user if the guess follows my rule
                    gameOne.followsRule(gameOne.changeUserInputToGuess(arrayOfDigits));
                    // Throws an exception if user enters an invalid entry, and runs the loop again
                } catch(Exception e){
                    System.out.println("Invqalid entry.");
                }
            }           
        }

    }
}   

