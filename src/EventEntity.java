import java.sql.*;

public class EventEntity {
	
    SQLEvent conn = new SQLEvent();
	private String eventTitle;
	private String eventDetails;
	private String eventDate;
	private Connection connection = null;
	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDetails() {
		return eventDetails;
	}

	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	
	public void createEvent()
	{
		//event entity query to send data to db from controller
		String query = "Insert into Event(Event_Title,Event_Date,Event_Details) values (?,?,?)";
		
		
		try {
			connection = sqliteconnection.dbConnector();
			conn.SQLcreateEvent(eventTitle, eventDetails, eventDate, connection, query);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	public void retrievedEvent ()
	{	//retrieve data from sql control and pass to event cal control
		String query = "Select Event_Title, Event_Details From Event Where Event_Date = ? ";
		
		try {
			connection = sqliteconnection.dbConnector();
			ResultSet rs = conn.SQLRetrieve(eventDate, connection, query);
			 while (rs.next()) {
	               eventTitle = rs.getString("Event_Title");
	               eventDetails =rs.getString("Event_Details"); 
	                                 
	            }
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
	}

}
