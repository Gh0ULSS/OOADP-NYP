import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLEvent {
 
	Connection conn;
	
	public void SQLcreateEvent(String eventTitle, String eventDetails, String eventDate, Connection conn, String query) throws SQLException
	{
		
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1,eventTitle);
		pst.setString(2,eventDate);
		pst.setString(3,eventDetails);
		
		 pst.executeUpdate();
	}
	
	//retrieve for display in label
	public ResultSet SQLRetrieve(String eventDate, Connection conn, String query) throws SQLException
	{
		
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1,eventDate);
		
	 ResultSet rs = pst.executeQuery();
		return rs;
		
	}
	
	
}
