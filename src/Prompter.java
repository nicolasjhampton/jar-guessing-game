import java.util.Scanner;

public class Prompter {
  private final Scanner scanner;
  private Jar mJar;
  private int mGuesses;
 
  public Prompter(Scanner scan) {
   scanner = scan;
  }
  
  public void printAdminScreen() {
   // clear screen http://stackoverflow.com/questions/2979383/java-clear-the-console
   System.out.print("\033[H\033[2J");  
   System.out.flush(); 
    
   System.out.println("ADMINISTRATION SETUP");
   System.out.println("====================");
  }
  
 public void setJar() {
   System.out.printf("Name of items in the jar: ");
   String itemName = scanner.nextLine();
   System.out.printf("Maximum number of %s in the jar: ", itemName);
   String capacityString = scanner.nextLine();
   itemName = itemName.toLowerCase();
   int capacity = Integer.parseInt(capacityString);
   mJar = new Jar(itemName, capacity);
   mJar.fill();
   mGuesses = 0;
 }
  
  public void titleScreen() {
   System.out.println();
   System.out.println("PLAYER");
   System.out.println("====================");
   System.out.printf("%nYour goal is to guess how many %s are in the jar.", mJar.getName());
   System.out.printf("%nYour guess should be between 1 and %s.", mJar.getCapacity());
   System.out.println(" ");
   System.out.printf("Ready? (press ENTER to start guessing)");
   scanner.nextLine();
  }
  
  private int getGuess() {
    int guess;
    boolean in = false;
    
    do {
      if(in) {
        System.out.println("Try again for free, that's outside of the range!");
      } else {
        in = true;
      }
      
      guess = 0;
      System.out.printf("%nGuess: ");
      String guessString = scanner.nextLine();
      try {
        guess = Integer.parseInt(guessString);
      } catch (NumberFormatException nfe) {
        System.out.println("Oops!");
      }
      
    } while (guess > mJar.getCapacity() || guess < 1);
    
    return mJar.isCount(guess);
  }
  
  public void makeGuess() {
    int result = getGuess();
    mGuesses++;
    
    if(result > 0) {
     System.out.println("Too High!"); 
    } else if (result < 0) {
     System.out.println("Too Low!"); 
    }
    
    if(result != 0) {
      makeGuess();
    }
  }
  
  public boolean win() {
    System.out.printf("%nCongrats! You guessed that there are %s %s in the jar!", 
                    mJar.getCount(), mJar.getName());
    System.out.printf("%nIt took you %s guess(es) to get it right.", 
                    mGuesses);
    System.out.println();
    System.out.println("Play Again? (y/n)");
    String again = scanner.nextLine();
    return again.toLowerCase().charAt(0) == 'y';
    
  }
}