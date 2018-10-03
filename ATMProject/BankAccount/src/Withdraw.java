import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Withdraw extends JFrame implements ActionListener {

	JPasswordField pass = new JPasswordField(25);
	JTextField txtpass = new JTextField(25);
	JLabel lbluser = new JLabel("Pin Number: ");
	JLabel lblpass = new JLabel("WithDraw: ");
	JButton menu = new JButton("Back to Menu");
	JButton withdraw = new JButton("Withdraw");
	JButton btnLogOut = new JButton("Logout");

	public Withdraw() {
		this.setTitle("Withdraw");
		this.setSize(350, 250);
		this.setLocationRelativeTo(null);

		JPanel pane = new JPanel();
		this.add(pane);
		pane.add(lbluser);
		pane.add(pass);
		pane.add(lblpass);
		pane.add(txtpass);
		pane.add(menu);
		menu.addActionListener(this);
		menu.setActionCommand("back");
		pane.add(withdraw);
		withdraw.addActionListener(this);
		withdraw.setActionCommand("withdraw");
		pane.add(btnLogOut);
		btnLogOut.addActionListener(this);
		btnLogOut.setActionCommand("logout");
		
		pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Withdraw Transaction"));

		setVisible(true);
		validate();
		repaint();

	}

	public void actionPerformed(ActionEvent e) {
		Connection cn = null;
		Object source = e.getSource();
		if (e.getActionCommand().equals("withdraw")) {
			if (source == withdraw) {
				try {
					try {

						Statement stmt = null;
						try {
							if (stmt == null) {

								Class.forName("com.mysql.jdbc.Driver").newInstance();
								Connection conn = null;
								conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ATM?user=root&password=");
								stmt = conn.prepareStatement("autoReconnect=true");
							}
						} catch (Exception ex) {
						}
						ResultSet rs = null;
						String st1 = txtpass.getText();
						if (st1.length() <= 1000) {

							txtpass.getText();
							// txtwid.setText("");
							if (stmt.execute("UPDATE User SET balance = balance -'" + st1 + "'WHERE PinNumber = '"
									+ pass.getText() + "';")) {

								while (rs.next()) {

									st1 = rs.getString(6);
									txtpass.setText(st1);

									JOptionPane.showMessageDialog(null, "Transaction cannot process", "ATM",
											JOptionPane.INFORMATION_MESSAGE);
									pass.requestFocus(true);
								}
							}
						}
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Enter now the amount to withdraw", "ATM",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException s) {
					System.out.println("No record found!\n\n\n");
					System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
				} catch (Exception x) {
					System.out.println("Error" + x.toString() + " " + x.getMessage());
				}
			}
		} else if (e.getActionCommand().equals("back")) {
			Menu panel = new Menu();
			panel.setSize(330, 300);
			panel.setVisible(true);
			panel.setResizable(false);
			panel.setLocation(400, 250);
			dispose();
		} else if (e.getActionCommand().equals("logout")) {
			int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirmation",
					JOptionPane.YES_NO_OPTION);
			if (a == 0) {
				dispose();
			}
		}

	}

}
