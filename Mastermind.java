public class Mastermind{
  private String code;
  private boolean gameOver;
  private int guesses;

  public Mastermind(String code) {
    this.code = code.toUpperCase();
    this.gameOver = false;
    this.guesses = 1;
  }

  public static String getDirections(){
    return "Welcome to Mastermind. You are the codebreaker. Your job is to guess the pattern in both letter and order. There are 6 available letters (A-F) and they will not repeat. You will have 8 guesses.  After each guess you will learn the number of letters that are correct and in the right spot as well as the number of letters that are correct but in the wrong spot. Guess by entering 4 letters between A and F with no spaces. Good luck!";
  }

  public String getTurnDirections(){
    return "Please make guess number " + guesses + " : ";
  }
  public boolean getGameOver(){
    return this.gameOver;
  }
  public static String getClosingMessage(){
    return "Thank you for playing this game.";
  }

  public static boolean checkCode(String code){

    //check if the code has repeating letters
    boolean hasRepeatingValues = false;
    for(int i = 0; i < code.length()-1; i++){
      String oneChar = code.substring(i, i+1); 
      String codeMinusOneChar = code.substring(0, i) + code.substring(i+1);
      int ifSame = codeMinusOneChar.indexOf(oneChar);
      if (ifSame >= 0){
        hasRepeatingValues = true;
      } else {
      }
    }

    //check if the letters in code are in between a-f inclusive
    String allowedLetters = "abcdef";
    boolean areLettersAllowed = true;
    for(int i = 0; i < code.length(); i++){
      String tempWord = code.substring(i, i+1);
      if(allowedLetters.indexOf(tempWord) < 0){
        areLettersAllowed = false;
      }else {
      }
    }

    //check if it's 4 letters & other follows other two rules
    if (code.length() == 4 && hasRepeatingValues == false && areLettersAllowed == true){
      return true; 
    } else {
      return false; 
    }
  }

  public String check(String input){
    input = input.toUpperCase();

    //increase guesses by 1 every time it runs
    guesses += 1;

    //once the player gets 8 turns, game is over
    if(guesses == 9){
      gameOver = true;
    }
    
    //variables for the # of correct letters
    int numWrongSpot = 0; 
    int numRightSpot = 0;

    //check if it's the length of 4
    if(input.length() != 4){
      if(guesses == 9){
        return "Your guess does not match the rules (only 4 letters between A and F without repeats). you have wasted a guess. \n \nYou are out of guesses. Better luck next time.";
      }
      return "Your guess does not match the rules (only 4 letters between A and F without repeats). you have wasted a guess.";
    }

    //check if letters repeat
    String a = input.substring(0, 1);
    String b = input.substring(1, 2);
    String c = input.substring(2, 3);
    String d = input.substring(3, 4);
    if (a.equals(b) || a.equals(c) || a.equals(d) || b.equals(c) || b.equals(d) || c.equals(d)) {
      if(guesses == 9){
        return "Your guess does not match the rules (only 4 letters between A and F without repeats). you have wasted a guess. \n \nYou are out of guesses. Better luck next time.";
      }
      return "Your guess does not match the rules (only 4 letters between A and F without repeats). you have wasted a guess.";
    }

    //check if the Code is in range of "abcdef";
    String alphabet = "abcdef";
    String tempWord = "";
    boolean inRange = true;
    if(code.length()-1 == 4){
      for(int i =0; i< input.length(); i++){
        tempWord = input.substring(i, i+1);
        if(alphabet.indexOf(tempWord) < 0){
          inRange = false;
        }
      }
    }
    if(inRange == false){
      if(guesses == 9){
        return "Your guess does not match the rules (only 4 letters between A and F without repeats). you have wasted a guess. \n \nYou are out of guesses. Better luck next time.";
      }
      return "Your guess does not match the rules (only 4 letters between A and F without repeats). you have wasted a guess.";
    }

    //now count the letters in right place and correct letters in wrong place
    for (int i = 0; i < 4; i++) {
      if (input.substring(i, i + 1).equals(code.substring(i, i + 1))) {
        numRightSpot += 1;
      } else if (code.indexOf(input.substring(i, i + 1)) != -1) {
        numWrongSpot += 1;
      }
    }

    //the player follows the rule but can't make a correct guess
    if(numRightSpot !=4 && guesses == 9){
      return "You had " + numRightSpot + " letter(s) in the correct spot and " + numWrongSpot + " correct letter(s) in the wrong spot. \n \nYou are out of guesses. Better luck next time.";
    }

    //check if four letters are all in the correct place, if so, end the game with congrats
    if(numRightSpot == 4){
      gameOver = true;
      return "Congratulations, you are correct!";
    }
    
    return "You had " + numRightSpot + " letter(s) in the correct spot and " + numWrongSpot + " correct letter(s) in the wrong spot.";
  }
}
