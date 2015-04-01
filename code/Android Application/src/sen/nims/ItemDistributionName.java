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
/* ItemDistributionName Activity
 * Take Item Name as an Input from Coordinator
 */


package sen.nims;

import android.os.Bundle;
import sen.nims.NIMSSQLiteOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.InputFilter;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDistributionName extends Activity {

	private NIMSSQLiteOpenHelper helper;
	int set_id=0;
	String coord_username=null;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		coord_username = bundle.getString("coorUserName");
		set_id = bundle.getInt("setID");
		get_item_name(coord_username);
	}
 
    /**Handle Item Name Input*/
    public void get_item_name(String coord_username)
    {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    	setContentView(R.layout.item_distribution_name); 
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
    	//Elements of ItemNameEntry
    	final EditText item_name = (EditText)findViewById(R.id.item_name_entry);
        final Button submit=(Button)findViewById(R.id.submit); 

        //Filters
        item_name.setFilters(new InputFilter[]{helper.nameInputFilter}); 

   	    //On Submitting the Campaign information by coordinator
   	    submit.setOnClickListener(new View.OnClickListener() {
        public void onClick(View clicked) {
	        	
	        	/**Validation **/
                if(item_name.length()==0)
	        	{
	        		Toast toast = Toast.makeText(item_name.getContext(),"Enter Item Name", Toast.LENGTH_SHORT);
	        		toast.show();
	        	}
	        	else
	        	{
	        		    Cursor cursor_item=helper.getItemInfoCursor();
	        		    cursor_item=helper.getCursorToName(cursor_item,item_name.getText().toString());
	        		    int item_id=0;
	            	    if(cursor_item==null)
	            	    	item_id=helper.addNewItem(item_name.getText().toString());
	            	    else 
	            	    {	helper.updateItemInfoDatabase(cursor_item.getInt(0),item_name.getText().toString()); 
	            	        item_id=cursor_item.getInt(0);
	            	    }
                    startFamilyListItemActivity((int)set_id,item_id);
	        	}
             }
         });
    }
    
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startSelectProjectActivity();
 
    }
    public void startFamilyListItemActivity(int set_id,int item_id)
    {
	     /**Calling Another Activity**/
	 	 Bundle bundle=new Bundle();
	 	 bundle.putString("coorUserName", coord_username);
	 	 bundle.putInt("setID", (int)set_id);
	 	 bundle.putInt("itemID", item_id); 
	 	 Intent newIntent = new Intent(this.getApplicationContext(), familiesListItemDistribute.class);
	 	 newIntent.putExtras(bundle);
	 	 startActivity(newIntent);
    }
    
    public void startSelectProjectActivity()
    {
	     /**Calling Another Activity**/
		 //setID as a parameter to the activity
	 	 Bundle bundle=new Bundle();
	 	 bundle.putString("coorUserName", coord_username);
	 	 bundle.putInt("setID", (int)set_id);
	 	 Intent newIntent = new Intent(this.getApplicationContext(), SelectProject.class);
	 	 newIntent.putExtras(bundle);
	 	 startActivity(newIntent);
    }

}