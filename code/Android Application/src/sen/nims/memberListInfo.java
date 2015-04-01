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



/* MemberListInfo Activity
 * It handles listing of member's name in list. 
 */


package sen.nims;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sen.nims.NIMSSQLiteOpenHelper;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class memberListInfo extends ListActivity {


	private NIMSSQLiteOpenHelper helper;
	public int familyID=0;
	public int set_id=0;
	String coord_username=null;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		//FamilyID as a parameter to the activity
	    familyID = bundle.getInt("FamilyId");
		set_id=bundle.getInt("setID");
		coord_username = bundle.getString("coorUserName");
		//ListView
		final ListView lv = getListView();
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
        
		TextView member_name_title=new TextView(this);
		member_name_title.setText("Member Name");
        lv.addHeaderView(member_name_title);
        //New Member Info
        Button new_member=new Button(this);
        new_member.setText("New Member");
        lv.addFooterView(new_member);
		// Create an ArrayAdapter of RowMemberLsting objects
		final ArrayAdapter<RowMemberListingStatus> adapter = new ArrayAdapterMemberListInfo(this,getRowMemberListingStatus(familyID));
		lv.setAdapter(adapter);
		//submit button listener
		new_member.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		StartInsertUpdateMemberInfoActivity(familyID,"new",0);
        	}
		 });
	}

	//Return list of RowMemberListingStatus objects
	private List<RowMemberListingStatus> getRowMemberListingStatus(int familyID) {
		Cursor cursorDB =helper.getMemInfoCursor();
		List<RowMemberListingStatus> rows_list = new ArrayList<RowMemberListingStatus>();
		ArrayList<String> list_members=new ArrayList<String>();
		if(cursorDB != null){
				cursorDB.moveToFirst();			//move the cursor to first row
			    if(! cursorDB.isAfterLast())		//returns whether cursor is pointing to position after last row
				{
			    	do{
			    		if(cursorDB.getInt(2)==familyID)
			    		{
			    			list_members.add(cursorDB.getString(1));
			    		}
			    			
			    	 }while (cursorDB.moveToNext());
				}
		}
		Collections.sort(list_members);
		for(String str:list_members)
			rows_list.add(get(str));
		cursorDB.close(); 
		return rows_list;
	}

    //Return RowMemberListingStatus Object containing string s
	private RowMemberListingStatus get(String s) {
		return new RowMemberListingStatus(s);
	}
	
	
    /************Array Adaper Class**************/
	public class ArrayAdapterMemberListInfo extends ArrayAdapter<RowMemberListingStatus> {

		private final List<RowMemberListingStatus> list;
		private final Activity context;

		//Constructor
		public ArrayAdapterMemberListInfo(Activity context, List<RowMemberListingStatus> list) {
			super(context, R.layout.member_list_row_info, list);
			this.context = context;
			this.list = list;
		}

		//ViewHolder Class
		class ViewHolder {
			protected TextView text;
			protected  Button view_info;
		}

		//Set the view
		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.member_list_row_info, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.text = (TextView) view.findViewById(R.id.label);
				viewHolder.view_info = (Button) view.findViewById(R.id.view_info);
				viewHolder.view_info.setOnClickListener(new OnClickListener() {
			          @Override
			          public void onClick(View v) {
			        	  StartMemberInfoView(viewHolder.text.getText());
			          }
			        });
				view.setTag(viewHolder);

			} else {
				view = convertView;
			}
			ViewHolder holder = (ViewHolder) view.getTag();
			holder.text.setText(list.get(position).getName());
			return view;
		}
		
    }

	
    
	public class RowMemberListingStatus {

		private String name;
		private boolean apply_selected;
		private boolean issued_selected;

		public RowMemberListingStatus(String name) {
			this.name = name;
			apply_selected = false;
			issued_selected = false;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isApplySelected() {
			return apply_selected;
		}

		public void setApplySelected(boolean selected) {
			this.apply_selected = selected;
		}

		public boolean isIssuedSelected() {
			return issued_selected;
		}

		public void setIssuedSelected(boolean selected) {
			this.issued_selected = selected;
		}

	}
	

	public void StartInsertUpdateMemberInfoActivity(int fam_id,String task,int mem_id)
	    {
	    	//Parameters to the activity
	    	Bundle bundle=new Bundle();
	    	bundle.putInt("memID",mem_id);
	    	bundle.putString("coorUserName", coord_username);
	    	bundle.putInt("famID", fam_id);
	    	bundle.putInt("setID", set_id);
	    	bundle.putString("Task",task);
	    	Intent newIntent = new Intent(this.getApplicationContext(), InsertUpdateMemberInfo.class);
	    	newIntent.putExtras(bundle);
	    	startActivity(newIntent);
	    }
	//View Member Info
	public void StartMemberInfoView(CharSequence member_name)
	{
		Bundle bundle=new Bundle();
		bundle.putString("coorUserName", coord_username);
    	bundle.putString("memName",(String) member_name);
    	bundle.putInt("setID", set_id);
    	bundle.putInt("FamilyId",familyID);
    	Intent newIntent = new Intent(this.getApplicationContext(), ViewMemberInfo.class);
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
	  	 bundle.putInt("setID", set_id);
	  	 bundle.putString("coorUserName", coord_username);
	  	 Intent newIntent = new Intent(this.getApplicationContext(), FamilyMenu.class);
	  	 newIntent.putExtras(bundle);
	  	 startActivity(newIntent);		
     }
 	 
 	public void startSelectProjectActivity()
    {
	     /**Calling Another Activity**/
		 //setID as a parameter to the activity
	 	 Bundle bundle=new Bundle();
	 	 bundle.putString("coorUserName", coord_username);
	 	 bundle.putInt("setID", set_id);
	 	 Intent newIntent = new Intent(this.getApplicationContext(), SelectProject.class);
	 	 newIntent.putExtras(bundle);
	 	 startActivity(newIntent);
    }


}