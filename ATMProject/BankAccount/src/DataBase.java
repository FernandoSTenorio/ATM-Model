import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DataBase {
	
	public static Statement stmt = null;
	
	public static Statement getStatment(){
		try{
			if (stmt == null){
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ATM?user=root&password=");
				stmt = conn.prepareStatement("autoReconnect=true");
			}
		}
		catch(Exception ex ){}
		return stmt;
	}
	
}
