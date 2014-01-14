/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
*/

package com.example.hangman;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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

	//Private UI Members
	private TextView outputText;
	private EditText guessEditText;
	private Button guessButton;
	private TextView endGameMessageTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Get references.
		outputText = (TextView)findViewById(R.id.outputTextView);
		guessEditText = (EditText)findViewById(R.id.guessEditText);
		guessButton = (Button)findViewById(R.id.guessButton);
		endGameMessageTextView = (TextView)findViewById(R.id.endGameMessageTextView);
		
		String word = getIntent().getStringExtra(WordEntryActivity.EXTRA_MESSAGE);
		
	}

	
	
}
