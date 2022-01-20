import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JProgressBar;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Color;
import datechooser.beans.DateChooserCombo;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;

public class taskInputBoundary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel newTaskPane;
	private JTextField txtTaskName;
	private JLabel lblDescription;
	private JTextArea txtDescription;
	private JComboBox comboBoxTaskType;
	private JButton btnAdd;
	taskController controller=new taskController();
	private JScrollPane scrollPane;
	private JLabel lblGroupId;
	private JTextField txtGroupId;
	private JLabel lblAdminNo;
	private JTextField txtAdminNo;
	private DateChooserCombo dateStart;
	private DateChooserCombo dateEnd;
	private JLabel lblStartAt;
	private JLabel lblEnd;
	private SimpleDateFormat sm = new SimpleDateFormat("dd-MMM-yyyy");
	private Calendar tCalendar = Calendar.getInstance();
	private Exception nameEmpty=new Exception("Task name should not be empty");
	private Exception descEmpty=new Exception("Description should not be empty");
	private Exception adminEmpty=new Exception("Please enter student's admin No.");
	private JButton btnClear;
	private taskInputBoundary current=this;
	private JLabel lblallFieldsAre;
	private JLabel lblTaskId;
	private JTextField txtTaskId;
	private Boolean editMode=false;
	private Boolean detailsMode=false;

	
	

	public void setTxtTaskId(String taskId) {
		this.txtTaskId.setText(taskId);
	}
	
	public void setTaskType(String taskType) {
		this.comboBoxTaskType.setSelectedItem(taskType);
	}
	
	public void setTxtTaskName(String txtTaskName) {
		this.txtTaskName.setText(txtTaskName);
	}

	public void setTxtDescription(String txtDescription) {
		this.txtDescription.setText(txtDescription);
	}

	public void setTxtGroupId(String string) {
		
		this.txtGroupId.setText(string);
	}

	public void setTxtAdminNo(String txtAdminNo) {
		this.txtAdminNo.setText(txtAdminNo);
	}

	public void setDateStart(String dateStart) {
		
		Date date;
		try {
			date = new SimpleDateFormat("dd-MMM-yyyy").parse(dateStart);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			this.dateStart.setSelectedDate(cal);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		}

	public void setDateEnd(String dateEnd) {
		Date date;
		try {
			date = new SimpleDateFormat("dd-MMM-yyyy").parse(dateEnd);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			this.dateEnd.setSelectedDate(cal);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void editMode() {
		lblTaskId.setVisible(true);
		txtTaskId.setVisible(true);
		txtTaskId.setEditable(false);
		btnClear.setVisible(false);
		editMode=true;
	}
	
	public void detailsMode() {
		lblTaskId.setVisible(true);
		txtTaskId.setVisible(true);
		txtTaskId.setEditable(false);
		comboBoxTaskType.setEnabled(false);
		txtTaskName.setEditable(false);
		txtDescription.setEditable(false);
		txtGroupId.setEditable(false);
		txtAdminNo.setEditable(false);
		dateStart.setFocusable(false);
		dateEnd.setFocusable(false);
		btnAdd.setVisible(false);
		btnClear.setVisible(false);
		detailsMode=true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taskInputBoundary frame = new taskInputBoundary();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public taskInputBoundary() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setTitle("Task Details");
		setBackground(new Color(100, 149, 237));
		
		setResizable(false);
		
		setBounds(100, 100, 600, 500);
		newTaskPane = new JPanel();
		newTaskPane.setForeground(Color.WHITE);
		newTaskPane.setBackground(new Color(100, 149, 237));
		newTaskPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(newTaskPane);
		newTaskPane.setLayout(null);
		
		JLabel lblTaskName = new JLabel("Task Name : ");
		lblTaskName.setForeground(Color.WHITE);
		lblTaskName.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblTaskName.setBounds(10, 66, 100, 19);
		newTaskPane.add(lblTaskName);
		
		txtTaskName = new JTextField();
		txtTaskName.setFont(new Font("Agency FB", Font.PLAIN, 22));
		txtTaskName.setBounds(108, 60, 166, 34);
		newTaskPane.add(txtTaskName);
		txtTaskName.setColumns(10);
		
		lblDescription = new JLabel("Description :");
		lblDescription.setForeground(Color.WHITE);
		lblDescription.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblDescription.setBounds(10, 121, 97, 34);
		newTaskPane.add(lblDescription);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(108, 122, 171, 249);
		newTaskPane.add(scrollPane);
		
		txtDescription = new JTextArea();
		txtDescription.setFont(new Font("Agency FB", Font.PLAIN, 22));
		txtDescription.setWrapStyleWord(true);
		scrollPane.setViewportView(txtDescription);
		txtDescription.setLineWrap(true);
		txtDescription.setBorder(UIManager.getBorder("TextField.border"));
		
		JLabel lblTaskType = new JLabel("Task Type :");
		lblTaskType.setForeground(Color.WHITE);
		lblTaskType.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblTaskType.setBounds(284, 58, 86, 34);
		newTaskPane.add(lblTaskType);
		
		comboBoxTaskType = new JComboBox();
		comboBoxTaskType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				JComboBox comboBox=(JComboBox)event.getSource();
				
				String result=(String)comboBox.getSelectedItem();
				switch(result)
				{case "Individual":
					txtAdminNo.setEnabled(true);
					txtGroupId.setEnabled(false);
					txtGroupId.setText("N/A");
					txtAdminNo.setText("");
					break;
					
				case "Group":
					txtGroupId.setEnabled(true);
					txtAdminNo.setEnabled(false);
					txtAdminNo.setText("N/A");
					txtGroupId.setText("");
					break;
				
				}
				
			}
		});
		comboBoxTaskType.setModel(new DefaultComboBoxModel(new String[] {"Individual", "Group"}));
		comboBoxTaskType.setFont(new Font("Agency FB", Font.PLAIN, 24));
		comboBoxTaskType.setBounds(380, 63, 115, 31);
		newTaskPane.add(comboBoxTaskType);
		
		btnAdd = new JButton("Confirm");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 0, 128));
		btnAdd.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					
					String taskName=txtTaskName.getText();
					if(taskName.equals(""))
					{
						throw nameEmpty;
					}
					String description=txtDescription.getText();
					if(description.equals(""))
					{
						throw descEmpty;
					}
					String taskType=(String)comboBoxTaskType.getSelectedItem();
					int groupId;
					if (txtGroupId.getText().equals("N/A")==false) {
						groupId = Integer.parseInt(txtGroupId.getText());
					}
					else{
						groupId=0;
					}
				 
					
					String adminNo=txtAdminNo.getText();
					if(groupId==0&&adminNo.equals("")) {
						//if groupId==0,groupId=N/A,adminNo MUST NOT NULL
						throw adminEmpty;
					}
					String startDate=dateStart.getText();
					String endDate=dateEnd.getText();
					
					
					if (editMode==false) {
						//call controller here
						controller.newTask(taskName, description, taskType, groupId, adminNo, startDate, endDate);
						JOptionPane.showMessageDialog(null, "New task successfully added (Please refresh to see the changes)","Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						int taskId=Integer.parseInt(txtTaskId.getText());
						controller.editTask(taskId, taskName, description, taskType, groupId, adminNo, startDate, endDate);
						JOptionPane.showMessageDialog(null, "Changes successfully saved (Please refresh to see the changes)","Success", JOptionPane.INFORMATION_MESSAGE);
					}
					
					current.dispose();
					
					}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Group Id should only contain numbers","Error", JOptionPane.ERROR_MESSAGE);
					txtGroupId.setText("");
					txtGroupId.requestFocus();
				}
				
				catch(Exception empty) {
					JOptionPane.showMessageDialog(null, empty.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
					if (empty.getMessage().equals("Task name should not be empty")) {
						//Task Name empty
						txtTaskName.requestFocus();
					}
					else if(empty.getMessage().equals("Description should not be empty")) {
						//description empty
						txtDescription.requestFocus();
					}
					else if(empty.getMessage().equals("Please enter student's admin No.")) {
						//admin No empty
						txtAdminNo.requestFocus();
					}
				}
				
			}
		});
		btnAdd.setBounds(333, 407, 137, 34);
		newTaskPane.add(btnAdd);
		
		lblGroupId = new JLabel("Group ID : ");
		lblGroupId.setForeground(Color.WHITE);
		lblGroupId.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblGroupId.setBounds(299, 149, 86, 20);
		newTaskPane.add(lblGroupId);
		
		txtGroupId = new JTextField();
		txtGroupId.setText("N/A");
		txtGroupId.setFont(new Font("Agency FB", Font.PLAIN, 20));
		txtGroupId.setToolTipText("");
		txtGroupId.setEnabled(false);
		txtGroupId.setColumns(10);
		txtGroupId.setBounds(380, 148, 115, 26);
		newTaskPane.add(txtGroupId);
		
		lblAdminNo = new JLabel("Admin No : ");
		lblAdminNo.setForeground(Color.WHITE);
		lblAdminNo.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblAdminNo.setBounds(289, 203, 86, 20);
		newTaskPane.add(lblAdminNo);
		
		txtAdminNo = new JTextField();
		txtAdminNo.setFont(new Font("Agency FB", Font.PLAIN, 20));
		txtAdminNo.setColumns(10);
		txtAdminNo.setBounds(380, 201, 115, 27);
		newTaskPane.add(txtAdminNo);
		
		dateStart = new DateChooserCombo();
		dateStart.setFormat(3);
		dateStart.setDateFormat(sm);
		dateStart.setBounds(380, 260, 120, 27);
		newTaskPane.add(dateStart);
		
		
		dateEnd = new DateChooserCombo();
		dateEnd.setFormat(3);
		dateEnd.setDateFormat(sm);
		
		dateEnd.setBounds(380, 311, 120, 27);
		
		newTaskPane.add(dateEnd);
		
		lblStartAt = new JLabel("Start : ");
		lblStartAt.setForeground(Color.WHITE);
		lblStartAt.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblStartAt.setBounds(312, 260, 86, 20);
		newTaskPane.add(lblStartAt);
		
		lblEnd = new JLabel("End : ");
		lblEnd.setForeground(Color.WHITE);
		lblEnd.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblEnd.setBounds(324, 313, 86, 20);
		newTaskPane.add(lblEnd);
		
		btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(0, 0, 128));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtTaskName.setText("");
				txtDescription.setText("");
				comboBoxTaskType.setSelectedItem("Individual");
				txtAdminNo.setEnabled(true);
				txtGroupId.setEnabled(false);
				txtGroupId.setText("N/A");
				txtAdminNo.setText("");
				dateStart.setSelectedDate(tCalendar);
				dateEnd.setSelectedDate(tCalendar);
				
			}
		});
		btnClear.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnClear.setBounds(121, 407, 137, 34);
		newTaskPane.add(btnClear);
		
		lblallFieldsAre = new JLabel("*All Fields are Mandantory");
		lblallFieldsAre.setForeground(new Color(255, 0, 0));
		lblallFieldsAre.setFont(new Font("Agency FB", Font.PLAIN, 19));
		lblallFieldsAre.setBounds(312, 369, 267, 19);
		newTaskPane.add(lblallFieldsAre);
		
		lblTaskId = new JLabel("Task Id : ");
		lblTaskId.setVisible(false);
		lblTaskId.setForeground(Color.WHITE);
		lblTaskId.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblTaskId.setBounds(37, 21, 100, 19);
		newTaskPane.add(lblTaskId);
		
		txtTaskId = new JTextField();
		txtTaskId.setVisible(false);
		txtTaskId.setEditable(false);
		txtTaskId.setEnabled(false);
		txtTaskId.setFont(new Font("Agency FB", Font.PLAIN, 22));
		txtTaskId.setColumns(10);
		txtTaskId.setBounds(108, 15, 68, 34);
		newTaskPane.add(txtTaskId);
	}
}
