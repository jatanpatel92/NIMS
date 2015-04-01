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


/* FamilyList Activity
 * Displays list of families's head name
 */


package sen.nims;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import sen.nims.NIMSSQLiteOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class familiesList extends Activity {

	private NIMSSQLiteOpenHelper helper;
	private int setID=0;
	String coord_username=null;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		//setID as a parameter to the activity
		setID = bundle.getInt("setID");
		coord_username = bundle.getString("coorUserName");
		setContentView(R.layout.families_list);
		
        ListView list=(ListView)findViewById(R.id.list);
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
        TextView family_head_name_title=new TextView(this);
        family_head_name_title.setText("Family Head Name");
        list.addHeaderView(family_head_name_title);
        final ArrayList<String> families=new ArrayList<String>();
        	
        	//bind the list
            Cursor cursorDB_family=helper.getCursor();
            if(cursorDB_family != null){
    			cursorDB_family.moveToFirst();			//move the cursor to first row
    		    if(! cursorDB_family.isAfterLast())		//returns whether cursor is pointing to position after last row
    			{
    		    	do{
    		    		if(cursorDB_family.getInt(19)==setID)
    		    		{
    		    			families.add(cursorDB_family.getString(1));
    		    		}
    		    			
    		    	 }while (cursorDB_family.moveToNext());
    			}
            }
            cursorDB_family.close(); 
            Collections.sort(families);
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,families);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new OnItemClickListener() {
    			@Override
    			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
    					long arg3) {
    				Cursor cursorDB_family=helper.getCursor();
    				cursorDB_family =helper.getCursorToName(cursorDB_family, (String) families.get(arg2-1));
    				familyMenu(setID,cursorDB_family.getInt(0));
    				cursorDB_family.close(); 
    			}
              });
	}
	/**Start FamilyMenu Activity**/
	public void familyMenu(final int set_id,final int fam_id)
    {
    	 /**Calling Another Activity**/
    	 //FamilyID as a parameter to the activity
		 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",fam_id);
	  	 bundle.putString("coorUserName", coord_username);
	  	 bundle.putInt("setID", (int)set_id);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), FamilyMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);	
		 finish();
    }
	@Override
    public void onBackPressed() {
        super.onBackPressed();
        //setID as a parameter to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", setID);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), CheckNewFamily.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    	finish();
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