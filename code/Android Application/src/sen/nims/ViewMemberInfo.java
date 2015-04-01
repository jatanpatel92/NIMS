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

/* ViewMemberInfo Activity
 * Displays Member Info
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


public class ViewMemberInfo extends Activity {
	private NIMSSQLiteOpenHelper helper;
	private int setID=0;
	private int familyID=0;
	String coord_username=null;
	String member_name=null;
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        Bundle bundle = this.getIntent().getExtras();
	    setID = bundle.getInt("setID");
	    familyID=bundle.getInt("FamilyId");
	    coord_username = bundle.getString("coorUserName");
		member_name=bundle.getString("memName");
		view_member_info();
       
    }    
    //View Main Family Info
  	public void view_member_info()
  	{
	        setContentView(R.layout.member_info_view);
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
	        
	        
  		    final TextView member_name_entry = (TextView)findViewById(R.id.member_name_entry);
		    final TextView relation_with_head= (TextView)findViewById(R.id.relation_with_head_entry);
		    final TextView birth_year = (TextView)findViewById(R.id.birth_year_entry);
		    final TextView occupation = (TextView)findViewById(R.id.occupation_entry);
		    final TextView gender_type=(TextView)findViewById(R.id.gender_type);
		    /*Cursor*/
	        Cursor cursorDB_member=helper.getMemInfoCursor();
	        cursorDB_member=helper.getCursorToName(cursorDB_member,member_name.toString());
	        /*Displaying Member Information*/
	        member_name_entry.setText(member_name.toString());
	        relation_with_head.setText(cursorDB_member.getString(5));
	        birth_year.setText(Integer.toString(cursorDB_member.getInt(3)));
	        occupation.setText(cursorDB_member.getString(4));
	        if(cursorDB_member.getString(6).equals("M"))
	        	gender_type.setText("Male");
	        else
	        	gender_type.setText("Female");
	 		
	        final int mem_id=cursorDB_member.getInt(0);
	        final int fam_id=cursorDB_member.getInt(2);
	        	
	 		//Edit Button on View Family Info Menu
	 		final Button edit=(Button)findViewById(R.id.edit);
	 		edit.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View clicked) {
	        		StartInsertUpdateMemberInfoActivity(fam_id,"update",mem_id);
	        	}
	        });
 	}
  	public void StartInsertUpdateMemberInfoActivity(int fam_id,String task,int mem_id)
	    {
	    	//Parameters to the activity
	    	Bundle bundle=new Bundle();
	    	bundle.putInt("memID",mem_id);
	    	bundle.putInt("famID", fam_id);
	    	bundle.putInt("setID", setID);
	    	bundle.putString("Task",task);
	    	Intent newIntent = new Intent(this.getApplicationContext(), InsertUpdateMemberInfo.class);
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
	  	 Intent newIntent = new Intent(this.getApplicationContext(), memberListInfo.class);
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