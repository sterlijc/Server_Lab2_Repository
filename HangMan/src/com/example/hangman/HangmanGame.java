/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
  Completed: 1/14/2014
*/


package com.example.hangman;

/**
 * <h2>Overview</h2>
 * 
 * <p>This class is used to implement that actual hangman game. It includes all of the funcitonality to 
 * play the game, such that the only thing a user of this class must do is create an instance, and 
 * supply a guess String (the funciton parses it to its first character). </p>
 * 
 * <h3>Testing Summary</h3>
 * 
 * <p>The correctness of this class was tested in conjunction with the HangmanActivity. For more details 
 * on the testing conditions, consult the {@link com.example.hangman.HangmanActivity} notes.</p>
 * 
 * @author Daniel Griffin
 * @author Jordan Ross
 * 
 * */
public class HangmanGame {
	
	//Public Class Members.
	/**Constant returned by getGameStatus() when the user has won the game.*/
	public static final int USER_WON = 0;
	/**Constant returned by getGameStatus() when the user has lost the game.*/
	public static final int USER_LOST = 1;
	/**Constant returned by getGameStatus() when the game is still in progress.*/
	public static final int IN_PROGRESS = 2;
	
	//Private Members
	/**Variable used to hold the solution string, or the string that is trying to be guessed.*/
	private String solutionString;
	/**Variable that holds the number of guesses left for the user to try and guess the word. There is a maximum of 7.*/
	private int getNumRemaining;
	/**Variable that holds the current hangman string. This includes previously found letters as well as letters not found yet (represented by "_").*/
	private String currentString = " ";
	
	/**Default fill constructor.*/
	public HangmanGame(String word) {
		//Ensure the word used is lowercase.
		solutionString = word.toLowerCase();
		getNumRemaining = 7;
		for(int i = 0; i< word.length(); i++){
			currentString += "_ " ;
		}
	}
	
	/*--------------------------------------------
	  					Public Methods
	  --------------------------------------------*/
	/**
	 * Description: Method is used to get the current status of the game.
	 * 
	 * @return USER_WON if the user has won the game.
	 * 		   USER_LOST if the user has lost the game.
	 * 		   IN_PROGRESS if the game is still in progress (The user still has chances to guess the word)
	 * 
	 * */
	public int getGameStatus(){
		String tempCurrentString = new String(currentString);

		if(tempCurrentString.replace(" ", "").replace("_", "").equals(solutionString) == true){
			return USER_WON;
		}else if(getNumRemaining == 0){
			return USER_LOST;
		}else if(getNumRemaining > 0){
			return IN_PROGRESS;
		}else{
			return -1;
		}

	}
	
	/**
	 * Description: Method accepts a guess, and checks to see if that guess is in the solution string. If it is, 
	 * it adds the letter to the current hangman string (including repeats) and returns the new hangman string.
	 * 
	 * @param guess the string representing the character guess.
	 * 
	 * @return returns the string containing previously guessed correct letters, as well as unguessed letters (represented by a "_").
	 * */
	public String tryGuess(String guess){
		guess = guess.toLowerCase();
		String tempCurrentString = "";
		int index = 0; 
		boolean guessRight = false;
		
		for(int j = 0; j < solutionString.length(); j++){
		
			if(solutionString.charAt(j) != guess.charAt(0)){
				//Solution string char not equal to guess char, so continue.
				continue;
			}else{
				//Clear tempCurrentString in case there is a repeated letter.
				tempCurrentString = "";
				
				//Set the flag to true.
				guessRight = true;
				
				//Loop through the current string to build a new string with the guess char added.
				for(int i = 0; i<currentString.length(); i++){
					if(i == ((2*j) + 1)){
						tempCurrentString += guess;
					}else if(i % 2 == 0){
						tempCurrentString += " ";
					}else{
						tempCurrentString += currentString.charAt(i);
					}
				}
			
				//Set the current string to the new value.
				currentString = tempCurrentString;
			}
		}
		
		if(guessRight == false){
			getNumRemaining--;
		}
		
		return currentString;
	}
	
	/**Description: Method returns the number of guesses the user has remaining.*/
	public int getNumRemaining(){
		return getNumRemaining;
	}
	
	/**Description: Method returns the current hangman string containing previously guessed correct letters, as well as unguessed letters (represented by a "_")*/
	public String getCurrentHangmanString(){
		return currentString;
	}

	/**Description: Method returns the solution string that the user is trying to guess.*/
	public String getSolutionString(){
		return solutionString;
	}
	
}
