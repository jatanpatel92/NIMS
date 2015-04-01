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



/* FamilyMenu Activity
 * It handles selection of options for a family. 
 */

package sen.nims;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;


public class FamilyMenu extends Activity {
	NIMSSQLiteOpenHelper helper;
	private int setID=0;
	String coord_username=null;
	
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign);
        //SQLite Database Object Initialization
        helper = new NIMSSQLiteOpenHelper(this);
        //Accept Parameters
        Bundle bundle = this.getIntent().getExtras();
		setID = bundle.getInt("setID");
		coord_username = bundle.getString("coorUserName");
		int familyID=bundle.getInt("FamilyId");
        familyMenu(familyID,setID);
       
    }    
    /****** Handle Family Information Menu  *******/
    public void familyMenu(final int familyID,final long set_id)
    {
    	 
    	setContentView(R.layout.family_info_menu);
    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
    	GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
        View title = getWindow().findViewById(R.id.header);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundDrawable(gd);
    	//Set Username
     	TextView coord_username_title=(TextView)findViewById(R.id.Username);
    	coord_username_title.setText(coord_username);
    	
    	//Handle NGO Project Click
    	Button ngo_projects_button=(Button)findViewById(R.id.Home);
    	ngo_projects_button.setText(Html.fromHtml(getString(R.string.Home)));
      	ngo_projects_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		startSelectProjectActivity((int)set_id);
        	}
        });
      	
    	//Setting the padding of each of the button
	   	Display display = getWindowManager().getDefaultDisplay();
	 	int w=display.getWidth();
	   	final Button view_family_info_button=(Button)findViewById(R.id.view_family_info);
	   	view_family_info_button.setPadding(w/6, 0, w/6, 0);
	   	final Button member_info_button = (Button)findViewById(R.id.member_list_info);
	   	member_info_button.setPadding(w/6, 0, w/6, 0);
	   	final Button identity_cards_button=(Button)findViewById(R.id.identity_cards);
	   	identity_cards_button.setPadding(w/6, 0, w/6, 0);
 		final Button govt_schemes_button=(Button)findViewById(R.id.govt_schemes);
 		govt_schemes_button.setPadding(w/6, 0, w/6, 0);

 		//Family Info Button Clicked
  		view_family_info_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		StartViewFamilyInfoMenu(familyID,set_id);
        	}
        });
  		//Member Info View
  		member_info_button.setOnClickListener(new View.OnClickListener() {
          	public void onClick(View clicked) {
          		 member_list_info_menu(familyID,set_id);
          	}
          });
  		//Identity Cards Button Clicked
  		identity_cards_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
         		identity_cards_menu(familyID,set_id);
        	}
        });
  	    //Identity Cards Button Clicked
  		govt_schemes_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
             		govt_schemes_menu(familyID,(int) set_id);
        	}
        });

    }
    /**Calls ViewFamilyInfo Activity **/
  	public void StartViewFamilyInfoMenu(final int familyID,final long set_id)
  	{
  		//Parameters to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", (int) set_id);
    	bundle.putInt("famID", familyID);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), ViewFamilyInfo.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
  	}
    
    /**Calls MemberListInfo Activity  **/
    public void member_list_info_menu(int familyID,final long set_id)
    {
    	//Parameters to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", (int) set_id);
    	bundle.putInt("FamilyId", familyID);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), memberListInfo.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }

   
    /**Calls IdentityCardsMenu Activity  **/
    public void identity_cards_menu(int familyID,long set_id)
    {
    	//Parameters to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("FamilyId", familyID);
    	bundle.putInt("setID",(int) set_id);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), IdentityCardsMenu.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
    
    /**Calls GovtSchemesMenu Activity  **/
    public void govt_schemes_menu(int fam_id,int set_id)
    {
    	//Parameters to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("FamilyId", fam_id);
    	bundle.putInt("setID",(int) set_id);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), GovtSchemesMenu.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
    
    /*Called When Back Button is pressed*/
    @Override
    public void onBackPressed()
    {
    	//Parameters to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", setID);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), CheckNewFamily.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);	
    }
    
    /**Calls SelectProject Activity  **/
    public void startSelectProjectActivity(int set_id)
    {
    	 //Parameters to the activity
	 	 Bundle bundle=new Bundle();
	 	 bundle.putString("coorUserName", coord_username);
	 	 bundle.putInt("setID", (int)set_id);
	 	 Intent newIntent = new Intent(this.getApplicationContext(), SelectProject.class);
	 	 newIntent.putExtras(bundle);
	 	 startActivity(newIntent);
    }
    
    /**Calls nims Activity**/
    public void startLoginActivity()
    {
    	Intent newIntent = new Intent(this.getApplicationContext(), nims.class);
        startActivity(newIntent);
    }
 }
