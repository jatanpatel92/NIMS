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
/* PlotCardTypeMenu Activity
 * It handles selection of type of plot card. 
 */

package sen.nims;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class PlotCardTypeMenu extends Activity {
	private NIMSSQLiteOpenHelper helper;
	public int setID=0;
	public int familyID=0;
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
		familyID=bundle.getInt("FamilyId");
		coord_username = bundle.getString("coorUserName");
		identity_cards_menu(familyID,setID);
       
    }    
  	//Identity Cards Menu
    public void identity_cards_menu(final int familyID,final long set_id)
    {
        		setContentView(R.layout.plot_card_apply);
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
              	
           		final RadioGroup plot_card_category=(RadioGroup)findViewById(R.id.category);
              	final family_info_structure family_info_struct=new family_info_structure();
           	    final Cursor cursorDB=helper.getCursorToID(helper.getCursor(),familyID);
           	    
        		if(cursorDB.getString(8)!=null)
        		{
        			String status=cursorDB.getString(8);
        			if(status.equals("SELF_OWNED"))
        				plot_card_category.check(R.id.self_owned);
        			else if(status.equals("GOVT_ALLOTED"))
        				plot_card_category.check(R.id.govt_alloted);
        			else
        				plot_card_category.check(R.id.govt_enroached);
        		}
        			
        		final Button submit=(Button)findViewById(R.id.submit); 
        		submit.setOnClickListener(new View.OnClickListener() {
                	public void onClick(View clicked) {
                		//Update Database
                		int plot_id=plot_card_category.getCheckedRadioButtonId();
                		if(plot_id==-1)
                        {
                        	Toast toast = Toast.makeText(submit.getContext(),"Select Type of Plot Card", Toast.LENGTH_SHORT);
                 		    toast.show();
                        }
                        else
                        {
	                		switch(plot_id)
	                		{
	                		   case R.id.self_owned:
	                			   family_info_struct.setFamilyPlotCardStatus("SELF_OWNED");
	                			   break;
	                		   case R.id.govt_alloted:
	                			   family_info_struct.setFamilyPlotCardStatus("GOVT_ALLOTED");
	                			   break;
	                		   case R.id.govt_enroached:
	                			   family_info_struct.setFamilyPlotCardStatus("ENCROACHED_GOVT");
	                			   break;
	                		   default:
	                			   //Invalidation message
	                			   break;
	                		}
	                		helper.updateDatabase("family_info",family_info_struct,familyID,"plot_card_status");
	                		StartIdentityCardMenuActivity();
                        }	
                      }
                 });
 		
  		
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
 	 @Override
     public void onBackPressed()
     {
 		StartIdentityCardMenuActivity();
    	 
     }
 	 public void StartIdentityCardMenuActivity()
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