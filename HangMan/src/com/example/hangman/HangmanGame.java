/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
*/


package com.example.hangman;

/**
 * <h2>Overview</h2>
 * 
 * <p></p>
 * 
 * @author Daniel Griffin
 * @author Jordan Ross
 * 
 * */
public class HangmanGame {

	//Public Class Members.
	public static final int USER_WON = 0;
	public static final int USER_LOST = 1;
	public static final int IN_PROGRESS = 2;
	
	//Private Members
	private String solutionString;
	private int getNumRemaining;
	private String currentString;
	
	/**Default fill constructor.*/
	public HangmanGame(String word) {
		solutionString = word;
		getNumRemaining = 7;
		currentString = " _ _ _ _ _ _";
	}
	
	/*--------------------------------------------
	  					Public Methods
	  --------------------------------------------*/
	public int getGameStatus(){
		String tempCurrentString = currentString;

		if(tempCurrentString.replace(" ", "").equals(solutionString) == true){
			return USER_WON;
		}else if(getNumRemaining == 0){
			return USER_LOST;
		}else if(getNumRemaining > 0){
			return IN_PROGRESS;
		}else{
			return -1;
		}

	}
	
	public String tryGuess(String guess){
		String tempCurrentString = "";
		int index = 0; 
		boolean guessRight = false;
		
		for(int j = 0; j < solutionString.length(); j++){
		
			if(solutionString.charAt(j) != guess.charAt(0)){
				//Solution string char not equal to guess char, so continue.
				continue;
			}else{
				//Set the flag to true.
				guessRight = true;
				
				//Loop through the current string to build a new string with the guess char added.
				for(int i = 0; i<currentString.length(); i++){
					if(i == ((2*index) + 1)){
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
	
	

}
