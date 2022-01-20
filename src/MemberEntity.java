

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberEntity {
	public  String groupId2;
	public String adminNo;
	public String studentName;
	public String rank;
	
	public String groupSearch;
	public int searchInt;

public MemberEntity(){
	
}

public MemberEntity (String groupId2, String adminNo, String studentName, String rank){
	this.groupId2 = groupId2;
	this.adminNo = adminNo;
	this.studentName = studentName;
	this.rank = rank;
}

public String getGroupId2() {
	return groupId2;
}

public void setGroupId2(String groupId2) {
	this.groupId2 = groupId2;
}

public String getAdminNo() {
	return adminNo;
}

public void setAdminNo(String adminNo) {
	this.adminNo = adminNo;
}

public String getStudentName(){
	return studentName;
}

public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public String getRank() {
	return rank;
}

public void setRank(String rank) {
	this.rank = rank;
}

public ArrayList<Object> saveStudInfo(ArrayList<Object> entity){
	
	Connection c = sqliteconnection.dbConnector();
	
	try{
		String sql = "insert into Student (groupId, adminNo, studName, rank) values (?,?,?,?)";
		PreparedStatement pst = c.prepareStatement(sql);
		pst.setString(1, (String) entity.get(0));
		pst.setString(2, (String) entity.get(1));
		pst.setString(3, (String) entity.get(2));
		pst.setString(4, (String) entity.get(3));
		
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
public ArrayList<Object> saveStudDel(ArrayList<Object> entity){
	Connection c = sqliteconnection.dbConnector();
	
	try{
		String sql = "delete from Student where adminNo = (?)";
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
//My attempt at 3-Tiering this
/*public ArrayList<MemberEntity> viewTable(String groupId2){


ArrayList<MemberEntity> tableData = new ArrayList<MemberEntity>();

     try{
	
	       Connection connection = SQLiteConnection.dbConnector();
	       String query = "select * from Student where groupId = '" +groupId2+"'";
	       PreparedStatement  pst = connection.prepareStatement(query);
	       ResultSet rs = pst.executeQuery();
	
       while(rs.next()){
      	 MemberEntity bd = new MemberEntity(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4));
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
*/
}