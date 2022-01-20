import java.awt.EventQueue;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.*;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.MenuEvent;
import java.awt.Color;


public class Main_Template extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/// BUDGET PLANNER ///
	
	private JTextField textfieldname;
	private JTextField textFieldmaterials;
	private JTextField textFieldlogistics;
	private JTextField textFieldtransport;
	private JTextField textFieldmisc;
	private JTextField textFieldbudget;
	
    public static JComboBox<String> comboBox_groupname;
	
	public int groupid;
	public String budget_plan_name;
	public double budget;
	public double materialcost;
    public double logisticscost;
    public double transportcost;
    public double misccost;
    public double totalcost;
    
	public ArrayList<Object> inputdata = new ArrayList<Object>();
	
	/// TASK ADMINISTRATOR ///
	
	private JTable table;
	private JScrollPane scrollPane11;
	private JButton btNewTask;
	private taskController controller=new taskController();
	private taskInputBoundary input;
	private JButton btnRefresh;
	private JButton btnEdit;
	private JButton btnRemove;
	private JButton btnDetails;
	private JButton btnSort;
	private JComboBox comboSort;
	private JLabel lblSortBy;
	private JCheckBox checkDesc;
	private JLabel lblSearchBy;
	private JComboBox comboSearch;
	private JTextField txtSearch;
	private JButton btnSearch;
	
	/// Routine Schedule ///
	
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	private JTextField textField8;
	private JTable table1;
	public String hour1;
	public String hour2;
	public String hour3;
	public String hour4;
	public String hour5;
	public String hour6;
	public String hour7;
	public String hour8;
	public String[] inputdata1=new String[8];
	
    /// EVENT ///
    
	private JTextField titleTextField;
	public int month;
	public int year;
	ArrayList<JLabel> allLabels = new ArrayList<JLabel>();
	
	/// Personnel Administrator ///
	
	private JTextField txtFGroupId;
	private JTextField txtFGroupName;
	private JTable tableGroups;
	private JTextField textFGroupID2;
	private JTextField textFStudName;
	private JTextField textFStudentAdmin;
	private JTable tableMembers;
	
	//Variables (Group)
	public String groupId;
	public String groupName;
	public String groupType;
	
	//Variables (Members)
	public  String groupId2;
	public String adminNo;
	public String studentName;
	public String rank;
	
	public ArrayList<Object> groupInfo = new ArrayList<Object>();
	public ArrayList<Object> studInfo = new ArrayList<Object>();
	public ArrayList<Object> groupDel = new ArrayList<Object>();
	public ArrayList<Object> studDel = new ArrayList<Object>();
	public ArrayList<String> groupSearch = new ArrayList<String>();
	
	// Connect to database
	public static Connection c = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Template frame = new Main_Template();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					c = sqliteconnection.dbConnector();
					
				    // fillcombobox();
					
					Budget_Plan_Controller con = new Budget_Plan_Controller();
					ArrayList<String> combobox = con.retrievecombobox();
				    
					for ( int i = 0; i <combobox.size() ; i++ ){
						comboBox_groupname.addItem(combobox.get(i));
					}
					comboBox_groupname.setSelectedIndex(-1);
					
						/* if fillcombobox is called before initializing the contents in the frame constructor
					 * it doesn't make sense to fillcombobox before the Jframe content has been initialized (JComboBox)
					 */
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main_Template() {
		setTitle("Community Planner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		////////////////////////////////  Instantiate all JPanels here ////////////////////////////////////
		
		
		JPanel Budget_Planner = new JPanel();
		Budget_Planner.setBackground(new Color(100, 149, 237));
		contentPane.add(Budget_Planner, "name_3804781491135");
		Budget_Planner.setLayout(null);
	
		JPanel Routine_Schedule = new JPanel();
		Routine_Schedule.setBackground(new Color(100, 149, 237));
		contentPane.add(Routine_Schedule, "name_3807707528527");
		Routine_Schedule.setLayout(null);
		
		JPanel Task_Administrator = new JPanel();
		Task_Administrator.setBackground(new Color(100, 149, 237));
		contentPane.add(Task_Administrator, "name_3809607451964");
		Task_Administrator.setLayout(null);
		
		JPanel Personnel_Administrator = new JPanel();
		Personnel_Administrator.setBackground(new Color(100, 149, 237));
		contentPane.add(Personnel_Administrator, "name_3811260166905");
		Personnel_Administrator.setLayout(null);
		
		JPanel Event = new JPanel();
		Event.setBackground(new Color(100, 149, 237));
		contentPane.add(Event, "name_3812869844646");
		Event.setLayout(null);
		
		JPanel Personnel_Administrator2 = new JPanel();
		Personnel_Administrator2.setBackground(new Color(100, 149, 237));
		contentPane.add(Personnel_Administrator2, "name_688169396193110");
		
		
		///////////////////////////////////////////////////// BUDGET PLANNER //////////////////////////////////////////////////////
		

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 772, 50);
		Budget_Planner.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Budget Planner");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Routine Schedule");
		mnNewMenu_2.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Budget_Planner.setVisible(false);
				Routine_Schedule.setVisible(true);
			}
		});
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Task Admininstrator");
		mnNewMenu_3.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Budget_Planner.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Personnel Administrator");
		mnNewMenu_4.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e){
			}
			public void menuDeselected(MenuEvent e){	
			}
			public void menuSelected(MenuEvent e){
				Budget_Planner.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_4);
		
		JMenu mnNewMenu_1 = new JMenu("Event");
		mnNewMenu_1.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Budget_Planner.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 63, 69, 28);
		Budget_Planner.add(lblNewLabel);
		
		textfieldname = new JTextField();
		textfieldname.setBounds(77, 63, 379, 30);
		Budget_Planner.add(textfieldname);
		textfieldname.setColumns(10);
		
		JLabel lblGroupName = new JLabel("Group Name:");
		lblGroupName.setHorizontalAlignment(SwingConstants.CENTER);
		lblGroupName.setForeground(Color.WHITE);
		lblGroupName.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblGroupName.setBounds(36, 115, 116, 34);
		Budget_Planner.add(lblGroupName);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int validation = JOptionPane.showConfirmDialog(null, "Are you sure you want to save?", "Save",JOptionPane.YES_NO_OPTION);
				
				if (validation == 0){
					
					budget_plan_name = textfieldname.getText();
					
					if (comboBox_groupname.getSelectedIndex() == 0){
						groupid = 1;
					}
					else if (comboBox_groupname.getSelectedIndex() == 1){
						groupid = 2;
					}
	                else if (comboBox_groupname.getSelectedIndex() == 2){
	                	groupid = 3;
					}
	                else if (comboBox_groupname.getSelectedIndex() == 3){
	                	groupid = 4;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 4){
	                	groupid = 5; 
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 5){
	                	groupid = 6;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 6){
	                	groupid = 7;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 7){
	                	groupid = 8;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 8){
	                	groupid = 9;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 9){
	                	groupid = 10;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 10){
	                	groupid = 11;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 11){
	                	groupid = 12;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 12){
	                	groupid = 13;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 13){
	                	groupid = 14;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 14){
	                	groupid = 15;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 15){
	                	groupid = 16;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 16){
	                	groupid = 17;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 17){
	                	groupid = 18;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 18){
	                	groupid = 19;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 19){
	                	groupid = 20;
	                }
	                else if (comboBox_groupname.getSelectedIndex() == 20){
	                	groupid = 21;
	                }  
					
			
					budget = Double.parseDouble(textFieldbudget.getText());
					materialcost = Double.parseDouble(textFieldmaterials.getText());
					transportcost = Double.parseDouble(textFieldtransport.getText());
					logisticscost = Double.parseDouble(textFieldlogistics.getText());
					
					try {
						misccost = Double.parseDouble(textFieldmisc.getText());
					} catch (NumberFormatException e1){
						misccost = 0;
					}
					
					totalcost = materialcost + transportcost + logisticscost + misccost;
					
					if ( totalcost > budget ){
						JOptionPane.showMessageDialog(null,"Total cost cannot be greater than budget specified");
					}
					else{
						inputdata.add(groupid);
						inputdata.add(budget_plan_name);
						inputdata.add(budget);
						inputdata.add(materialcost);
						inputdata.add(transportcost);
						inputdata.add(logisticscost);
						inputdata.add(misccost);
						inputdata.add(totalcost);
										
						// call controller
						Budget_Plan_Controller c = new Budget_Plan_Controller();
						c.processbudgetplan(inputdata);
						
					    /*  Budget_Plan_Controller c = new Budget_Plan_Controller();
					      ArrayList<Object> conlayer = c.processbudgetplan(inputdata);
					    
					    // if can print parameter passing is successful 
					     
					     for (int i = 0; i < conlayer.size(); i++){
					    	System.out.println(conlayer.get(i)); 
					    } */
						
						JOptionPane.showMessageDialog(null, "Data Successfully inserted");
						
					}
					
				}
				else{
					// do nothing
				}
			
			}
			
		});
		btnNewButton.setFont(new Font("Agency FB", Font.PLAIN, 26));
		btnNewButton.setBounds(51, 437, 84, 40);
		Budget_Planner.add(btnNewButton);
		
		JLabel lblMaterials = new JLabel("Materials:");
		lblMaterials.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaterials.setForeground(Color.WHITE);
		lblMaterials.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblMaterials.setBounds(63, 177, 99, 28);
		Budget_Planner.add(lblMaterials);
		
		JLabel lblTransport = new JLabel("Logistics:");
		lblTransport.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransport.setForeground(Color.WHITE);
		lblTransport.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblTransport.setBounds(63, 242, 99, 31);
		Budget_Planner.add(lblTransport);
		
		JLabel lblTransport_1 = new JLabel("Transport:");
		lblTransport_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransport_1.setForeground(Color.WHITE);
		lblTransport_1.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblTransport_1.setBounds(63, 306, 90, 34);
		Budget_Planner.add(lblTransport_1);
		
		textFieldmaterials = new JTextField();
		textFieldmaterials.setColumns(10);
		textFieldmaterials.setBounds(156, 179, 75, 28);
		Budget_Planner.add(textFieldmaterials);
		
		textFieldlogistics = new JTextField();
		textFieldlogistics.setColumns(10);
		textFieldlogistics.setBounds(156, 246, 75, 28);
		Budget_Planner.add(textFieldlogistics);
		
		textFieldtransport = new JTextField();
		textFieldtransport.setColumns(10);
		textFieldtransport.setBounds(156, 311, 75, 28);
		Budget_Planner.add(textFieldtransport);
		
		textFieldmisc = new JTextField();
		textFieldmisc.setBounds(156, 376, 75, 28);
		Budget_Planner.add(textFieldmisc);
		textFieldmisc.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBackground(new Color(0, 0, 128));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		           textfieldname.setText("");
		           textFieldmaterials.setText("");
		           textFieldlogistics.setText("");
		           textFieldtransport.setText("");
		           textFieldmisc.setText("");
		           textFieldbudget.setText("");
		           comboBox_groupname.setSelectedIndex(-1);
		           
		           // clear everything before entering new entry to database
		           
		           groupid = 0;
		           budget_plan_name = "" ;
		           budget = 0.0 ;
		           materialcost = 0.0;
		           logisticscost = 0.0;
		           transportcost = 0.0;
		           misccost = 0.0;
		           totalcost = 0.0;
		           
		           inputdata.clear();
			}
		});
		btnClear.setFont(new Font("Agency FB", Font.PLAIN, 26));
		btnClear.setBounds(153, 437, 84, 40);
		Budget_Planner.add(btnClear);
		
		JLabel lblBudget = new JLabel("Budget:");
		lblBudget.setHorizontalAlignment(SwingConstants.CENTER);
		lblBudget.setForeground(Color.WHITE);
		lblBudget.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblBudget.setBounds(460, 57, 105, 40);
		Budget_Planner.add(lblBudget);
		
		textFieldbudget = new JTextField();
		textFieldbudget.setColumns(10);
		textFieldbudget.setBounds(548, 63, 90, 30);
		Budget_Planner.add(textFieldbudget);
		
	    comboBox_groupname = new JComboBox<String>();
		comboBox_groupname.setBounds(149, 123, 149, 22);
		Budget_Planner.add(comboBox_groupname);
		
		JLabel lblMiscellaneous = new JLabel("Miscellaneous:");
		lblMiscellaneous.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiscellaneous.setForeground(Color.WHITE);
		lblMiscellaneous.setFont(new Font("Agency FB", Font.PLAIN, 26));
		lblMiscellaneous.setBounds(29, 376, 126, 28);
		Budget_Planner.add(lblMiscellaneous);
		
		JLabel lblMiscellaneousReferTo = new JLabel("*Miscellaneous refer to other cost areas such as services and training cost of personnel.");
		lblMiscellaneousReferTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiscellaneousReferTo.setForeground(Color.RED);
		lblMiscellaneousReferTo.setFont(new Font("Agency FB", Font.PLAIN, 22));
		lblMiscellaneousReferTo.setBounds(16, 563, 619, 28);
		Budget_Planner.add(lblMiscellaneousReferTo);
		
		JButton btnViewBudgetPlans = new JButton("View Budget Plans");
		btnViewBudgetPlans.setForeground(new Color(255, 255, 255));
		btnViewBudgetPlans.setBackground(new Color(0, 0, 128));
		btnViewBudgetPlans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Display_Budget_Plan_Boundary d = new Display_Budget_Plan_Boundary();
				d.setVisible(true);
			}
		});
		btnViewBudgetPlans.setFont(new Font("Agency FB", Font.PLAIN, 26));
		btnViewBudgetPlans.setBounds(51, 501, 186, 49);
		Budget_Planner.add(btnViewBudgetPlans);
		
		
		///////////////////////////////////////////////////////// ROUTINE SCHEDULE /////////////////////////////////////////////////
		
		
		JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setBounds(0, 0, 772, 50);
		Routine_Schedule.add(menuBar1);
		
		JMenu mnNewMenu1 = new JMenu("Budget Planner");
		mnNewMenu1.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Budget_Planner.setVisible(true);
			}
		});
		mnNewMenu1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu1);
		
		JMenu mnNewMenu_11 = new JMenu("Routine Schedule");
		mnNewMenu_11.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_11);
		
		JMenu mnNewMenu_21 = new JMenu("Task Administrator");
		mnNewMenu_21.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_21.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_21);
		
		JMenu mnNewMenu_31 = new JMenu("Personnel Administrator");
		mnNewMenu_31.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_31.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_31);
		
		JMenu mnNewMenu_41 = new JMenu("Event");
		mnNewMenu_41.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_41.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_41);
		
		JLabel lblTaskNameLimited = new JLabel("Task name limited to 20 characters");
		lblTaskNameLimited.setBounds(28, 61, 353, 40);
		lblTaskNameLimited.setForeground(Color.WHITE);
		lblTaskNameLimited.setFont(new Font("Agency FB", Font.BOLD, 30));
		Routine_Schedule.add(lblTaskNameLimited);
		
		JLabel label_1 = new JLabel("8am-9am");
		label_1.setBounds(78, 111, 133, 40);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_1);
		
		JLabel label_2 = new JLabel("9am-10am");
		label_2.setBounds(78, 161, 133, 40);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_2);
		
		JLabel label_3 = new JLabel("10am-11am");
		label_3.setBounds(78, 211, 133, 40);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_3);
		
		JLabel label_4 = new JLabel("11am-12pm");
		label_4.setBounds(78, 261, 133, 40);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_4);
		
		JLabel label_5 = new JLabel("12pm-1pm");
		label_5.setBounds(78, 311, 133, 40);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_5);
		
		JLabel label_6 = new JLabel("1pm-2pm");
		label_6.setBounds(78, 361, 133, 40);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_6);
		
		JLabel label_7 = new JLabel("2pm-3pm");
		label_7.setBounds(78, 411, 133, 40);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_7);
		
		JLabel label_8 = new JLabel("3pm-4pm");
		label_8.setBounds(78, 461, 133, 40);
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_8);
		
		textField1 = new JTextField(15);
		textField1.setBounds(186, 117, 146, 26);
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField1);
		
		textField2 = new JTextField(15);
		textField2.setBounds(186, 167, 146, 26);
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField2);
		
		textField3 = new JTextField(15);
		textField3.setBounds(186, 217, 146, 26);
		textField3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField3);
		
		textField4 = new JTextField(15);
		textField4.setBounds(186, 267, 146, 26);
		textField4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField4);
		
		textField5 = new JTextField(15);
		textField5.setBounds(186, 317, 146, 26);
		textField5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField5);
		
		textField6 = new JTextField(15);
		textField6.setBounds(186, 367, 146, 26);
		textField6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField6);
		
		textField7 = new JTextField(15);
		textField7.setBounds(186, 417, 146, 26);
		textField7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField7);
		
		textField8 = new JTextField(15);
		textField8.setBounds(186, 467, 146, 26);
		textField8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField8);
		
		//Buttons
		
		JButton btnClear1 = new JButton("Clear");
		btnClear1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				textField5.setText("");
				textField6.setText("");
				textField7.setText("");
				textField8.setText("");				
			}
		});
		btnClear1.setBounds(78, 542, 82, 37);
		btnClear1.setForeground(Color.WHITE);
		btnClear1.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnClear1.setBackground(new Color(0, 0, 128));
		Routine_Schedule.add(btnClear1);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hour1=textField1.getText();
				hour2=textField2.getText();
				hour3=textField3.getText();
				hour4=textField4.getText();
				hour5=textField5.getText();
				hour6=textField6.getText();
				hour7=textField7.getText();
				hour8=textField8.getText();
				
				inputdata1[0]=hour1;
				inputdata1[1]=hour2;
				inputdata1[2]=hour3;
				inputdata1[3]=hour4;
				inputdata1[4]=hour5;
				inputdata1[5]=hour6;
				inputdata1[6]=hour7;
				inputdata1[7]=hour8;

				routineControl c = new routineControl();
				c.processSchedule(inputdata1);
				
				
			     String[] conlayer = c.processSchedule(inputdata1);
			    
				  
			     for (int i = 0; i < conlayer.length; i++){
			    	System.out.println(conlayer[i]); 
			    } 
			     
			}
		});
		btnSave.setBounds(270, 541, 96, 39);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnSave.setBackground(new Color(0, 0, 128));
		Routine_Schedule.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// prevent rows from duplicating when clicking Load Table start at the 2nd time
				try{
					 
					Connection connection = sqliteconnection.dbConnector();
					String  query="Select * FROM RoutineSchedule";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table1.setModel(DbUtils.resultSetToTableModel(rs));

					routineControl c = new routineControl();
				    ResultSet totable = c.getSchedule();
				 
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
		    }
				
		});
		btnLoad.setBounds(645, 542, 96, 39);
		btnLoad.setForeground(Color.WHITE);
		btnLoad.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnLoad.setBackground(new Color(0, 0, 128));
		Routine_Schedule.add(btnLoad);
		
		//table-------------------

		
	    JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(410, 77, 331, 274);
		Routine_Schedule.add(scrollPane);
		
		table1 = new JTable();
		scrollPane.setColumnHeaderView(table1);
		
		
		////////////////////////////////////////////////// TASK ADMINISTRATOR //////////////////////////////////////////////////////

		
		JMenuBar menuBar11 = new JMenuBar();
		menuBar11.setBounds(0, 0, 772, 50);
		Task_Administrator.add(menuBar11);
		
		JMenu mnNewMenu11 = new JMenu("Budget Planner");
		mnNewMenu11.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
				Budget_Planner.setVisible(true);
			}
		});
		mnNewMenu11.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu11);
		
		JMenu mnNewMenu_111 = new JMenu("Routine Schedule");
		mnNewMenu_111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
				Routine_Schedule.setVisible(true);
			}
		});
		mnNewMenu_111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_111);
		
		JMenu mnNewMenu_211 = new JMenu("Task Administrator");
		mnNewMenu_211.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_211);
		
		JMenu mnNewMenu_311 = new JMenu("Personnel Administrator");
		mnNewMenu_311.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_311.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_311);
		
		JMenu mnNewMenu_5 = new JMenu("Event");
		mnNewMenu_5.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_5);
		
		JMenu mnNewMenu_411 = new JMenu("New menu");
		mnNewMenu_411.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		scrollPane11 = new JScrollPane();
		scrollPane11.setBounds(34, 114, 566, 477);
		Task_Administrator.add(scrollPane11);
		
		table = new JTable();
		scrollPane11.setViewportView(table);
		try {
			table.setModel(controller.setTableModel());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		btNewTask = new JButton("Add");
		btNewTask.setBackground(new Color(0, 0, 128));
		btNewTask.setForeground(new Color(255, 255, 255));
		btNewTask.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				input=new taskInputBoundary();
				input.setVisible(true);	
			}
		});
		btNewTask.setBounds(647, 166, 113, 31);
		Task_Administrator.add(btNewTask);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(255, 255, 255));
		btnRefresh.setBackground(new Color(0, 0, 128));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		btnRefresh.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnRefresh.setBounds(647, 124, 113, 31);
		Task_Administrator.add(btnRefresh);
		
		btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(255, 255, 255));
		btnEdit.setBackground(new Color(0, 0, 128));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskInputBoundary edit=new taskInputBoundary();
				edit=setInput(selectedItem());
				edit.editMode();
				edit.setVisible(true);
				
			}
		});
		btnEdit.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnEdit.setBounds(647, 212, 113, 31);
		Task_Administrator.add(btnEdit);
		
		btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(0, 0, 128));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=(int) table.getValueAt(table.getSelectedRow(), 0);
				 int n = JOptionPane.showOptionDialog(new JFrame(),"Confirm to delete task with ID = "+id+"?", 
						 "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
					        null, new Object[] {"Yes", "No"}, JOptionPane.YES_OPTION);
				 if (n == JOptionPane.YES_OPTION){
					 try {
						controller.deleteTask(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 refreshTable();
			        }
			}
		});
		btnRemove.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnRemove.setBounds(647, 260, 113, 31);
		Task_Administrator.add(btnRemove);
		
		btnDetails = new JButton("Details");
		btnDetails.setForeground(new Color(255, 255, 255));
		btnDetails.setBackground(new Color(0, 0, 128));
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taskInputBoundary edit=new taskInputBoundary();
				edit=setInput(selectedItem());
				edit.detailsMode();
				edit.setVisible(true);
			}
		});
		btnDetails.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnDetails.setBounds(647, 302, 113, 31);
		Task_Administrator.add(btnDetails);
		
		btnSort = new JButton("Sort");
		btnSort.setForeground(new Color(255, 255, 255));
		btnSort.setBackground(new Color(0, 0, 128));
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String column=(String) comboSort.getSelectedItem();
				if(checkDesc.isSelected()==true) {
					column+=" desc";
				}
				try {
					table.setModel(controller.setSortedModel(column));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSort.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnSort.setBounds(647, 513, 113, 31);
		Task_Administrator.add(btnSort);
		
		comboSort = new JComboBox();
		comboSort.setModel(new DefaultComboBoxModel(new String[] {"taskId", "taskName", "description", "taskType", "groupId", "adminNo", "startDate", "endDate"}));
		comboSort.setFont(new Font("Agency FB", Font.PLAIN, 24));
		comboSort.setBounds(645, 445, 115, 31);
		Task_Administrator.add(comboSort);
		
		lblSortBy = new JLabel("Sort by");
		lblSortBy.setForeground(Color.WHITE);
		lblSortBy.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblSortBy.setBounds(647, 410, 97, 34);
		Task_Administrator.add(lblSortBy);
		
		checkDesc = new JCheckBox("Descending");
		checkDesc.setBackground(new Color(100, 149, 237));
		checkDesc.setForeground(new Color(255, 255, 255));
		checkDesc.setFont(new Font("Agency FB", Font.PLAIN, 21));
		checkDesc.setBounds(647, 483, 97, 23);
		Task_Administrator.add(checkDesc);
		
		lblSearchBy = new JLabel("Search by");
		lblSearchBy.setForeground(Color.WHITE);
		lblSearchBy.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblSearchBy.setBounds(60, 63, 97, 34);
		Task_Administrator.add(lblSearchBy);
		
		comboSearch = new JComboBox();
		comboSearch.setModel(new DefaultComboBoxModel(new String[] {"Group ID", "AdminNo"}));
		comboSearch.setFont(new Font("Agency FB", Font.PLAIN, 24));
		comboSearch.setBounds(146, 65, 115, 31);
		Task_Administrator.add(comboSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Agency FB", Font.PLAIN, 20));
		txtSearch.setColumns(10);
		txtSearch.setBounds(284, 63, 128, 31);
		Task_Administrator.add(txtSearch);
		
		btnSearch = new JButton("Search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(0, 0, 128));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected=comboSearch.getSelectedIndex();
				String condition=null;
				try {
					condition=txtSearch.getText();
				}
				catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Please specify what to search","Error", JOptionPane.ERROR_MESSAGE);
					txtSearch.setText("");
					txtSearch.requestFocus();
				}
				
				if(selected==0) {
					try {
						table.setModel(controller.searchGroup(Integer.parseInt(condition)));
					}
					catch(NumberFormatException | SQLException e1){
						JOptionPane.showMessageDialog(null, "Group Id only contains numbers","Error", JOptionPane.ERROR_MESSAGE);
						txtSearch.setText("");
						txtSearch.requestFocus();
					}
				}
				else if(selected==1) {
					try {
						table.setModel(controller.searchAdminNo(condition));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch.setFont(new Font("Agency FB", Font.PLAIN, 24));
		btnSearch.setBounds(422, 63, 113, 31);
		Task_Administrator.add(btnSearch);
		
		
        ////////////////////////////////////////////////// PERSONNEL ADMINISTRATOR //////////////////////////////////////////////////////
		
		
		JMenuBar menuBar111 = new JMenuBar();
		menuBar111.setBounds(0, 0, 772, 50);
		Personnel_Administrator.add(menuBar111);
		
		JMenu mnNewMenu111 = new JMenu("Budget Planner");
		mnNewMenu111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Budget_Planner.setVisible(true);
			}
		});
		mnNewMenu111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu111);
		
		JMenu mnNewMenu_1111 = new JMenu("Routine Schedule");
		mnNewMenu_1111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Routine_Schedule.setVisible(false);
			}
		});
		mnNewMenu_1111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_1111);
		
		JMenu mnNewMenu_2111 = new JMenu("Task Administrator");
		mnNewMenu_2111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_2111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_2111);
		
		JMenu mnNewMenu_3111 = new JMenu("Personnel Administrator");
		mnNewMenu_3111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_3111);
		
		JMenu mnNewMenu_51 = new JMenu("Event");
		mnNewMenu_51.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_51.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_51);
		
		JMenu mnNewMenu_4111 = new JMenu("New menu");
		mnNewMenu_4111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblMainTitle = new JLabel("Group Creation & Deletion");
		lblMainTitle.setForeground(Color.WHITE);
		lblMainTitle.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblMainTitle.setBounds(23, 59, 250, 40);
		Personnel_Administrator.add(lblMainTitle);
		
		JLabel lblMainDesc = new JLabel("Create and Delete Community Groups");
		lblMainDesc.setForeground(Color.WHITE);
		lblMainDesc.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblMainDesc.setBounds(23, 99, 300, 40);
		Personnel_Administrator.add(lblMainDesc);
		
		JLabel lblGroupId = new JLabel("Group ID");
		lblGroupId.setForeground(Color.WHITE);
		lblGroupId.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblGroupId.setBounds(23, 149, 150, 50);
		Personnel_Administrator.add(lblGroupId);
		
		JLabel lblGroupName1 = new JLabel("Group Name\r\n");
		lblGroupName1.setForeground(Color.WHITE);
		lblGroupName1.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblGroupName1.setBounds(23, 249, 150, 50);
		Personnel_Administrator.add(lblGroupName1);
		
		txtFGroupId = new JTextField();
		txtFGroupId.setForeground(new Color(0, 0, 128));
		txtFGroupId.setFont(new Font("Agency FB", Font.PLAIN, 25));
		txtFGroupId.setBounds(23, 199, 100, 50);
		Personnel_Administrator.add(txtFGroupId);
		txtFGroupId.setColumns(10);
		
		txtFGroupName = new JTextField();
		txtFGroupName.setForeground(new Color(0, 0, 128));
		txtFGroupName.setFont(new Font("Agency FB", Font.PLAIN, 25));
		txtFGroupName.setColumns(10);
		txtFGroupName.setBounds(23, 299, 200, 50);
		Personnel_Administrator.add(txtFGroupName);
		
		JComboBox comboBGroupType = new JComboBox();
		comboBGroupType.setModel(new DefaultComboBoxModel(new String[] {"Project", "CCA"}));
		comboBGroupType.setMaximumRowCount(2);
		comboBGroupType.setForeground(new Color(0, 0, 128));
		comboBGroupType.setBackground(new Color(255, 255, 255));
		comboBGroupType.setFont(new Font("Agency FB", Font.PLAIN, 25));
		comboBGroupType.setBounds(148, 374, 150, 50);
		Personnel_Administrator.add(comboBGroupType);
		comboBGroupType.setSelectedIndex(0);
		
		JButton btnCreateGroup = new JButton("Create Group");
		btnCreateGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//Get text
		        groupName = txtFGroupName.getText();
		        groupType = comboBGroupType.getSelectedItem().toString();
		        
				//Check
				 if (txtFGroupName.getText().equals(null)){
					 JOptionPane.showMessageDialog(null,"Please fill in the Group Name");
				 }
					
		        else{
		        	    groupInfo.add(groupName);
				        groupInfo.add(groupType);
				        
				        GroupControl toControl = new GroupControl();
				        toControl.processGroupInfo(groupInfo);
				        
				      //Load Table
						/*try {
							String query = "select * from Community_Group";
							PreparedStatement pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							tableGroups.setModel(DbUtils.resultSetToTableModel(rs));
							
						} catch (Exception g) {
							g.printStackTrace();
						}
						*/
				        
				        //Clear fields
				        txtFGroupId.setText("");
						txtFGroupName.setText("");
						comboBGroupType.setSelectedIndex(0);
						
						groupId = "";
						groupName = "";
						groupType = "";
						
						groupInfo.clear();
				 }
		        };
				
		});
		
		btnCreateGroup.setBackground(new Color(0, 0, 128));
		btnCreateGroup.setForeground(Color.WHITE);
		btnCreateGroup.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnCreateGroup.setBounds(23, 449, 150, 50);
		Personnel_Administrator.add(btnCreateGroup);
		
		JButton btnToModify = new JButton("Manage Members\r\n");
		btnToModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personnel_Administrator.setVisible(false);
				Personnel_Administrator2.setVisible(true);
			}
		});
		btnToModify.setForeground(Color.WHITE);
		btnToModify.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnToModify.setBackground(new Color(0, 0, 128));
		btnToModify.setBounds(23, 524, 175, 50);
		Personnel_Administrator.add(btnToModify);
		
		JButton btnDeleteGroup = new JButton("Delete Group");
		btnDeleteGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Get text
				 groupId = txtFGroupId.getText();
				 
				 int validation = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete?","Deleting Group",JOptionPane.YES_NO_OPTION);
				 if (validation == 0){
					  groupDel.add(groupId);
				        
				        GroupControl toControl = new GroupControl();
				        toControl.processGroupDel(groupDel);
				        
				    	/*try {
							String query = "select * from Community_Group";
							PreparedStatement pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							tableGroups.setModel(DbUtils.resultSetToTableModel(rs));
				
					} catch (Exception e) {
						e.printStackTrace();
					}*/
				    	
				    	//Clear fields
				        txtFGroupId.setText("");
						
						groupId = "";
						
						groupDel.clear();
				 }
						
			}
			
		});
		btnDeleteGroup.setForeground(Color.WHITE);
		btnDeleteGroup.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnDeleteGroup.setBackground(new Color(0, 0, 128));
		btnDeleteGroup.setBounds(198, 449, 150, 50);
		Personnel_Administrator.add(btnDeleteGroup);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(398, 199, 350, 300);
		Personnel_Administrator.add(scrollPane1);
		
	    tableGroups = new JTable();
		DefaultTableModel groupsModel = new DefaultTableModel();
		//Set model
		Object[] columnsName2 = new Object[3];
        
		columnsName2[0] = "Group ID";
		columnsName2[1] = "Group Name";
		columnsName2[2] = "Group Type";
		       
		groupsModel.setColumnIdentifiers(columnsName2);
		
		tableGroups.setForeground(new Color(0, 0, 128));
		tableGroups.setFont(new Font("Agency FB", Font.BOLD, 15));
		scrollPane1.setViewportView(tableGroups);
		
		JButton btnLoadGroupTable = new JButton("Load All Groups");
		btnLoadGroupTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//Load Table
				try {
					groupsModel.setRowCount(0);
					
					 GroupControl toControl = new GroupControl();
					 ArrayList<GroupEntity> toTable = toControl.getData();
					 
					
					Object[] rowData = new Object[3];
				    for (int i = 0; i < toTable.size(); i++) {
								rowData[0] = (toTable.get(i).getGroupId());
								rowData[1] = (toTable.get(i).getGroupName());
								rowData[2] = (toTable.get(i).getGroupType());
								
								groupsModel.addRow(rowData);
								}	
					      
					tableGroups.setModel(groupsModel);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnLoadGroupTable.setForeground(Color.WHITE);
		btnLoadGroupTable.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnLoadGroupTable.setBackground(new Color(0, 0, 128));
		btnLoadGroupTable.setBounds(398, 524, 350, 50);
		Personnel_Administrator.add(btnLoadGroupTable);
		
		
		JLabel lblGroups = new JLabel("Groups :");
		lblGroups.setForeground(Color.WHITE);
		lblGroups.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblGroups.setBounds(398, 149, 150, 50);
		Personnel_Administrator.add(lblGroups);
		
		JLabel lblGroupType = new JLabel("Group Type\r\n");
		lblGroupType.setForeground(Color.WHITE);
		lblGroupType.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblGroupType.setBounds(23, 374, 150, 50);
		Personnel_Administrator.add(lblGroupType);
		Personnel_Administrator2.setLayout(null);
		
		
		/// Personnel_Administrator2 ///
		
		
		JMenuBar menuBar1111 = new JMenuBar();
		menuBar1111.setBounds(0, 0, 772, 50);
		Personnel_Administrator2.add(menuBar1111);
		
		JMenu mnNewMenu1111 = new JMenu("Budget Planner");
		mnNewMenu1111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Budget_Planner.setVisible(true);
			}
		});
		mnNewMenu1111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1111.add(mnNewMenu1111);
		
		JMenu mnNewMenu_11111 = new JMenu("Routine Schedule");
		mnNewMenu_11111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Routine_Schedule.setVisible(false);
			}
		});
		mnNewMenu_11111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1111.add(mnNewMenu_11111);
		
		JMenu mnNewMenu_21111 = new JMenu("Task Administrator");
		mnNewMenu_21111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_21111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1111.add(mnNewMenu_21111);
		
		JMenu mnNewMenu_31111 = new JMenu("Personnel Administrator");
		mnNewMenu_31111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1111.add(mnNewMenu_31111);
		
		JMenu mnNewMenu_511 = new JMenu("Event");
		mnNewMenu_511.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Personnel_Administrator.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_511.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1111.add(mnNewMenu_511);
		
		JMenu mnNewMenu_41111 = new JMenu("New menu");
		mnNewMenu_41111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblGroupModification = new JLabel("Group Modification");
		lblGroupModification.setForeground(Color.WHITE);
		lblGroupModification.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblGroupModification.setBounds(24, 57, 250, 40);
		Personnel_Administrator2.add(lblGroupModification);
		
		JLabel lblManageGroupMembers = new JLabel("Manage Group Members");
		lblManageGroupMembers.setForeground(Color.WHITE);
		lblManageGroupMembers.setFont(new Font("Agency FB", Font.BOLD, 25));
		lblManageGroupMembers.setBounds(24, 97, 300, 40);
		Personnel_Administrator2.add(lblManageGroupMembers);
		
		JLabel lblGroupID2 = new JLabel("Group ID");
		lblGroupID2.setForeground(Color.WHITE);
		lblGroupID2.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblGroupID2.setBounds(24, 147, 150, 50);
		Personnel_Administrator2.add(lblGroupID2);
		
		textFGroupID2 = new JTextField();
		textFGroupID2.setForeground(new Color(0, 0, 128));
		textFGroupID2.setFont(new Font("Agency FB", Font.PLAIN, 25));
		textFGroupID2.setColumns(10);
		textFGroupID2.setBounds(124, 147, 100, 50);
		Personnel_Administrator2.add(textFGroupID2);
		
		JLabel lblStudentName = new JLabel("Student Name\r\n");
		lblStudentName.setForeground(Color.WHITE);
		lblStudentName.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblStudentName.setBounds(24, 272, 150, 50);
		Personnel_Administrator2.add(lblStudentName);
		
		textFStudName = new JTextField();
		textFStudName.setForeground(new Color(0, 0, 128));
		textFStudName.setFont(new Font("Agency FB", Font.PLAIN, 25));
		textFStudName.setColumns(10);
		textFStudName.setBounds(24, 322, 300, 50);
		Personnel_Administrator2.add(textFStudName);
		
		JLabel lblAdminNo = new JLabel("Admin No.\r\n");
		lblAdminNo.setForeground(Color.WHITE);
		lblAdminNo.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblAdminNo.setBounds(24, 222, 150, 50);
		Personnel_Administrator2.add(lblAdminNo);
		
		textFStudentAdmin = new JTextField();
		textFStudentAdmin.setForeground(new Color(0, 0, 128));
		textFStudentAdmin.setFont(new Font("Agency FB", Font.PLAIN, 25));
		textFStudentAdmin.setColumns(10);
		textFStudentAdmin.setBounds(124, 222, 175, 50);
		Personnel_Administrator2.add(textFStudentAdmin);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(399, 197, 350, 300);
		Personnel_Administrator2.add(scrollPane_1);
		
		tableMembers = new JTable();
		DefaultTableModel membersModel = new DefaultTableModel();
		//Set model
		Object[] columnsName = new Object[4];
        
		columnsName[0] = "Group ID";
		columnsName[1] = "Admin No";
		columnsName[2] = "Student";
		columnsName[3] = "Rank";
		
		membersModel.setColumnIdentifiers(columnsName);
		       
		tableMembers.setForeground(new Color(0, 0, 128));
		tableMembers.setFont(new Font("Agency FB", Font.BOLD, 15));
		scrollPane_1.setViewportView(tableMembers);
		
		JComboBox comboBRank = new JComboBox();
		comboBRank.setModel(new DefaultComboBoxModel(new String[] {"Member", "Leader"}));
		comboBRank.setForeground(new Color(0, 0, 128));
		comboBRank.setFont(new Font("Agency FB", Font.BOLD, 25));
		comboBRank.setBounds(124, 382, 150, 50);
		Personnel_Administrator2.add(comboBRank);
		comboBRank.setSelectedIndex(0);
		
		
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Get text
				groupId2 = textFGroupID2.getText();
				adminNo = textFStudentAdmin.getText();
		        studentName = textFStudName.getText();
		        rank = comboBRank.getSelectedItem().toString();
		        
		        if(textFGroupID2.getText().equals(null) || textFStudentAdmin.getText().equals(null) || textFStudName.getText().equals(null)){
					JOptionPane.showInternalMessageDialog(null, "Please input all fields.");
				}
					
				else {
		        
		        studInfo.add(groupId2);
		        studInfo.add(adminNo);
		        studInfo.add(studentName);
		        studInfo.add(rank);
		        
		        MemberControl toControl = new MemberControl();
		        toControl.processStudInfo(studInfo);
		        
		      //Load Table
		        /*
			try {
					String query = "select * from Student where groupId = " +textFGroupID2.getText() ;
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableMembers.setModel(DbUtils.resultSetToTableModel(rs));
					
				} catch (Exception g) {
					g.printStackTrace();
				}
		
		        */
		        //Clear fields
				textFStudentAdmin.setText("");
				textFStudName.setText("");
				comboBRank.setSelectedIndex(-1);
				
				groupId2 = "";
				adminNo = "";
		        studentName = "";
		        rank = "";
				
				studInfo.clear();
				}
			}
		});
		btnAddStudent.setForeground(Color.WHITE);
		btnAddStudent.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnAddStudent.setBackground(new Color(0, 0, 128));
		btnAddStudent.setBounds(24, 447, 150, 50);
		Personnel_Administrator2.add(btnAddStudent);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Get text
				 adminNo = textFStudentAdmin.getText();
				 
				//Check
				 int validation = JOptionPane.showConfirmDialog(null,"Are you sure you want to remove student?","Removing Member",JOptionPane.YES_NO_OPTION);
				 if (validation == 0){
					 studDel.add(adminNo);
				        
				        MemberControl toControl = new MemberControl();
				        toControl.processStudDel(studDel);
				        
				    	/*try {
							String query = "select * from Student";
							PreparedStatement pst = connection.prepareStatement(query);
							ResultSet rs = pst.executeQuery();
							tableGroups.setModel(DbUtils.resultSetToTableModel(rs));
				
					} catch (Exception e) {
						e.printStackTrace();
					}*/
						
						studDel.clear();
						
				 }
				
			}
			});
		
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnRemove.setBackground(new Color(0, 0, 128));
		btnRemove.setBounds(199, 447, 150, 50);
		Personnel_Administrator2.add(btnRemove);
		
		JButton btnLoadMemberTable = new JButton("Load Members By Group ID");
		btnLoadMemberTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Load Table
				try {
					
					groupId2 = textFGroupID2.getText();
					
					if(textFGroupID2.getText().equals(null)){
						JOptionPane.showInternalMessageDialog(null, "Please input the Group ID");
					}
						
					else {
					c = sqliteconnection.dbConnector();
					String query = "select groupId, adminNo, studName, rank from Student where groupId = '" +groupId2+ "' ";
					PreparedStatement pst = c.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
				    tableMembers.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
					
					
					//Attempt at 3-Tiering this
					/*
					//Get GroupID input
					groupId2 = textFGroupID2.getText();
					MemberEntity toEntity = new MemberEntity();
					toEntity.viewTable(groupId2);
					
					
					 MemberControl toControl = new MemberControl();
					 ArrayList<MemberEntity> toTable = toControl.getData();
					 
					
					Object[] rowData = new Object[4];
				    for (int i = 0; i < toTable.size(); i++) {
								rowData[0] = (toTable.get(i).getGroupId2());
								rowData[1] = (toTable.get(i).getAdminNo());
								rowData[2] = (toTable.get(i).getStudentName());
								rowData[3] = (toTable.get(i).getRank());
								
								membersModel.addRow(rowData);
								}	
					      
					tableMembers.setModel(membersModel);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				*/
			}
		});

		btnLoadMemberTable.setForeground(Color.WHITE);
		btnLoadMemberTable.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnLoadMemberTable.setBackground(new Color(0, 0, 128));
		btnLoadMemberTable.setBounds(399, 522, 350, 50);
		Personnel_Administrator2.add(btnLoadMemberTable);
		
		JButton btnCreateGroups = new JButton("Create Groups");
		btnCreateGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personnel_Administrator.setVisible(true);
				Personnel_Administrator2.setVisible(false);
			}
		});
		btnCreateGroups.setForeground(Color.WHITE);
		btnCreateGroups.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnCreateGroups.setBackground(new Color(0, 0, 128));
		btnCreateGroups.setBounds(24, 522, 175, 50);
		Personnel_Administrator2.add(btnCreateGroups);
		
		JLabel lblMembers = new JLabel("Members :");
		lblMembers.setForeground(Color.WHITE);
		lblMembers.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblMembers.setBounds(399, 147, 150, 50);
		Personnel_Administrator2.add(lblMembers);
		
		JLabel lblRank = new JLabel("Rank\r\n");
		lblRank.setForeground(Color.WHITE);
		lblRank.setFont(new Font("Agency FB", Font.BOLD, 30));
		lblRank.setBounds(24, 382, 150, 50);
		Personnel_Administrator2.add(lblRank);
		
	
        ////////////////////////////////////////////////// EVENT //////////////////////////////////////////////////////
		
		
		JMenuBar menuBar11111 = new JMenuBar();
		menuBar11111.setBounds(0, 0, 772, 50);
		Event.add(menuBar11111);
		
		JMenu mnNewMenu11111 = new JMenu("Budget Planner");
		mnNewMenu11111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Event.setVisible(false);
				Budget_Planner.setVisible(true);
			}
		});
		mnNewMenu11111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11111.add(mnNewMenu11111);
		
		JMenu mnNewMenu_111111 = new JMenu("Routine Schedule");
		mnNewMenu_111111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Event.setVisible(false);
				Routine_Schedule.setVisible(true);
			}
		});
		mnNewMenu_111111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11111.add(mnNewMenu_111111);
		
		JMenu mnNewMenu_211111 = new JMenu("Task Administrator");
		mnNewMenu_211111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Event.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_211111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11111.add(mnNewMenu_211111);
		
		JMenu mnNewMenu_311111 = new JMenu("Personnel Administrator");
		mnNewMenu_311111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Event.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_311111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11111.add(mnNewMenu_311111);
		
		JMenu mnNewMenu_5111 = new JMenu("Event");
		mnNewMenu_5111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11111.add(mnNewMenu_5111);
		

				java.util.Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				month = cal.get(Calendar.MONTH) + 1;
				year = cal.get(Calendar.YEAR);
				CalendarEntity ce = new CalendarEntity(month, year);
				EventCalendarControl ec = new EventCalendarControl(ce);

				//getStrMth method < control < Calendar entity
				String monthStr = ec.getStrMth();		
				String yearStr = ec.getStrYear();
				
				//passes month to label 
				JLabel lblMonthDisplay = new JLabel(monthStr);
				lblMonthDisplay.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblMonthDisplay.setForeground(Color.DARK_GRAY);
				lblMonthDisplay.setBounds(138, 93, 48, 30);
				Event.add(lblMonthDisplay);

				JLabel lblYear = new JLabel(yearStr);
				lblYear.setForeground(Color.DARK_GRAY);
				lblYear.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblYear.setBounds(180, 93, 60, 30);
				Event.add(lblYear);

				JLabel lblSun = new JLabel(" Su");
				lblSun.setOpaque(true);
				lblSun.setBackground(Color.RED);
				lblSun.setForeground(Color.WHITE);
				lblSun.setFont(new Font("Verdana", Font.BOLD, 18));
				lblSun.setBounds(26, 139, 38, 43);
				Event.add(lblSun);

				JLabel lblMon = new JLabel(" Mo");
				lblMon.setBackground(Color.GRAY);
				lblMon.setOpaque(true);
				lblMon.setForeground(Color.WHITE);
				lblMon.setFont(new Font("Verdana", Font.BOLD, 18));
				lblMon.setBounds(74, 139, 38, 43);
				Event.add(lblMon);

				JLabel lblTu = new JLabel(" Tu");
				lblTu.setBackground(Color.GRAY);
				lblTu.setOpaque(true);
				lblTu.setForeground(Color.WHITE);
				lblTu.setFont(new Font("Verdana", Font.BOLD, 18));
				lblTu.setBounds(122, 139, 38, 43);
				Event.add(lblTu);

				JLabel lblWe = new JLabel(" We");
				lblWe.setBackground(Color.GRAY);
				lblWe.setOpaque(true);
				lblWe.setForeground(Color.WHITE);
				lblWe.setFont(new Font("Verdana", Font.BOLD, 18));
				lblWe.setBounds(170, 139, 38, 43);
				Event.add(lblWe);

				JLabel lblTh = new JLabel(" Th");
				lblTh.setBackground(Color.GRAY);
				lblTh.setOpaque(true);
				lblTh.setForeground(Color.WHITE);
				lblTh.setFont(new Font("Verdana", Font.BOLD, 18));
				lblTh.setBounds(218, 139, 38, 43);
				Event.add(lblTh);

				JLabel lblFr = new JLabel(" Fr");
				lblFr.setBackground(Color.GRAY);
				lblFr.setOpaque(true);
				lblFr.setForeground(Color.WHITE);
				lblFr.setFont(new Font("Verdana", Font.BOLD, 18));
				lblFr.setBounds(266, 139, 38, 43);
				Event.add(lblFr);

				JLabel lblSa = new JLabel(" Sa");
				lblSa.setOpaque(true);
				lblSa.setBackground(Color.GRAY);
				lblSa.setForeground(Color.WHITE);
				lblSa.setFont(new Font("Verdana", Font.BOLD, 18));
				lblSa.setBounds(314, 139, 38, 43);
				Event.add(lblSa);

				JLabel lblDisplayEventTitle = new JLabel("");
				lblDisplayEventTitle.setFont(new Font("Agency FB", Font.PLAIN, 24));
				lblDisplayEventTitle.setForeground(Color.WHITE);
				lblDisplayEventTitle.setVisible(false);
				lblDisplayEventTitle.setBounds(488, 155, 261, 26);
				Event.add(lblDisplayEventTitle);

				JLabel lblDisplayDate = new JLabel("");
				lblDisplayDate.setFont(new Font("Agency FB", Font.PLAIN, 24));
				lblDisplayDate.setForeground(Color.WHITE);
				lblDisplayDate.setVisible(false);
				lblDisplayDate.setBounds(488, 216, 80, 23);
				Event.add(lblDisplayDate);

				JTextArea textAreaDisplayEventDetail = new JTextArea();
				textAreaDisplayEventDetail.setForeground(Color.WHITE);
				textAreaDisplayEventDetail.setFont(new Font("Agency FB", Font.PLAIN, 24));
				textAreaDisplayEventDetail.setBounds(488, 264, 261, 157);
				textAreaDisplayEventDetail.setWrapStyleWord(true);
				textAreaDisplayEventDetail.setLineWrap(true);
				textAreaDisplayEventDetail.setOpaque(false);
				textAreaDisplayEventDetail.setEditable(false);
				textAreaDisplayEventDetail.setFocusable(false);
				textAreaDisplayEventDetail.setBackground(UIManager.getColor("Label.background"));
				textAreaDisplayEventDetail.setBorder(UIManager.getBorder("Label.border"));
				textAreaDisplayEventDetail.setVisible(false);
				Event.add(textAreaDisplayEventDetail);

				JButton btnCreate = new JButton("Create");
				btnCreate.setForeground(new Color(255, 255, 255));
				btnCreate.setBackground(new Color(0, 0, 128));
				btnCreate.setEnabled(false);
				btnCreate.setFont(new Font("Agency FB", Font.PLAIN, 24));
				btnCreate.setBounds(488, 452, 116, 30);
				Event.add(btnCreate);

				JButton btnClearRS = new JButton("Clear");
				btnClearRS.setForeground(new Color(255, 255, 255));
				btnClearRS.setBackground(new Color(0, 0, 128));
				btnClearRS.setEnabled(false);
				btnClearRS.setFont(new Font("Agency FB", Font.PLAIN, 24));
				btnClearRS.setBounds(633, 452, 116, 30);
				Event.add(btnClearRS);

				JButton btnAddEvent = new JButton("Add");
				btnAddEvent.setForeground(new Color(255, 255, 255));
				btnAddEvent.setBackground(new Color(0, 0, 128));
				btnAddEvent.setFont(new Font("Agency FB", Font.PLAIN, 24));
				btnAddEvent.setBounds(150, 519, 89, 37);
				Event.add(btnAddEvent);

				String[] dateList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };

				JComboBox comboBoxDate = new JComboBox(dateList);
				comboBoxDate.setEnabled(false);
				comboBoxDate.setBounds(488, 216, 60, 23);
				Event.add(comboBoxDate);

				String[] monthList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
				JComboBox comboBoxMonth = new JComboBox(monthList);
				comboBoxMonth.setEnabled(false);
				comboBoxMonth.setBounds(568, 216, 60, 23);
				Event.add(comboBoxMonth);

				String[] yearList = { "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027",
						"2028", "2029", "2030" };
				JComboBox comboBoxYear = new JComboBox(yearList);
				comboBoxYear.setEnabled(false);
				comboBoxYear.setBounds(656, 216, 69, 23);
				Event.add(comboBoxYear);

			    titleTextField = new JTextField();
				titleTextField.setFont(new Font("Agency FB", Font.PLAIN, 24));
				titleTextField.setEnabled(false);
				titleTextField.setBounds(488, 155, 261, 26);
				Event.add(titleTextField);
				titleTextField.setColumns(10);

				JTextArea detailTextArea = new JTextArea();
				detailTextArea.setFont(new Font("Agency FB", Font.PLAIN, 24));
				detailTextArea.setEnabled(false);
				detailTextArea.setLineWrap(true);
				detailTextArea.setWrapStyleWord(true);
				detailTextArea.setBounds(488, 262, 261, 159);
				Event.add(detailTextArea);

				JLabel lbl1 = new JLabel("");
				lbl1.setBackground(Color.WHITE);
				lbl1.setBounds(74, 192, 38, 43);
				lbl1.setOpaque(true);
				lbl1.setVisible(false);

				lbl1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl1.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl1);

				JLabel lbl2 = new JLabel("");
				lbl2.setOpaque(true);
				lbl2.setBackground(Color.WHITE);
				lbl2.setBounds(122, 192, 38, 43);
				lbl2.setVisible(false);
				lbl2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl2.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl2);

				JLabel lbl3 = new JLabel("");
				lbl3.setOpaque(true);
				lbl3.setBackground(Color.WHITE);
				lbl3.setBounds(170, 192, 38, 43);
				lbl3.setVisible(false);
				lbl3.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl3.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl3);

				JLabel lbl4 = new JLabel("");
				lbl4.setOpaque(true);
				lbl4.setBackground(Color.WHITE);
				lbl4.setBounds(218, 192, 38, 43);
				lbl4.setVisible(false);
				lbl4.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl4.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl4);

				JLabel lbl5 = new JLabel("");
				lbl5.setOpaque(true);
				lbl5.setBackground(Color.WHITE);
				lbl5.setBounds(266, 192, 38, 43);
				lbl5.setVisible(false);
				lbl5.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl5.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl5);

				JLabel lbl6 = new JLabel("");
				lbl6.setOpaque(true);
				lbl6.setBackground(Color.WHITE);
				lbl6.setBounds(314, 192, 38, 43);
				lbl6.setVisible(false);
				lbl6.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl6.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl6);

				JLabel lbl7 = new JLabel("");
				lbl7.setOpaque(true);
				lbl7.setBackground(Color.WHITE);
				lbl7.setBounds(26, 192, 38, 43);
				lbl7.setVisible(false);
				lbl7.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl7.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl7);

				JLabel lbl8 = new JLabel("");
				lbl8.setOpaque(true);
				lbl8.setBackground(Color.WHITE);
				lbl8.setBounds(74, 246, 38, 43);
				lbl8.setVisible(false);
				lbl8.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl8.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl8);

				JLabel lbl9 = new JLabel("");
				lbl9.setOpaque(true);
				lbl9.setBackground(Color.WHITE);
				lbl9.setBounds(122, 246, 38, 43);
				lbl9.setVisible(false);
				lbl9.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl9.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl9);

				JLabel lbl10 = new JLabel("");
				lbl10.setOpaque(true);
				lbl10.setBackground(Color.WHITE);
				lbl10.setBounds(170, 246, 38, 43);
				lbl10.setVisible(false);
				lbl10.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl10.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl10);

				JLabel lbl11 = new JLabel("");
				lbl11.setOpaque(true);
				lbl11.setBackground(Color.WHITE);
				lbl11.setBounds(218, 246, 38, 43);
				lbl11.setVisible(false);
				lbl11.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl11.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl11);

				JLabel lbl12 = new JLabel("");
				lbl12.setOpaque(true);
				lbl12.setBackground(Color.WHITE);
				lbl12.setBounds(266, 246, 38, 43);
				lbl12.setVisible(false);
				lbl12.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl12.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl12);

				JLabel lbl13 = new JLabel("");
				lbl13.setOpaque(true);
				lbl13.setBackground(Color.WHITE);
				lbl13.setBounds(314, 246, 38, 43);
				lbl13.setVisible(false);
				lbl13.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl13.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl13);

				JLabel lbl14 = new JLabel("");
				lbl14.setOpaque(true);
				lbl14.setBackground(Color.WHITE);
				lbl14.setBounds(26, 246, 38, 43);
				lbl14.setVisible(false);
				lbl14.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl14.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl14);

				JLabel lbl15 = new JLabel("");
				lbl15.setOpaque(true);
				lbl15.setBackground(Color.WHITE);
				lbl15.setBounds(74, 300, 38, 43);
				lbl15.setVisible(false);
				lbl15.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl15.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}
					}

				});
				Event.add(lbl15);

				JLabel lbl16 = new JLabel("");
				lbl16.setOpaque(true);
				lbl16.setBackground(Color.WHITE);
				lbl16.setBounds(122, 300, 38, 43);
				lbl16.setVisible(false);
				lbl16.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl16.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}
				});
				Event.add(lbl16);

				JLabel lbl17 = new JLabel("");
				lbl17.setOpaque(true);
				lbl17.setBackground(Color.WHITE);
				lbl17.setBounds(170, 300, 38, 43);
				lbl17.setVisible(false);
				lbl17.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl17.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl17);

				JLabel lbl18 = new JLabel("");
				lbl18.setOpaque(true);
				lbl18.setBackground(Color.WHITE);
				lbl18.setBounds(218, 300, 38, 43);
				lbl18.setVisible(false);
				lbl18.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl18.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl18);

				JLabel lbl19 = new JLabel("");
				lbl19.setOpaque(true);
				lbl19.setBackground(Color.WHITE);
				lbl19.setBounds(266, 300, 38, 43);
				lbl19.setVisible(false);
				lbl19.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl19.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl19);

				JLabel lbl20 = new JLabel("");
				lbl20.setOpaque(true);
				lbl20.setBackground(Color.WHITE);
				lbl20.setBounds(314, 300, 38, 43);
				lbl20.setVisible(false);
				lbl20.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl20.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl20);

				JLabel lbl21 = new JLabel("");
				lbl21.setOpaque(true);
				lbl21.setBackground(Color.WHITE);
				lbl21.setBounds(26, 300, 38, 43);
				lbl21.setVisible(false);
				lbl21.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl21.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl21);

				JLabel lbl22 = new JLabel("");
				lbl22.setOpaque(true);
				lbl22.setBackground(Color.WHITE);
				lbl22.setBounds(74, 354, 38, 43);
				lbl22.setVisible(false);
				lbl22.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl22.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl22);

				JLabel lbl23 = new JLabel("");
				lbl23.setOpaque(true);
				lbl23.setBackground(Color.WHITE);
				lbl23.setBounds(122, 354, 38, 43);
				lbl23.setVisible(false);
				lbl23.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl23.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl23);

				JLabel lbl24 = new JLabel("");
				lbl24.setOpaque(true);
				lbl24.setBackground(Color.WHITE);
				lbl24.setBounds(170, 354, 38, 43);
				lbl24.setVisible(false);
				lbl24.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl24.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl24);

				JLabel lbl25 = new JLabel("");
				lbl25.setOpaque(true);
				lbl25.setBackground(Color.WHITE);
				lbl25.setBounds(218, 354, 38, 43);
				lbl25.setVisible(false);
				lbl25.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl25.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl25);

				JLabel lbl26 = new JLabel("");
				lbl26.setOpaque(true);
				lbl26.setBackground(Color.WHITE);
				lbl26.setBounds(266, 354, 38, 43);
				lbl26.setVisible(false);
				lbl26.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl26.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl26);

				JLabel lbl27 = new JLabel("");
				lbl27.setOpaque(true);
				lbl27.setBackground(Color.WHITE);
				lbl27.setBounds(314, 354, 38, 43);
				lbl27.setVisible(false);
				lbl27.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl27.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl27);

				JLabel lbl28 = new JLabel("");
				lbl28.setOpaque(true);
				lbl28.setBackground(Color.WHITE);
				lbl28.setBounds(26, 354, 38, 43);
				lbl28.setVisible(false);
				lbl28.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl28.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl28);

				JLabel lbl29 = new JLabel("");
				lbl29.setOpaque(true);
				lbl29.setBackground(Color.WHITE);
				lbl29.setBounds(74, 408, 38, 43);
				lbl29.setVisible(false);
				lbl29.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl29.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl29);

				JLabel lbl30 = new JLabel("");
				lbl30.setOpaque(true);
				lbl30.setBackground(Color.WHITE);
				lbl30.setBounds(122, 408, 38, 43);
				lbl30.setVisible(false);
				lbl30.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl30.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl30);

				JLabel lbl31 = new JLabel("");
				lbl31.setOpaque(true);
				lbl31.setBackground(Color.WHITE);
				lbl31.setBounds(170, 408, 38, 43);
				lbl31.setVisible(false);
				lbl31.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl31.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl31);

				JLabel lbl32 = new JLabel("");
				lbl32.setOpaque(true);
				lbl32.setBackground(Color.WHITE);
				lbl32.setBounds(218, 408, 38, 43);
				lbl32.setVisible(false);
				lbl32.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl32.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl32);

				JLabel lbl33 = new JLabel("");
				lbl33.setOpaque(true);
				lbl33.setBackground(Color.WHITE);
				lbl33.setBounds(266, 408, 38, 43);
				lbl33.setVisible(false);
				lbl33.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl33.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl33);

				JLabel lbl34 = new JLabel("");
				lbl34.setOpaque(true);
				lbl34.setBackground(Color.WHITE);
				lbl34.setBounds(314, 408, 38, 43);
				lbl34.setVisible(false);
				lbl34.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl34.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl34);

				JLabel lbl35 = new JLabel("");
				lbl35.setOpaque(true);
				lbl35.setBackground(Color.WHITE);
				lbl35.setBounds(26, 408, 38, 43);
				lbl35.setVisible(false);
				lbl35.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl35.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl35);

				JLabel lbl36 = new JLabel("");
				lbl36.setOpaque(true);
				lbl36.setBackground(Color.WHITE);
				lbl36.setBounds(26, 462, 38, 43);
				lbl36.setVisible(false);
				lbl36.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl36.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl36);

				JLabel lbl37 = new JLabel("");
				lbl37.setOpaque(true);
				lbl37.setBackground(Color.WHITE);
				lbl37.setBounds(74, 462, 38, 43);
				lbl37.setVisible(false);
				lbl37.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						EventCalendarControl n = new EventCalendarControl();
						EventEntity eventEntity = new EventEntity();
						eventEntity.setEventDate(lbl37.getText() + "-" + month + "-" + year);
						n.setNewEvent(eventEntity);
						n.getNewEvent().retrievedEvent();

						if (n.getNewEvent().getEventTitle() == null || n.getNewEvent().getEventDetails() == null) {
							JOptionPane.showMessageDialog(null, "There is no event on this date");
						}

						else {
							titleTextField.setVisible(false);
							comboBoxDate.setVisible(false);
							comboBoxMonth.setVisible(false);
							comboBoxYear.setVisible(false);
							detailTextArea.setVisible(false);
							btnCreate.setVisible(false);
							btnClear1.setVisible(false);
							lblDisplayEventTitle.setVisible(true);
							lblDisplayEventTitle.setText(n.getNewEvent().getEventTitle());
							lblDisplayDate.setVisible(true);
							lblDisplayDate.setText(eventEntity.getEventDate());
							textAreaDisplayEventDetail.setVisible(true);
							textAreaDisplayEventDetail.setText(n.getNewEvent().getEventDetails());
							btnAddEvent.setEnabled(true);

						}

					}

				});
				Event.add(lbl37);

				JButton btnDecreaseMth = new JButton("<");
				btnDecreaseMth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (month == 1) {
							month = 12;
							year--;
						} else {
							month -= 1;
						}

						for (int k = 0; k < 37; k++) {
							allLabels.get(k).setVisible(false);
						}
						ce.setMonth(month);
						ce.setYear(year);
						ec.setCe(ce);
						String monthStr = ec.getStrMth();
						String yearStr = ec.getStrYear();

						lblMonthDisplay.setText(monthStr);
						lblYear.setText(yearStr);

						setCalendarDate(ec, ce);

					}
				});
				btnDecreaseMth.setFocusPainted(false);
				btnDecreaseMth.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnDecreaseMth.setBounds(82, 94, 48, 26);
				Event.add(btnDecreaseMth);

				JButton btnDecreaseYear = new JButton("<<");
				btnDecreaseYear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						year--;

						for (int k = 0; k < 37; k++) {
							allLabels.get(k).setVisible(false);
						}
						ce.setMonth(month);
						ce.setYear(year);
						ec.setCe(ce);
						String monthStr = ec.getStrMth();
						String yearStr = ec.getStrYear();

						lblMonthDisplay.setText(monthStr);
						lblYear.setText(yearStr);

						setCalendarDate(ec, ce);
					}
				});
				btnDecreaseYear.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnDecreaseYear.setFocusPainted(false);
				btnDecreaseYear.setBounds(26, 94, 50, 26);
				Event.add(btnDecreaseYear);

				JButton btnIncreaseYear = new JButton(">>");
				btnIncreaseYear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						year++;

						for (int k = 0; k < 37; k++) {
							allLabels.get(k).setVisible(false);
						}
						ce.setMonth(month);
						ce.setYear(year);
						ec.setCe(ce);
						String monthStr = ec.getStrMth();
						String yearStr = ec.getStrYear();

						lblMonthDisplay.setText(monthStr);
						lblYear.setText(yearStr);

						setCalendarDate(ec, ce);
					}
				});
				btnIncreaseYear.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnIncreaseYear.setFocusPainted(false);
				btnIncreaseYear.setBounds(302, 94, 50, 26);
				Event.add(btnIncreaseYear);

				JButton btnIncreaseMth = new JButton(">");
				btnIncreaseMth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (month == 12) {
							month = 1;
							year++;
						} else {
							month += 1;
						}

						for (int k = 0; k < 37; k++) {
							allLabels.get(k).setVisible(false);
						}
						ce.setMonth(month);
						ce.setYear(year);
						ec.setCe(ce);
						String monthStr = ec.getStrMth();
						String yearStr = ec.getStrYear();

						lblMonthDisplay.setText(monthStr);
						lblYear.setText(yearStr);

						setCalendarDate(ec, ce);

					}
				});
				btnIncreaseMth.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnIncreaseMth.setFocusPainted(false);
				btnIncreaseMth.setBounds(243, 94, 48, 26);
				Event.add(btnIncreaseMth);

				allLabels.add(lbl7);
				allLabels.add(lbl1);
				allLabels.add(lbl2);
				allLabels.add(lbl3);
				allLabels.add(lbl4);
				allLabels.add(lbl5);
				allLabels.add(lbl6);
				allLabels.add(lbl14);
				allLabels.add(lbl8);
				allLabels.add(lbl9);
				allLabels.add(lbl10);
				allLabels.add(lbl11);
				allLabels.add(lbl12);
				allLabels.add(lbl13);
				allLabels.add(lbl21);
				allLabels.add(lbl15);
				allLabels.add(lbl16);
				allLabels.add(lbl17);
				allLabels.add(lbl18);
				allLabels.add(lbl19);
				allLabels.add(lbl20);
				allLabels.add(lbl28);
				allLabels.add(lbl22);
				allLabels.add(lbl23);
				allLabels.add(lbl24);
				allLabels.add(lbl25);
				allLabels.add(lbl26);
				allLabels.add(lbl27);
				allLabels.add(lbl35);
				allLabels.add(lbl29);
				allLabels.add(lbl30);
				allLabels.add(lbl31);
				allLabels.add(lbl32);
				allLabels.add(lbl33);
				allLabels.add(lbl34);
				allLabels.add(lbl36);
				allLabels.add(lbl37);
				
						JLabel lblEvent = new JLabel("Event");
						lblEvent.setForeground(Color.WHITE);
						lblEvent.setEnabled(false);
						lblEvent.setFont(new Font("Agency FB", Font.PLAIN, 30));
						lblEvent.setBounds(407, 80, 69, 57);
						Event.add(lblEvent);

				JLabel lblTitle = new JLabel("Title");
				lblTitle.setForeground(Color.WHITE);
				lblTitle.setEnabled(false);
				lblTitle.setFont(new Font("Agency FB", Font.PLAIN, 26));
				lblTitle.setBounds(407, 153, 48, 30);
				Event.add(lblTitle);

				JLabel lblDetails = new JLabel("Details");
				lblDetails.setForeground(Color.WHITE);
				lblDetails.setEnabled(false);
				lblDetails.setFont(new Font("Agency FB", Font.PLAIN, 26));
				lblDetails.setBounds(397, 257, 80, 30);
				Event.add(lblDetails);

				btnClear1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						titleTextField.setText(null);
						detailTextArea.setText(null);
					}
				});

				JLabel lblDate = new JLabel("Date");
				lblDate.setForeground(Color.WHITE);
				lblDate.setEnabled(false);
				lblDate.setFont(new Font("Agency FB", Font.PLAIN, 26));
				lblDate.setBounds(407, 209, 80, 30);
				Event.add(lblDate);	

				btnAddEvent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						lblEvent.setEnabled(true);
						lblTitle.setEnabled(true);
						lblDetails.setEnabled(true);
						detailTextArea.setVisible(true);
						detailTextArea.setEnabled(true);
						titleTextField.setVisible(true);
						titleTextField.setEnabled(true);
						btnClear1.setVisible(true);
						btnClear1.setEnabled(true);
						btnCreate.setVisible(true);
						btnCreate.setEnabled(true);
						lblDate.setEnabled(true);
						comboBoxDate.setVisible(true);
						comboBoxDate.setEnabled(true);
						comboBoxMonth.setVisible(true);
						comboBoxMonth.setEnabled(true);
						comboBoxYear.setVisible(true);
						comboBoxYear.setEnabled(true);
						lblDisplayEventTitle.setText("");
						lblDisplayEventTitle.setVisible(false);
						textAreaDisplayEventDetail.setText("");
						textAreaDisplayEventDetail.setVisible(false);
						lblDisplayDate.setText("");
						lblDisplayDate.setVisible(false);

						btnAddEvent.setEnabled(false);

					}
				});

				//btn create moves details to the controller then to sql
				btnCreate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EventCalendarControl checkDate = new EventCalendarControl();
						String eventDate = comboBoxDate.getSelectedItem().toString() + "-"
								+ comboBoxMonth.getSelectedItem().toString() + "-" + comboBoxYear.getSelectedItem().toString();
						String eventTitle = titleTextField.getText();
						String eventDetails = detailTextArea.getText();

						if (eventTitle.length() > 30) {

							if (eventDetails.length() > 300) {
								JOptionPane.showMessageDialog(null,
										"Event Title and Event Details length is too long, Please re enter");
							}

							else if (!(checkDate.isDateValid(eventDate))) {
								JOptionPane.showMessageDialog(null,
										"Event Title length is too long and Date is invalid, Please re enter");
							}

							else if (!(checkDate.isDateValid(eventDate)) && eventDetails.length() > 50) {
								JOptionPane.showMessageDialog(null,
										"Event Title and Event Details length is too long and Date is invalid, Please re enter");
							}

							else {
								JOptionPane.showMessageDialog(null, "Event Title length is too long, Please re enter");
							}
						}

						else if (!(checkDate.isDateValid(eventDate))) {

							if (eventDetails.length() > 300) {
								JOptionPane.showMessageDialog(null,
										"Date is invalid and Event Details length is too long, Please re enter");
							} else {
								JOptionPane.showMessageDialog(null, "Date is invalid, Please re enter");
							}
						}

						else if (eventDetails.length() > 300) {
							JOptionPane.showMessageDialog(null, "Event Detail length is too long, Please re enter");
						}

						else if (eventTitle.length() == 0) {
							JOptionPane.showMessageDialog(null, "Event Title is empty, Please re enter");
						}

						else if (eventDetails.length() == 0) {
							JOptionPane.showMessageDialog(null, "Event Detail is empty, Please re enter");
						}

						else {

							EventEntity newEvent = new EventEntity();
							newEvent.setEventDetails(eventDetails);
							newEvent.setEventTitle(eventTitle);
							newEvent.setEventDate(eventDate);
							ec.setNewEvent(newEvent);
							ec.createEvent();
							JOptionPane.showMessageDialog(null, "Event Sucessfully Added");
							lblEvent.setEnabled(false);
							lblTitle.setEnabled(false);
							lblDetails.setEnabled(false);
							detailTextArea.setEnabled(false);
							titleTextField.setEnabled(false);
							btnClear1.setEnabled(false);
							btnCreate.setEnabled(false);
							comboBoxDate.setSelectedIndex(0);
							comboBoxMonth.setSelectedIndex(0);
							comboBoxYear.setSelectedIndex(0);
							comboBoxDate.setEnabled(false);
							comboBoxMonth.setEnabled(false);
							comboBoxYear.setEnabled(false);
							btnAddEvent.setEnabled(true);
							lblDate.setEnabled(false);
							titleTextField.setText(null);
							detailTextArea.setText(null);

						}

					}
				});

				setCalendarDate(ec, ce);

			}
			
			//this part does not use controller > entity. Jus for date
	     public void setCalendarDate(EventCalendarControl ec, CalendarEntity ce) {

		 int j = ec.getDayofWeek();

		    for (int i = 0; i <ec.getCe ().getDaysOfMonth (); i++) {
				allLabels.get(j).setVisible(true);
				allLabels.get(j).setText(Integer.toString(i + 1));
				if (j == 7 || j == 14 || j == 21 || j == 28 || j == 35 || j == 0) {
					allLabels.get(j).setForeground(Color.RED);
				}
					j++;
			}
		}
	
	
	    public static void fillcombobox(){
		c = sqliteconnection.dbConnector();
		
	    // fillcombobox();
		
		Budget_Plan_Controller con = new Budget_Plan_Controller();
		ArrayList<String> combobox = con.retrievecombobox();
	    
		for ( int i = 0; i <combobox.size() ; i++ ){
			comboBox_groupname.addItem(combobox.get(i));
		}
		comboBox_groupname.setSelectedIndex(-1);
		
			/* if fillcombobox is called before initializing the contents in the frame constructor
		 * it doesn't make sense to fillcombobox before the Jframe content has been initialized (JComboBox)
		 */
		
	  }
	    
		public Object[] selectedItem() {
			
			int row = table.getSelectedRow();
			int column = table.getColumnCount();
			Object[] result=new Object[column];
			for(int i = 0; i < column; i++) {
				result[i]=table.getValueAt(row, i);
			}
			
			return result;
		}
		
		public taskInputBoundary setInput(Object[] list) {
			taskInputBoundary input=new taskInputBoundary();
			input.setTxtTaskId(Integer.toString((int) list[0]));
			input.setTxtTaskName((String)list[1]);
			input.setTxtDescription((String)list[2]);
			input.setTaskType((String)list[3]);
			if ((int)list[4]!=0) {
				input.setTxtGroupId((Integer.toString((int) list[4])));
			}
			else {
				input.setTxtGroupId("N/A");
			}
			input.setTxtAdminNo((String)list[5]);
			input.setDateStart((String)list[6]);
			input.setDateEnd((String)list[7]);
			
			return input;
		}
		
		public void refreshTable() {
			try {
				table.setModel(controller.setTableModel());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
