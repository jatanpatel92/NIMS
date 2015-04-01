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
/* GovtSchemesMenu Activity
 * Displays Menu of Govt Schemes
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


public class GovtSchemesMenu extends Activity {
	
    NIMSSQLiteOpenHelper helper;
	
	public int setID=0;
	public int familyID=0; 
	String coord_username=null;
	String scheme_name=null;
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign);
        //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        Bundle bundle = this.getIntent().getExtras();
		//setID as a parameter to the activity
        coord_username = bundle.getString("coorUserName");
		setID = bundle.getInt("setID");
		familyID=bundle.getInt("FamilyId");
		govt_schemes_menu(familyID,setID);
       
    }    
    /**Govt Schemes Menu Handle**/
    private void govt_schemes_menu(final int familyID,final long set_id)
    {
    	setContentView(R.layout.govt_schemes);
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
    	final Button housing_support=(Button)findViewById(R.id.hosing_support);
  		final Button janni_support=(Button)findViewById(R.id.janni_support);
  		final Button virudh_pension=(Button)findViewById(R.id.virudh_pension);
  		final Button window_pension=(Button)findViewById(R.id.window_pension);
  		//Housing Support Scheme Clicked
  		housing_support.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		 scheme_name="fam_housing_support";
        		 StartGovtSchemesStatus();
                 }
             });
     
  	    //Janni Support Scheme Clicked
  		janni_support.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		  scheme_name="fam_janani_support_status";
        		  StartGovtSchemesStatus();
                 }
             });
  	    //Virudh Pension Scheme Clicked
  		virudh_pension.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		  scheme_name="fam_vraddh_pen_scheme";
        		  StartGovtSchemesStatus();
                 }
             });
  	    //Window Pension Scheme Clicked     
  		window_pension.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		 scheme_name="fam_widow_pension_scheme";
        		 StartGovtSchemesStatus();
                 }
             });
    }
    public void StartGovtSchemesStatus()
    {
    	 /**Calling Another Activity**/
   	     //FamilyID as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID", setID);
	  	 bundle.putString("coorUserName", coord_username);
	  	 bundle.putString("schemeName", scheme_name);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), GovtSchemesStatus.class);
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
 		StartFamilyMenuActivty((int) setID,familyID);	
     }
    public void StartFamilyMenuActivty(int set_id,int fam_id)
    {
    	 /**Calling Another Activity**/
    	 //FamilyID as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",fam_id);
	  	 bundle.putInt("setID", (int)set_id);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), FamilyMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);	
    }
    
    
    
}