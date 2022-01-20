import java.util.ArrayList;

public class Login_Controller {

	// ****** BUSINESS METHODS ******
	
	// 1. computation methods
	// 2. retrieve data from the entity
    // 3. parameter passing
	
	 public boolean processlogindetailsL(ArrayList<String> getdata) {
	    	
	    	Student s = new Student();
	    	boolean logindetails = s.loginL(getdata);
	   
	    	return logindetails;
	    }
	 
	 public boolean processlogindetailsS(ArrayList<String> getdata) {
	    	
	    	Student s = new Student();
	    	boolean logindetails = s.loginS(getdata);
	   
	    	return logindetails;
	    }
	
}
