

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class GroupControl {
	
	//Creating
		public ArrayList <Object> processGroupInfo(ArrayList<Object> getData){
			//Call Entity
			GroupEntity toEntity = new GroupEntity();
			ArrayList<Object> retrieved_data = toEntity.saveGroupInfo(getData);
			
			return retrieved_data;
		}
		
		//Deleting
		public ArrayList <Object> processGroupDel(ArrayList<Object> getData){
			//Call Entity
			GroupEntity toEntity = new GroupEntity();
			ArrayList<Object> retrieved_data = toEntity.saveGroupDel(getData);
			
			return retrieved_data;
		}
		
		//Loading
		 public ArrayList<GroupEntity> getData(){
		    	
    	     GroupEntity toEntity = new GroupEntity();
    	     ArrayList<GroupEntity> groupData = toEntity.viewTable();
    	
    	  return groupData;
       }
}
	
