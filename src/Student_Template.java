import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import net.proteanit.sql.DbUtils;

import java.awt.Color;

public class Student_Template extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
    /// Routine Schedule ///
	
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	private JTextField textField6;
	private JTextField textField7;
	private JTextField textField8;
	private JTable table;
	public String hour1;
	public String hour2;
	public String hour3;
	public String hour4;
	public String hour5;
	public String hour6;
	public String hour7;
	public String hour8;
	public String[] inputdata =new String[8];
	
	/// Budget Planner ///
	
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
    
	public ArrayList<Object> inputdata1 = new ArrayList<Object>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Template frame = new Student_Template();
					frame.setLocationRelativeTo(null);
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
	public Student_Template() {
		setTitle("Community Planner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 670);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
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
		
		JMenuBar menuBar1 = new JMenuBar();
		menuBar1.setBounds(0, 0, 772, 50);
		Budget_Planner.add(menuBar1);
		
		JMenu mnNewMenu1 = new JMenu("Budget Planner");
		mnNewMenu1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu1);
		
		JMenu mnNewMenu_21 = new JMenu("Routine Schedule");
		mnNewMenu_21.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Budget_Planner.setVisible(false);
				Routine_Schedule.setVisible(true);
			}
		});
		mnNewMenu_21.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_21);
		
		JMenu mnNewMenu_31 = new JMenu("Task Admininstrator");
		mnNewMenu_31.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Budget_Planner.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_31.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_31);
		
		JMenu mnNewMenu_41 = new JMenu("Personnel Administrator");
		mnNewMenu_41.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e){
			}
			public void menuDeselected(MenuEvent e){	
			}
			public void menuSelected(MenuEvent e){
				Budget_Planner.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_41.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_41);
		
		JMenu mnNewMenu_11 = new JMenu("Event");
		mnNewMenu_11.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Budget_Planner.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_11.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar1.add(mnNewMenu_11);
		
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
						inputdata1.add(groupid);
						inputdata1.add(budget_plan_name);
						inputdata1.add(budget);
						inputdata1.add(materialcost);
						inputdata1.add(transportcost);
						inputdata1.add(logisticscost);
						inputdata1.add(misccost);
						inputdata1.add(totalcost);
										
						// call controller
						Budget_Plan_Controller c = new Budget_Plan_Controller();
						c.processbudgetplan(inputdata1);
						
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
		// disable button
		btnNewButton.setEnabled(false);
		
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
		           
		           inputdata1.clear();
			}
		});
		btnClear.setFont(new Font("Agency FB", Font.PLAIN, 26));
		btnClear.setBounds(153, 437, 84, 40);
		Budget_Planner.add(btnClear);
		// disable button
		btnClear.setEnabled(false);
		
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
				Display_Budget_Plan_Boundary_Student d = new Display_Budget_Plan_Boundary_Student();
				d.setVisible(true);
			}
		});
		btnViewBudgetPlans.setFont(new Font("Agency FB", Font.PLAIN, 26));
		btnViewBudgetPlans.setBounds(51, 501, 186, 49);
		Budget_Planner.add(btnViewBudgetPlans);
		
		
		///////////////////////////////////////////////////////// ROUTINE SCHEDULE /////////////////////////////////////////////////
		
		
		JMenuBar menuBar11 = new JMenuBar();
		menuBar11.setBounds(0, 0, 772, 50);
		Routine_Schedule.add(menuBar11);
		
		JMenu mnNewMenu11 = new JMenu("Budget Planner");
		mnNewMenu11.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Budget_Planner.setVisible(true);
			}
		});
		mnNewMenu11.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu11);
		
		JMenu mnNewMenu_111 = new JMenu("Routine Schedule");
		mnNewMenu_111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_111);
		
		JMenu mnNewMenu_211 = new JMenu("Task Administrator");
		mnNewMenu_211.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Task_Administrator.setVisible(true);
			}
		});
		mnNewMenu_211.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_211);
		
		JMenu mnNewMenu_311 = new JMenu("Personnel Administrator");
		mnNewMenu_311.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_311.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_311);
		
		JMenu mnNewMenu_411 = new JMenu("Event");
		mnNewMenu_411.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Routine_Schedule.setVisible(false);
				Event.setVisible(true);
			}
		});
		mnNewMenu_411.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11.add(mnNewMenu_411);
		
		JLabel label = new JLabel("Task name limited to 12 characters");
		label.setBounds(22, 63, 353, 40);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Agency FB", Font.BOLD, 30));
		Routine_Schedule.add(label);
		
		JLabel label_1 = new JLabel("8am-9am");
		label_1.setBounds(72, 113, 133, 40);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_1);
		
		JLabel label_2 = new JLabel("9am-10am");
		label_2.setBounds(72, 163, 133, 40);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_2);
		
		JLabel label_3 = new JLabel("10am-11am");
		label_3.setBounds(72, 213, 133, 40);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_3);
		
		JLabel label_4 = new JLabel("11am-12pm");
		label_4.setBounds(72, 263, 133, 40);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_4);
		
		JLabel label_5 = new JLabel("12pm-1pm");
		label_5.setBounds(72, 313, 133, 40);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_5);
		
		JLabel label_6 = new JLabel("1pm-2pm");
		label_6.setBounds(72, 363, 133, 40);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_6);
		
		JLabel label_7 = new JLabel("2pm-3pm");
		label_7.setBounds(72, 413, 133, 40);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_7);
		
		JLabel label_8 = new JLabel("3pm-4pm");
		label_8.setBounds(72, 463, 133, 40);
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Agency FB", Font.BOLD, 25));
		Routine_Schedule.add(label_8);
		
		textField1 = new JTextField(15);
		textField1.setBounds(180, 119, 146, 26);
		textField1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField1);
		
		textField2 = new JTextField(15);
		textField2.setBounds(180, 169, 146, 26);
		textField2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField2);
		
		textField3 = new JTextField(15);
		textField3.setBounds(180, 219, 146, 26);
		textField3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField3);
		
		textField4 = new JTextField(15);
		textField4.setBounds(180, 269, 146, 26);
		textField4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField4);
		
		textField5 = new JTextField(15);
		textField5.setBounds(180, 319, 146, 26);
		textField5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField5);
		
		textField6 = new JTextField(15);
		textField6.setBounds(180, 369, 146, 26);
		textField6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField6);
		
		textField7 = new JTextField(15);
		textField7.setBounds(180, 419, 146, 26);
		textField7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Routine_Schedule.add(textField7);
		
		textField8 = new JTextField(15);
		textField8.setBounds(180, 469, 146, 26);
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
		btnClear1.setBounds(72, 544, 82, 37);
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
				
				inputdata[0]=hour1;
				inputdata[1]=hour2;
				inputdata[2]=hour3;
				inputdata[3]=hour4;
				inputdata[4]=hour5;
				inputdata[5]=hour6;
				inputdata[6]=hour7;
				inputdata[7]=hour8;

				routineControl c = new routineControl();
				c.processSchedule(inputdata);
				
				
			      String[] conlayer = c.processSchedule(inputdata);
			    
				  
			     for (int i = 0; i < conlayer.length; i++){
			    	System.out.println(conlayer[i]); 
			    } 
			     
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
		btnSave.setBounds(264, 543, 96, 39);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnSave.setBackground(new Color(0, 0, 128));
		Routine_Schedule.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// prevent rows from duplicating when clicking Load Table start at the 2nd time
				try{
					
				    Connection connection=sqliteconnection.dbConnector();
					String  query="Select * FROM RoutineSchedule";
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

					routineControl c = new routineControl();
				    ResultSet totable = c.getSchedule();
				}
				catch(Exception e1){
					
					e1.printStackTrace();
				}
				}
				
			});
		btnLoad.setBounds(639, 544, 96, 39);
		btnLoad.setForeground(Color.WHITE);
		btnLoad.setFont(new Font("Agency FB", Font.BOLD, 25));
		btnLoad.setBackground(new Color(0, 0, 128));
		Routine_Schedule.add(btnLoad);
		
		//table-------------------

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(404, 79, 331, 274);
		Routine_Schedule.add(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		
		
		////////////////////////////////////////////////// TASK ADMINISTRATOR //////////////////////////////////////////////////////

		
		JMenuBar menuBar111 = new JMenuBar();
		menuBar111.setBounds(0, 0, 772, 50);
		Task_Administrator.add(menuBar111);
		
		JMenu mnNewMenu111 = new JMenu("Budget Planner");
		mnNewMenu111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
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
				Task_Administrator.setVisible(false);
				Routine_Schedule.setVisible(true);
			}
		});
		mnNewMenu_1111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_1111);
		
		JMenu mnNewMenu_2111 = new JMenu("Task Administrator");
		mnNewMenu_2111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_2111);
		
		JMenu mnNewMenu_3111 = new JMenu("Personnel Administrator");
		mnNewMenu_3111.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
				Personnel_Administrator.setVisible(true);
			}
		});
		mnNewMenu_3111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_3111);
		
		JMenu mnNewMenu_5 = new JMenu("Event");
		mnNewMenu_5.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
			}
			public void menuSelected(MenuEvent e) {
				Task_Administrator.setVisible(false);
				Event.setVisible(false);
			}
		});
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar111.add(mnNewMenu_5);
		
		JMenu mnNewMenu_4111 = new JMenu("New menu");
		mnNewMenu_4111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		
        ////////////////////////////////////////////////// PERSONNEL ADMINISTRATOR //////////////////////////////////////////////////////
		
		
		JMenuBar menuBar1111 = new JMenuBar();
		menuBar1111.setBounds(0, 0, 772, 50);
		Personnel_Administrator.add(menuBar1111);
		
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
		menuBar1111.add(mnNewMenu_51);
		
		JMenu mnNewMenu_41111 = new JMenu("New menu");
		mnNewMenu_41111.setFont(new Font("Segoe UI", Font.PLAIN, 20));
	
		
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
		
		JMenu mnNewMenu_511 = new JMenu("Event");
		mnNewMenu_511.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar11111.add(mnNewMenu_511);
		
	}
	
	 
	

}
