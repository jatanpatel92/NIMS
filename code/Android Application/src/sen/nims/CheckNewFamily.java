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

/* CheckNewFamily Activity
 * Checks Whether Coordinator is going To Create New Family Info or Show Existent Family Info 
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
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class CheckNewFamily extends Activity {
	
	NIMSSQLiteOpenHelper helper;
	String coord_username=null;
	private int set_id=0;
		
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);

        //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        Bundle bundle = this.getIntent().getExtras();
		set_id = bundle.getInt("setID");
		coord_username = bundle.getString("coorUserName");
		check_new_family(set_id);       
    }
    
    /****** Handle Check New Family Menu  *******/
    public void check_new_family(final long set_id)
	{
    	setContentView(R.layout.family_check_existence);
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
        		startSelectProjectActivity((int)set_id);
        	}
        });

 		Button new_family_info=(Button)findViewById(R.id.new_family_info);
   		Button exisiting_family_info=(Button)findViewById(R.id.exisiting_family_info);
 		
         //New Family Button Click
    	 new_family_info.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View clicked) {
                	         StartInsertUpdateFamilyInfoActivity((int)set_id,"get");
                          }
                     });
         //Existing Family Button Click
         exisiting_family_info.setOnClickListener(new View.OnClickListener() {
                   public void onClick(View clicked) {
                	          StartFamiliesListActivity(set_id);
                      }
                     });
    }
    
    
    public void StartInsertUpdateFamilyInfoActivity(int set_id,String task)
    {
    	//FamilyID as a parameter to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", (int) set_id);
    	bundle.putInt("famID", 0);
    	bundle.putString("Task",task);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), InsertUpdateFamilyInfo.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
  
    //Existing Family Information Menu
    public void StartFamiliesListActivity(final long set_id)
    {
    	
    	//FamilyID as a parameter to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", (int) set_id);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), familiesList.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
    
    public void startSelectProjectActivity(int set_id)
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
    
    public void startLoginActivity()
    {
    	Intent newIntent = new Intent(this.getApplicationContext(), nims.class);
        startActivity(newIntent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /**Calling Another Activity**/
		 //setID as a parameter to the activity
	 	 Bundle bundle=new Bundle();
	 	 bundle.putInt("setID",set_id);
	 	 bundle.putString("coorUserName", coord_username);
	 	 Intent newIntent = new Intent(this.getApplicationContext(), SelectProject.class);
	 	 newIntent.putExtras(bundle);
	 	 startActivity(newIntent);

    } 
}