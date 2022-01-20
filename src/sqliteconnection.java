import java.sql.*;
import javax.swing.*;

public class sqliteconnection {
	
	Connection conn = null;

	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			// Change the file path to the directory/absolute path of the sqlite database in your computer 
			Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\DIT Year 2 Sem 1\\OOADP\\project\\Community planner.sqlite");
		//	JOptionPane.showMessageDialog(null, "Connection is successfull");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
}
