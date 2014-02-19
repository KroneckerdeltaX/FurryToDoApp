package com.example.todoapp1;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment.SavedState;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends Activity {
	private  EditText etItems ;	
 int pos ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_item);
		pos = getIntent().getIntExtra("positionvalue",0); // cliffnotes wrong. This fcn needs two arguments. I just picked 0 as default?
		String viewitem = getIntent().getStringExtra("viewitem");
		etItems = (EditText) findViewById(R.id.etItems);
		etItems.setText(viewitem);
	}
	//set the form text field to contain the item's initial value. 
	//Be sure the user's cursor in the text field is at the end of the current text value 
	//and is in focus by default.

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_item, menu);
		return true;
	}
	
	public void onSave(View v) {
//fire when save button is pressed
		//SavedState
		  // Prepare data intent 
		  Intent data = new Intent();
		  // Pass relevant data back as a result
		  data.putExtra("etItems", etItems.getText().toString()); //etItems is the edit text object .class edittext
		  data.putExtra( "positionvalue", pos);
		  // Activity finished ok, return the data
		  setResult(RESULT_OK, data); // set result code and bundle data for response
		  finish(); // closes the activity, pass data to parent
		} 
	
	}
	
	
