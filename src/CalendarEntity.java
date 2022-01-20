
public class CalendarEntity {

public CalendarEntity() {}

int year;
public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}

public int getMonth() {
	return month;
}

public void setMonth(int month) {
	this.month = month;
}

int month;

public CalendarEntity(int month, int year)
{
	this.month = month;
	this.year = year;
}

public String getYearStr()
{
	String yr = Integer.toString(year);
	return yr;
}

//method to get month
public String getMonthStr()
{
	
	switch(month) {
    case 1 :
      return "Jan"; 
      
    case 2 :
    	 return "Feb"; 
    	
    case 3 :
    	 return "Mar"; 
      
    case 4 :
    	 return "Apr"; 
    case 5 :
    	 return "May"; 
       
       
    case 6 :
    	 return "Jun"; 
       
        
    case 7 :
    	 return "Jul"; 
     
        
    case 8 :
    	 return "Aug"; 
       
        
    case 9 :
    	 return "Sep"; 
        
    case 10:
    	 return "Oct"; 
        
    case 11 :
    	 return "Nov"; 
       
    case 12 :
    	 return "Dec"; 
    	 
    	 default: 
    		 return "";
        
 }

}

//method for days 
public int getDaysOfMonth()
{
	int number_Of_DaysInMonth;
	switch (month) {
    case 1:
        //MonthOfName = "January";
    	return  number_Of_DaysInMonth = 31;
       
    case 2:
        //MonthOfName = "February";
        if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
        	return   number_Of_DaysInMonth = 29;
        } else {
        	return  number_Of_DaysInMonth = 28;
        }
        
    case 3:
        //MonthOfName = "March";
    	return  number_Of_DaysInMonth = 31;
        
    case 4:
        //MonthOfName = "April";
    	return  number_Of_DaysInMonth = 30;
        
    case 5:
        //MonthOfName = "May";
    	return  number_Of_DaysInMonth = 31;
        
    case 6:
       // MonthOfName = "June";
    	return number_Of_DaysInMonth = 30;
        
    case 7:
        //MonthOfName = "July";
    	return  number_Of_DaysInMonth = 31;
        
    case 8:
       // MonthOfName = "August";
    	return  number_Of_DaysInMonth = 31;
        
    case 9:
       // MonthOfName = "September";
    	return number_Of_DaysInMonth = 30;
        
    case 10:
      //  MonthOfName = "October";
    	return  number_Of_DaysInMonth = 31;
       
    case 11:
       // MonthOfName = "November";
    	return number_Of_DaysInMonth = 30;
        
    case 12:
       // MonthOfName = "December";
       return number_Of_DaysInMonth = 31;
       default:
    	   return 0;
}

}
}




