import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

public class Display_Budget_Plan_Boundary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	public static DefaultTableModel model;
	public static Object[] rowData = new Object[11];
	public int plan_id;
	
	public static Connection c = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_Budget_Plan_Boundary frame = new Display_Budget_Plan_Boundary();
					frame.setVisible(true);
					c = sqliteconnection.dbConnector();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Display_Budget_Plan_Boundary() {
		setTitle("View Budget Plan");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1550, 790);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 1532, 743);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnProject = new JRadioButton("Project");
		JRadioButton rdbtnCca = new JRadioButton("CCA");
		rdbtnCca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnCca.isSelected() == true){
					rdbtnProject.setSelected(false);
				}
			}
		});
		rdbtnCca.setForeground(new Color(255, 255, 255));
		rdbtnCca.setFont(new Font("Agency FB", Font.PLAIN, 24));
		rdbtnCca.setBackground(new Color(100, 149, 237));
		rdbtnCca.setBounds(37, 235, 170, 25);
		panel.add(rdbtnCca);
	
		rdbtnProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnProject.isSelected() == true){
					rdbtnCca.setSelected(false);
				}
			}
		});
		rdbtnProject.setForeground(new Color(255, 255, 255));
		rdbtnProject.setFont(new Font("Agency FB", Font.PLAIN, 24));
		rdbtnProject.setBackground(new Color(100, 149, 237));
		rdbtnProject.setBounds(37, 176, 170, 41);
		panel.add(rdbtnProject);
	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 28, 1261, 683);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	    model = new DefaultTableModel();
	        
		Object[] columnsName = new Object[11];
		        
		columnsName[0] = "Plan Id";
		columnsName[1] = "groupId";
		columnsName[2] = "groupName";
		columnsName[3] = "groupType";
		columnsName[4] = "budget_plan_name";
		columnsName[5] = "budget";
		columnsName[6] = "materialcost";
		columnsName[7] = "logisticscost";
		columnsName[8] = "transportcost";
		columnsName[9] = "misccost";
		columnsName[10] = "totalcost";
		       
		model.setColumnIdentifiers(columnsName);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(0, 0, 128));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		     
			  // get selected row id
			  int rowid = table.getSelectedRow();
				
		      if ( rowid == -1 ){
					JOptionPane.showMessageDialog(null,"Please select a row to delete");
			  }	
		      else{
		      
		    	 int validation = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete","Delete",JOptionPane.YES_NO_OPTION);
				  
				 if (validation == 0){
	                   plan_id = (int) table.getValueAt(rowid,0);
							
					   Budget_Plan_Controller c = new Budget_Plan_Controller();
					   c.deletebudgetplan(plan_id);
					
					   //System.out.println(plan_id);
						  
					   model.removeRow(rowid);
						
					   JOptionPane.showMessageDialog(null,"Deletion Successfull");
						
					   rowid = 0;
					   plan_id = 0;  
				   }
				   else{
					  
				   }
		    	  
		        }	
		
			}
		});
		btnDelete.setFont(new Font("Agency FB", Font.PLAIN, 34));
		btnDelete.setBounds(27, 407, 182, 57);
		panel.add(btnDelete);
	
		
		JButton btnLoadTable = new JButton("Load Table");
		btnLoadTable.setForeground(new Color(255, 255, 255));
		btnLoadTable.setBackground(new Color(0, 0, 128));
		btnLoadTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// prevent rows from duplicating when clicking Load Table start at the 2nd time
				model.setRowCount(0);
				
				if(rdbtnProject.isSelected() == true){
					
					   Budget_Plan_Controller c = new Budget_Plan_Controller();
					    ArrayList<View_Budget_Plan_Entity> totable = c.retrievebudgetdataProject();


					      Object[] rowData = new Object[11];
					      for (int i = 0 ; i < totable.size() ; i++)  {
			                rowData[0] = ( totable.get(i).getPlan_id());
			                rowData[1] = ( totable.get(i).getGroupid());
			                rowData[2] = ( totable.get(i).getGroupname());
			                rowData[3] = ( totable.get(i).getGrouptype());
			                rowData[4] = ( totable.get(i).getBudget_plan_name());
			                rowData[5] = ( totable.get(i).getBudget());
			                rowData[6] = ( totable.get(i).getMaterialcost());
			                rowData[7] = ( totable.get(i).getLogisticscost());
			                rowData[8] = ( totable.get(i).getTransportcost());
			                rowData[9] = ( totable.get(i).getMisccost());
			                rowData[10] = ( totable.get(i).getTotalcost());
			               
			              model.addRow(rowData);
					}	
					      
					table.setModel(model);
					
				}
				else if(rdbtnCca.isSelected() == true){
					
					   Budget_Plan_Controller c = new Budget_Plan_Controller();
					    ArrayList<View_Budget_Plan_Entity> totable = c.retrievebudgetdataCCA();

					    
					      Object[] rowData = new Object[11];
					      for (int i = 0 ; i < totable.size() ; i++)  {
			                rowData[0] = ( totable.get(i).getPlan_id());
			                rowData[1] = ( totable.get(i).getGroupid());
			                rowData[2] = ( totable.get(i).getGroupname());
			                rowData[3] = ( totable.get(i).getGrouptype());
			                rowData[4] = ( totable.get(i).getBudget_plan_name());
			                rowData[5] = ( totable.get(i).getBudget());
			                rowData[6] = ( totable.get(i).getMaterialcost());
			                rowData[7] = ( totable.get(i).getLogisticscost());
			                rowData[8] = ( totable.get(i).getTransportcost());
			                rowData[9] = ( totable.get(i).getMisccost());
			                rowData[10] = ( totable.get(i).getTotalcost());
			               
			              model.addRow(rowData);
					}	
					      
					table.setModel(model);
					
				}
				else{
					
				   Budget_Plan_Controller c = new Budget_Plan_Controller();
				    ArrayList<View_Budget_Plan_Entity> totable = c.retrievebudgetdata();


				      Object[] rowData = new Object[11];
				      for (int i = 0 ; i < totable.size() ; i++)  {
		                rowData[0] = ( totable.get(i).getPlan_id());
		                rowData[1] = ( totable.get(i).getGroupid());
		                rowData[2] = ( totable.get(i).getGroupname());
		                rowData[3] = ( totable.get(i).getGrouptype());
		                rowData[4] = ( totable.get(i).getBudget_plan_name());
		                rowData[5] = ( totable.get(i).getBudget());
		                rowData[6] = ( totable.get(i).getMaterialcost());
		                rowData[7] = ( totable.get(i).getLogisticscost());
		                rowData[8] = ( totable.get(i).getTransportcost());
		                rowData[9] = ( totable.get(i).getMisccost());
		                rowData[10] = ( totable.get(i).getTotalcost());
		               
		              model.addRow(rowData);
				}	
				      
				table.setModel(model);
				
				}
				
				
			}
		});
		btnLoadTable.setFont(new Font("Agency FB", Font.PLAIN, 34));
		btnLoadTable.setBounds(25, 297, 187, 63);
		panel.add(btnLoadTable);
		
		JLabel lblClickingLoad = new JLabel("<html>* Clicking Load Table without <br> any selection will retrieve <br> all budget plans</html>");
		lblClickingLoad.setForeground(new Color(255, 0, 0));
		lblClickingLoad.setFont(new Font("Agency FB", Font.PLAIN, 24));
		lblClickingLoad.setBounds(14, 512, 219, 149);
		panel.add(lblClickingLoad);
	}
}
