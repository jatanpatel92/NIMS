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
/* GovtSchemesStatus Activity
 * It handles selection of government scheme status.
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


public class GovtSchemesStatus extends Activity {
	
	private NIMSSQLiteOpenHelper helper;
	
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
		scheme_name=bundle.getString("schemeName");
		govt_scheme_status();
       
    }    

    /**Handle input of Govt_schemes_status**/ 
    private void govt_scheme_status()
    {
    	setContentView(R.layout.govt_schemes_status);
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
    	final Cursor cursorDB=helper.getCursorToID(helper.getCursor(),familyID);
	    final RadioGroup scheme_status=(RadioGroup)findViewById(R.id.scheme_status);
	    String status_input=new String();
	    if(scheme_name.equals("fam_housing_support"))
	    	status_input=cursorDB.getString(11);
	    else if(scheme_name.equals("fam_janani_support_status"))
	    	status_input=cursorDB.getString(12);
	    else if(scheme_name.equals("fam_vraddh_pen_scheme"))
	         status_input=cursorDB.getString(13);
	    else
	    	 status_input=cursorDB.getString(14);
	    	     
	    if(status_input!=null)
		{
			if(status_input.equals("APPLIED_NOT_ISSUED"))
				scheme_status.check(R.id.applied);
			else if(status_input.equals("ISSUED"))
				scheme_status.check(R.id.issued);
			else
				scheme_status.check(R.id.not_applicable);
		}
		
		final Button submit=(Button)findViewById(R.id.submit);
		submit.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		String govt_scheme_status=new String();
        		int scheme_id=scheme_status.getCheckedRadioButtonId();
        		if(scheme_id==-1)
                 {
                 	Toast toast = Toast.makeText(submit.getContext(),"Select Government Scheme", Toast.LENGTH_SHORT);
          		    toast.show();
                 }
                 else
                 {
	        		switch(scheme_id)
	        		{
	        		   case R.id.applied:
	        			   govt_scheme_status="APPLIED_NOT_ISSUED";
	        			   break;
	        		   case R.id.issued:
	        			   govt_scheme_status="ISSUED";
	        			   break;
	        		   case R.id.not_applicable:
	        			   govt_scheme_status="NOT_APPLICABLE";
	        			   break;
	        		   default:
	        			   //Invalidation message
	        			   break;
	        		}
	        		helper.updateFamilySchemesDatabase("family_info",govt_scheme_status,familyID,scheme_name); 
	        		StartGovtSchemeMenuActivty();
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
 		StartGovtSchemeMenuActivty();	
     }
     public void StartGovtSchemeMenuActivty()
    {
    	 /**Calling Another Activity**/
    	 //FamilyID as a parameter to the activity
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",familyID);
	  	 bundle.putInt("setID", setID);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), GovtSchemesMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);	
    }
    
    
}