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


/* MemberListStatus Activity
 * It handles Listing of Member's name and his/her corresponding status. 
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class memberListStatus extends ListActivity {

/** Called when the activity is first created. */
	private NIMSSQLiteOpenHelper helper;
	public int setID=0;
	int familyID;
	String coord_username=null;
	
	public void onCreate(Bundle icicle) {
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		//FamilyID as a parameter to the activity
		setID = bundle.getInt("setID");
		familyID=bundle.getInt("FamilyId");
		coord_username = bundle.getString("coorUserName");
		String card_type=bundle.getString("Card Type");
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
		//Add header to list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.member_list_status_header, lv, false);
        lv.addHeaderView(header);
        //Add Footer to the list
        Button submit=new Button(this);
        submit.setText("Submit");
        lv.addFooterView(submit);
		// Create an ArrayAdapter of RowMemberLsting objects
		final ArrayAdapter<RowMemberListingStatus> adapter = new ArrayAdapterMemberListStatus(this,getRowMemberListingStatus(familyID),card_type);
		lv.setAdapter(adapter);
		//submit button listener
		submit.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		StartIdentityCardMenuActivity();
         	}
		 });
	}

	/**Return list of RowMemberListingStatus objects**/
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

    /**Return RowMemberListingStatus Object containing string s**/
	private RowMemberListingStatus get(String s) {
		return new RowMemberListingStatus(s);
	}

	/***************Array Adapter Member List Status Class**********/
	public class ArrayAdapterMemberListStatus extends ArrayAdapter<RowMemberListingStatus> {

		private final List<RowMemberListingStatus> list;
		private NIMSSQLiteOpenHelper helper;
		private final Activity context;
	    private int card_type_index=0;
	    private String card_type=null;
		//Constructor
		public ArrayAdapterMemberListStatus(Activity context, List<RowMemberListingStatus> list,String card_type) {
			super(context, R.layout.member_list_row_status, list);
			if(card_type.equals("Voter"))
			   card_type_index=8;
			else if(card_type.equals("Job"))
				card_type_index=7;
			else
				card_type_index=9;
			this.card_type=card_type;
			this.context = context;
			helper = new NIMSSQLiteOpenHelper(this.context);
			this.list = list;
		}

		//ViewHolder Class
		class ViewHolder {
			protected TextView text;
			protected  RadioButton apply;
			protected  RadioButton issued;
		}

		//Set the view
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				LayoutInflater inflator = context.getLayoutInflater();
				view = inflator.inflate(R.layout.member_list_row_status, null);
				final ViewHolder viewHolder = new ViewHolder();
				viewHolder.text = (TextView) view.findViewById(R.id.label);
				viewHolder.apply = (RadioButton) view.findViewById(R.id.apply);
				viewHolder.issued = (RadioButton) view.findViewById(R.id.issued);
			    RadioGroup scheme_status=(RadioGroup)view.findViewById(R.id.types);
			    
			    scheme_status.setOnCheckedChangeListener (new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						Cursor cursorDB_member=helper.getMemInfoCursor();
						cursorDB_member=helper.getCursorToName(cursorDB_member,viewHolder.text.getText().toString());
						if (checkedId==R.id.apply)			
							helper.updateMemDatabase("null","null","null","null",0,cursorDB_member.getInt(0),card_type,"APPLIED_NOT_ISSUED");
						else
							helper.updateMemDatabase("null","null","null","null",0,cursorDB_member.getInt(0),card_type,"ISSUED");
						
					}
			    });       
				view.setTag(viewHolder);

			} else {
				view = convertView;
			}
			ViewHolder holder = (ViewHolder) view.getTag();
			holder.text.setText(list.get(position).getName());
			holder.apply.setChecked(list.get(position).isApplySelected());
			holder.issued.setChecked(list.get(position).isIssuedSelected());
			Cursor cursorDB_member=helper.getMemInfoCursor();
			cursorDB_member=helper.getCursorToName(cursorDB_member,list.get(position).getName());
			
			if(cursorDB_member!=null)
			{
				if(!(cursorDB_member.getString(card_type_index).equals("NOT_APPLIED")))
				{
    				  if(cursorDB_member.getString(card_type_index).equals("APPLIED_NOT_ISSUED"))
						 holder.apply.setChecked(true);
			          else
			        	 holder.issued.setChecked(true);
				}
				cursorDB_member.close();
			}
			else
				Log.e("Member Cursor","NULL"); 
			return view;
		}
			
	}

	/**End of ArrayAdapterMemberList Class***/
	
	
	/**RowMemberListingStatus Class**/
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
	/**End of RowMemberListingStatus Class**/
	
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

	
}