/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
  Completed: 1/14/2014
*/
package com.example.hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * <h2>Overview</h2>
 * 
 * <p>This class is used to retrieve a string that will be used by the Hangman game. The string is currently limited to 
 * 15 characters. However, the HangmanGame class can use a string with any number of characters.</p>
 * 
 * <h3>Testing Summary</h3>
 * 
 * <p>This class was tested with the boundary conditions of no input, input with over 15 characters, and 
 * input with characters 0< C <= 15. In the boundary conditions, the program created a Toast notifying the 
 * user. In the correct range, the string was correctly sent to another activity.</p>
 * 
 * @author Daniel Griffin
 * @author Jordan Ross
 * 
 * */
public class WordEntryActivity extends Activity {
	
	/**Description: Key used for transmitting and retrieving a selected hangman word from the WordEntryActivity.*/
	public final static String EXTRA_MESSAGE = "com.example.hangman.MESSAGE";
	
	/**Description: Method used to load GUI and instantiate members of the Activity.*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_entry_layout);
	}

	/**Description: Method used to create an options menu and inflate it for this activity.*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * Description: This method is called by the xml layout for this activity. It acts as the "OnClickListener" for the setWordButton.
	 * 
	 * @param view View class that calls this method.
	 * */
	public void setWord(View view)
	{
		//Create an intent for starting the HangmanActivity.
		Intent intent = new Intent(this, HangmanActivity.class);
		EditText wordEditText = (EditText) findViewById(R.id.enteredWordEditText);
		
		// Check the number of characters in the word
		String word = wordEditText.getText().toString();
		
		//Limit the word length to less than 15 characters.
		if (word.length() > 15)
		{
			Toast.makeText(wordEditText.getContext(), "Enter a word less than 15 characters!", Toast.LENGTH_SHORT).show();
			return;
		}
		//Limit the word length to being greater than 0.
		else if (word.length() == 0)
		{
			Toast.makeText(wordEditText.getContext(), "Enter a word!", Toast.LENGTH_SHORT).show();
			return;
		}
		else
		{
			//Put the string in an intent.
			intent.putExtra(EXTRA_MESSAGE, word);
			//Start the HangmanActivity.
			startActivity(intent);
		}
			
	}

}
