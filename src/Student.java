import java.util.ArrayList;
import java.sql.*;

public class Student {

	public String adminNo;
	public String studentName;
	public int groupId;
	public int rank;
	public String password;
	public static boolean status;
	
	public Student(){
		
	}
	
	public Student(String adminNo, String studentName, int groupId, int rank, String password){
		this.adminNo = adminNo;
		this.studentName = studentName;
		this.groupId = groupId;
		this.rank = rank;
		this.password = password;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean loginL(ArrayList<String> logindetailsL){
		
		try{
			
			Connection con = sqliteconnection.dbConnector();
			String sql = "select adminNo, password from Student where adminNo = ? and password = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, logindetailsL.get(0));
			pst.setString(2, logindetailsL.get(1));
			
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()){
				count = count + 1;
				
			}
			
			if (count == 1){
				status = true;
			}
			else{
				status = false;
			}
			
			rs.close();
			pst.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return status;
	}
	
    public boolean loginS(ArrayList<String> logindetailsS){
		
		try{
			
			Connection con = sqliteconnection.dbConnector();
			String sql = "select adminNo, password from Student where studName = ? and adminNo = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, logindetailsS.get(0));
			pst.setString(2, logindetailsS.get(1));
			
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()){
				count = count + 1;
			}
			
			if (count == 1){
				status = true;
			}
			else{
				status = false;
			}
			rs.close();
			pst.close();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return status;
	}
	
}
