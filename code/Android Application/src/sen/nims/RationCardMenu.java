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
/* RationCardMenu Activity
 * It handles selection of status of ration card. 
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

public class RationCardMenu extends Activity {
	NIMSSQLiteOpenHelper helper;
	public int setID=0;
	public int familyID=0;
	String coord_username=null;
	String ration_card_status=null;
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
         //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        Bundle bundle = this.getIntent().getExtras();
		//setID as a parameter to the activity
        familyID=bundle.getInt("FamilyId");
		setID = bundle.getInt("setID");
		coord_username = bundle.getString("coorUserName");
		ration_card_menu(familyID,setID);
       
    }    
  	//Identity Cards Menu
    public void ration_card_menu(final int familyID,final long set_id)
    {
              		setContentView(R.layout.ration_card);
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
              		final Button card_apply=(Button)findViewById(R.id.card_apply);
              		final Button card_issued=(Button)findViewById(R.id.card_issued);
              		
              		//Ration Card Apply Clicked
              		card_apply.setOnClickListener(new View.OnClickListener() {
                    	public void onClick(View clicked) {
                    		    ration_card_status="APPLIED_NOT_ISSUED";
                    		    StartRationCardCategory();
                    	    }
                      });
                    //Ration Card Issue Clicked
              		card_issued.setOnClickListener(new View.OnClickListener() {
                    	public void onClick(View clicked) {
                    		ration_card_status="ISSUED";
                		    StartRationCardCategory();
                       }
                    });            		

  	   
    }
    public void StartRationCardCategory()
    {
    	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID",  setID);
	  	 bundle.putString("coorUserName", coord_username);
	  	 bundle.putString("status",ration_card_status );
	  	 Intent newIntent = new Intent(this.getApplicationContext(), RationCardCategory.class);
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
 	 @Override
     public void onBackPressed()
     {
    	 /**Calling Another Activity**/
    	 //FamilyID and set_id as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID", setID);
	   	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), IdentityCardsMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);		
     }
    
    
    
}