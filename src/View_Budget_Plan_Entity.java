import java.sql.*;
import java.util.ArrayList;

public class View_Budget_Plan_Entity {

	public int plan_id;
	public int groupid;
	public String groupname;
	public String grouptype;
	public String budget_plan_name;
	public double budget;
	public double materialcost;
	public double logisticscost;
	public double transportcost;
	public double misccost;
	public double totalcost;
	
	public View_Budget_Plan_Entity(){
		
	}
	
	public View_Budget_Plan_Entity(int plan_id, int groupid, String groupname, String grouptype, String budget_plan_name, double budget,
			                       double materialcost, double logisticscost, double transportcost, double misccost, double totalcost){
		   this.plan_id = plan_id;
		   this.groupid = groupid;
		   this.groupname = groupname;
		   this.grouptype = grouptype;
		   this.budget_plan_name = budget_plan_name;
		   this.budget = budget;
		   this.materialcost = materialcost;
		   this.logisticscost = logisticscost;
		   this.transportcost = transportcost;
		   this.misccost = misccost;
		   this.totalcost = totalcost;
		
	}
	
	// the table does not display the right thing cuz calling the auto generated constructor here
	// Deleted 

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getBudget_plan_name() {
		return budget_plan_name;
	}

	public void setBudget_plan_name(String budget_plan_name) {
		this.budget_plan_name = budget_plan_name;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getMaterialcost() {
		return materialcost;
	}

	public void setMaterialcost(double materialcost) {
		this.materialcost = materialcost;
	}

	public double getLogisticscost() {
		return logisticscost;
	}

	public void setLogisticscost(double logisticscost) {
		this.logisticscost = logisticscost;
	}

	public double getTransportcost() {
		return transportcost;
	}

	public void setTransportcost(double transportcost) {
		this.transportcost = transportcost;
	}

	public double getMisccost() {
		return misccost;
	}

	public void setMisccost(double misccost) {
		this.misccost = misccost;
	}

	public double getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(double totalcost) {
		this.totalcost = totalcost;
	}
	
	public ArrayList<View_Budget_Plan_Entity> viewbudgetplan(){
	
	
	    ArrayList<View_Budget_Plan_Entity> budgetdata = new ArrayList<View_Budget_Plan_Entity>();
	
           try{
    	
    	      Connection connection = sqliteconnection.dbConnector();
    	      String query = "select b.planId, b.groupId , c.groupName , c.groupType , b.budget_plan_name , b.budget , b.materialcost , b.transportcost , b.logisticscost , b.misccost , b.totalcost "
    	      		       + "from Budget_Plan b "
    	      		       + "inner join Community_Group c "
    	      		       + "on b.groupId = c.groupId;";
    	      PreparedStatement  pst = connection.prepareStatement(query);
    	      ResultSet rs = pst.executeQuery();
    	
              while(rs.next()){
            	  
               /*  plan_id = rs.getInt(1);
             	 groupid = rs.getInt(2);
             	 groupname = rs.getString(3);
             	 grouptype = rs.getString(4);
             	 budget_plan_name = rs.getString(5);
             	 budget = rs.getDouble(6);
             	 materialcost = rs.getDouble(7);
             	 transportcost = rs.getDouble(8);
             	 logisticscost = rs.getDouble(9);
             	 misccost = rs.getDouble(10);
             	 totalcost = rs.getDouble(11); */

    	        View_Budget_Plan_Entity bd = new View_Budget_Plan_Entity(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
    	        		                     rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11));
    	        budgetdata.add(bd);
              }
    	        rs.close();
    	        pst.close();
                connection.close();
    	
           }catch(Exception e){
          	e.printStackTrace();
           }
	
	       return budgetdata;
       }   
	
	
	public ArrayList<View_Budget_Plan_Entity> viewbudgetplanProject(){
		
		
	    ArrayList<View_Budget_Plan_Entity> budgetdata = new ArrayList<View_Budget_Plan_Entity>();
	
           try{
    	
    	      Connection connection = sqliteconnection.dbConnector();
    	      String query = "select b.planId, c.groupId , c.groupName , c.groupType , b.budget_plan_name , b.budget , b.materialcost , b.transportcost , b.logisticscost , b.misccost , b.totalcost "
    	      		       + "from Budget_Plan b "
    	      		       + "inner join Community_Group c "
    	      		       + "on b.groupId = c.groupId "
    	      		       + "where c.grouptype like '%Project%'; ";
    	      PreparedStatement  pst = connection.prepareStatement(query);
    	      ResultSet rs = pst.executeQuery();
    	
              while(rs.next()){
            	  
               /*  plan_id = rs.getInt(1);
             	 groupid = rs.getInt(2);
             	 groupname = rs.getString(3);
             	 grouptype = rs.getString(4);
             	 budget_plan_name = rs.getString(5);
             	 budget = rs.getDouble(6);
             	 materialcost = rs.getDouble(7);
             	 transportcost = rs.getDouble(8);
             	 logisticscost = rs.getDouble(9);
             	 misccost = rs.getDouble(10);
             	 totalcost = rs.getDouble(11); */

    	        View_Budget_Plan_Entity bd = new View_Budget_Plan_Entity(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
    	        		                     rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11));
    	        budgetdata.add(bd);
              }
    	        rs.close();
    	        pst.close();
                connection.close();
    	
           }catch(Exception e){
          	e.printStackTrace();
           }
	
	       return budgetdata;

       }   
	
     public ArrayList<View_Budget_Plan_Entity> viewbudgetplanCCA(){
		
		
	    ArrayList<View_Budget_Plan_Entity> budgetdata = new ArrayList<View_Budget_Plan_Entity>();
	
           try{
    	
    	      Connection connection = sqliteconnection.dbConnector();
    	      String query = "select b.planId, c.groupId , c.groupName , c.groupType , b.budget_plan_name , b.budget , b.materialcost , b.transportcost , b.logisticscost , b.misccost , b.totalcost "
    	      		       + "from Budget_Plan b "
    	      		       + "inner join Community_Group c "
    	      		       + "on b.groupId = c.groupId "
    	      		       + "where c.grouptype like '%CCA%'; ";
    	      PreparedStatement  pst = connection.prepareStatement(query);
    	      ResultSet rs = pst.executeQuery();
    	
              while(rs.next()){
            	  
               /*  plan_id = rs.getInt(1);
             	 groupid = rs.getInt(2);
             	 groupname = rs.getString(3);
             	 grouptype = rs.getString(4);
             	 budget_plan_name = rs.getString(5);
             	 budget = rs.getDouble(6);
             	 materialcost = rs.getDouble(7);
             	 transportcost = rs.getDouble(8);
             	 logisticscost = rs.getDouble(9);
             	 misccost = rs.getDouble(10);
             	 totalcost = rs.getDouble(11); */

    	        View_Budget_Plan_Entity bd = new View_Budget_Plan_Entity(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
    	        		                     rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11));
    	        budgetdata.add(bd);
              }
    	        rs.close();
    	        pst.close();
                connection.close();
    	
           }catch(Exception e){
          	e.printStackTrace();
           }
	
	       return budgetdata;

       }   
	
	
}
