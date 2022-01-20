

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MemberControl {

		public ArrayList <Object> processStudInfo(ArrayList<Object> getData){
			//Call Entity
		    MemberEntity toEntity = new MemberEntity();
			ArrayList<Object> retrieved_data = toEntity.saveStudInfo(getData);
			
			return retrieved_data;
		}
		
		//Deleting
				public ArrayList <Object> processStudDel(ArrayList<Object> getData){
					//Call Entity
					MemberEntity toEntity = new MemberEntity();
					ArrayList<Object> retrieved_data = toEntity.saveStudDel(getData);
					
					return retrieved_data;
				}
				 
				//Loading
				//My attempt at 3-Tiering this
				/*
				 public ArrayList<MemberEntity> getData(){
				    	
		    	     MemberEntity toEntity = new MemberEntity();
		    	     ArrayList<MemberEntity> memberData = toEntity.viewTable(null);
		    	
		    	  return memberData;
		       }
				 */
				
		
}
	