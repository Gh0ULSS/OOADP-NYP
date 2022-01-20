import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login_Page extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static Connection connection = null;
	
	public String name;
	public String adminNo;
	public String password;
	
	ArrayList<String> studentleader = new ArrayList<String>();
	ArrayList<String> student = new ArrayList<String>();
	
	public static Login_Page frame = new Login_Page();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Page() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
	    // ***** Student Leader *****
		
		
         	JPanel panel_Student_leader = new JPanel();
			contentPane.add(panel_Student_leader, "name_577819825617056");
			panel_Student_leader.setLayout(null);
			panel_Student_leader.setVisible(false);
			
		    JPasswordField passwordField = new JPasswordField();
			passwordField.setBounds(224, 135, 184, 28);
			panel_Student_leader.add(passwordField);
			
			JLabel lblLoginPage = new JLabel("Admin No:");
			lblLoginPage.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
			lblLoginPage.setBounds(109, 72, 105, 35);
			panel_Student_leader.add(lblLoginPage);
			
			JLabel lblUsername = new JLabel("Password:");
			lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblUsername.setBounds(99, 133, 123, 28);
			panel_Student_leader.add(lblUsername);
			
			JTextField textFieldadminNo = new JTextField();
			textFieldadminNo.setBounds(224, 77, 184, 28);
			panel_Student_leader.add(textFieldadminNo);
			textFieldadminNo.setColumns(10);
			
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) {
					
					adminNo = textFieldadminNo.getText();
					password = passwordField.getText();
					
					studentleader.add(adminNo);
					studentleader.add(password);
					
					Login_Controller c = new Login_Controller();
					    
					   if (c.processlogindetailsL(studentleader) == true){
					    	frame.setVisible(false);
							Main_Template frame = new Main_Template();
							frame.setVisible(true);
	                        frame.setLocationRelativeTo(null);
							Main_Template.fillcombobox();
					   }
					   else if (c.processlogindetailsL(studentleader) == false){
					    	JOptionPane.showMessageDialog(null, "Name or AdminNo is wrong");
					   }

					   
					textFieldadminNo.setText("");
					passwordField.setText("");
					   
				    adminNo = "";
				    password = "";
				    
				    studentleader.clear();
				    
				}
			});
			btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnLogin.setBounds(263, 203, 87, 35);
			panel_Student_leader.add(btnLogin);
			
			
			//***** Student *****
			
			
			JPanel panel_Student = new JPanel();
			contentPane.add(panel_Student, "name_578064963049496");
			panel_Student.setLayout(null);
			panel_Student.setVisible(false);
			
			JLabel lblLoginPage1 = new JLabel("Name:");
			lblLoginPage1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblLoginPage1.setHorizontalAlignment(SwingConstants.CENTER);
			lblLoginPage1.setBounds(128, 72, 105, 35);
			panel_Student.add(lblLoginPage1);
			
			JLabel lblUsername1 = new JLabel("Admin No:");
			lblUsername1.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsername1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblUsername1.setBounds(99, 133, 130, 28);
			panel_Student.add(lblUsername1);
			
			JTextField textFieldname = new JTextField();
			textFieldname.setBounds(224, 77, 184, 28);
			panel_Student.add(textFieldname);
			textFieldname.setColumns(10);
			
			JTextField textFieldadminNo2 = new JTextField();
			textFieldadminNo2.setBounds(224, 135, 184, 28);
			panel_Student.add(textFieldadminNo2);
			textFieldadminNo2.setColumns(10);
			
			JButton btnLogin1 = new JButton("Login");
			btnLogin1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				   
				 name =	textFieldname.getText();
				 adminNo = textFieldadminNo2.getText();
				 
				 student.add(name);
				 student.add(adminNo);
				 
				 Login_Controller c = new Login_Controller();
			     
			     if ( c.processlogindetailsS(student) == true){
			    	 frame.setVisible(false);
					 Student_Template frame = new Student_Template();
					 frame.setVisible(true);
					 frame.setLocationRelativeTo(null);
			     }
			     else {
			    	 JOptionPane.showMessageDialog(null, "Name or AdminNo is wrong");
			     }
				 
			     textFieldname.setText("");
			     textFieldadminNo2.setText("");
			     
			     
			     name = "";
			     adminNo = "";
				 
				 student.clear();
					
				}
			});
			btnLogin1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnLogin1.setBounds(269, 203, 87, 35);
			panel_Student.add(btnLogin1);
			
			
			// ***** Main *****
			
			
			JPanel panel_start = new JPanel();
		    contentPane.add(panel_start, "name_577417640519253");
			panel_start.setLayout(null);
			panel_start.setVisible(true);
			
			JLabel lblSelectOneOf = new JLabel("Select one of the options:");
			lblSelectOneOf.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectOneOf.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblSelectOneOf.setBounds(101, 36, 387, 43);
			panel_start.add(lblSelectOneOf);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Student");
			rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rdbtnNewRadioButton.setBounds(211, 112, 127, 25);
			panel_start.add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnStudentLeader = new JRadioButton("Student Leader");
			rdbtnStudentLeader.setFont(new Font("Tahoma", Font.PLAIN, 18));
			rdbtnStudentLeader.setBounds(211, 172, 154, 25);
			panel_start.add(rdbtnStudentLeader);
			
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnStudentLeader.setSelected(false);
				}
			});
			
			rdbtnStudentLeader.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnNewRadioButton.setSelected(false);
				}
			});
			JButton btnNewButton = new JButton("Next >");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (rdbtnNewRadioButton.isSelected() == true){
						panel_start.setVisible(false);
						panel_Student.setVisible(true);
						connection = sqliteconnection.dbConnector();
					}
					else if (rdbtnStudentLeader.isSelected() == true){
						panel_start.setVisible(false);
					    panel_Student_leader.setVisible(true);
					    connection = sqliteconnection.dbConnector();
					}
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setBounds(406, 239, 106, 43);
			panel_start.add(btnNewButton);
		
	}

}
