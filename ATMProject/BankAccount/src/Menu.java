import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menu extends JFrame implements ActionListener{
	
	JButton btnBals = new JButton("Balance");
	JButton btnWid = new JButton("Withdraw");
	JButton btnLogOut = new JButton("Logout");
	
	public Menu(){
		setTitle("Menu");
		this.setSize(250,250);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		JPanel pane = new JPanel();
		this.add(pane);

		pane.add(btnBals);
		btnBals.addActionListener(this);
		btnBals.setBackground(Color.black);
;
		pane.add(btnWid);
		btnWid.addActionListener(this);		
		btnWid.setBackground(Color.black);

		pane.add(btnLogOut);
		btnLogOut.setActionCommand("logout");
		btnLogOut.addActionListener(this);
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select Transaction"));
		
		
		repaint();
		validate();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == btnBals){			
			Balance balance=new Balance();
			balance.setLocation(400,250);
			balance.setSize(250,250);
			balance.setTitle("Balance");
			balance.setResizable(false);
			balance.setVisible(true);
			dispose();
		}
		if(source == btnWid){
			Withdraw log=new Withdraw();

			log.setLocation(350,250);
			log.setSize(450,300);
			log.setTitle("Withdraw");
			log.setResizable(false);
			log.setVisible(true);
			dispose();
			
		}else if(e.getActionCommand().equals("logout")){
			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?",
					"Confirmation",JOptionPane.YES_NO_OPTION);
			if (a == 0){
				dispose();			
			}			
		}
		
	}

}
