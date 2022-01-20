import java.util.ArrayList;

public class Budget_Plan_Controller {
	
	// ****** BUSINESS METHODS ******
	
	// 1. computation methods
	// 2. retrieve data from the entity
    // 3. parameter passing
	
    public ArrayList<Object> processbudgetplan(ArrayList<Object> getdata) {
    	
    	Budget_Plan_Entity b = new Budget_Plan_Entity();
    	ArrayList<Object> retrieved_data = b.savebudgetplan(getdata);
   
    	return retrieved_data;
    }
    
    public int deletebudgetplan(int budgetid){
    	
    	Budget_Plan_Entity e1 = new Budget_Plan_Entity();
    	int retrievedid = e1.deleteBudgetPlan(budgetid);
    	
    	return retrievedid;
    }
    
    public ArrayList<String> retrievecombobox(){
    	
    	Budget_Plan_Entity entity = new Budget_Plan_Entity();
    	ArrayList<String> retrieve = entity.fillcombobox();
		
    	return retrieve;
    }
       
    public ArrayList<View_Budget_Plan_Entity> retrievebudgetdata(){
    	
    	View_Budget_Plan_Entity e = new View_Budget_Plan_Entity();
    	ArrayList<View_Budget_Plan_Entity> budgetdata = e.viewbudgetplan();
    	
    	return budgetdata;
    }
    
    public ArrayList<View_Budget_Plan_Entity> retrievebudgetdataProject(){
    	
    	View_Budget_Plan_Entity e = new View_Budget_Plan_Entity();
    	ArrayList<View_Budget_Plan_Entity> budgetdata = e.viewbudgetplanProject();
    	
    	return budgetdata;
    }
    
    public ArrayList<View_Budget_Plan_Entity> retrievebudgetdataCCA(){
    	
    	View_Budget_Plan_Entity e = new View_Budget_Plan_Entity();
    	ArrayList<View_Budget_Plan_Entity> budgetdata = e.viewbudgetplanCCA();
    	
    	return budgetdata;
    }
}
