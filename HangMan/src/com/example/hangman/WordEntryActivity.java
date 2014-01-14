/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
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
 * <p></p>
 * 
 * @author Daniel Griffin
 * @author Jordan Ross
 * 
 * */
public class WordEntryActivity extends Activity {
	
	public final static String EXTRA_MESSAGE = "com.example.hangman.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.word_entry_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void setWord(View view)
	{
		Intent intent = new Intent(this, HangmanActivity.class);
		EditText wordEditText = (EditText) findViewById(R.id.enteredWordEditText);
		
		// Check the number of characters in the word
		String word = wordEditText.getText().toString();
		if (word.length() > 6)
		{
			Toast.makeText(wordEditText.getContext(), "Enter a word less than 6 characters!", Toast.LENGTH_SHORT).show();
			return;
		}
		else if (word.length() == 0)
		{
			Toast.makeText(wordEditText.getContext(), "Enter a word!", Toast.LENGTH_SHORT).show();
			return;
		}
		else
		{
			intent.putExtra(EXTRA_MESSAGE, word);
			
			startActivity(intent);
		}
			
	}

}
