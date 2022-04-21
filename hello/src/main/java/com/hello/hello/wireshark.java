package com.hello.hello;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
public class wireshark {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wireshark window = new wireshark();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public wireshark() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 1504, 765);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(416, 88, 152, 19);
		frame.getContentPane().add(dateChooser_1);
		
		JLabel lblNewLabel_1 = new JLabel("developed as a part of PRISM Project by Samsung Research Institute, Bangalore");
		lblNewLabel_1.setForeground(new Color(192, 192, 192));
		lblNewLabel_1.setFont(new Font("Arial Narrow", Font.BOLD, 22));
		lblNewLabel_1.setBounds(268, 39, 791, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Recshark");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 40));
		lblNewLabel.setBounds(36, 10, 222, 74);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 1540, 84);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Start Date");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(36, 94, 85, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("End Date");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(335, 94, 71, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(125, 88, 152, 19);
		frame.getContentPane().add(dateChooser);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner.setBounds(125, 133, 45, 20);
		frame.getContentPane().add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_1.setBounds(202, 133, 45, 20);
		frame.getContentPane().add(spinner_1);
		
		JLabel lblNewLabel_4 = new JLabel("Start Time");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(36, 132, 75, 17);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("End Time");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(335, 134, 71, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_3.setBounds(416, 132, 40, 20);
		frame.getContentPane().add(spinner_3);
		
		JSpinner spinner_4 = new JSpinner();
		spinner_4.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_4.setBounds(492, 133, 40, 20);
		frame.getContentPane().add(spinner_4);
		
		String[] optionsToChoose = {"none", "IMSI", "MEI", "TAI"};
		JComboBox comboBox = new JComboBox(optionsToChoose);
		comboBox.setBounds(713, 114, 152, 18);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_6 = new JLabel("Filter");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(658, 114, 45, 19);
		frame.getContentPane().add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setBounds(919, 108, 187, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				java.sql.Date start_sqldate = new java.sql.Date(dateChooser.getDate().getTime());
				System.out.println(start_sqldate.getTime());
				
				java.sql.Date end_sqldate = new java.sql.Date(dateChooser_1.getDate().getTime());
				long end_epoch=0;
				System.out.println(end_sqldate.toString());
				System.out.println(end_sqldate.toString().substring(8,10)+"/"+end_sqldate.toString().substring(5,7)+"/"+end_sqldate.toString().substring(0,4)+" 00:00:00");

				int starthour=(int) spinner.getValue();
				System.out.println(starthour);
				int startmin=(int) spinner_1.getValue();
				System.out.println(startmin);
				int endhour=(int) spinner_3.getValue();
				System.out.println(endhour);
				int endmin=(int) spinner_4.getValue();
				System.out.println(endmin);
				try {
					
					end_epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(end_sqldate.toString().substring(5,7)+"/"+end_sqldate.toString().substring(8,10)+"/"+end_sqldate.toString().substring(0,4)+" 00:00:00").getTime();
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					end_epoch=0;
				}
				System.out.println(end_epoch);
				String Filter=(String) comboBox.getSelectedItem();
				System.out.println(Filter=="none");
				String Filtervalue=textField.getText();
				System.out.println(Filtervalue);
				long startmil=start_sqldate.getTime()+starthour*60*60*1000+startmin*60*1000;
				long endmil=end_epoch+endhour*60*60*1000+endmin*60*1000;
				System.out.println(startmil);
				System.out.println(endmil);
				String queryno="1";
				if (Filter=="none")
					queryno="1";
				if (Filter=="IMSI")
					queryno="2";
				if (Filter=="MEI")
					queryno="3";
				DefaultTableModel mod2=(DefaultTableModel)table.getModel();
				mod2.setRowCount(0);
				
				try (Socket socket = new Socket("localhost", 3003)) 
		        {
		            
		            // writing to server
		            PrintWriter out = new PrintWriter(
		                socket.getOutputStream(), true);
		            ObjectInputStream oos=new ObjectInputStream(socket.getInputStream());
		 
		  
		            // object of scanner class
		            // reading from user
		                // sending the user input to server
		                out.println(queryno+","+Filtervalue+","+Filtervalue+","+String.valueOf(startmil)+","+String.valueOf(endmil));
		                out.flush();
		  
		                // displaying server reply
		                ArrayList<entryinfo> ans;
		                String ts=" ";
		                    ans=(ArrayList<entryinfo>)oos.readObject();
		                    for(int i=0;i<ans.size();i++)
		            		{ System.out.println(ans.get(i).timestamp);
		            			//System.out.println(ans.get(i).timestamp);
		            		ts=ans.get(i).timestamp.toString().substring(0,10)+" "+ ans.get(i).timestamp.toString().substring(11,19);
		                    	Object[] data= {ts,ans.get(i).hexdom,ans.get(i).message_type,ans.get(i).imsi,ans.get(i).mei,ans.get(i).tai};
		                    	DefaultTableModel mod1=(DefaultTableModel)table.getModel();
		                    	mod1.addRow(data);
		            		}

		        }
		        catch (IOException e1) {
		            e1.printStackTrace();
		        } catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(1135, 113, 85, 21);
		frame.getContentPane().add(btnNewButton);
		String data[][] = null;
		String columns[] = {"Time","Hexdom","Message Type","IMSI","MEI","TAI"};
		DefaultTableModel model = new DefaultTableModel(data,columns);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 183, 1490, 541);
		frame.getContentPane().add(scrollPane);
		table = new JTable(model);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		
		JLabel lblNewLabel_7 = new JLabel("hrs");
		lblNewLabel_7.setBounds(173, 134, 30, 17);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(" min");
		lblNewLabel_8.setBounds(250, 136, 45, 13);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_7_1 = new JLabel("  hrs");
		lblNewLabel_7_1.setBounds(452, 134, 30, 17);
		frame.getContentPane().add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_8_1 = new JLabel(" min");
		lblNewLabel_8_1.setBounds(534, 136, 45, 13);
		frame.getContentPane().add(lblNewLabel_8_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(0, 83, 1500, 103);
		frame.getContentPane().add(panel_1);
		
	}
}
