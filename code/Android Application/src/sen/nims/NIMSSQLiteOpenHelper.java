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


/* Database Class
 * Create/Insert/Update Queries
 */


package sen.nims;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

public class NIMSSQLiteOpenHelper extends SQLiteOpenHelper {

	public static final String OBJECTS_TABLE ="family_info";
	public static final String OBJECTS_TABLE2 ="admin_info";
	public static final String OBJECTS_TABLE3 ="campaign_info";
	public static final String OBJECTS_TABLE4 ="community_info";
	public static final String OBJECTS_TABLE5 ="coordinator_info";
	public static final String OBJECTS_TABLE6 ="district_info";
	public static final String OBJECTS_TABLE7 ="member_info";
	public static final String OBJECTS_TABLE8 ="ngo";
	public static final String OBJECTS_TABLE9 ="settlement_info";
	public static final String OBJECTS_TABLE10 ="survey_info";
	public static final String OBJECTS_TABLE11 ="taluka_info";
	public static final String OBJECTS_TABLE12 ="village_info";
	
	public static final String OBJECTS_DatabaseID= "id";			
	public static final String OBJECTS_NAME = "nims";
	private static String DB_NAME = "NIMS";			//Database name
	private static int VERSION = 1;

	public NIMSSQLiteOpenHelper(Context context){
		super(context, DB_NAME , null, VERSION );	//context:attached to database
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
          createTable(db);
          insertQueries(db);
	}

	//Create Queries
	private void createTable(SQLiteDatabase db) { 
		
		db.execSQL("CREATE TABLE coordinator_info('coord_id' int(11) NOT NULL ," + "'coord_name' varchar(20) NOT NULL,"
				+ "'coord_contactno' int(11) DEFAULT NULL,"+ "'coord_joining_date' date DEFAULT NULL,"+ "'coord_username' varchar(12) NOT NULL,"
				+ "'coord_password' varchar(20) NOT NULL,"+"PRIMARY KEY ('coord_id')"+")");
		//District Info
		db.execSQL("CREATE TABLE district_info('dist_id' integer NOT NULL,"+ "'dist_name' varchar(20) NOT NULL,"+" PRIMARY KEY ('dist_id')"+")");

		//Community Info
		db.execSQL("CREATE TABLE community_info ('com_id' int NOT NULL," + "'com_name' varchar(20) NOT NULL,"
				+ "PRIMARY KEY ('com_id')"+")");
		
		//Taluka Info
		db.execSQL("CREATE TABLE taluka_info ('taluka_id' integer NOT NULL," + " 'taluka_name' varchar(20) NOT NULL," +
				"'dist_id' integer NOT NULL," + " PRIMARY KEY ('taluka_id')," + "FOREIGN KEY ('dist_id') REFERENCES 'district_info'"+")");
		//Village Info
		db.execSQL("CREATE TABLE village_info ('vil_id' integer NOT NULL," + "'vil_name' varchar(20) NOT NULL,"+
				"PRIMARY KEY ('vil_id')" +")");
		//Settlement Table
		db.execSQL("CREATE TABLE settlement_info('set_id' integer NOT NULL," + "'set_name' varchar(20) NOT NULL,"
				+ "PRIMARY KEY ('set_id')"+")");
		
		//Family Table
		db.execSQL("CREATE TABLE  family_info ('fam_id' integer not null," + " 'Fam_Head_Name' varchar(20) DEFAULT NULL," + 
				"'Fam_No_of_Members' int DEFAULT NULL, " + " 'fam_no_of_children' int DEFAULT NULL,"
				+"'fam_last_migrated_from' varchar(20) DEFAULT NULL," +"'fam_traditional_occupation' varchar(20) DEFAULT NULL,"
				+ "'fam_daily_income' int DEFAULT NULL,"+"'com_id' int DEFAULT NULL," + 
				"'fam_latitude' varchar(11) DEFAULT NULL," +"'fam_longitude' varchar(11) DEFAULT NULL,"+
				"'set_id' integer DEFAULT NULL," + "'fam_ration_card_status' varchar(20) DEFAULT NULL," +
				"'fam_ration_card_category' varchar(15) DEFAULT NULL," + "'fam_electricity_status' int DEFAULT NULL,"
				+ "'fam_no_of_handicapped' decimal(2,0) DEFAULT '0', " + "'fam_janani_support_status' varchar(20) DEFAULT NULL, "
				+ "'fam_loan_application_status' tinyint(1) DEFAULT NULL," + "'fam_water_connection' tinyint(1) DEFAULT NULL,"
				+ "'fam_vraddh_pen_scheme' varchar(20) DEFAULT NULL," + "'fam_plot_card_Status' varchar(20) DEFAULT NULL,"
				+ "'fam_no_of_children_school' decimal(2,0) DEFAULT '0'," + "'fam_settlement_date' date DEFAULT NULL,"
				+ "'fam_housing_support' tinyint(1) DEFAULT NULL," + "'fam_widow_pension_scheme' varchar(20) DEFAULT NULL," 
				+ " PRIMARY KEY ('fam_id')," +"FOREIGN KEY ('com_id') REFERENCES 'com_id',"+ "FOREIGN KEY ('set_id') REFERENCES 'set_id'"+")");
		//Member Info
		db.execSQL("CREATE TABLE member_info('mem_id' integer NOT NULL," + "'fam_id' integer NOT NULL," + 
				"'mem_name' varchar(20) NOT NULL," + "'mem_birthyear' decimal(4,0) DEFAULT NULL," + "'mem_occupation' varchar(20) DEFAULT NULL,"+
				"'mem_relationwithead' varchar(20) DEFAULT NULL," + "'mem_gender' char(1) DEFAULT NULL," + 
				"'mem_jobcard_status' varchar(20) DEFAULT 'NOT_APPLIED'," + "'mem_voterstatus' varchar(20) DEFAULT 'NOT_APPLIED'," + 
				"'mem_aadhar_status' varchar(20) DEFAULT 'NOT_APPLIED'," + "PRIMARY KEY ('mem_id')," + 
				"FOREIGN KEY ('fam_id') REFERENCES 'fam_id'"+")");

		// Social Map table 
		db.execSQL("CREATE TABLE social_map ('latitude' double NOT NULL," + "'longitude' double NOT NULL,"
				+ "'place_type' String NOT NULL," + "'name_type' String NOT NULL "+")");

		db.execSQL("CREATE TABLE item_info ('item_id' integer NOT NULL,"+
				  "'item_name' varchar(30) NOT NULL,"+
				  "PRIMARY KEY ('item_id')"+")");
		
		db.execSQL("CREATE TABLE item_distribution ('item_family_id' integer NOT NULL,'item_id' integer NOT NULL,"+
				  "'fam_id' integer NOT NULL,"+
				  "'no_of_items' integer NOT NULL,"+
				  "PRIMARY KEY ('item_family_id'),"+
				  "FOREIGN KEY ('fam_id') REFERENCES 'fam_id'"+"FOREIGN KEY ('item_id') REFERENCES 'fam_id'"+")");
		

	}
	//Insert Queries
	private void insertQueries(SQLiteDatabase db){
		
		//db.execSQL("INSERT INTO 'district_info' ('dist_id', 'dist_name') VALUES (1, 'Sabarkantha');");
		//db.execSQL("INSERT INTO 'taluka_info' ('taluka_id', 'taluka_name', 'dist_id') VALUES(1, 'Daskroi', 1);");
		//db.execSQL("INSERT INTO 'village_info' ('vil_id', 'vil_name') VALUES (1, 'Rampur');");
		db.execSQL("INSERT INTO 'coordinator_info' ('coord_id', 'coord_name', 'coord_contactno', " +
				"'coord_joining_date', 'coord_username', 'coord_password') VALUES (1, 'Bharat', 999999999, '2012-02-14', 'bharat', 'bharat')");
		db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (1, 'Nat-Bajania');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (2, 'Bhavaiya');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (3, 'SHIAS');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (4, 'Vadi-Madari');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (5, 'Gadaliya');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (6, 'Vansfoda');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (7, 'Ghantia');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (8, 'Salatghera');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (9, 'Sarania');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (10, 'Vanjara');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (11, 'Bajania');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (12, 'Bhand');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (13, 'Garudi');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (14, 'Kathodi');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (15, 'Shirligar');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (16, 'Nath');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (17, 'Bharathari');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (18, 'Kotvalia');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (19, 'Turi');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (20, 'Vitoliya');");
	    db.execSQL("INSERT INTO 'community_info' ('com_id', 'com_name') VALUES (21, 'Devipujak');");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE2);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE3);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE4);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE5);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE6);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE7);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE8);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE9);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE10);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE11);
		db.execSQL("DROP TABLE IF EXISTS " + OBJECTS_TABLE12);
		onCreate(db);
		

	}
	//Populating Data in Family Info Table
	public int addFamilyInfoDB(family_info_structure family_info_struct,long set_id) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
	       values.put("Fam_Head_Name", family_info_struct.getFamilyHeadName());
	       values.put("Fam_No_of_Members", family_info_struct.getFamilyNoOfMembers());
	       values.put("fam_no_of_children", family_info_struct.getFamilyNoOfChildren());
	       values.put("fam_last_migrated_from", family_info_struct.getFamilyLastMigratedFrom());
	       values.put("fam_traditional_occupation",family_info_struct.getFamilyTraditionalOccupation());
	       values.put("fam_daily_income",family_info_struct.getFamilyDailyIncome());
	       values.put("set_id", set_id);
	       values.put("fam_no_of_children_school",family_info_struct.getFamilyNoOfChildrenSchool());
	       values.put("fam_electricity_status", family_info_struct.getFamilyElectricityStatus());
	       values.put("fam_no_of_handicapped", family_info_struct.getFamilyNumberOfHandicapped());
	       values.put("fam_water_connection", family_info_struct.getFamilyWaterConnection());
	       values.put("com_id", family_info_struct.getComId());
	       long id = db.insert("family_info",null, values);
	       return (int)id;
	}
	//Populating Data from campaign info view
	public long addCampaignInfoDB(String set_name) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("set_name",set_name);
		   //values.put("vil_id",vill_id);
		   long set_id = db.insert("settlement_info",null, values);
		   return set_id;
	}
	
	public int addVillageDB(String village_name)
	{

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("vil_name",village_name);
		   int vill_id = (int)db.insert("village_info",null, values);
		   return vill_id;
	}
	//Populating Data in Member info table
	public long addNewMemberDB(String member_name,String relation_with_head,String occupation,String gender,int birthyear,int fam_id) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("mem_name", member_name);
	       values.put("mem_relationwithead", relation_with_head);
	       values.put("mem_occupation", occupation);
	       values.put("mem_gender", gender);
	       values.put("mem_birthyear", birthyear);
	       values.put("fam_id", fam_id);
		   long id= db.insert("member_info",null, values);
		   return id;
	}
	//Populating Data of community
	public long addCommunInfoDB(String comm_name) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("com_name",comm_name);
		   long com_id = db.insert("community_info",null, values);
		   return com_id;
	}
	
	//Populating data of social map
	public void addNewPlace(String place_type ,String name_type,double latitude,double longitude) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		 
		   values.put("place_type", place_type);
	       values.put("name_type", name_type);
	       values.put("longitude", longitude);  
	       values.put("latitude", latitude);
	       db.insert("social_map ",null, values);
		   return;
	}
	
	//Populating new item
	public int addNewItem(String item_name) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("item_name",item_name);
	       int item_id=(int)db.insert("item_info",null, values);
		   return item_id;
	}
	//Populating item distributed to family
	public int addNewItemDistributedFamily(int item_id,int fam_id,int no_items) {

		   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("item_id",item_id);
		   values.put("fam_id",fam_id);
		   values.put("no_of_items",no_items);
	       int item_family_id=(int)db.insert("item_distribution",null, values);
		   return item_family_id;
	}
	
	
	//Return Family Cursor
	public Cursor getCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("family_info", new String[] {"fam_id","Fam_Head_Name","Fam_No_of_Members",
				"fam_no_of_children","fam_last_migrated_from","fam_traditional_occupation","fam_daily_income",
				"fam_no_of_children_school","fam_plot_card_Status","fam_ration_card_status","fam_ration_card_category",
				"fam_housing_support","fam_janani_support_status","fam_vraddh_pen_scheme","fam_widow_pension_scheme",
				"fam_no_of_handicapped","com_id","fam_electricity_status","fam_water_connection","set_id"},null,null,null,null, null);		 
		return cursorDb;
	}
	public Cursor getVillageCursor(String TableName)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query(TableName, new String[] {"vil_id","vil_name"},null,null,null,null, null);		 
		return cursorDb;
	}
	//Return Taluka Cursor
	public Cursor getTalukaCursor(String TableName)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query(TableName, new String[] {"taluka_id","taluka_name","dist_id"},null,null,null,null, null);		 
		return cursorDb;
	}
	public Cursor getDistrictCursor(String TableName)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query(TableName, new String[] {"dist_id","dist_name"},null,null,null,null, null);		 
		return cursorDb;
	}
	public Cursor getSettleCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("settlement_info", new String[] {"set_id","set_name"},null,null,null,null, null);		 
		return cursorDb;
	}
	public Cursor getMemInfoCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("member_info", new String[] {"mem_id","mem_name","fam_id","mem_birthyear",
				"mem_occupation","mem_relationwithead","mem_gender","mem_jobcard_status","mem_voterstatus","mem_aadhar_status"},null,null,null,null, null);
		return cursorDb;
	}
	public Cursor getCommunityCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("community_info", new String[] {"com_id","com_name"},null,null,null,null, null);
		return cursorDb;
	}
	public Cursor getCoordCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("coordinator_info", new String[] {"coord_id","coord_username","coord_password"},null,null,null,null, null);
		return cursorDb;
	}
	public Cursor getPlaceCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("social_map", new String[] {"latitude","longitude","place_type","name_type"},null,null,null,null, null);
		return cursorDb;
	}
	public Cursor getItemDistributeCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("item_distribution", new String[] {"fam_id","item_family_id","item_id","no_of_items"},null,null,null,null, null);
		return cursorDb;
	}
	public Cursor getItemInfoCursor()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursorDb = db.query("item_info", new String[] {"item_id","item_name"},null,null,null,null, null);
		return cursorDb;
	}
	Cursor getCursorToID(Cursor cursorDB,int ID)
    {
    	   //Search cursor for given familyID 
    	   boolean search_success = false;
	 	   if(cursorDB != null){
	 				cursorDB.moveToFirst();			//move the cursor to first row
	 			    if(! cursorDB.isAfterLast())		//returns whether cursor is pointing to position after last row
	 				{
	 			    	do{
								if(cursorDB.getInt(0)==ID)
								{	 
								    search_success=true;
	 			    			    break;
								}
	 							         							
	   					 }while (cursorDB.moveToNext());	    	
	 				}
	 		}
	 	    if(search_success==true)
	 		   return cursorDB;
	 	    else
	 	    	return null;
    }
    //Return Cursor for given main_name e.g. Family Head Name
    Cursor getCursorToName(Cursor cursorDB,String main_name)
    {
    	   //Search cursor for given familyID 
    	   boolean search_success = false;
    	   String db_name=new String();
	    	if(cursorDB != null){
					cursorDB.moveToFirst();			//move the cursor to first row
				    if(! cursorDB.isAfterLast())		//returns whether cursor is pointing to position after last row
					{
				    	do{
								db_name = cursorDB.getString(1);
								if(db_name.equalsIgnoreCase(main_name))
								{
									search_success=true;
                                    break;
								}
																         							
						 }while (cursorDB.moveToNext());
				    	
					}
			}
	    	if(search_success==true)
		 		   return cursorDB;
		 	else
		 	       return null;
    }

 
     
     //Update Family info into Database
     public boolean updateDatabase(String Table_name, family_info_structure family_info_struct,
    		 int familyID,String updateType) 
     {
    	 Log.e("A","B");
    	 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues values = new ContentValues();
		 if(updateType=="basic_info")
		 {
			   values.put("Fam_Head_Name", family_info_struct.getFamilyHeadName());
		       values.put("Fam_No_of_Members", family_info_struct.getFamilyNoOfMembers());
		       values.put("fam_no_of_children", family_info_struct.getFamilyNoOfChildren());
		       values.put("fam_last_migrated_from", family_info_struct.getFamilyLastMigratedFrom());
		       values.put("fam_traditional_occupation",family_info_struct.getFamilyTraditionalOccupation());
		       values.put("fam_daily_income",family_info_struct.getFamilyDailyIncome());
		       values.put("fam_no_of_children_school",family_info_struct.getFamilyNoOfChildrenSchool());
		       values.put("fam_electricity_status", family_info_struct.getFamilyElectricityStatus());
		       values.put("fam_no_of_handicapped", family_info_struct.getFamilyNumberOfHandicapped());
		       values.put("fam_water_connection", family_info_struct.getFamilyWaterConnection());
		       values.put("com_id", family_info_struct.getComId());
		    	  
		 }
		 else if(updateType=="plot_card_status")
		 {
			 values.put("fam_plot_card_Status", family_info_struct.getFamilyPlotCardStatus());
		 }
		 else if(updateType=="ration_card_status")
		 {
			 values.put("fam_ration_card_status", family_info_struct.getFamilyRationCardStatus());
			 values.put("fam_ration_card_category", family_info_struct.getFamilyRationCardCategory());
		 }
	     db.update(Table_name,values,"fam_id="+familyID,null);
         return true;
     }
     //To Update Govt Schemes of Family
     public boolean updateFamilySchemesDatabase(String Table_name, String govt_scheme_status,int familyID,String scheme_name) 
     {
    	 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues values = new ContentValues();
		 values.put(scheme_name, govt_scheme_status);
	     db.update(Table_name,values,"fam_id="+familyID,null);
         return true;
     }
     //Update Member Info in Database
     public boolean updateMemDatabase(String member_name,String relation_with_head,
    		 String occupation,String gender,int birthyear,int mem_id,String updateType,String card_status)
     {
    	   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   
		   if(updateType=="basic_info")
		   {
			   values.put("mem_name", member_name);
		       values.put("mem_relationwithead", relation_with_head);
		       values.put("mem_occupation", occupation);
		       values.put("mem_gender", gender);
		       values.put("mem_birthyear", birthyear);
		   }
		   else if(updateType.equals("Voter"))
		   {
			   values.put("mem_voterstatus", card_status);
		   }
		   else if(updateType.equals("Job"))
		   {
			   values.put("mem_jobcard_status", card_status);
		   }
		   else if(updateType.equals("Adahar"))
		   {
			   values.put("mem_aadhar_status", card_status);
		   }
		   
	       db.update("member_info",values,"mem_id="+mem_id,null);
		   return true;
     }
     
   //Update Member Info in Database
     public boolean updateItemDistributeDatabase(int fam_id,int no_items)
     {
           SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("no_of_items", no_items);
	       db.update("item_distribution",values,"fam_id="+fam_id,null);
		   return true;
     }
     public boolean updateItemInfoDatabase(int item_id,String item_name)
     {
    	   SQLiteDatabase db=this.getWritableDatabase();
		   ContentValues values = new ContentValues();
		   values.put("item_name", item_name);
	       db.update("item_info",values,"item_id="+item_id,null);
		   return true;
     }
     //---closes the database---    
     public void close() 
     {
         this.close();
     }

     public  ArrayList<String>  getAllCommunitiesName()
     {
    	final ArrayList<String> communities=new ArrayList<String>();
    	Cursor cursorDB_community=this.getCommunityCursor();
        if(cursorDB_community != null){
        	cursorDB_community.moveToFirst();			//move the cursor to first row
		    if(! cursorDB_community.isAfterLast())		//returns whether cursor is pointing to position after last row
			{
		    	do{
		    		communities.add(cursorDB_community.getString(1));
		    	 }while (cursorDB_community.moveToNext());
			}
        }
       
    	return communities;
    }
     public InputFilter nameInputFilter=new InputFilter() { 
         public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) { 
                 for (int i = start; i < end; i++) { 
                         if (!Character.isLetter(source.charAt(i)) && !Character.isSpaceChar(source.charAt(i))) { 
                                 return ""; 
                         } 
                 } 
                 return null; 
             } 
      };
      public void clearTable(String table_name)
      {
      	SQLiteDatabase db= this.getWritableDatabase();
          db.delete(table_name, null, null);
      }

      /*FORM FIELD VALIDATION MODULE*/
      //Validate Form Input Field for emptiness 
      public boolean field_invalidate(EditText inputText)
      {
        if(inputText.length()==0)
      	 return true;
        else
      	  return false;
      	
      }
    
}
