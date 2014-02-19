package com.example.todoapp1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Todo1Activity extends Activity {
	private ArrayList<String> todoItems; //create an array list
	private ArrayAdapter<String> todoAdapter; //created an adapter that just maps/translates data to a view
	private ListView lvItems; //attach adapter to list view
	private  EditText etNewItem ;
	private final int requestCode = 20;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo1);
		etNewItem = (EditText) findViewById(R.id.etNewItem);
		lvItems = (ListView) findViewById(R.id.lvItems);
		readItems();
		todoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems); 
		//adapter maps particualr data into a spec view. Creating the adapter by passing in context,view that we want to use (text view), and then the array of items
		lvItems.setAdapter(todoAdapter); //populate the list view here using this todoadapter
		setupListViewListener();
		
		
	}
	

	private void setupListViewListener() {
		lvItems.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> adapter, View item, int pos,long id) {
				Intent i = new Intent(Todo1Activity.this, EditItemActivity.class);
				 i.putExtra("positionvalue", pos); 
				  i.putExtra("viewitem",  todoItems.get(pos)); //Array is a class and todoitems is an instance of that class. so can use a method like GET/SET which is in that class to access/pull value
				  
				
				startActivityForResult(i, requestCode); // brings up the second activity. Going to send it to the second activity and wait for result to come back.
				
					}
	
		
		
	} );
		lvItems.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View item,int pos, long id) {
				todoItems.remove(pos);
				todoAdapter.notifyDataSetChanged();
				writeItems();
				return true;
			}
			
		})	;	
	}

	//private void populateArrayItems() {
		//todoItems = new ArrayList<String>();
		//todoItems.add("Item 1"); //added items to created Array list here ArrayList<String>todoitems cmd line
		//todoItems.add("Item 2");
		//todoItems.add("Item 3");

	//}
	

   public void onAddedItem(View v) {
	   String itemText = etNewItem.getText().toString();
	   todoAdapter.add(itemText);
	   etNewItem.setText("");
	   writeItems();
   }
   
   private void readItems() {
	   File filesDir = getFilesDir();
	   File todoFile = new File(filesDir, "todo.tx");
	   try { 
		   todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
	   } catch (IOException e) {
		   todoItems = new ArrayList<String>();
		   
	   }

}
   private void writeItems() {
	   File filesDir = getFilesDir();
	   File todoFile = new File(filesDir, "todo.tx");
	   
	   try {
		   FileUtils.writeLines(todoFile, todoItems);
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
		   
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo1, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // REQUEST_CODE is defined above
	  
	     // Extract name value from result extras
	    int pos = data.getIntExtra("positionvalue",0); // cliffnotes wrong. This fcn needs two arguments. I just picked 0 as default?
			String newitem = data.getStringExtra("etItems");
			//object.method
		
	 todoItems.set(pos, newitem); //Array is a class and todoitems is an instance of that class. 
	 //so can use a method like GET/SET which is in that class to access/pull value
	 //SET changes array list items
		// 1. needs which item number to change 2. Needs new text. agr1 and 2 for SET. Set needs 2 args.
	 
	 //Notify adapter
	 todoAdapter.notifyDataSetChanged();
	 
	 writeItems();
			
	  }
	
	
	
	
		
		

	} 

