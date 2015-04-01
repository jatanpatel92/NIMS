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



/**
 * This class acts as a structure to store all family information
 */

package sen.nims;

import java.util.Date;

public class family_info_structure {
       private String family_head_name=null;
       private int family_no_of_members=0;
       private int fam_no_of_children =0;
	   private String fam_last_migrated_from=null;
	   private int fam_id ; 
	   private int com_id;
	   private String fam_traditional_occupation =null;
	   private int fam_daily_income=0;
	   private String fam_ration_card_status=null;
	   private String fam_ration_card_category=null;
	   private int fam_electricity_status=0;
	   private int fam_no_of_handicapped=0;
	   private String fam_janani_support_status=null;
	   private int fam_loan_application_status=0;
	   private int fam_water_connection=0;
	   private String fam_vraddh_pen_scheme=null;
	   private String fam_plot_card_Status=null;
	   private int fam_no_of_children_school=0;
	   private Date fam_settlement_date;
	   private String fam_housing_support=null;
	   private String fam_widow_pension_scheme=null;
	   
	   //Set Methods
	   public void setFamilyId (int number)
       {
	            fam_id=number;
       }	
	   public void setCommId (int number)
       {
	            com_id=number;
       }
       public void setFamilyHeadName(String text)
       {
	            family_head_name=text;
       }
       public void setFamilyNoOfMembers(int number)
       {
	            family_no_of_members=number;
       }	
	   
       public void setFamilyNoOfChildren(int number)
       {
	            fam_no_of_children=number;
       }	
       
       public void setFamilyLastMigratedFrom(String text)
       {
	            fam_last_migrated_from=text;
       }
       
       public void setFamilyTraditionalOccupation(String text)
       {
	            fam_traditional_occupation=text;
       }
       
       public void setFamilyDailyIncome(int number)
       {
    	   		fam_daily_income =number;
       }
       
       public void setFamilyRationCardStatus(String text)
       {
    	   		fam_ration_card_status=text;
       }
       
       public void setFamilyRationCardCategory(String text)
       {
    	   			fam_ration_card_category=text;
       }
       
       public void setFamilyElectricityStatus(int number)
       {
    	   			fam_electricity_status=number;
       }
       
       public void setFamilyNumberOfHandicapped(int number)
       {
    	   			fam_no_of_handicapped=number;
       }
       
       public void setFamilyJananiSupportStatus(String text)
       {
    	   		fam_janani_support_status=text;
       }
       public void setFamilyLoanApplicationStatus(int number)
       {
    	   fam_loan_application_status=number;
       }
       public void setFamilyWaterConnection(int number)
       {
    	      fam_water_connection=number;
       }
       
        public void setFamilyVraddhPensionScheme(String text)
       {
    	   fam_vraddh_pen_scheme=text;
       }
       
        public void setFamilyPlotCardStatus(String text)
        {
        	fam_plot_card_Status=text;
        }
        
        public void setFamilyNoOfChildrenSchool(int number)
        {
        	fam_no_of_children_school=number;
        }
        
        public void setFamilySettlementDate(Date text)
        {
        	fam_settlement_date=text;
        }
        
        public void setFamilyHousingSupport(String text)
        {
        	fam_housing_support=text;
        }
        
        public void setFamilyWidowPensionScheme(String text)
        {
        	fam_widow_pension_scheme=text;
        }
        
       //Get Methods
       public int getFamilyId()
       {
	            return fam_id;
       }	 
       public int getComId()
       {
	            return com_id;
       }	 
       
       public String getFamilyHeadName()
       {
	            return family_head_name;
       }
       public int getFamilyNoOfMembers()
       {
	            return family_no_of_members;
       }	 
       
       public int getFamilyNoOfChildren()
       {
	            return fam_no_of_children;
       }
       
       public String getFamilyLastMigratedFrom()
       {
	            return fam_last_migrated_from;
       }
       
       public String getFamilyTraditionalOccupation()
       {
	            return fam_traditional_occupation;
       }
       
       public int getFamilyDailyIncome()
       {
    	   		return fam_daily_income ;
       }
       
       public String getFamilyRationCardStatus()
       {
    	   		return fam_ration_card_status;
       }
       
       public String getFamilyRationCardCategory()
       {
    	   			return fam_ration_card_category;
       }
       
       public int getFamilyElectricityStatus()
       {
    	   			return fam_electricity_status;
       }
       
       public int getFamilyNumberOfHandicapped()
       {
    	   			return fam_no_of_handicapped;
       }
       
       public String getFamilyJananiSupportStatus()
       {
    	   		return fam_janani_support_status;
       }
       
       public int getFamilyLoanApplicationStatus()
       {
    	   return fam_loan_application_status;
       }
       
       public int getFamilyWaterConnection()
       {
    	      return fam_water_connection;
       }
       
        public String getFamilyVraddhPensionScheme()
       {
    	   return fam_vraddh_pen_scheme;
       }
       
        public String getFamilyPlotCardStatus()
        {
        	return fam_plot_card_Status;
        }
        
        public int getFamilyNoOfChildrenSchool()
        {
        	return fam_no_of_children_school;
        }
        
        public Date getFamilySettlementDate()
        {
        	return fam_settlement_date;
        }
        
        public String getFamilyHousingSupport()
        {
        	return fam_housing_support;
        }
        
        public String getFamilyWidowPensionScheme()
        {
        	return fam_widow_pension_scheme;
        }        
}
