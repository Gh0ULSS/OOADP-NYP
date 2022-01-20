

import java.sql.ResultSet;
import java.sql.SQLException;

public class routineControl<viewRoutineEntity> {

	public String[] processSchedule(String[] inputdata) {
		
		routineEntity<Object> b = new routineEntity<Object>();
		String[] retrievedSchedule=b.saveSchedule(inputdata);
		return retrievedSchedule;
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public ResultSet getSchedule(){
		
		routineEntity<Object> load=new routineEntity<Object>();
		ResultSet getSchedule = null;
		try {
			getSchedule = load.loadSchedule();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getSchedule;
		
		
	}

	// (routineEntity<Object>)

}
