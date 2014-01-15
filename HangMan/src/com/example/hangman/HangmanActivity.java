/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
  Completed: 1/14/2014
*/

package com.example.hangman;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * <h2>Overview</h2>
 * 
 * <p>This class holds the main functionality of the Hangman App. It shows the 
 * user how many chances he has left, the previously found letters, and supplies a method for 
 * inputing guesses. When the user has won, a "Winner!!!" string will appear in the top TextView. 
 * When the user has lost, a "Looser..." string will appear in the top TextView.</p>
 * 
 * <h3>Testing Summary</h3>
 * <p>This class has been tested using the word "look", "college", and a random sequence fo numbers. 
 * In all cases, this class accepted a string (and correctly parsed it by using only the first character 
 * of any string input) and output the correct hangman string. When the user had finally guessed the 
 * correct word, the "Winner!!!" string was correctly output. Also, by inputing "Loccccccc" for the case 
 * where "look" was the correct word, the program was shown to accurately output the "Looser" string, and 
 * enable the button letting the user try again. It also disabled the button used to submit guesses, thus 
 * ensuring no errors would occur due to guesses input after the game was over.</p>
 * 
 * @author Daniel Griffin
 * @author Jordan Ross
 * 
 * */
public class HangmanActivity extends Activity {

	//Private Members
	/**Reference to the HangmanGame object used to perform the game.*/
	private HangmanGame game;
	
	//Private UI Members
	/**Reference to the View holding the word that is trying to be guessed.*/
	private TextView outputText;
	/**Reference to the View used to input guesses.*/
	private EditText guessEditText;
	/**Reference to the Button used to submit a guess.*/
	private Button guessButton;
	/**Reference to a button used to indicate the user wants to play again.*/
	private Button replayButton;
	/**Reference to the View showing the "HANGMAN" letters indicating the chances left for a guess.*/
	private TextView hangmanTextView;
	
	/**Description: Method initializes this Activity, gets GUI references, and defines the "OnClickListeners" for the buttons.*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Load the GUI.
		setContentView(R.layout.hangman_activity_layout);
		
		//Get references.
		outputText = (TextView)findViewById(R.id.outputTextView);
		guessEditText = (EditText)findViewById(R.id.guessEditText);
		guessButton = (Button)findViewById(R.id.guessButton);
		replayButton = (Button)findViewById(R.id.replayButton);
		hangmanTextView = (TextView)findViewById(R.id.hangmanTextView);
		
		//Get the hangman game word
		String word = getIntent().getStringExtra(WordEntryActivity.EXTRA_MESSAGE);
		
		//Create a HangmanObject and pass it the game word.
		game = new HangmanGame(word);
		
		//Get the current string and set it to the outputTextView.
		outputText.setText(game.getCurrentHangmanString());
		
		//Set the replayButton OnClickListener.
		replayButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//If this button is clicked, finish this activity and return to the "WordEntryActivity".
				finish();
			}
		});
		
		//Set the guessButotn OnClickListener.
		guessButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Get the edit text string.
				String guess = guessEditText.getText().toString();
				
				//If no text, make toast and return.
				if(guess.length() == 0){
					Toast.makeText(guessEditText.getContext(), "Enter guess", Toast.LENGTH_SHORT);
					return;
				}
				
				//Set the guess string to the value of the first char.
				guess = String.valueOf(guess.charAt(0));
				
				//Pass the guessed char to the game object.
				String outputString = game.tryGuess(guess);
				//Check whether or not the game has ended.
				int status = game.getGameStatus();
				
				int temp = 0;
				
				if(status == HangmanGame.USER_WON){
					//Set the output text.
					outputText.setText(outputString);
					//Send winner signal.
					hangmanTextView.setBackgroundColor(Color.BLUE);
					hangmanTextView.setText("Winner!!!");
					//Enable replay button.
					replayButton.setEnabled(true);
					//Disable the guess button.
					guessButton.setEnabled(false);
				}else if(status == HangmanGame.USER_LOST){
					//Set the output text with the correct answer.
					outputText.setText("Ans: " + game.getSolutionString());
					//Set the hangman text view.
					hangmanTextView.setBackgroundColor(Color.RED);
					hangmanTextView.setText("Looser!!!");
					//Enable replay button.
					replayButton.setEnabled(true);
					//Disable guess button.
					guessButton.setEnabled(false);
				}else if(status == HangmanGame.IN_PROGRESS){
					int numTriesLeft = game.getNumRemaining();
					
					if(numTriesLeft == 7){
						hangmanTextView.setText("       ");
					}else if(numTriesLeft == 6){
						hangmanTextView.setText("H      ");
					}else if(numTriesLeft == 5){
						hangmanTextView.setText("HA     ");
					}else if(numTriesLeft == 4){
						hangmanTextView.setText("HAN    ");
					}else if(numTriesLeft == 3){
						hangmanTextView.setText("HANG   ");
					}else if(numTriesLeft == 2){
						hangmanTextView.setText("HANGM  ");
					}else if(numTriesLeft == 1){
						hangmanTextView.setText("HANGMA ");
					}
					
					outputText.setText(outputString);
					
				}
				
			}
		});
		
		
	}

	
	
}
