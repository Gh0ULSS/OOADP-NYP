

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import net.proteanit.sql.DbUtils;

public class routineEntity<viewRoutineEntity> {

	
	//Save data
	public routineEntity() {
		// TODO Auto-generated constructor stub
	}

	public String[] saveSchedule  (String[] inputdata){
		
    	Connection c = sqliteconnection.dbConnector();
    	
    	try{
		     String sql = "UPDATE RoutineSchedule SET Task= '"+inputdata[0]+"'  WHERE Time='8am-9am';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[1]+"'  WHERE Time='9am-10am';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[2]+"'  WHERE Time='10am-11am';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[3]+"'  WHERE Time='11am-12pm';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[4]+"'  WHERE Time='12pm-1pm';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[5]+"'  WHERE Time='1pm-2pm';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[6]+"'  WHERE Time='2pm-3pm';"
		     			+ "UPDATE RoutineSchedule SET Task= '"+inputdata[7]+"'  WHERE Time='3pm-4pm';";
		     PreparedStatement pst = c.prepareStatement(sql);
		     
		    
		     
		     
		     pst.executeUpdate();
		     
		     System.out.println("Data is inserted successfully");
		     
		     pst.close();
		     c.close();
		}
		
		catch (Exception e){
		    e.printStackTrace();
		}

    	return inputdata;
  
	}
	
	
	 //Load Data
	public ResultSet loadSchedule  () throws SQLException{

    	Connection c = sqliteconnection.dbConnector();
		String  query="Select * FROM RoutineSchedule";
		PreparedStatement pst=c.prepareStatement(query);
		ResultSet rs = null;
		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
}
}

