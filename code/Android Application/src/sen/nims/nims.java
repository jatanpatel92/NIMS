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
/*This is the main class of Our Android Application NIMS.
 * It handles coordinator login. 
 */

package sen.nims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;


public class nims extends Activity {
	//Database class's object
	private NIMSSQLiteOpenHelper helper;
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        coord_login();
    }
    
    /****** Coordinator Login Function *******/
    public void coord_login()
    {
    	//Customize the default title bar
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    	setContentView(R.layout.login);
    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_login);
    	GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
        View title = getWindow().findViewById(R.id.header);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundDrawable(gd);
        
        //Edit Texts of XML
    	final EditText username = (EditText)findViewById(R.id.username_entry);
    	username.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        final EditText password = (EditText)findViewById(R.id.password_entry);
        final Button login=(Button)findViewById(R.id.login);
        final Context context = getApplicationContext();
        //Login Button Clicked
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View clicked) {
            	//Family Cursor
            	Cursor cursorDB_coord=helper.getCoordCursor();
            	//Search for input username in database
            	cursorDB_coord=helper.getCursorToName(cursorDB_coord, username.getText().toString());
            	
            	//Validation of input fields
            	if (username.length()==0)
            	{
            		Toast toast = Toast.makeText(context,"Enter Your Username", Toast.LENGTH_SHORT);
            		toast.show();
            	}
            	else if(password.length()==0)
            	{
            		Toast toast = Toast.makeText(context,"Enter Your Password", Toast.LENGTH_SHORT);
            		toast.show();
            	}
            	else if (cursorDB_coord==null)
            	{
            		Toast toast = Toast.makeText(context,"Enter Valid Username", Toast.LENGTH_SHORT);         		
            		toast.show();
            	}
            	else
            	{	
	            	if(cursorDB_coord.getString(2).equals(password.getText().toString()))
	            	{
	            		startCampaignActivity(cursorDB_coord.getString(1));
	            		cursorDB_coord.close();
	            	}
	            	else
	            	{
	            		Toast toast = Toast.makeText(context,"Enter Valid Password", Toast.LENGTH_SHORT);	            		
	            		toast.show();
	            	}
            	}
            		
            }
        });
    }
    /****** This function Call Campaign Activity *******/
    public void startCampaignActivity(String coord_username)
    {
    	Bundle bundle=new Bundle();
	 	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), CampaignInfo.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
    /****** This function is called when back button is clicked *******/
    @Override
    public void onBackPressed()
    {
   	    finish();	
    }
    
   
}