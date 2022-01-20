import java.util.*;
import java.sql.*;

public class Task {
	private int taskId;
	private String taskName;
	private String description;
	private String taskType;
	private int groupId;
	private String adminNo;
	private String startDate;
	private String endDate;
	private Statement stmt= null;
	private Connection c = null;
	private PreparedStatement pst=null;
	
    private ArrayList<Task> taskList=new ArrayList<Task>();
    //private taskController controller=new taskController();
	
	Task(){}
	
	Task(int taskId,String taskName,String description,String taskType,int groupId,String adminNo,String startDate,String endDate){
		this.taskId=taskId;
		this.taskName=taskName;
		this.description=description;
		this.taskType=taskType;
		this.groupId=groupId;
		this.adminNo=adminNo;
		this.startDate=startDate;
		this.endDate=endDate;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/*public void connect() {
		try {
	         Class.forName("org.sqlite.JDBC").newInstance();
	         c = DriverManager.getConnection("jdbc:sqlite:D:\\Program Files (x86)\\DIT Year 2 Sem 1\\OOADP\\project\\Community planner.sqlite");
	         System.out.println("Opened database successfully");
	         
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      
	}
	
	public void closeConnection() {
		try {
			c.close();
			System.out.println("Connection is closed");
		} catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	}*/
	
	public ArrayList<Task> returnAllTask()  {
		c = sqliteconnection.dbConnector();
		try {
		stmt = c.createStatement();
        String sql = "select * from Task";
        ResultSet rs=stmt.executeQuery(sql);
       
        while(rs.next()){
        	Task t =new Task();
        	t.setTaskId(rs.getInt("taskId"));
        	t.setTaskName(rs.getString("taskName"));
        	t.setDescription(rs.getString("description"));
        	t.setTaskType(rs.getString("taskType"));
        	t.setGroupId(rs.getInt("groupId"));
        	t.setAdminNo(rs.getString("adminNo"));
			t.setStartDate(rs.getString("startDate"));
        	t.setEndDate(rs.getString("endDate"));
        	
        	taskList.add(t);
        };
       
        stmt.close();
        c.close();
        
        }
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			
		}
		return taskList;
		
	}
	
	public Object[][] returnTaskArray() {
		c = sqliteconnection.dbConnector();
		int count=countTask();
		Object[][] data =new Object [count][8];
		
		try {
		stmt = c.createStatement();
        String sql = "select * from Task";
        ResultSet rs=stmt.executeQuery(sql);
       
        
        int i=0;
        int k=1;
        while(rs.next()){
        	for(int j=0;j<8;j++) {
        		if(j==0||j==4){
        			data[i][j]=rs.getInt(k);
        		}
        		else {
        			data[i][j]=rs.getString(k);
        		}
        		k++;
        		if(k==9)
        		{
        			break;
        		}
        	}
        	i++;
        	k=1;
        };
       
        stmt.close();
        
        
        }
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
		
	}
	
	public Object[][] returnSorted(String column)  {
		c = sqliteconnection.dbConnector();
		int count=countTask();
		Object[][] data =new Object [count][8];
		
		try {
		stmt = c.createStatement();
        String sql = "select * from Task order by "+column+";";
        ResultSet rs=stmt.executeQuery(sql);
       
        
        int i=0;
        int k=1;
        while(rs.next()){
        	for(int j=0;j<8;j++) {
        		if(j==0||j==4){
        			data[i][j]=rs.getInt(k);
        		}
        		else {
        			data[i][j]=rs.getString(k);
        		}
        		k++;
        		if(k==9)
        		{
        			break;
        		}
        	}
        	i++;
        	k=1;
        };
       
        stmt.close();
        c.close();
        
        }
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			;
		}
		return data;
		
	}
	
	public String[] getColumns() {
		c = sqliteconnection.dbConnector();
		String[] columns = null;
		try {
			stmt = c.createStatement();
	        String sql = "select * from Task";
	        ResultSet rs=stmt.executeQuery(sql);
	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();
	        columns=new String[columnCount];
	       
	        for (int i = 1; i <= columnCount; i++ ) {
	        	  String name = rsmd.getColumnName(i);
	        	  columns[i-1]=name;
	        	}
	       
	        stmt.close();
	    	c.close();
	        }
			catch(Exception e){
				 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		         System.exit(0);
			}
			finally {
			
			}
			return columns;
			
		
	}
	
	
	public void addToDB(Task task)  {
		c = sqliteconnection.dbConnector();
		try {
			pst = c.prepareStatement("Insert into Task (taskName,description,taskType,groupId,adminNo,startDate,endDate) values (?,?,?,?,?,?,?)");
			pst.setString(1, task.getTaskName());
			pst.setString(2, task.getDescription());
			pst.setString(3, task.getTaskType());
			pst.setInt(4, task.getGroupId());
			pst.setString(5, task.getAdminNo());
			pst.setString(6, task.getStartDate());
			pst.setString(7, task.getEndDate());
	        pst.executeUpdate();
			
	        c.close();
		}
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			
			
		}
	}
	
	public void editDB(Task task)  {
		c = sqliteconnection.dbConnector();
		try {
			pst = c.prepareStatement("Update Task set taskName=?,description=?,taskType=?,groupId=?,adminNo=?,startDate=?,endDate=? where taskId=?");
			pst.setString(1, task.getTaskName());
			pst.setString(2, task.getDescription());
			pst.setString(3, task.getTaskType());
			pst.setInt(4, task.getGroupId());
			pst.setString(5, task.getAdminNo());
			pst.setString(6, task.getStartDate());
			pst.setString(7, task.getEndDate());
			pst.setInt(8, task.getTaskId());
	        pst.executeUpdate();
			
			
	        c.close();
		}
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
				
		}
	}
	
	public void deleteTask(int id)  {
		c = sqliteconnection.dbConnector();
		try {
			
			pst = c.prepareStatement("delete from Task where taskId=?");
			pst.setInt(1, id);
	        pst.executeUpdate();
			
	        c.close();
		}
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			
			
		}
	}
	
	public int countTask() {
		//connect();
		int count=-1;
		try {
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("Select Count(*) from Task");
			count=rs.getInt(1);
			
			
			
		}
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	         
		}
		/*finally {
			closeConnection();
		}*/
		return count;
	}
	
	public int countGroup(int groupId) {
		int count=-1;
		try {
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("Select Count(*) from Task where groupId="+groupId+";");
			count=rs.getInt(1);
			
			
			
		}
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	         
		}
		/*finally {
			closeConnection();
		}*/
		return count;
	}
	
	public int countAdminNo(String adminNo) {
		int count=-1;
		try {
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("Select Count(*) from Task where adminNo='"+adminNo+"';");
			count=rs.getInt(1);
			
			
			
		}
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	         
		}
		/*finally {
			closeConnection();
		}*/
		return count;
	}
	
	public Task returnTask(int id)  {
		c = sqliteconnection.dbConnector();
		Task t =new Task();
		try {
		stmt = c.createStatement();
        String sql = "select * from Task where taskId="+id+";";
        ResultSet rs=stmt.executeQuery(sql);
        
       
       
        t.setTaskId(rs.getInt("taskId"));
        t.setTaskName(rs.getString("taskName"));
        t.setDescription(rs.getString("description"));
        t.setTaskType(rs.getString("taskType"));
        t.setGroupId(rs.getInt("groupId"));
        t.setAdminNo(rs.getString("adminNo"));
		t.setStartDate(rs.getString("startDate"));
        t.setEndDate(rs.getString("endDate"));
        
       
        stmt.close();
        c.close();
        
        }
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			
		}
		return t;
		
	}
	
	
	public Object[][] searchGroup(int groupId) {
		c = sqliteconnection.dbConnector();
		int count=countGroup(groupId);
		Object[][] data =new Object [count][8];
		
		try {
		stmt = c.createStatement();
        String sql = "select * from Task where groupId="+groupId+";";
        ResultSet rs=stmt.executeQuery(sql);
       
        
        int i=0;
        int k=1;
        while(rs.next()){
        	for(int j=0;j<8;j++) {
        		if(j==0||j==4){
        			data[i][j]=rs.getInt(k);
        		}
        		else {
        			data[i][j]=rs.getString(k);
        		}
        		k++;
        		if(k==9)
        		{
        			break;
        		}
        	}
        	i++;
        	k=1;
        };
       
        stmt.close();
        c.close();
        
        }
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
			
		}
		return data;
	}
	
	public Object[][] searchAdminNo(String adminNo) {
		c = sqliteconnection.dbConnector();
		int count=countAdminNo(adminNo);
		Object[][] data =new Object [count][8];
		
		try {
		stmt = c.createStatement();
        String sql = "select * from Task where adminNo='"+adminNo+"';";
        ResultSet rs=stmt.executeQuery(sql);
       
        
        int i=0;
        int k=1;
        while(rs.next()){
        	for(int j=0;j<8;j++) {
        		if(j==0||j==4){
        			data[i][j]=rs.getInt(k);
        		}
        		else {
        			data[i][j]=rs.getString(k);
        		}
        		k++;
        		if(k==9)
        		{
        			break;
        		}
        	}
        	i++;
        	k=1;
        };
       
        stmt.close();
        c.close();
        
        }
		catch(Exception e){
			 System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
		}
		finally {
		    
		}
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	

}
