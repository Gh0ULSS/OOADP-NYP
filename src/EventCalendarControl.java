import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventCalendarControl {
	final static String DATE_FORMAT = "dd-MM-yyyy";
	CalendarEntity ce = new CalendarEntity();
	
	//newEvent = Event Entity
	EventEntity newEvent = new EventEntity();

	public EventCalendarControl(){}
	
	public EventCalendarControl(CalendarEntity ce) {
		this.ce = ce;
	}
	// get event entity
	public EventEntity getNewEvent() {
		return newEvent;
	}
	
	public void setNewEvent(EventEntity newEvent) {
		this.newEvent = newEvent;
	}

	public CalendarEntity getCe() {
		return ce;
	}

	public void setCe(CalendarEntity ce) {
		this.ce = ce;
	}
	
	// gets method from entity
	public String getStrMth() {
		return ce.getMonthStr();
	}
	//method from entity
	public String getStrYear() {
		return ce.getYearStr();
	}

	public int getDayofWeek() {
		java.util.Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String yourDate = "1/" + ce.getMonth() + "/" + ce.getYear();
		try {
			cal.setTime(new SimpleDateFormat("dd/M/yyyy").parse(yourDate));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek - 1;
	}

	public void createEvent() {
		newEvent.createEvent();
	}

	public boolean isDateValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

}
