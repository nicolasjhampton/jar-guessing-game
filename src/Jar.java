import java.util.Random;

public class Jar {
  private Random mRandom = new Random();
  private String mContainer;
  private int mCapacity;
  private int mCount;
 
  
  public Jar(String container, int capacity) {
     mContainer = container;
     mCapacity = capacity;
  }
  
  public String getName() {
   return mContainer; 
  }
  
  public int getCapacity() {
   return mCapacity;
  }
  
  private boolean validRange(int amount) {
    return amount >= 1 && amount <= mCapacity;
  }
  
  private void fill(int amount) {
   if(!validRange(amount)) {
     throw new IllegalArgumentException("Too little or too many items!");
   }
   mCount = amount; 
  }
  
  public void fill() {
   int randomInt = mRandom.nextInt(getCapacity());
   try {
    fill(randomInt);
   } catch (IllegalArgumentException iae) {
     fill();
   }
  }
  
  public int isCount(int thisGuess) {
   int result = 0;
    
   if(thisGuess > mCount) {
     result = 1;
   } else if (thisGuess < mCount) {
     result = -1;
   } 
    
   return result; 
  }
  
  public int getCount() {
   return mCount; 
  }
  
  
}