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
/* SelectProject Activity
 * It handles selection of NGO Project. 
 */


package sen.nims;

import android.net.ConnectivityManager;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import sen.nims.NIMSSQLiteOpenHelper;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SelectProject extends Activity {

   
 	private NIMSSQLiteOpenHelper helper;
    public int set_id=0;
    String coord_username=null;
    boolean server_function_correct;

    /** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		//Accept Parameters
		coord_username = bundle.getString("coorUserName");
		set_id = bundle.getInt("setID");
		select_project(set_id);
	}
    /**Handle Selection of NGO Project*/
    public void select_project(final long set_id)
    {
     	 //Project Information View
    	 requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    	 setContentView(R.layout.ngo_projects);
    	 getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title_logout);
    	 GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
         View title = getWindow().findViewById(R.id.header);
         View titleBar = (View) title.getParent();
         titleBar.setBackgroundDrawable(gd);
         
         TextView coord_username_title=(TextView)findViewById(R.id.Username);
     	 coord_username_title.setText(coord_username);
     	 
     	Button logout_button=(Button)findViewById(R.id.logout);
     	logout_button.setText(Html.fromHtml(getString(R.string.logout)));
     	logout_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		 StartLoginActivity();
        	}
        });
         
         //Detecting Submit Button Click on Projects View 
         Button family_info_button = (Button)findViewById(R.id.family_info);   
         Button social_map_button = (Button)findViewById(R.id.social_map);
         Button item_distribute_button = (Button)findViewById(R.id.item_distribute);
         final Button update_database=(Button)findViewById(R.id.updateDatabase);
         //Family Information Project Button Clicked
         family_info_button.setOnClickListener(new View.OnClickListener() {
         	public void onClick(View clicked) {
         		StartCheckNewFamilyActivity((int)set_id);
         	}
         });
         //Social Map Clicked
         social_map_button.setOnClickListener(new View.OnClickListener() {
         	public void onClick(View clicked) {
         		StartSocialMapActivity();
         	}
         });
         //Social Map Clicked
         item_distribute_button.setOnClickListener(new View.OnClickListener() {
         	public void onClick(View clicked) {
         		StartItenDistributionActivity();
         	}
         });
         //Update Database Clicked
 		 update_database.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		start_update_database_activity(update_database);
        	}
        });
     }
    
    /**Start CheckNewFamily Activity**/
    public void StartCheckNewFamilyActivity(int set_id)
    {
    	//Parameters to calling activity
    	Bundle bundle=new Bundle();
    	bundle.putInt("setID", set_id);
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), CheckNewFamily.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startCampaignActivity();
    }
    
    /**Handle Update Database Button Click **/
    public void start_update_database_activity(final Button update_database)
    {
    	if(isOnline()==false)
   	    {
   	    	Toast toast = Toast.makeText(update_database.getContext(),"Your internet is not connected.First connect the internet and then try again.", Toast.LENGTH_SHORT);
    		toast.show();
   	    }
   	    else
   	    {
	    	showDialog(2);
	    	new Thread(new Runnable(){
	    			public void run(){
	    				Log.e("Thread","Start");
	    				makeUpdateRequest();
	    				Log.e("Thread","End");
	    				if(server_function_correct==true)
	    				{	
	    					helper.clearTable("family_info");
	    					helper.clearTable("member_info");
		    			    helper.clearTable("social_map");
		    			    helper.clearTable("item_distribution");
		    			    helper.clearTable("item_info");
		    			    helper.clearTable("village_info");
		    			    helper.clearTable("settlement_info");
		    			    dismissDialog(2);
		    			    startCampaignActivity();
	    				}
	    				else
	    				{
	    					dismissDialog(2);
	    					Toast toast = Toast.makeText(update_database.getContext(),"There is some problem with server.Update to database fail.", Toast.LENGTH_SHORT);
	    		    		toast.show();
	    				}
	    			}
	    			}).start();
   	    }
    	
    }
    /**For Dialog **/ 
    @Override
    protected Dialog onCreateDialog(int id) {
     if(id == 2){
             ProgressDialog loadingDialog = new ProgressDialog(this);
             loadingDialog.setMessage("Updating and Clearing Database,Please Wait...");
             loadingDialog.setIndeterminate(true);
             loadingDialog.setCancelable(true);
             return loadingDialog;
     }
     return super.onCreateDialog(id);
    }

    /**Send XML String to Server**/
    public String makeUpdateRequest()
    {
    	HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://rural-health.co.cc/test_xml.php");
        String resultStr=null;
        try {
	        	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        	Log.e("XML",generate_xml());
	            nameValuePairs.add(new BasicNameValuePair("file", generate_xml()));
	            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            HttpResponse response = httpclient.execute(httppost);
	            //Reading the response
	            HttpEntity httpEntity=response.getEntity();
	            if (httpEntity != null) {
		            InputStream instream = httpEntity.getContent();
		            BufferedReader reader = new BufferedReader( new InputStreamReader(instream));
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            try {
			                while ((line = reader.readLine()) != null) {
			                    sb.append(line + "\n");
			                }
		            } catch (IOException e) {
		                    e.printStackTrace();
		            } finally {
			                try {
			                    resultStr = sb.toString();
			                    Log.e("Result",resultStr);
			                    instream.close();
			                } catch (IOException e) {
			                        e.printStackTrace();
			                }
		            }
		            server_function_correct=true;   
	        	}
	
            }catch (UnsupportedEncodingException e) {
            	server_function_correct=false;
	            Log.e("TAG", e.toString());
	        } catch (ClientProtocolException e) {
	        	server_function_correct=false;
	            Log.e("TAG", e.toString());
	        } catch (IOException e) {
	        	server_function_correct=false;
	            Log.e("TAG", e.toString());
	        }
	        return resultStr;
    }

    /**Generate XML String from database to be send to Server**/ 
    public String generate_xml()
    {
	    Cursor cursorDB_family=helper.getCursor();
		//XML Build
	    StringBuilder sba = new StringBuilder();
        sba.append("<?xml version="+"\"1.0\""+"?>");
        sba.append("<nims>");
        sba.append("<coordinator_name>"+coord_username+"</coordinator_name>");
        Cursor cursorDB_settle=helper.getSettleCursor();
        cursorDB_settle=helper.getCursorToID(cursorDB_settle, set_id);
        //Cursor cursorDB_village=helper.getVillageCursor("village_info");
        //cursorDB_village=helper.getCursorToID(cursorDB_village, cursorDB_settle.getInt(2));
        sba.append("<village"+" value="+"\""+"A"+"\""+">");
        sba.append("<settlement"+" value="+"\""+cursorDB_settle.getString(1)+"\""+">");
        if(cursorDB_family != null){
        	cursorDB_family.moveToFirst();			//move the cursor to first row
			    if(!cursorDB_family.isAfterLast())		//returns whether cursor is pointing to position after last row
				{
			    	do{
						if(cursorDB_family.getInt(19)==set_id)
						{			
						    sba.append("<family>");
					            sba.append("<headname>"+cursorDB_family.getString(1)+"</headname>");
					            sba.append("<numofmembers>"+cursorDB_family.getString(2)+"</numofmembers>");
					            sba.append("<numofchildren>"+cursorDB_family.getString(3)+"</numofchildren>");
					            sba.append("<lastmigrated>"+cursorDB_family.getString(4)+"</lastmigrated>");
					            sba.append("<tradocc>"+cursorDB_family.getString(5)+"</tradocc>");
					            sba.append("<income>"+cursorDB_family.getString(6)+"</income>");
					            sba.append("<commid>"+cursorDB_family.getString(16)+"</commid>");
					            sba.append("<rationstatus>"+cursorDB_family.getString(9)+"</rationstatus>");
					            sba.append("<rationcat>"+cursorDB_family.getString(10)+"</rationcat>");
					            sba.append("<electricity>"+cursorDB_family.getString(17)+"</electricity>");
					            sba.append("<numofhandicapped>"+cursorDB_family.getString(15)+"</numofhandicapped>");
					            sba.append("<jananistatus>"+cursorDB_family.getString(12)+"</jananistatus>");
					            sba.append("<waterconn>"+cursorDB_family.getString(18)+"</waterconn>");
					            sba.append("<vraddhsch>"+cursorDB_family.getString(13)+"</vraddhsch>");
					            sba.append("<plotcard>"+cursorDB_family.getString(8)+"</plotcard>");
					            sba.append("<numofchildrenschool>"+cursorDB_family.getString(7)+"</numofchildrenschool>");
					            sba.append("<housingsupport>"+cursorDB_family.getString(11)+"</housingsupport>");
					            sba.append("<widowscheme>"+cursorDB_family.getString(14)+"</widowscheme>");
					            	
					            Cursor cursorDB_mem=helper.getMemInfoCursor();
				            	if(cursorDB_mem != null){
				            	    cursorDB_mem.moveToFirst();			//move the cursor to first row
				    	 		    if(!cursorDB_mem.isAfterLast())		//returns whether cursor is pointing to position after last row
				    	 			{
				    	 		      do{
				    	 		    	  if(cursorDB_mem.getInt(2)==cursorDB_family.getInt(0))
									  	  {
				    	 		    		 sba.append("<member>");
				    	 		    		 sba.append("<memname>"+cursorDB_mem.getString(1)+"</memname>");
				    	 		    		 sba.append("<birthyear>"+cursorDB_mem.getString(3)+"</birthyear>");
				    	 		    		 sba.append("<occupation>"+cursorDB_mem.getString(4)+"</occupation>");
                                             sba.append("<relationwithhead>"+cursorDB_mem.getString(5)+"</relationwithhead>");
                                             sba.append("<gender>"+cursorDB_mem.getString(6)+"</gender>");
                                             sba.append("<jobcardstatus>"+cursorDB_mem.getString(7)+"</jobcardstatus>");
                                             sba.append("<voterstatus>"+cursorDB_mem.getString(8)+" </voterstatus>");
                                             sba.append("<aadharstatus>"+cursorDB_mem.getString(9)+"</aadharstatus>");                                             
			    	 		    		     sba.append("</member>");
										  }
				    	 			    
				    	 			 }while (cursorDB_mem.moveToNext());
				    	 			}
				            	}
				            sba.append("</family>");
			    			  
						}
							         							
					 }while (cursorDB_family.moveToNext());	    	
				}
		     }  
        Cursor cursor_socialmap=helper.getPlaceCursor();
        if(cursor_socialmap != null){
        	cursor_socialmap.moveToFirst();			//move the cursor to first row
			    if(!cursor_socialmap.isAfterLast())		//returns whether cursor is pointing to position after last row
				{
			    	do{
			    		 sba.append("<place>");
			    		 sba.append("<place_type>"+cursor_socialmap.getString(2)+"</place_type>");
			    		 sba.append("<name>"+cursor_socialmap.getString(3)+"</name>");
			    		 sba.append("<latitude>"+cursor_socialmap.getString(0)+"</latitude>");
			    		 sba.append("<longitude>"+cursor_socialmap.getString(1)+"</longitude>");
			    		 sba.append("</place>");
				 }while (cursor_socialmap.moveToNext());	    	
			}
	     } 
        
        Cursor cursor_item_info=helper.getItemInfoCursor();
        if(cursor_item_info != null){
        	cursor_item_info.moveToFirst();			//move the cursor to first row
			    if(!cursor_item_info.isAfterLast())		//returns whether cursor is pointing to position after last row
				{
			    	do{
			    	    	sba.append("<item_distribution>");
			    		    sba.append("<item_name>");
			                Cursor cursorDB_item_distribute=helper.getItemDistributeCursor();
			                sba.append(cursor_item_info.getString(1).toString());
			                sba.append("</item_name>");
			                if(cursorDB_item_distribute != null){
				            		cursorDB_item_distribute.moveToFirst();			//move the cursor to first row
				    	 		    if(!cursorDB_item_distribute.isAfterLast())		//returns whether cursor is pointing to position after last row
				    	 			{
				    	 		      do{
				    	 		    	  if(cursorDB_item_distribute.getInt(2)==cursor_item_info.getInt(0))
									  	  {
				    	 		    		 Cursor cursor_family=helper.getCursor();
				    	 		    		 Log.e("FamilyID",Integer.toString(cursorDB_item_distribute.getInt(2)));
				    	 		    		 cursor_family=helper.getCursorToID(cursor_family,cursorDB_item_distribute.getInt(0));
				    	 		    		
				    	 		    		 sba.append("<family>");
				    	 		    		 sba.append("<headname>"+cursor_family.getString(1)+"</headname>");
				    	 		    		 sba.append("<no_items>"+cursorDB_item_distribute.getInt(3)+"</no_items>");
			    	 		    		     sba.append("</family>");
										  }
				    	 			    
				    	 			 }while (cursorDB_item_distribute.moveToNext());
				    	 			}
				            	}
			                sba.append("</item_distribution>");		               
				     }while (cursor_item_info.moveToNext());	    	
			    }
	     }  
        
        sba.append("</settlement></village></nims>");
        return sba.toString();
    }
    
    /**Start CampaignInfo Activity**/
    public void startCampaignActivity()
    {	
    	Bundle bundle=new Bundle();
    	bundle.putString("coorUserName", coord_username);
    	Intent newIntent = new Intent(this.getApplicationContext(), CampaignInfo.class);
    	newIntent.putExtras(bundle);
		startActivity(newIntent);
    
    }
    
    /**Start SocialMap Activity**/
    public void StartSocialMapActivity()
    {
    	Bundle bundle=new Bundle();
	 	bundle.putString("coorUserName", coord_username);
	 	bundle.putInt("setID", (int)set_id);
    	Intent newIntent = new Intent(this.getApplicationContext(), SocialMap.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }

    /**Start ItemDistributionName Activity**/
    public void StartItenDistributionActivity()
    {
    	Bundle bundle=new Bundle();
	 	bundle.putString("coorUserName", coord_username);
	 	bundle.putInt("setID", (int)set_id);
    	Intent newIntent = new Intent(this.getApplicationContext(), ItemDistributionName.class);
    	newIntent.putExtras(bundle);
    	startActivity(newIntent);
    }
    

    public void StartLoginActivity() {
        Intent newIntent = new Intent(this.getApplicationContext(), nims.class);
        startActivity(newIntent);
    }
    
    public boolean isOnline() {
        ConnectivityManager conMgr =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&    conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
      } else {
            System.out.println("Internet Connection Not Present");
          return false;
      }
    }
}