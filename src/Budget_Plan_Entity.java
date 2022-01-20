import java.sql.*;
import java.util.ArrayList;


public class Budget_Plan_Entity {

	public int plan_id;
	public int group_id;
	public String budget_plan_name;
	public double budget;
	public double materialcost;
    public double logisticscost;
    public double transportcost;
    public double misccost;
    public double totalcost;
    
  //public static Connection c = sqliteconnection.dbConnector();
    public static Statement stmt = null;
    
    public Budget_Plan_Entity(){
    	
    }
    
    public Budget_Plan_Entity(int plan_id, int group_id,  String group_name, String group_type, String budget_plan_name, double budget, 
    		                  double materialcost, double logisticscost, double transportcost, double misccost , double totalcost){
    	this.plan_id = plan_id;
    	this.group_id = group_id;
    	this.budget_plan_name = budget_plan_name;
    	this.budget = budget;
    	this.materialcost = materialcost;
    	this.logisticscost = logisticscost;
    	this.transportcost = transportcost;
    	this.misccost = misccost;
    	this.totalcost = totalcost;
    }

    public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	
	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
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
	
	//***** SQL Java codes ***** 
    public ArrayList<Object> savebudgetplan(ArrayList<Object> entity){
    		
    	Connection c = sqliteconnection.dbConnector();
    	
    	try{
		     String sql = "insert into Budget_Plan (groupId,budget_plan_name,budget,materialcost,logisticscost,transportcost,misccost,totalcost)  values (?,?,?,?,?,?,?,?)";
		     PreparedStatement pst = c.prepareStatement(sql);
		     pst.setInt(1, (int) entity.get(0));
		     pst.setString(2, (String) entity.get(1));
		     pst.setDouble(3, (double) entity.get(2));
		     pst.setDouble(4, (double) entity.get(3));
		     pst.setDouble(5, (double) entity.get(4));
		     pst.setDouble(6, (double) entity.get(5));
		     pst.setDouble(7, (double) entity.get(6));
		     pst.setDouble(8, (double) entity.get(7));
		     
		     pst.executeUpdate();
		     
		     //System.out.println("Data is inserted successfully");
		     
		     pst.close();
		     c.close();
		}
		
		catch (Exception e){
		    e.printStackTrace();
		}

    	return entity;
  
	}
    
	public ArrayList<String> fillcombobox(){
	    
	      ArrayList<String> groups = new ArrayList<String>();
	      
		   try {
	
			  Connection conn = sqliteconnection.dbConnector();
		      stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery( "select * from Community_Group order by groupId" );
		      
		      
		      while ( rs.next() ) { 
		    	   int id = rs.getInt("groupId");
		           String name = rs.getString("groupName");  
		           groups.add(Integer.toString(id)+" "+name);
		      }
		     
		      rs.close();
		      stmt.close();
		      conn.close();
		      
		   } catch ( Exception e ) {
                 e.printStackTrace();
		   }
		   
		return groups;
    	
       }
	
   
	   public int deleteBudgetPlan(int id){
		
		  Connection conn2 = sqliteconnection.dbConnector();
		  String sql = "delete from Budget_Plan where planId = '"+id+"'; ";
	      try{
	    	  
	    	  PreparedStatement pst = conn2.prepareStatement(sql);

	    	  pst.execute();
	    	 // System.out.println("Deletion Successful");
			
	    	  pst.close();
	    	  conn2.close();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	     
		 return id;
		 
		 
	}
	
	
	 // *** NULL pointer exception cuz Connection conn is null
	 // NULL POINTER EXCEPTION cuz pointing to null
	
	
	
	

}
