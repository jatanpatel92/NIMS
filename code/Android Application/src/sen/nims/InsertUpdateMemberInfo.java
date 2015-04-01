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



/* InsertUpdateMemberInfo Activity
 * Insert/Update Member Info
 */

package sen.nims;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;


public class InsertUpdateMemberInfo extends Activity {
	
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
		int mem_id = bundle.getInt("memID");
		set_id = bundle.getInt("setID");
        fam_id=bundle.getInt("famID");
        coord_username = bundle.getString("coorUserName");
        task=bundle.getString("Task");
        insert_update_member_form(fam_id,task,mem_id);
		
       
    }
    static final String[] Relations = new String[] {
    	  "Self", "Wife", "Son", "Sister", "Father",
    	  "DAUGHTERINLAW", "Brother", "Mother", "GrandSon", "GrandDaughter",
    	  "Sisterinlaw",
   	};
    
    static final String[] Occupation = new String[] {
  	  "STUDENT", "Cobbler", "Carpenter", "Tailor", "SMITH",
  	  "Carpenter", "FARMER", "WOODCUTTER", "RETIRED", "HOUSEWIFE",
  	  "Shopkeeper",
 	};
    /**New/Update Family Member Form Handle**/
    public void insert_update_member_form(final int familyID,final String task,final int mem_id)
    {
    	//New Member Information Handling
        setContentView(R.layout.new_member);
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
        
  	    final EditText member_name = (EditText)findViewById(R.id.member_name_entry);
  	    member_name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
	    final AutoCompleteTextView relation_with_head= (AutoCompleteTextView)findViewById(R.id.relation_with_head_entry);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_dropdown_item_1line,Relations);
		relation_with_head.setThreshold(0);
		relation_with_head.setAdapter(adapter);
	    final EditText birth_year = (EditText)findViewById(R.id.birth_year_entry);
	    final AutoCompleteTextView occupation= (AutoCompleteTextView)findViewById(R.id.occupation_entry);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getApplicationContext(), android.R.layout.simple_dropdown_item_1line,Occupation);
		occupation.setThreshold(0);
		occupation.setAdapter(adapter1);
	    final RadioGroup gender_type=(RadioGroup)findViewById(R.id.gender_type);
	    final RadioButton male_type=(RadioButton)findViewById(R.id.male_type);
	    final RadioButton female_type=(RadioButton)findViewById(R.id.female_type);
        final Button submit=(Button)findViewById(R.id.submit);
        
        //Set Filter to Strings to take only character and space as input
        member_name.setFilters(new InputFilter[]{helper.nameInputFilter});
        occupation.setFilters(new InputFilter[]{helper.nameInputFilter});
        
        if(task.equals("update"))
		   {
   		    Cursor cursorDB_member=helper.getCursorToID(helper.getMemInfoCursor(),mem_id);
   		    member_name.setText(cursorDB_member.getString(1));
	        relation_with_head.setText(cursorDB_member.getString(5));
	        birth_year.setText(Integer.toString(cursorDB_member.getInt(3)));
	        occupation.setText(cursorDB_member.getString(4));
	        if(cursorDB_member.getString(6).equals("M"))
	        	male_type.setChecked(true);
	        else
	        	female_type.setChecked(true);
	        cursorDB_member.close();
		   }
        
        //Submit Button Clicked
        submit.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View clicked) {
	        	final int gender_id=gender_type.getCheckedRadioButtonId();
	        	
	        	//Check for emptiness of EditText
                if(member_name.length()==0)
                {
             	   Toast toast = Toast.makeText(member_name.getContext(),"Enter Member Name", Toast.LENGTH_SHORT);
            	   toast.show();
                }
                else if (relation_with_head.length()==0)
                {
             	   Toast toast = Toast.makeText(member_name.getContext(),"Enter Relationship with Head", Toast.LENGTH_SHORT);
            		   toast.show();
                }
                else if (birth_year.length()==0)
                {
             	   Toast toast = Toast.makeText(member_name.getContext(),"Enter Birth Year", Toast.LENGTH_SHORT);
            		   toast.show();
                }
                else if (occupation.length()==0)
                {
             	     Toast toast = Toast.makeText(occupation.getContext(),"Enter Occupation", Toast.LENGTH_SHORT);
            		 toast.show();
                }
                else if(gender_id==-1)
                {
                	Toast toast = Toast.makeText(occupation.getContext(),"Select Gender", Toast.LENGTH_SHORT);
         		    toast.show();
                }
                else
                {
		        	
		        	String gender_type_input_1=new String();
		        	switch(gender_id)
	        		{
	        		   case R.id.male_type:
	        			   gender_type_input_1="M";
	        			   break;
	        		   case R.id.female_type:
	        			   gender_type_input_1="F";
	        			   break;
	           		   default:
	        			   break;
	        		}
		        	
		      	      if(task.equals("update"))
		      	    	  helper.updateMemDatabase(member_name.getText().toString(),relation_with_head.getText().toString(),occupation.getText().toString(),gender_type_input_1,Integer.parseInt(birth_year.getText().toString()),mem_id,"basic_info","null");
		      	      else
		      	    	  helper.addNewMemberDB(member_name.getText().toString(),relation_with_head.getText().toString(),occupation.getText().toString(),gender_type_input_1,Integer.parseInt(birth_year.getText().toString()),familyID);
		      	 	        	
		        	//Call Current Activity Again
		      	    StartMemberListInfoMenu();
                }
	         }
        });
    }
   
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StartMemberListInfoMenu();
    }
    
    public void StartMemberListInfoMenu()
    {
    	//FamilyID as a parameter to the activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("FamilyId", fam_id);
    	bundle.putInt("setID", set_id);
    	bundle.putString("coorUserName", coord_username);
    	//MemberList is the class to display member's list
    	Intent newIntent = new Intent(this.getApplicationContext(), memberListInfo.class);
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