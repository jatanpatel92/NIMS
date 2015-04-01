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


/*Campaign Module
 * It handles Campaign Information Input Task . 
 */

package sen.nims;

import android.net.ConnectivityManager;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CampaignInfo extends Activity {

	/** Global Variables. */
	private NIMSSQLiteOpenHelper helper;
	long set_id=0;
	String coord_username=null;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		Bundle bundle = this.getIntent().getExtras();
		coord_username = bundle.getString("coorUserName");
		get_campaign_information(coord_username);
	}
 
    /**Save Campaign Information**/
    public void get_campaign_information(String coord_username)
    {
    	//Customize the default title bar
    	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
    	setContentView(R.layout.campaign); 
    	getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title_logout);
    	GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
        View title = getWindow().findViewById(R.id.header);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundDrawable(gd);

    	//Edit Texts of XML
    	TextView coord_username_title=(TextView)findViewById(R.id.Username);
    	coord_username_title.setText(coord_username);
    	
    	Button logout_button=(Button)findViewById(R.id.logout);
    	logout_button.setText(Html.fromHtml(getString(R.string.logout)));
     	logout_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		 StartLoginActivity();
        	}
        });
    	final EditText set_name = (EditText)findViewById(R.id.set_name_entry);
        final EditText village_name = (EditText)findViewById(R.id.village_name_entry);
        final Button submit=(Button)findViewById(R.id.submit); 

        //Input Filters
        set_name.setFilters(new InputFilter[]{helper.nameInputFilter}); 
        set_name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        village_name.setFilters(new InputFilter[]{helper.nameInputFilter}); 
        village_name.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

   	    //On Submitting the Campaign information by coordinator
   	    submit.setOnClickListener(new View.OnClickListener() {
        public void onClick(View clicked) {
	        	/*Settlement Cursor*/
		   	    Cursor cursorDB_settlement=helper.getSettleCursor();
		   	    cursorDB_settlement=helper.getCursorToName(cursorDB_settlement,set_name.getText().toString());
		   	    
	        	/**Validation **/
		   	    if(set_name.length()==0)
		   	    {
		   	    	Toast toast = Toast.makeText(set_name.getContext(),"Enter Settlement Name", Toast.LENGTH_SHORT);
	        		toast.show();
		   	    }
		   	    else if(village_name.length()==0)
	        	{
	        		Toast toast = Toast.makeText(set_name.getContext(),"Enter Village Name", Toast.LENGTH_SHORT);
	        		toast.show();
	        	}
	        	else
	        	{
	        		/*Cursor cursorDB_village=helper.getVillageCursor("village_info");
	        		cursorDB_village=helper.getCursorToName(cursorDB_village,village_name.getText().toString());*/
	        		/*int vill_id=0;
	        		if(cursorDB_village==null)
			   	        vill_id= helper.addVillageDB(village_name.getText().toString());   	   
	        		else 
	        			vill_id=cursorDB_village.getInt(0);*/
			   	    set_id=0;
			   	    //Insert/Update Settlement Info 
			   	    if(cursorDB_settlement==null )
			   	    	set_id=helper.addCampaignInfoDB(set_name.getText().toString());
			   	    else
			   	    	set_id=cursorDB_settlement.getInt(0);
			   	    Log.e("Internet",Boolean.toString(isOnline()));
			   	    if(isOnline()==false)
			   	    {
			   	    	Toast toast = Toast.makeText(set_name.getContext(),"Your internet is not connected.First connect the internet and then try again.", Toast.LENGTH_SHORT);
		        		toast.show();
			   	    }
			   	    else
			   	    {
				   	    //Show Notification of 'Fetching Database'
				   	    showDialog(1);
				   	    //Starting New Thread
				    	new Thread(new Runnable(){
				    			public void run(){
				    				receive_families_info(set_name.getText().toString());
				    				//Hide Notification Message
				    				dismissDialog(1);
				    				startSelectProjectActivity((int)set_id);
				    			}
				    			}).start();
			   	    } 
			      	
	        	}
             }
         });
    }
    /**Creating Notification Message 'Fetching Database'**/
    @Override
    protected Dialog onCreateDialog(int id) {
     if(id == 1){
             ProgressDialog loadingDialog = new ProgressDialog(this);
             loadingDialog.setMessage("Fetching Database,Please Wait...");
             loadingDialog.setIndeterminate(true);
             loadingDialog.setCancelable(true);
             return loadingDialog;
     }
     return super.onCreateDialog(id);
    }
    /**Receive XML String from server **/
    public void receive_families_info(String settlement_name)
    {
    	HttpClient httpclient = new DefaultHttpClient();
    	HttpPost httppost = new HttpPost("http://rural-health.co.cc/xml_write.php");
        try {
		        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		        nameValuePairs.add(new BasicNameValuePair("get_families_info", settlement_name));
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost);
		        Log.e("Anshul",response.getEntity().toString());
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
			                    String resultStr = sb.toString();
			                    Log.e("Result",resultStr);
			                    /*Cursor cursorDB_settlement=helper.getSettleCursor();
			                    cursorDB_settlement=helper.getCursorToName(cursorDB_settlement, settlement_name);*/
			                    if(resultStr.length()==0)
			                    	Log.e("Android","NewlyInserted");
			                    else		
			                        parse_get_families_info_string(resultStr); 
			                    instream.close();
			                } catch (IOException e) {
			                        e.printStackTrace();
			                }
		            }
		           
		            
		      
		        }
		        

	    } catch (UnsupportedEncodingException e) {
	        Log.e("TAG1", e.toString());
	    } catch (ClientProtocolException e) {
	        Log.e("TAG2", e.toString());
	    } catch (IOException e) {
	        Log.e("TAG3", e.toString());
	    }
    	
    }
    /**Parse XML String and populate into Android Database**/     
    public void parse_get_families_info_string(String families_info_string)
    {
    	family_info_structure family_info_struct=new family_info_structure();
    	member_info_structure member_info_struct=new member_info_structure();

    	DocumentBuilderFactory factory;
        DocumentBuilder builder;
        InputStream is;
        Document dom = null;
            try {
                factory = DocumentBuilderFactory.newInstance();
                is = new ByteArrayInputStream(families_info_string.getBytes("UTF-8"));
                builder = factory.newDocumentBuilder();
                dom = builder.parse(is);
                }
         catch(Exception e){}
         Element root = dom.getDocumentElement();
         try
         {
        	 NodeList settlement_tag=root.getElementsByTagName("settlement");
             Log.e("Settlement Name",settlement_tag.item(0).getFirstChild().getNodeValue());
	         
	         /*families_list*/
	         NodeList families_list = root.getElementsByTagName("family");;
	         for (int i=0;i<families_list.getLength();i++){
	             Node family = families_list.item(i);
	             int family_id=0;
	             NodeList family_fields = family.getChildNodes();
	             for (int j=0;j<family_fields.getLength();j++){
	                 Node family_field = family_fields.item(j);
	                 String name = family_field.getNodeName();
	                 if (name.equalsIgnoreCase("headname")){
	                	 family_info_struct.setFamilyHeadName(family_field.getFirstChild().getNodeValue());
	                 } else if (name.equalsIgnoreCase("numofmembers")){
	                	 family_info_struct.setFamilyNoOfMembers(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
                     } else if(name.equalsIgnoreCase("numofchildren")){
	                	 family_info_struct.setFamilyNoOfChildren(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
	                 } else if(name.equalsIgnoreCase("lastmigrated")){
	                	 family_info_struct.setFamilyLastMigratedFrom(family_field.getFirstChild().getNodeValue());
	                 } else if(name.equalsIgnoreCase("tradocc")){
	                	 family_info_struct.setFamilyTraditionalOccupation(family_field.getFirstChild().getNodeValue());
	                 } else if(name.equalsIgnoreCase("income")){
	                	 family_info_struct.setFamilyDailyIncome(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
	                 } else if(name.equalsIgnoreCase("commid")){
	                	 family_info_struct.setCommId(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
	                 } else if(name.equalsIgnoreCase("rationstatus")){
	                	 family_info_struct.setFamilyRationCardStatus(family_field.getFirstChild().getNodeValue());
	                 } else if(name.equalsIgnoreCase("rationcat")){
	                	 family_info_struct.setFamilyRationCardCategory(family_field.getFirstChild().getNodeValue());
	                 } else if(name.equalsIgnoreCase("electricity")){
	                	 family_info_struct.setFamilyElectricityStatus(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
	                 } else if(name.equalsIgnoreCase("numofhandicapped")){
	                	 family_info_struct.setFamilyNumberOfHandicapped(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
		             } else if(name.equalsIgnoreCase("jananistatus")){
		            	 family_info_struct.setFamilyJananiSupportStatus(family_field.getFirstChild().getNodeValue());
		             } else if(name.equalsIgnoreCase("waterconn")){
		            	 family_info_struct.setFamilyWaterConnection(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
		             } else if(name.equalsIgnoreCase("vraddhsch")){
		            	 family_info_struct.setFamilyVraddhPensionScheme(family_field.getFirstChild().getNodeValue());
		             } else if(name.equalsIgnoreCase("plotcard")){
		            	 family_info_struct.setFamilyPlotCardStatus(family_field.getFirstChild().getNodeValue());
		             } else if(name.equalsIgnoreCase("numofchildrenschool")){
		            	 family_info_struct.setFamilyNoOfChildrenSchool(Integer.parseInt (family_field.getFirstChild().getNodeValue()));
		             } else if(name.equalsIgnoreCase("housingsupport")){
		            	 family_info_struct.setFamilyHousingSupport(family_field.getFirstChild().getNodeValue());
		             } else if(name.equalsIgnoreCase("widowscheme")){ 
		            	 family_info_struct.setFamilyWidowPensionScheme(family_field.getFirstChild().getNodeValue());
		                 family_id=helper.addFamilyInfoDB(family_info_struct,set_id);
		            	 
		            	 helper.updateFamilySchemesDatabase("family_info",family_info_struct.getFamilyHousingSupport(),family_id,"fam_housing_support"); 
		            	 helper.updateFamilySchemesDatabase("family_info",family_info_struct.getFamilyJananiSupportStatus(),family_id,"fam_janani_support_status");
		            	 helper.updateFamilySchemesDatabase("family_info",family_info_struct.getFamilyVraddhPensionScheme(),family_id,"fam_vraddh_pen_scheme");
		            	 helper.updateFamilySchemesDatabase("family_info",family_info_struct.getFamilyWidowPensionScheme(),family_id,"fam_widow_pension_scheme");
		            	 helper.updateDatabase("family_info",family_info_struct,family_id,"plot_card_status");
		            	 helper.updateDatabase("family_info",family_info_struct,family_id,"ration_card_status");
		             } else if (name.equalsIgnoreCase("member"))
		             {
	                     NodeList member_tag = family_field.getChildNodes();
	                     for (int k=0;k<member_tag.getLength();k++)
	                     {
	                    	 Node member_field = member_tag.item(k);
	                         String member_field_name = member_field.getNodeName();
	                    	 if (member_field_name.equalsIgnoreCase("memname")){
	                    		 member_info_struct.setMemName(member_field.getFirstChild().getNodeValue());
	                         } else if (member_field_name.equalsIgnoreCase("birthyear")){
	                        	
	                        	 member_info_struct.setMemBirthYear((Integer.parseInt (member_field.getFirstChild().getNodeValue())));
	                         } else if(member_field_name.equalsIgnoreCase("occupation")){
	                        	
	                        	 if(member_field.getFirstChild()!=null)
	                        	 member_info_struct.setMemOccupation(member_field.getFirstChild().getNodeValue());
	                         } else if(member_field_name.equalsIgnoreCase("relationwithhead")){
	                        	
	                        	 member_info_struct.setMemRelationWithHead(member_field.getFirstChild().getNodeValue());
	                         } else if(member_field_name.equalsIgnoreCase("gender")){
	                        	 
	                        	 member_info_struct.setMemGender(member_field.getFirstChild().getNodeValue());
	                         } else if(member_field_name.equalsIgnoreCase("jobcardstatus")){
	                        	
	                        	 member_info_struct.setMemJobCardStatus(member_field.getFirstChild().getNodeValue());
	                         } else if(member_field_name.equalsIgnoreCase("voterstatus")){
	                        	
	                        	 member_info_struct.setMemVoterStatus(member_field.getFirstChild().getNodeValue());
	                         } else if(member_field_name.equalsIgnoreCase("aadharstatus")){
	                        	
	                        	 member_info_struct.setMemAadharStatus(member_field.getFirstChild().getNodeValue());
	                        	 Cursor cursor_member=helper.getMemInfoCursor();
	                        	 cursor_member=helper.getCursorToName(cursor_member, member_info_struct.getMemName());
	                        	 int mem_id=0;
	                        	 mem_id=(int)helper.addNewMemberDB(member_info_struct.getMemName(),member_info_struct.getMemRelationWithHead(),member_info_struct.getMemOccupation(),member_info_struct.getMemGender(),member_info_struct.getMemBirthYear(),family_id);

	                        	 helper.updateMemDatabase("null","null","null","null",0,mem_id,"Job",member_info_struct.getMemJobCardStatus());
	                        	 helper.updateMemDatabase("null","null","null","null",0,mem_id,"Voter",member_info_struct.getMemVoterStatus());
	                        	 helper.updateMemDatabase("null","null","null","null",0,mem_id,"Adahar",member_info_struct.getMemAadharStatus());
	                         }	 
	                     }  
		             } //End of Member Tag
                  }
             	}
            }
	         catch(NullPointerException e) {
	 	        Log.e("TAG", e.toString());
	 	    }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        StartLoginActivity();
 
    }
    /**Start SelectProject Activity**/
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