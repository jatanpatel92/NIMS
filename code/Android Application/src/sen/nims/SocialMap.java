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



/* SociaMap Class
 * Handles Social Map Data Input Task.
 */



package sen.nims;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class SocialMap extends Activity {
	
	private NIMSSQLiteOpenHelper helper;
	double latitude;
	double longitude;
    public int set_id=0;
    int flag=0;
    String coord_username=null;
    
    static final String[] PLACES = new String[] {"airport", "hospital", "bakery", "bank", "bar" , "bus_stop", "butcher", "chemist", "cinema"
		, "electronics" ,"food_bar", "florist", "forest", "fruits", "hotel", "hairdresser", "laundry", "library", "mosque", "memorial", "parking"
		, "post_office", "police", "power_wind", "rental_bicycle", "tailor", "shoes", "toilets", "tower", "well", "windmill"};
 
    /** Called when the activity is first created. */
  	public void onCreate(Bundle icicle) {
  		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(icicle);
		helper = new NIMSSQLiteOpenHelper(this);
		setContentView(R.layout.gps);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.window_title);
    	GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[] {Color.rgb(255, 51,0),Color.rgb(174,25,27)});
        View title = getWindow().findViewById(R.id.header);
        View titleBar = (View) title.getParent();
        titleBar.setBackgroundDrawable(gd);
		Bundle bundle = this.getIntent().getExtras();
		coord_username = bundle.getString("coorUserName");
		//setID as a parameter to the activity
		set_id = bundle.getInt("setID");
		
		TextView coord_username_title=(TextView)findViewById(R.id.Username);
    	coord_username_title.setText(coord_username);
    	
    	//Handle NGO Project Click
    	Button ngo_projects_button=(Button)findViewById(R.id.Home);
    	ngo_projects_button.setText(Html.fromHtml(getString(R.string.Home)));
      	ngo_projects_button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View clicked) {
        		startSelectProjectActivity();
        	}
        });
		
		final AutoCompleteTextView place_type = (AutoCompleteTextView) findViewById(R.id.edit);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, PLACES);
	    place_type.setAdapter(adapter);

 	    final EditText name_type = (EditText)findViewById(R.id.name_type_entry);
 	    place_type.setFilters(new InputFilter[]{helper.nameInputFilter});
 	    name_type.setFilters(new InputFilter[]{helper.nameInputFilter});
 	    final Button submit=(Button)findViewById(R.id.submit_gps);
 	    
 	    submit.setOnClickListener(new View.OnClickListener() {
           public void onClick(View clicked) {
	        	   if(place_type.length()==0)
	               {
	            	   Toast toast = Toast.makeText(place_type.getContext(),"Enter type of place", Toast.LENGTH_SHORT);
	           		   toast.show();
	               }
	               else if (name_type.length()==0)
	               {
	            	   Toast toast = Toast.makeText(name_type.getContext(),"Enter exact name of place", Toast.LENGTH_SHORT);
	           		   toast.show();
	               }
	               else
	               {
	            	   Log.e("Latitude NIMS",Double.toString(latitude));
	            	   Log.e("Longitude NIMS",Double.toString(longitude));
	            	   if(isOnline()==false)
				   	    {
				   	    	Toast toast = Toast.makeText(name_type.getContext(),"Your internet is not connected.First connect the internet and then try again.", Toast.LENGTH_SHORT);
			        		toast.show();
				   	    }
				   	    else
				   	    {
		            	   showDialog(3);
					       new Thread(new Runnable(){
					    			public void run(){
					    				if(flag==1)
					    				{    
					    					dismissDialog(3);
					    				
					    				}
					    			}
					    			}).start();
		            	   helper.addNewPlace(place_type.getText().toString(),name_type.getText().toString(),latitude,longitude );
		            	   startSelectProjectActivity();
				   	    }
	               }
           }
 	   });
		
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
              // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
      
        	public void onLocationChanged(Location location) {
              // Called when a new location is found by the network location provider.
        		makeUseOfNewLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {
            }
          };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
}
        
    
	//For Dialog
    @Override
    protected Dialog onCreateDialog(int id) {
     if(id == 3){
             ProgressDialog loadingDialog = new ProgressDialog(this);
             loadingDialog.setMessage("Fetching Location and keep moving...");
             loadingDialog.setIndeterminate(true);
             loadingDialog.setCancelable(true);
             return loadingDialog;
     }
     return super.onCreateDialog(id);
    }
	protected void  makeUseOfNewLocation(Location location) {
		flag=1;
	    latitude=location.getLatitude();
	    longitude=location.getLongitude();		
	} 
	
	 /**Called When Back Button is pressed**/
    @Override
    public void onBackPressed()
    {
    	startSelectProjectActivity();
    }
    
    /**Calls SelectProject Activity  **/
	public void startSelectProjectActivity()
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
