/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
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
 * <p></p>
 * 
 * @author Daniel Griffin
 * @author Jordan Ross
 * 
 * */
public class HangmanActivity extends Activity {

	//Private Members
	private HangmanGame game;
	
	//Private UI Members
	private TextView outputText;
	private EditText guessEditText;
	private Button guessButton;
	private Button replayButton;
	private TextView endGameMessageTextView;
	private TextView hangmanTextView;
	
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
