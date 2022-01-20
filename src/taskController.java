import java.util.*;

import javax.swing.table.DefaultTableModel;

import java.sql.*;


public class taskController {
	
	
	private Task task =new Task();
	
    private ArrayList<Task> taskList=new ArrayList<Task>();
    private DefaultTableModel tm;
    
	
	
	public void newTask(String taskName,String description,String taskType,int groupId,String adminNo,String startDate,String endDate) throws SQLException {
		Task newTask=new Task();
		newTask.setTaskName(taskName);
		newTask.setDescription(description);
		newTask.setTaskType(taskType);
		newTask.setGroupId(groupId);
		newTask.setAdminNo(adminNo);
		newTask.setStartDate(startDate);
		newTask.setEndDate(endDate);
		task.addToDB(newTask);
		
	}
	
	public DefaultTableModel setTableModel() throws SQLException {
		tm=new DefaultTableModel(task.returnTaskArray(),task.getColumns());
		return tm;
	}
	
	public DefaultTableModel setSortedModel(String column) throws SQLException {
		tm=new DefaultTableModel(task.returnSorted(column),task.getColumns());
		return tm;
	}
	
	public void editTask(int taskId,String taskName,String description,String taskType,int groupId,String adminNo,String startDate,String endDate) throws SQLException {
		Task newTask=new Task();
		newTask.setTaskId(taskId);
		newTask.setTaskName(taskName);
		newTask.setDescription(description);
		newTask.setTaskType(taskType);
		newTask.setGroupId(groupId);
		newTask.setAdminNo(adminNo);
		newTask.setStartDate(startDate);
		newTask.setEndDate(endDate);
		task.editDB(newTask);
	}
	
	public void deleteTask(int id) throws SQLException {
		task.deleteTask(id);
	}
	
	public DefaultTableModel searchGroup(int groupId) throws SQLException {
		tm=new DefaultTableModel(task.searchGroup(groupId),task.getColumns());
		return tm;
	}
	
	public DefaultTableModel searchAdminNo(String adminNo) throws SQLException {
		tm=new DefaultTableModel(task.searchAdminNo(adminNo),task.getColumns());
		return tm;
	}
	
	

}
