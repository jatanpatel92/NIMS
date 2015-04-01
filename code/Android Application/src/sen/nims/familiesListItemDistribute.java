/**
   Copyright 2012 "Name"

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License

**/



/* FamilyListItemDistribution Activity
 * Displays List of FamilyList for item distribution
 */


package sen.nims;


import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sen.nims.NIMSSQLiteOpenHelper;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class familiesListItemDistribute extends ListActivity {
	
	private NIMSSQLiteOpenHelper helper;
	private int setID=0;
	String coord_username=null;
    private int item_id=0;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		//setID as a parameter to the activity
		setID = bundle.getInt("setID");
		coord_username = bundle.getString("coorUserName");
	    item_id=bundle.getInt("itemID");	
		//ListView
		final ListView lv = getListView();
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
    	GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
        View title = getWindow().findViewById(R.id.header);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundDrawable(gd);
        

    	TextView coord_username_title=(TextView)findViewById(R.id.Username);
    	coord_username_title.setText(coord_username);
    	
    	
    	//Handle NGO Project Click
    	Button ngo_projects_button=(Button)findViewById(R.id.Home);
    	ngo_projects_button.setText(Html.fromHtml(getString(R.string.Home)));
      	ngo_projects_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		startSelectProjectActivity();
        	}
        });
        //Add Footer to the list
        Button submit=new Button(this);
        submit.setText("Submit");
        lv.addFooterView(submit);
        //Submit Button Click
        submit.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		startSelectProjectActivity();
        		    
        	}
		 });
		// Create an ArrayAdapter of RowMemberLsting objects
		final ArrayAdapter<RowMemberListingStatus> adapter = new ArrayAdapterMemberListStatus
		(this,getRowMemberListingStatus(),item_id);
		lv.setAdapter(adapter);
	}

	//Return list of RowMemberListingStatus objects
	private List<RowMemberListingStatus> getRowMemberListingStatus() {
		 Cursor cursorDB_family=helper.getCursor();
		 List<RowMemberListingStatus> rows_list = new ArrayList<RowMemberListingStatus>();
		 ArrayList<String> list_families=new ArrayList<String>();
		 if(cursorDB_family != null){
				cursorDB_family.moveToFirst();			//move the cursor to first row
			    if(! cursorDB_family.isAfterLast())		//returns whether cursor is pointing to position after last row
				{
			    	do{
			    		if(cursorDB_family.getInt(19)==setID)
			    		{
			    			list_families.add(cursorDB_family.getString(1));
			    		}
			    			
			    	 }while (cursorDB_family.moveToNext());
				}
	        }
		cursorDB_family.close(); 
		Collections.sort(list_families);
		for(String str:list_families)
			rows_list.add(get(str));
		return rows_list;

	}

    //Return RowMemberListingStatus Object containing string s
	private RowMemberListingStatus get(String s) {
		return new RowMemberListingStatus(s);
	}

	/***************Array Adapter Member List Status Class**********/
	public class ArrayAdapterMemberListStatus extends ArrayAdapter<RowMemberListingStatus> {

		private final List<RowMemberListingStatus> list;
		private NIMSSQLiteOpenHelper helper;
		private final Activity context;
		private int item_id=0;
		//Constructor
		public ArrayAdapterMemberListStatus(Activity context, List<RowMemberListingStatus> list,int item_id) {
			super(context, R.layout.family_list_row_item_distribute, list);
			this.context = context;
			helper = new NIMSSQLiteOpenHelper(this.context);
			this.list = list;
			this.item_id=item_id;
		}

		//ViewHolder Class
		class ViewHolder {
			protected TextView text;
			protected  EditText no_items;
			
		}

		//Set the view
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.family_list_row_item_distribute, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.text = (TextView) view.findViewById(R.id.label);
				viewHolder.no_items = (EditText) view.findViewById(R.id.no_items);
			    
				viewHolder.no_items.setOnFocusChangeListener(new OnFocusChangeListener() {
					public void onFocusChange(View v, boolean hasFocus) {
					    Log.e("A",viewHolder.no_items.getText().toString());
					    Cursor cursorDB_family=helper.getCursor();
	    				cursorDB_family =helper.getCursorToName(cursorDB_family,viewHolder.text.getText().toString());
	    				String no_items=viewHolder.no_items.getText().toString();
	    				if( no_items.length()!=0)
	    				{
	    					
	    					Cursor cursor_item_distribute=helper.getItemDistributeCursor();
		   	              	//Search cursor for given familyID 
		   	                boolean search_success = false;
		   	          	 	if(cursor_item_distribute != null){
		   	          	 	   cursor_item_distribute.moveToFirst();			//move the cursor to first row
		   	          	 			    if(! cursor_item_distribute.isAfterLast())		//returns whether cursor is pointing to position after last row
		   	          	 				{
		   	          	 			    	do{
		   	          								if(cursor_item_distribute.getInt(0)==setID && cursor_item_distribute.getInt(2)==item_id)
		   	          								{	 
		   	          								    search_success=true;
		   	          	 			    			    break;
		   	          								}
		   	          	 							         							
		   	          	   					 }while (cursor_item_distribute.moveToNext());	    	
		   	          	 				}
		   	          	 		}

	   	                  
	   	            	    
							if(search_success==false)
	   	            	    	helper.addNewItemDistributedFamily(item_id,cursorDB_family.getInt(0), Integer.parseInt(no_items));
	   	            	    else 
	   	            	    {	 helper.updateItemDistributeDatabase(cursorDB_family.getInt(0),Integer.parseInt(no_items)); 
	   	            	    }	
	    				}
					}

			    });       
				view.setTag(viewHolder);

			} else {
				view = convertView;
			}
			ViewHolder holder = (ViewHolder) view.getTag();
			holder.text.setText(list.get(position).getName());
			
			/*Cursor cursorDB_family=helper.getCursor();
			cursorDB_family=helper.getCursorToName(cursorDB_family,list.get(position).getName());
			
			if(cursorDB_family!=null)
			{
				if()
				/*if(!(cursorDB_member.getString(card_type_index).equals("NOT_APPLIED")))
				{
    				  if(cursorDB_member.getString(card_type_index).equals("APPLIED_NOT_ISSUED"))
						 holder.apply.setChecked(true);
			          else
			        	 holder.issued.setChecked(true);
				}
				cursorDB_member.close();*/
			//}
			/*else
				Log.e("Member Cursor","NULL");*/
			return view;
		}
			
	}

	/**End of ArrayAdapterMemberList Class***/
	
	
	/**RowMemberListingStatus Class**/
	public class RowMemberListingStatus {

		private String name;
		private int no_items;


		public RowMemberListingStatus(String name) {
			this.name = name;
			no_items = 0;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNoItems() {
			return no_items;
		}

		public void setNoItems(int no_items) {
			this.no_items = no_items;
		}

	}
	/**End of RowMemberListingStatus Class**/

	@Override
    public void onBackPressed() {
        super.onBackPressed();
        StartItenDistributionActivity();
        
    }
	public void StartItenDistributionActivity()
    {
    	Bundle bundle=new Bundle();
	 	bundle.putString("coorUserName", coord_username);
	 	bundle.putInt("setID", setID);
    	Intent newIntent = new Intent(this.getApplicationContext(), ItemDistributionName.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
	 public void startSelectProjectActivity()
	    {
		     /**Calling Another Activity**/
			 //setID as a parameter to the activity
		 	 Bundle bundle=new Bundle();
		 	 bundle.putString("coorUserName", coord_username);
		 	 bundle.putInt("setID", setID);
		 	 Intent newIntent = new Intent(this.getApplicationContext(), SelectProject.class);
		 	 newIntent.putExtras(bundle);
		 	 startActivity(newIntent);
	    }

}