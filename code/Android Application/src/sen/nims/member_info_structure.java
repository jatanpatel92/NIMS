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


/**Member Information Structure.
 * Stores All Information of a member**/

package sen.nims;

public class member_info_structure {
	
	private int mem_ID=0;
	private String mem_name=null;
	private int mem_birthyear=0;
	private String mem_occupation=null;
	private String mem_relationwithead=null;
	private String mem_gender;
	private String mem_jobcard_status=null;
	private String mem_voterstatus=null;
	private String mem_aadhar_status=null;
	
	//Set methods
	public void setMemId (int number)
    {
	            mem_ID=number;
    }	

	public void setMemName (String text)
    {
	            mem_name=text;
    }	
	
	public void setMemBirthYear (int number)
    {
	            mem_birthyear=number;
    }	
	
	public void setMemOccupation (String text)
    {
	            mem_occupation=text;
    }	
	
	public void setMemRelationWithHead (String text)
    {
	            mem_relationwithead=text;
    }	
	
	public void setMemGender (String a)
    {
	            mem_gender=a;
    }	
	
	public void setMemJobCardStatus(String text)
    {
				mem_jobcard_status=text;
    }	
	
	public void setMemVoterStatus(String text)
    {
				mem_voterstatus=text;
    }	
	
	public void setMemAadharStatus(String text)
    {
				mem_aadhar_status=text;
    }	
	
	//Get methods
	public int getMemId()
    {
	            return mem_ID;
    }
	
	public String getMemName()
    {
	            return mem_name;
    }
	
	public int getMemBirthYear()
    {
	            return mem_birthyear;
    }
	
	public String getMemOccupation()
    {
	            return mem_occupation;
    }
	
	public String getMemRelationWithHead()
    {
	            return mem_relationwithead;
    }
	
	public String getMemGender()
    {
	            return mem_gender;
    }
	
	public String getMemJobCardStatus()
    {
	            return mem_jobcard_status;
    }
	
	public String getMemVoterStatus()
    {
	            return mem_voterstatus;
    }
	
	public String getMemAadharStatus()
    {
	            return mem_aadhar_status;
    }
	
}
