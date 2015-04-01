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
/* IdentityCardsMenu Activity
 * It handles selection of an identity card from menu. 
 */

package sen.nims;
import sen.nims.memberListStatus;
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

public class IdentityCardsMenu extends Activity {
	NIMSSQLiteOpenHelper helper;
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
    	setContentView(R.layout.identity_cards_project);
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
    	final Button voter_card=(Button)findViewById(R.id.voter_card);
  		final Button ration_card=(Button)findViewById(R.id.ration_card);
  		final Button plot_card=(Button)findViewById(R.id.plot_card);
  		final Button job_card=(Button)findViewById(R.id.job_card);
  		final Button adahar_card=(Button)findViewById(R.id.adahar_card);
  		//Voter Card
  		voter_card.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
              		members_list_cards(familyID,"Voter");
                 }
             });
  		
  		//Ration Card
  		ration_card.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
              		StartRationCardMenuActivity();		
              }
         });
  	    //Plot Card
  		plot_card.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		   StartPlotCardTypeMenuActivity();
                 }
             });
  		job_card.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        			members_list_cards(familyID,"Job");
             }
         });
  		adahar_card.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        			members_list_cards(familyID,"Adahar");
             }
         });
     
  		
    }
    //Members Listing For Voter Card Apply
    public void members_list_cards(final int familyID,String card_type)
    {
    	//FamilyID as a parameter to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("FamilyId", familyID);
    	bundle.putString("Card Type", card_type);
    	bundle.putInt("setID", setID);
	  	bundle.putString("coorUserName", coord_username);
    	//MemberList is the class to display member's list
    	Intent newIntent = new Intent(this.getApplicationContext(), memberListStatus.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    	
    		
    }
   
    public void StartRationCardMenuActivity()
    {
    	 /**Calling Another Activity**/
    	 //FamilyID as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID", setID);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), RationCardMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);	
    }
    
    public void StartPlotCardTypeMenuActivity()
    {
    	 /**Calling Another Activity**/
    	 //FamilyID as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID", setID);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(),PlotCardTypeMenu.class);
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
	  	 Intent newIntent = new Intent(this.getApplicationContext(), FamilyMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);		
     }
}