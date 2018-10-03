import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Balance extends JFrame implements ActionListener {

	JPasswordField txtpass = new JPasswordField(20);
	JTextField txtbal = new JTextField(20);
	JLabel lblpass = new JLabel("Pin Number: ");
	JLabel lblbal = new JLabel("Balance");
	JButton menu = new JButton("Back to Menu");
	JButton balance = new JButton("Check Balance");
	JButton btnLogOut = new JButton("Logout");

	public Balance() {

		this.setTitle("Check Your Nalance");
		this.setSize(250, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		JPanel panel = new JPanel();
		this.add(panel);

		panel.add(lblpass);
		panel.add(txtpass);
		panel.add(lblbal);
		panel.add(txtbal);
		panel.add(balance);
		balance.addActionListener(this);
		balance.setActionCommand("balance");
		panel.add(menu);
		menu.setActionCommand("menu");
		menu.addActionListener(this);
		panel.add(btnLogOut);
		btnLogOut.addActionListener(this);
		btnLogOut.setActionCommand("logout");

		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Check Your Balance"));

		repaint();
		validate();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (e.getActionCommand().equals("balance")) {
			try {
				Statement st = null;
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ATM?user=root&password=");
				st = conn.prepareStatement("autoReconnect=true");
				ResultSet rs = st.executeQuery("SELECT * FROM User WHERE PinNumber ='" + txtpass.getText() + "'");
				while (rs.next()) {
					txtbal.setText(rs.getString(6));

					JOptionPane.showMessageDialog(null, "Record Found.", "ATM System", JOptionPane.INFORMATION_MESSAGE);

				}
				st.close();
			} catch (SQLException s) {
				System.out.println("No record found!\n\n\n");
				System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
			} catch (Exception x) {
				System.out.println("Error" + x.toString() + " " + x.getMessage());
			}
		} else if (e.getActionCommand().equals("menu")) {
			Menu panel = new Menu();
			panel.setSize(250, 250);
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
