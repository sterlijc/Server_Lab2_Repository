/*
  Programmer 1: Daniel Griffin
  Programmer 2: Jordan Ross
*/
package com.example.hangman;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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

}
