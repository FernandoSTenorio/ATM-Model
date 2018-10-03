import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends JFrame implements ActionListener {
	
	Font f1 = new Font("", Font.BOLD, 10);
	
	JLabel lblUser = new JLabel("User Name ",JLabel.RIGHT);	
	JLabel lblFName = new JLabel("First Name ",JLabel.RIGHT);
	JLabel lblLName = new JLabel("Last Name ",JLabel.RIGHT);
	JLabel lblPass = new JLabel("Pin Number ",JLabel.RIGHT);
	JLabel lblBday = new JLabel("B-Day ",JLabel.RIGHT);
	JLabel lblCash = new JLabel("Cash Tender ",JLabel.RIGHT);
	
	JTextField txtUser = new JTextField(20);
	JTextField txtFName= new JTextField(20);
	JTextField txtLName = new JTextField(20);
	JPasswordField txtPass= new JPasswordField(20);

	JTextField txtCash = new JTextField(20);
	
	public Register(){
		setTitle("Registration Panel");
		this.setSize(370,400);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocation(400,250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		this.add(panel);
		
		JButton btnCret = new JButton("Register");
		btnCret.setActionCommand("register");
		btnCret.addActionListener(this);
		
		JButton btnclose = new JButton("Close");
		btnclose.setActionCommand("close");
		btnclose.addActionListener(this);

		JPanel pane = new JPanel();
		this.add(pane);
		pane.setLayout(null);

		lblUser.setBounds(5,50,120,25);
		pane.add(lblUser);
		txtUser.setBounds(125,50,150,25);
		pane.add(txtUser);
		
		lblFName.setBounds(5,85,120,25);
		pane.add(lblFName);
		txtFName.setBounds(125,85,150,25);
		pane.add(txtFName);
		
		lblLName.setBounds(5,120,120,25); 
		pane.add(lblLName);
		txtLName.setBounds(125,120,150,25); 
		pane.add(txtLName);
		
		lblPass.setBounds(5,155,120,25);
		pane.add(lblPass);

		txtPass.setBounds(125,155,150,25); 
		pane.add(txtPass);	
		
		lblCash.setBounds(5,185,120,25);
		pane.add(lblCash);
		txtCash.setBounds(125,185,100,25);
		pane.add(txtCash);
			
		btnCret.setBounds(50,230,100,35);
		pane.add(btnCret);
		btnclose.setBounds(150,230,100,35);
		pane.add(btnclose);

		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Registration Form"));
		
		repaint();
		validate();
	}
	public boolean addUser(User user){
		try{
			
			String suser = 	txtUser.getText();
			String sname =	txtFName.getText();
			String spass = 	txtPass.getText();
			String slname =	txtLName.getText();
			String cash = txtCash.getText();
			
			Statement stmt = DataBase.getStatment();
			
			if(stmt.execute("INSERT INTO User (`UserName`,`FName`,`LName`,`PinNumber`,`Balance`) " + " VALUES('"+suser+"','"+sname+"','"+slname+"','"+spass+"','"+cash+"');")){
				return true;
			}		
						
		}catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} 
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("close")){
			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (a == 0){
				dispose();			
			}			
		}else if(e.getActionCommand().equals("register")){
			User user = new User();
			user.setUserName(new String(txtUser.getText()));
			user.setFName(new String(txtFName.getText()));
			user.setLName(new String(txtLName.getText()));
			user.setPinNumber(new String(txtPass.getText()));
			user.setBalance(new String(txtCash.getText()));
			
			if(addUser(user)){
				JOptionPane.showMessageDialog(this, "User Regitered", "", JOptionPane.INFORMATION_MESSAGE);
			}		
		}
	}

}
