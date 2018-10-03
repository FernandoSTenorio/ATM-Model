import java.awt.*;
import javax.swing.*;


import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
	
	public static Register register;
	public static DataBase setUP;
	public static User user;
	public static Menu menu;
	public static Balance balance;
	public static Withdraw withdraw;
	
	private JPasswordField pass;
	private JTextField txt;
	private JLabel lblPass;
	private JButton button;
	private JButton button2;
	
	public Login(){
		
		this.setTitle("Login");
		this.setSize(250, 250);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints c1 = new GridBagConstraints();
		
		JPanel panel = new JPanel();
		this.add(panel);
		JLabel lblUser = new JLabel("Username ");
		panel.add(lblUser);
		txt = new JTextField(20);
		panel.add(txt);
		lblPass = new JLabel("Pin Number");
		panel.add(lblPass);
		pass = new JPasswordField(20);
		panel.add(pass);
		
		button = new JButton("Login");
		panel.add(button);
		button.addActionListener(this);
		button.setActionCommand("login");
		
		button2 = new JButton("Register");
		panel.add(button2);
		button2.addActionListener(this);
		button2.setActionCommand("register");
			
		setVisible(true);
		repaint();
		validate();
	}
	
	public static void main(String[] args){
		new Login();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("register")){
			Register rg = new Register();
			
		}else if(e.getActionCommand().equals("login")){
			Object source=e.getSource();
			
			if(source==button){
				try{
					String str1=txt.getText();
					String str2=pass.getText();
					if((str1.length()==0 || str2.length()==0)){
	    					JOptionPane.showMessageDialog(null,"Connot be Process","WARNING",JOptionPane.WARNING_MESSAGE);
	    				}
	    				else{
					Statement st=DataBase.getStatment();
					String strUser="";
					String strPass="";
					
					ResultSet rs=st.executeQuery("SELECT * FROM User WHERE UserName='"+str1+"' and PinNumber = '"+str2+"';");
					while(rs.next()){
						strUser=rs.getString(2);
						strPass=rs.getString(5);
					}		
					st.close();
			
					if(strUser.equals(str1)){
						if(strPass.equals(str2)){
					    					
							JOptionPane.showMessageDialog(null,"Welcome "+txt.getText(),"Welcome",JOptionPane.INFORMATION_MESSAGE);
							
							Menu panel = new Menu();
							
						}else{
							JOptionPane.showMessageDialog(null,"Username found but the password is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
							pass.requestFocus(true);
						}
					}else{
						JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txt.requestFocus(true);
					}
	    				}	
				}catch(SQLException w){
				}
			}
		}
		
	}

	}
	


