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

/* ViewFamilyInfo Activity
 * Displays Family Info
 */

package sen.nims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;


public class ViewFamilyInfo extends Activity {
	private NIMSSQLiteOpenHelper helper;
	private int setID=0;
	private int familyID=0;
	String coord_username=null;
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        Bundle bundle = this.getIntent().getExtras();
		//setID as a parameter to the activity
	    setID = bundle.getInt("setID");
	    familyID=bundle.getInt("famID");
	    coord_username = bundle.getString("coorUserName");
		view_family_info(familyID,setID);
       
    }    
    //View Main Family Info
  	public void view_family_info(final int familyID,final long set_id)
  	{
  		setContentView(R.layout.family_main_info_view);
  		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
    	GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
        View title = getWindow().findViewById(R.id.header);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundDrawable(gd);
        
  		TextView coord_username_title=(TextView)findViewById(R.id.Username);
    	coord_username_title.setText(coord_username);
    	
    	Button ngo_projects_button=(Button)findViewById(R.id.Home);
    	ngo_projects_button.setText(Html.fromHtml(getString(R.string.Home)));
      	ngo_projects_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		startSelectProjectActivity();
        	}
        });
  		//Extracting Family Info from Database and display it on 
  		Cursor cursorDB=helper.getCursorToID(helper.getCursor(),familyID);
  		final TextView family_head_name_entry = (TextView)findViewById(R.id.family_head_name_entry);
		family_head_name_entry.setText(cursorDB.getString(1));
		final TextView no_of_members_entry = (TextView)findViewById(R.id.no_of_members_entry);
		no_of_members_entry.setText(Integer.toString(cursorDB.getInt(2)));
		final TextView fam_no_of_children_entry= (TextView)findViewById(R.id.fam_no_of_children_entry);
		fam_no_of_children_entry.setText(cursorDB.getString(3));
	    final TextView fam_last_migrated_from_entry = (TextView)findViewById(R.id.fam_last_migrated_from_entry);
	    fam_last_migrated_from_entry.setText(cursorDB.getString(4));
	    final TextView fam_traditional_occupation_entry = (TextView)findViewById(R.id.fam_traditional_occupation_entry);
	    fam_traditional_occupation_entry.setText(cursorDB.getString(5));
		final TextView fam_daily_income_entry = (TextView)findViewById(R.id.fam_daily_income_entry);
		fam_daily_income_entry.setText(Integer.toString(cursorDB.getInt(6)));
		final TextView fam_no_of_children_school_entry = (TextView)findViewById(R.id.fam_no_of_children_school_entry);
		fam_no_of_children_school_entry.setText(Integer.toString(cursorDB.getInt(7)));
		final TextView family_community_entry = (TextView)findViewById(R.id.family_community_entry);    
		Cursor cursorDB_com=helper.getCursorToID(helper.getCommunityCursor(),cursorDB.getInt(16));
		family_community_entry.setText(cursorDB_com.getString(1));
		final TextView fam_no_handicapped_entry = (TextView)findViewById(R.id.fam_no_handicapped_entry);
		fam_no_handicapped_entry.setText(Integer.toString(cursorDB.getInt(15)));
		final TextView electricity_access_entry= (TextView)findViewById(R.id.electricity_access_entry);
	    if(cursorDB.getInt(17)==1)
	    	electricity_access_entry.setText("Yes");
	    else 
	    	electricity_access_entry.setText("No");
	    final TextView water_connection_entry= (TextView)findViewById(R.id.water_connection_entry);
		if(cursorDB.getInt(18)==1)
			water_connection_entry.setText("Yes");
		else
			water_connection_entry.setText("No");
 		cursorDB.close();
 		
 		//Edit Button on View Family Info Menu
 		final Button edit=(Button)findViewById(R.id.edit);
 		edit.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		StartInsertUpdateFamilyInfoActivity((int)set_id,(int)familyID,"update");
        	}
        });
 	}
  	public void StartInsertUpdateFamilyInfoActivity(int set_id,int fam_id,String task)
    {
    	//FamilyID as a parameter to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", (int) set_id);
    	bundle.putInt("famID", fam_id);
    	bundle.putString("Task",task);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), InsertUpdateFamilyInfo.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
  	 @Override
     public void onBackPressed()
     {
    	 /**Calling Another Activity**/
    	 //FamilyID and set_id as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID", setID);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), FamilyMenu.class);
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