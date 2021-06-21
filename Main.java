import java.util.Scanner;

class Main {

	private static final Scanner SCAN = new Scanner(System.in);

	public static void main(String[] args) {

		String code = "";
		//We need to get the code for the opponent to break. This will ask a player for a code and then check to make sure it follows the rules.
		boolean validCode = false;
		while (!validCode){
			System.out.println("\n\nEnter the code your opponent will be guessing. It can only use the letters A - F and the letters cannot repeat.");
			code = SCAN.nextLine();
		validCode = Mastermind.checkCode(code);
		}

		//these 2 lines clear the console so that the other person can't see the code they are trying to guess.
		System.out.print("\033[H\033[2J"); 
    System.out.flush();  
		
	  System.out.println(Mastermind.getDirections());
		System.out.println();
		System.out.println();

		Mastermind game = new Mastermind(code);

		while (!game.getGameOver()){
			System.out.print(game.getTurnDirections());

			String input = SCAN.nextLine();
			String results = game.check(input);

			System.out.println();
			System.out.println(results);
			System.out.println();
		}

		System.out.println(game.getClosingMessage());
	}

}
