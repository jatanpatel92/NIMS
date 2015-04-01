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

/**InsertUpdateFamilyInfo Activity.
 * Inserts/Updates Family Info**/

package sen.nims;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class InsertUpdateFamilyInfo extends Activity {
	private NIMSSQLiteOpenHelper helper;
	String coord_username=null;
	
	private int set_id=0;
	private int fam_id=0;
	private String task=null;
	
    /****** Called when the activity is first created. *******/
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign);
        //SQLite Database Object
        helper = new NIMSSQLiteOpenHelper(this);
        Bundle bundle = this.getIntent().getExtras();
		//setID as a parameter to the activity
		set_id = bundle.getInt("setID");
        fam_id=bundle.getInt("famID");
        task=bundle.getString("Task");
        coord_username = bundle.getString("coorUserName");
		insert_update_family_info(set_id,fam_id,task);
  
    }
   
    //Save the family Info into database
    public void insert_update_family_info(final long set_id,final int fam_id,final String task)
	{
    	   //Family Information Menu
    	   setContentView(R.layout.new_family);
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

    	   Display display = getWindowManager().getDefaultDisplay();
    	   int w=display.getWidth();
           TextView family_head_name_view=(TextView)findViewById(R.id.family_head_name);
           family_head_name_view.setPadding(w/8, 0, 0,0);
           TextView no_of_members_view=(TextView)findViewById(R.id.no_of_members);
           no_of_members_view.setPadding(w/8, 0, 0,0);
           TextView family_community_name_view=(TextView)findViewById(R.id.family_community_name);
           family_community_name_view.setPadding(w/8, 0, 0,0);
           TextView fam_no_of_children_view=(TextView)findViewById(R.id.fam_no_of_children);
           fam_no_of_children_view.setPadding(w/8, 0, 0,0);
           TextView fam_last_migrated_from_view=(TextView)findViewById(R.id.fam_last_migrated_from);
           fam_last_migrated_from_view.setPadding(w/8, 0, 0,0);
           TextView fam_traditional_occupation_view=(TextView)findViewById(R.id.fam_traditional_occupation);
           fam_traditional_occupation_view.setPadding(w/8, 0, 0,0);
           TextView fam_daily_income_view=(TextView)findViewById(R.id.fam_daily_income);
           fam_daily_income_view.setPadding(w/8, 0, 0,0);
           TextView fam_no_of_children_school_view=(TextView)findViewById(R.id.fam_no_of_children_school);
           fam_no_of_children_school_view.setPadding(w/8, 0, 0,0);
           TextView fam_no_handicapped_view=(TextView)findViewById(R.id.fam_no_handicapped);
           fam_no_handicapped_view.setPadding(w/8, 0, 0,0);
           TextView electricity_access_view=(TextView)findViewById(R.id.electricity_access);
           electricity_access_view.setPadding(w/8, 0, 0,0);
           TextView water_connection_view=(TextView)findViewById(R.id.water_connection);
           water_connection_view.setPadding(w/8, 0, 0,0);

           
       	   //Initializing Views of XML
    	   final EditText family_head_name = (EditText)findViewById(R.id.family_head_name_entry);
    	   family_head_name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    	   final EditText no_of_members = (EditText)findViewById(R.id.no_of_members_entry);
    	   final AutoCompleteTextView community_name = (AutoCompleteTextView) findViewById(R.id.family_community_entry);
    	   ArrayList<String> allCommunitiesName = helper.getAllCommunitiesName();
		   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_dropdown_item_1line, allCommunitiesName);
    	   community_name.setThreshold(0);
		   community_name.setAdapter(adapter);
    	   
    	   final EditText fam_no_of_children= (EditText)findViewById(R.id.fam_no_of_children_entry);
    	   final EditText fam_last_migrated_from = (EditText)findViewById(R.id.fam_last_migrated_from_entry);
    	   fam_last_migrated_from.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    	   final EditText fam_traditional_occupation = (EditText)findViewById(R.id.fam_traditional_occupation_entry);
    	   fam_traditional_occupation.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    	   final EditText fam_daily_income = (EditText)findViewById(R.id.fam_daily_income_entry);
    	   final EditText fam_no_of_children_school = (EditText)findViewById(R.id.fam_no_of_children_school_entry);
    	   final EditText fam_no_handicapped_entry = (EditText)findViewById(R.id.fam_no_handicapped_entry);
    	   final CheckBox fam_electricity_access= (CheckBox)findViewById(R.id.electricity_access_entry);
    	   final CheckBox fam_water_connection = (CheckBox)findViewById(R.id.water_connection_entry);  	     	   
      	   final Button submit=(Button)findViewById(R.id.submit); 
      	   
      	   //Set Filter to Strings to take only character and space as input
      	   family_head_name.setFilters(new InputFilter[]{helper.nameInputFilter});
      	   fam_last_migrated_from.setFilters(new InputFilter[]{helper.nameInputFilter});
      	   community_name.setFilters(new InputFilter[]{helper.nameInputFilter});
      	   fam_traditional_occupation.setFilters(new InputFilter[]{helper.nameInputFilter});
     	   
      	   if(task.equals("update"))
      			   {
		      		Cursor cursorDB=helper.getCursorToID(helper.getCursor(),fam_id);
		 	  	    family_head_name.setText(cursorDB.getString(1));
		 		    no_of_members.setText(Integer.toString(cursorDB.getInt(2)));
		 		    Cursor cursorDB_com=helper.getCursorToID(helper.getCommunityCursor(),cursorDB.getInt(16));
		 		    community_name.setText(cursorDB_com.getString(1));
		 		    fam_no_of_children.setText(cursorDB.getString(3));
		 		    fam_last_migrated_from.setText(cursorDB.getString(4));
		 		    fam_traditional_occupation.setText(cursorDB.getString(5));
		 		    fam_daily_income.setText(Integer.toString(cursorDB.getInt(6)));
		 		    fam_no_of_children_school.setText(Integer.toString(cursorDB.getInt(7)));
		 		    fam_no_handicapped_entry.setText(Integer.toString(cursorDB.getInt(15)));
		 		    if(cursorDB.getInt(17)==1)
		 		    	fam_electricity_access.setChecked(true);
		 		    if(cursorDB.getInt(18)==1)
		 		    	fam_water_connection.setChecked(true);
		 		    cursorDB.close();      	     	   
      			   }
      	   
      	   //On Submitting the family information by coordinator
      	   submit.setOnClickListener(new View.OnClickListener() {
           public void onClick(View clicked) {
        	   	   //Check for emptiness of EditText
                   if(family_head_name.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Family Head Name", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (no_of_members.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Number of Members", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (community_name.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Community Name", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (fam_no_of_children.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Number of Children", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (fam_last_migrated_from.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Last Migrated From Place", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (fam_traditional_occupation.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Traditionl Occupation", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (fam_daily_income.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Daily Income", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (fam_no_of_children_school.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Number of Children Going School", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else if (fam_no_handicapped_entry.length()==0)
                   {
                	   Toast toast = Toast.makeText(family_head_name.getContext(),"Enter Number of Children Going School", Toast.LENGTH_SHORT);
               		   toast.show();
                   }
                   else
                   {
                	   /*Converting Boolean to Integer*/
                	   int elec_status=0;
                	   if(fam_electricity_access.isChecked())
                		    elec_status=1;
                	   else
                		   elec_status=0;
                	   int water_connec=0;
                	   if(fam_water_connection.isChecked())
                		   water_connec=1;
               	       else
               	    	   water_connec=0;
                	   
                	   /*Checking Community Name in the database*/
                	   Cursor cursorDB_community =helper.getCommunityCursor();
                	   cursorDB_community=helper.getCursorToName(cursorDB_community,community_name.getText ().toString ());
	       		   	   int com_id=0;
	       		   	   if(cursorDB_community==null)
	       		   	   {
	       		   	      Toast toast = Toast.makeText(family_head_name.getContext(),"Entered Comunity Name is not correct", Toast.LENGTH_SHORT);
             		      toast.show();
	       		   	   }
	       		   	    else
	       		   	    {	
	       		   	      com_id=cursorDB_community.getInt(0);
                      	  //Populating Inputed Family Information into Database
		        	      family_info_structure family_info_struct=new family_info_structure();
		        	      family_info_struct.setFamilyHeadName(family_head_name.getText().toString());
		        	      family_info_struct.setFamilyNoOfMembers(Integer.parseInt (no_of_members.getText ().toString ()));
		        	      family_info_struct.setFamilyDailyIncome(Integer.parseInt (fam_daily_income.getText ().toString ()));
		        	      family_info_struct.setFamilyNoOfChildren(Integer.parseInt (fam_no_of_children.getText ().toString ()));
		        	      family_info_struct.setFamilyLastMigratedFrom(fam_last_migrated_from.getText().toString());
		        	      family_info_struct.setFamilyTraditionalOccupation(fam_traditional_occupation.getText().toString());
		        	      family_info_struct.setFamilyNoOfChildrenSchool(Integer.parseInt (fam_no_of_children_school.getText().toString ()));
		        	      family_info_struct.setFamilyNumberOfHandicapped(Integer.parseInt (fam_no_handicapped_entry.getText ().toString ()));
		        	      family_info_struct.setFamilyElectricityStatus(elec_status);
		        	      family_info_struct.setFamilyWaterConnection(water_connec);
		        	      family_info_struct.setCommId(com_id);
	
		        	      int family_id=fam_id;
		  	        	  if(task.equals("update"))
		               	    	  helper.updateDatabase("family_info",family_info_struct,fam_id,"basic_info");  
		            	  else
		        	    	      family_id=helper.addFamilyInfoDB(family_info_struct,set_id);
	 
		        	      StartFamilyMenuActivty((int)set_id,family_id);
	       		   	    }
    	      
                   }
                }
            });
	}
 
    public void StartFamilyMenuActivty(int set_id,int fam_id)
    {
	  	 Bundle bundle=new Bundle();
	  	 bundle.putInt("FamilyId",fam_id);
	  	 bundle.putInt("setID", (int)set_id);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), FamilyMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);	
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", set_id);
    	bundle.putInt("famID",fam_id);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent;
    	if(task.equals("update"))
    		newIntent = new Intent(this.getApplicationContext(), ViewFamilyInfo.class);
    	else
    	    newIntent = new Intent(this.getApplicationContext(), CheckNewFamily.class);
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
    
}