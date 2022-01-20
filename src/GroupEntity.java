

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GroupEntity {
	public String groupId;
    public String groupName;
    public String groupType;

public GroupEntity(){
	
}

public GroupEntity (String groupId, String groupName, String groupType){
	this.groupId = groupId;
	this.groupName = groupName;
	this.groupType = groupType;
}

public String getGroupId() {
	return groupId;
}

public void setGroupId(String groupName){
	this.groupId = groupId;
}

public String getGroupName() {
	return groupName;
}

public void setGroupName(String groupName) {
	this.groupName = groupName;
}

public String getGroupType() {
	return groupType;
}

public void setGroupType(String groupType) {
	this.groupType = groupType;
}

//Creating
public ArrayList<Object> saveGroupInfo(ArrayList<Object> entity){
	
	Connection c = sqliteconnection.dbConnector();
	
	try{
		String sql = "insert into Community_Group (groupName, groupType) values (?,?)";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setString(1, (String) entity.get(0));
		pst.setString(2, (String) entity.get(1));
		
		pst.executeUpdate();
		
		System.out.println("Data inserted successfully!");
		
		pst.close();
		c.close();
				
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return entity;
	
}

//Deleting
public ArrayList<Object> saveGroupDel(ArrayList<Object> entity){
	Connection c = sqliteconnection.dbConnector();
	
	try{
		String sql = "delete from Community_Group where groupId = (?)";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setString(1, (String) entity.get(0));
		
		pst.executeUpdate();
		
		System.out.println("Group deleted successfully");
		pst.close();
		c.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}

	return entity;
}

//Loading

public ArrayList<GroupEntity> viewTable(){


  ArrayList<GroupEntity> tableData = new ArrayList<GroupEntity>();

       try{
	
	       Connection connection = sqliteconnection.dbConnector();
	       String query = "select * from Community_Group";
	       PreparedStatement  pst = connection.prepareStatement(query);
	       ResultSet rs = pst.executeQuery();
	
         while(rs.next()){
        	 GroupEntity bd = new GroupEntity(rs.getString(1),rs.getString(2),rs.getString(3));
        	 tableData.add(bd);
        	 }
         rs.close();
         pst.close();
         connection.close();
         }
       catch(Exception e){
    	   e.printStackTrace();
    	   }
       return tableData;
       }   
}