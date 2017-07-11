package com.regp_assign;

import java.sql.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
public class regp_assign {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //Initialize connection variables
	static final String DB_URL = "jdbc:mysql://localhost/tata?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
	static final String USER = "root";
	static final String PASS = "";
/* **********MAIN FUCNTION STARTS ********* */	
	public static void main(String[] args){
		String path = System.getProperty("user.dir");
		JOptionPane.showMessageDialog(null, "                          *****ALERT*****\n Ensure Assign.csv is closed before you run this file!");
		Connection conn= null;
		Statement stmt = null;
		Statement stmt1 = null;
		PreparedStatement pstmt = null;
		/*Executions*/
		try{
			/*Connecting to database*/
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt=conn.createStatement();
			stmt1=conn.createStatement();
			String sql;
			try{
				sql = "DROP TABLE report";
				stmt.executeUpdate(sql);
			}catch(SQLException se){
				
			}
			try{
				sql = "DROP TABLE tickets";
				stmt.executeUpdate(sql);
			}catch(SQLException se){
				
			}
			try{
				sql = "DROP TABLE regp_assign";
				stmt.executeUpdate(sql);
			}catch(SQLException se){
				
			}
			try{
				sql = "DROP TABLE users";
				stmt.executeUpdate(sql);
			}catch(SQLException se){
					
			}			
			//create and populate report from report.csv
			sql="CREATE TABLE report " + "(`Ticket no. +` VARCHAR(20), " + "`Information Source` VARCHAR(100), " + "`Condition Code` VARCHAR(100), " + "`Condition Code Priority` VARCHAR(2), "
			+ "`Time Remaining ( Minutes )` INTEGER, " + "`Priority+` INTEGER, " + "`VTS Customer Type` VARCHAR(100), " + "`Serv. Level` VARCHAR(100), " + "`First_Accepted_User` VARCHAR(100), "
			+ "`Reporting Organization +` VARCHAR(100), " + "`Destination Country+` VARCHAR(100), " + "`LCR Destination` VARCHAR(100), " + "`Fault Start Date (UTC)+` INTEGER, " + 
			"`Last-modified-by` VARCHAR(100), " + "`Fault Category` VARCHAR(100), " + "`Fault Symptom` VARCHAR(100), " + "`Support Group` VARCHAR(100), " + "User VARCHAR(100), " + 
			"`Fault Summary` VARCHAR(100), " + "`Carrier Ticket No` VARCHAR(100), " + "`Fault Organization Name+` VARCHAR(100), " + "`Rep. Org. Abbr.+` VARCHAR(100), " + "`Ref. Report Org. No.` VARCHAR(100))";
			stmt.executeUpdate(sql);
			sql="LOAD DATA LOCAL INFILE 'Report.csv' INTO TABLE report FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES" +
			"(`Ticket no. +`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`,`First_Accepted_User`," + 
			"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
			"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)";
			stmt.executeUpdate(sql);
			//create tickets
			sql="CREATE TABLE tickets " + "(`Ticket no. +` VARCHAR(20), " + "`Information Source` VARCHAR(100), " + "`Condition Code` VARCHAR(100), " + "`Condition Code Priority` VARCHAR(2), "
					+ "`Time Remaining ( Minutes )` INTEGER, " + "`Priority+` INTEGER, " + "`VTS Customer Type` VARCHAR(100), " + "`Serv. Level` VARCHAR(100), " + "`First_Accepted_User` VARCHAR(100), "
					+ "`Reporting Organization +` VARCHAR(100), " + "`Destination Country+` VARCHAR(100), " + "`LCR Destination` VARCHAR(100), " + "`Fault Start Date (UTC)+` INTEGER, " + 
					"`Last-modified-by` VARCHAR(100), " + "`Fault Category` VARCHAR(100), " + "`Fault Symptom` VARCHAR(100), " + "`Support Group` VARCHAR(100), " + "User VARCHAR(100), " + 
					"`Fault Summary` VARCHAR(100), " + "`Carrier Ticket No` VARCHAR(100), " + "`Fault Organization Name+` VARCHAR(100), " + "`Rep. Org. Abbr.+` VARCHAR(100), " + "`Ref. Report Org. No.` VARCHAR(100))";
			stmt.executeUpdate(sql);
			//create and populate users
			sql="CREATE TABLE users " + "(User VARCHAR(100), " + "`Email IDs` VARCHAR(100) )";
			stmt.executeUpdate(sql);
			sql="LOAD DATA LOCAL INFILE 'Users.csv' INTO TABLE users FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' IGNORE 1 LINES" +
				"(User,`Email IDs`)";
			stmt.executeUpdate(sql);
			int ticket_user=Integer.parseInt(JOptionPane.showInputDialog("How many tickets per head?"));
			sql = "SELECT * FROM users";
			ResultSet users = stmt.executeQuery(sql);
			sql = "SELECT COUNT(*) FROM users";
			ResultSet rs=stmt.executeQuery(sql);
			int user_count=0; //number of users in users.csv
			while (rs.next()){
				user_count=rs.getInt("COUNT(*)");
			}
			//create regp_assign table which will contain all the assignments
			sql="CREATE TABLE regp_assign " + "(`Ticket no. +` VARCHAR(20), " + "`Assigned User` VARCHAR(100), " + "`First_Accepted_User` VARCHAR(100), " + "`Information Source` VARCHAR(100), " + 
			"`Condition Code` VARCHAR(100), " + "`Condition Code Priority` VARCHAR(2), " + "`Time Remaining ( Minutes )` INTEGER, " + "`Priority+` INTEGER, " + "`VTS Customer Type` VARCHAR(100), " + 
			"`Serv. Level` VARCHAR(100), " + "`Reporting Organization +` VARCHAR(100), " + "`Destination Country+` VARCHAR(100), " + "`LCR Destination` VARCHAR(100), " + "`Fault Start Date (UTC)+` INTEGER, " + 
			"`Last-modified-by` VARCHAR(100), " + "`Fault Category` VARCHAR(100), " + "`Fault Symptom` VARCHAR(100), " + "`Support Group` VARCHAR(100), " + "User VARCHAR(100), " + "`Fault Summary` VARCHAR(100), " + "`Carrier Ticket No` VARCHAR(100), " + "`Fault Organization Name+` VARCHAR(100), " 
			+ "`Rep. Org. Abbr.+` VARCHAR(100), " + "`Ref. Report Org. No.` VARCHAR(100))";
			stmt.executeUpdate(sql);
			String psql= "INSERT INTO regp_assign" + "(`Ticket no. +`,`Assigned User`,`First_Accepted_User`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`," + 
					"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
					"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String psql1="DELETE FROM report WHERE `Ticket no. +` =?";
			PreparedStatement pstmt1 = null;
			pstmt=conn.prepareStatement(psql);
			pstmt1=conn.prepareStatement(psql1);
			/* ******Assign repeat tickets of active users******** */
			sql = "SELECT * FROM report WHERE `First_Accepted_User`!=\"\"";
			ResultSet rep = stmt.executeQuery(sql);
			while (rep.next()){
					String tr= rep.getString(1);
					String ass=rep.getString(9);
					String fau= rep.getString(9);
					String is= rep.getString(2);
					String cc= rep.getString(3);
					String ccp = rep.getString(4);
					int trm= rep.getInt(5);
					int p= rep.getInt(6);
					String vct= rep.getString(7);
					String sl= rep.getString(8);
					String ro= rep.getString(10);
					String dc= rep.getString(11);
					String lcrd= rep.getString(12);
					String fsd= rep.getString(13);
					String lm= rep.getString(14);
					String fc= rep.getString(15);
					String fs= rep.getString(16);
					String sg= rep.getString(17);
					String u= rep.getString(18);
					String fsu= rep.getString(19);
					String ctn= rep.getString(20);
					String fo= rep.getString(21);
					String roa= rep.getString(22);
					String ron= rep.getString(23);
					pstmt.setString(1, tr);
					pstmt.setString(2, ass);
					pstmt.setString(3, fau);
					pstmt.setString(4, is);
					pstmt.setString(5, cc);
					pstmt.setString(6, ccp);
					pstmt.setInt(7, trm);
					pstmt.setInt(8, p);
					pstmt.setString(9, vct);
					pstmt.setString(10, sl);
					pstmt.setString(11, ro);
					pstmt.setString(12, dc);
					pstmt.setString(13, lcrd);
					pstmt.setString(14, fsd);
					pstmt.setString(15, lm);
					pstmt.setString(16, fc);
					pstmt.setString(17, fs);
					pstmt.setString(18, sg);
					pstmt.setString(19, u);
					pstmt.setString(20, fsu);
					pstmt.setString(21, ctn);
					pstmt.setString(22, fo);
					pstmt.setString(23, roa);
					pstmt.setString(24, ron);
					pstmt.executeUpdate();
					pstmt1.setString(1,tr);
					pstmt1.executeUpdate();
			}
			int flag=0;
			psql = "INSERT INTO report" + "(`Ticket no. +`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`,`First_Accepted_User`," + 
					"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
					"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			psql1="DELETE FROM regp_assign WHERE `Ticket no. +` =?";
			pstmt=conn.prepareStatement(psql);
			pstmt1=conn.prepareStatement(psql1);
			sql = "SELECT * FROM regp_assign";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				flag=0;
				String user=rs.getString("First_Accepted_User");
				sql = "SELECT * FROM users";
				users = stmt1.executeQuery(sql);
				while(users.next()){
					if(users.getString("User").equals(user)){
						flag=1;
					}
				}
				if (flag==0){
						String tr= rs.getString(1);
						String is= rs.getString(4);
						String cc= rs.getString(5);
						String ccp = rs.getString(6);
						int trm= rs.getInt(7);
						int p= rs.getInt(8);
						String vct= rs.getString(9);
						String sl= rs.getString(10);
						String fau= rs.getString(3);
						String ro= rs.getString(11);
						String dc= rs.getString(12);
						String lcrd= rs.getString(13);
						String fsd= rs.getString(14);
						String lm= rs.getString(15);
						String fc= rs.getString(16);
						String fs= rs.getString(17);
						String sg= rs.getString(18);
						String u= rs.getString(19);
						String fsu= rs.getString(20);
						String ctn= rs.getString(21);
						String fo= rs.getString(22);
						String roa= rs.getString(23);
						String ron= rs.getString(24);
						pstmt.setString(1, tr);
						pstmt.setString(2, is);
						pstmt.setString(3, cc);
						pstmt.setString(4, ccp);
						pstmt.setInt(5, trm);
						pstmt.setInt(6, p);
						pstmt.setString(7, vct);
						pstmt.setString(8, sl);
						pstmt.setString(9, fau);
						pstmt.setString(10, ro);
						pstmt.setString(11, dc);
						pstmt.setString(12, lcrd);
						pstmt.setString(13, fsd);
						pstmt.setString(14, lm);
						pstmt.setString(15, fc);
						pstmt.setString(16, fs);
						pstmt.setString(17, sg);
						pstmt.setString(18, u);
						pstmt.setString(19, fsu);
						pstmt.setString(20, ctn);
						pstmt.setString(21, fo);
						pstmt.setString(22, roa);
						pstmt.setString(23, ron);
						pstmt.executeUpdate();
						pstmt1.setString(1, tr);
						pstmt1.executeUpdate();
				}
			}
			
			/* ******sorting starts****** */
			//Mobile OTT priority
			psql = "INSERT INTO tickets" + "(`Ticket no. +`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`,`First_Accepted_User`," + 
					"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
					"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			sql="SELECT * FROM report WHERE `VTS Customer Type` = 'Mobile' OR `VTS Customer Type` = 'OTT' ORDER BY `Time Remaining ( Minutes )` DESC";
			rs = stmt.executeQuery(sql);
			pstmt=conn.prepareStatement(psql);
			//insert to tickets
			while (rs.next())
			{
				String tr= rs.getString(1);
				String is= rs.getString(2);
				String cc= rs.getString(3);
				String ccp = rs.getString(4);
				int trm= rs.getInt(5);
				int p= rs.getInt(6);
				String vct= rs.getString(7);
				String sl= rs.getString(8);
				String fau= rs.getString(9);
				String ro= rs.getString(10);
				String dc= rs.getString(11);
				String lcrd= rs.getString(12);
				String fsd= rs.getString(13);
				String lm= rs.getString(14);
				String fc= rs.getString(15);
				String fs= rs.getString(16);
				String sg= rs.getString(17);
				String u= rs.getString(18);
				String fsu= rs.getString(19);
				String ctn= rs.getString(20);
				String fo= rs.getString(21);
				String roa= rs.getString(22);
				String ron= rs.getString(23);
				pstmt.setString(1, tr);
				pstmt.setString(2, is);
				pstmt.setString(3, cc);
				pstmt.setString(4, ccp);
				pstmt.setInt(5, trm);
				pstmt.setInt(6, p);
				pstmt.setString(7, vct);
				pstmt.setString(8, sl);
				pstmt.setString(9, fau);
				pstmt.setString(10, ro);
				pstmt.setString(11, dc);
				pstmt.setString(12, lcrd);
				pstmt.setString(13, fsd);
				pstmt.setString(14, lm);
				pstmt.setString(15, fc);
				pstmt.setString(16, fs);
				pstmt.setString(17, sg);
				pstmt.setString(18, u);
				pstmt.setString(19, fsu);
				pstmt.setString(20, ctn);
				pstmt.setString(21, fo);
				pstmt.setString(22, roa);
				pstmt.setString(23, ron);
				pstmt.executeUpdate();
			}
			//delete sorted rows from report
			sql = "DELETE FROM report WHERE `VTS Customer Type` = 'Mobile' OR `VTS Customer Type` = 'OTT'";
			stmt.executeUpdate(sql);
			//TR >0 and <100
			sql="SELECT * FROM report WHERE `Time Remaining ( Minutes )` > 0 AND `Time Remaining ( Minutes )` <= 100 ORDER BY `Priority+` ASC";
			rs = stmt.executeQuery(sql);
			pstmt=conn.prepareStatement(psql);
			while (rs.next())
			{
				String tr= rs.getString(1);
				String is= rs.getString(2);
				String cc= rs.getString(3);
				String ccp = rs.getString(4);
				int trm= rs.getInt(5);
				int p= rs.getInt(6);
				String vct= rs.getString(7);
				String sl= rs.getString(8);
				String fau= rs.getString(9);
				String ro= rs.getString(10);
				String dc= rs.getString(11);
				String lcrd= rs.getString(12);
				String fsd= rs.getString(13);
				String lm= rs.getString(14);
				String fc= rs.getString(15);
				String fs= rs.getString(16);
				String sg= rs.getString(17);
				String u= rs.getString(18);
				String fsu= rs.getString(19);
				String ctn= rs.getString(20);
				String fo= rs.getString(21);
				String roa= rs.getString(22);
				String ron= rs.getString(23);
				pstmt.setString(1, tr);
				pstmt.setString(2, is);
				pstmt.setString(3, cc);
				pstmt.setString(4, ccp);
				pstmt.setInt(5, trm);
				pstmt.setInt(6, p);
				pstmt.setString(7, vct);
				pstmt.setString(8, sl);
				pstmt.setString(9, fau);
				pstmt.setString(10, ro);
				pstmt.setString(11, dc);
				pstmt.setString(12, lcrd);
				pstmt.setString(13, fsd);
				pstmt.setString(14, lm);
				pstmt.setString(15, fc);
				pstmt.setString(16, fs);
				pstmt.setString(17, sg);
				pstmt.setString(18, u);
				pstmt.setString(19, fsu);
				pstmt.setString(20, ctn);
				pstmt.setString(21, fo);
				pstmt.setString(22, roa);
				pstmt.setString(23, ron);
				pstmt.executeUpdate();
			}
			sql = "DELETE FROM report WHERE `Time Remaining ( Minutes )` > 0 AND `Time Remaining ( Minutes )` <= 100";
			stmt.executeUpdate(sql);
			
			//********TR >=0
			sql="SELECT * FROM report WHERE `Time Remaining ( Minutes )` >= 0 ORDER BY `Priority+` ASC";
			rs = stmt.executeQuery(sql);
			pstmt=conn.prepareStatement(psql);
			while (rs.next())
			{
				String tr= rs.getString(1);
				String is= rs.getString(2);
				String cc= rs.getString(3);
				String ccp = rs.getString(4);
				int trm= rs.getInt(5);
				int p= rs.getInt(6);
				String vct= rs.getString(7);
				String sl= rs.getString(8);
				String fau= rs.getString(9);
				String ro= rs.getString(10);
				String dc= rs.getString(11);
				String lcrd= rs.getString(12);
				String fsd= rs.getString(13);
				String lm= rs.getString(14);
				String fc= rs.getString(15);
				String fs= rs.getString(16);
				String sg= rs.getString(17);
				String u= rs.getString(18);
				String fsu= rs.getString(19);
				String ctn= rs.getString(20);
				String fo= rs.getString(21);
				String roa= rs.getString(22);
				String ron= rs.getString(23);
				pstmt.setString(1, tr);
				pstmt.setString(2, is);
				pstmt.setString(3, cc);
				pstmt.setString(4, ccp);
				pstmt.setInt(5, trm);
				pstmt.setInt(6, p);
				pstmt.setString(7, vct);
				pstmt.setString(8, sl);
				pstmt.setString(9, fau);
				pstmt.setString(10, ro);
				pstmt.setString(11, dc);
				pstmt.setString(12, lcrd);
				pstmt.setString(13, fsd);
				pstmt.setString(14, lm);
				pstmt.setString(15, fc);
				pstmt.setString(16, fs);
				pstmt.setString(17, sg);
				pstmt.setString(18, u);
				pstmt.setString(19, fsu);
				pstmt.setString(20, ctn);
				pstmt.setString(21, fo);
				pstmt.setString(22, roa);
				pstmt.setString(23, ron);
				pstmt.executeUpdate();
			}
			sql="DELETE FROM report WHERE `Time Remaining ( Minutes )` >= 0 ";
			stmt.executeUpdate(sql);
			
			//Insert Remaining Values
			sql="SELECT * FROM report ORDER BY `Priority+` ASC";
			rs = stmt.executeQuery(sql);
			pstmt=conn.prepareStatement(psql);
			while (rs.next())
			{
				String tr= rs.getString(1);
				String is= rs.getString(2);
				String cc= rs.getString(3);
				String ccp = rs.getString(4);
				int trm= rs.getInt(5);
				int p= rs.getInt(6);
				String vct= rs.getString(7);
				String sl= rs.getString(8);
				String fau= rs.getString(9);
				String ro= rs.getString(10);
				String dc= rs.getString(11);
				String lcrd= rs.getString(12);
				String fsd= rs.getString(13);
				String lm= rs.getString(14);
				String fc= rs.getString(15);
				String fs= rs.getString(16);
				String sg= rs.getString(17);
				String u= rs.getString(18);
				String fsu= rs.getString(19);
				String ctn= rs.getString(20);
				String fo= rs.getString(21);
				String roa= rs.getString(22);
				String ron= rs.getString(23);
				pstmt.setString(1, tr);
				pstmt.setString(2, is);
				pstmt.setString(3, cc);
				pstmt.setString(4, ccp);
				pstmt.setInt(5, trm);
				pstmt.setInt(6, p);
				pstmt.setString(7, vct);
				pstmt.setString(8, sl);
				pstmt.setString(9, fau);
				pstmt.setString(10, ro);
				pstmt.setString(11, dc);
				pstmt.setString(12, lcrd);
				pstmt.setString(13, fsd);
				pstmt.setString(14, lm);
				pstmt.setString(15, fc);
				pstmt.setString(16, fs);
				pstmt.setString(17, sg);
				pstmt.setString(18, u);
				pstmt.setString(19, fsu);
				pstmt.setString(20, ctn);
				pstmt.setString(21, fo);
				pstmt.setString(22, roa);
				pstmt.setString(23, ron);
				pstmt.executeUpdate();
			}
			sql="DELETE FROM report";
			stmt.executeUpdate(sql);
			
			//Copy tickets back to report
			sql="SELECT * FROM tickets";
			rs=stmt.executeQuery(sql);
			psql = "INSERT INTO report" + "(`Ticket no. +`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`,`First_Accepted_User`," + 
					"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
					"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(psql);
			while (rs.next())
			{
				String tr= rs.getString(1);
				String is= rs.getString(2);
				String cc= rs.getString(3);
				String ccp = rs.getString(4);
				int trm= rs.getInt(5);
				int p= rs.getInt(6);
				String vct= rs.getString(7);
				String sl= rs.getString(8);
				String fau= rs.getString(9);
				String ro= rs.getString(10);
				String dc= rs.getString(11);
				String lcrd= rs.getString(12);
				String fsd= rs.getString(13);
				String lm= rs.getString(14);
				String fc= rs.getString(15);
				String fs= rs.getString(16);
				String sg= rs.getString(17);
				String u= rs.getString(18);
				String fsu= rs.getString(19);
				String ctn= rs.getString(20);
				String fo= rs.getString(21);
				String roa= rs.getString(22);
				String ron= rs.getString(23);
				pstmt.setString(1, tr);
				pstmt.setString(2, is);
				pstmt.setString(3, cc);
				pstmt.setString(4, ccp);
				pstmt.setInt(5, trm);
				pstmt.setInt(6, p);
				pstmt.setString(7, vct);
				pstmt.setString(8, sl);
				pstmt.setString(9, fau);
				pstmt.setString(10, ro);
				pstmt.setString(11, dc);
				pstmt.setString(12, lcrd);
				pstmt.setString(13, fsd);
				pstmt.setString(14, lm);
				pstmt.setString(15, fc);
				pstmt.setString(16, fs);
				pstmt.setString(17, sg);
				pstmt.setString(18, u);
				pstmt.setString(19, fsu);
				pstmt.setString(20, ctn);
				pstmt.setString(21, fo);
				pstmt.setString(22, roa);
				pstmt.setString(23, ron);
				pstmt.executeUpdate();
			}
		
			/* *********ASSIGNMENT STARTS********** */
			
				
				/* Keep only the required number of entries in report */
				
				sql = "SELECT * FROM report";
				rep = stmt.executeQuery(sql);
				int num_col=user_count*ticket_user;
				psql1="SELECT * FROM report LIMIT ?";
				pstmt1=conn.prepareStatement(psql1);
				pstmt1.setInt(1, num_col);
				rs=pstmt1.executeQuery();
				sql="DELETE FROM report";
				stmt.executeUpdate(sql);
				psql = "INSERT INTO report" + "(`Ticket no. +`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`,`First_Accepted_User`," + 
						"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
						"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(psql);
				while (rs.next())
				{
					String tr= rs.getString(1);
					String is= rs.getString(2);
					String cc= rs.getString(3);
					String ccp = rs.getString(4);
					int trm= rs.getInt(5);
					int p= rs.getInt(6);
					String vct= rs.getString(7);
					String sl= rs.getString(8);
					String fau= rs.getString(9);
					String ro= rs.getString(10);
					String dc= rs.getString(11);
					String lcrd= rs.getString(12);
					String fsd= rs.getString(13);
					String lm= rs.getString(14);
					String fc= rs.getString(15);
					String fs= rs.getString(16);
					String sg= rs.getString(17);
					String u= rs.getString(18);
					String fsu= rs.getString(19);
					String ctn= rs.getString(20);
					String fo= rs.getString(21);
					String roa= rs.getString(22);
					String ron= rs.getString(23);
					pstmt.setString(1, tr);
					pstmt.setString(2, is);
					pstmt.setString(3, cc);
					pstmt.setString(4, ccp);
					pstmt.setInt(5, trm);
					pstmt.setInt(6, p);
					pstmt.setString(7, vct);
					pstmt.setString(8, sl);
					pstmt.setString(9, fau);
					pstmt.setString(10, ro);
					pstmt.setString(11, dc);
					pstmt.setString(12, lcrd);
					pstmt.setString(13, fsd);
					pstmt.setString(14, lm);
					pstmt.setString(15, fc);
					pstmt.setString(16, fs);
					pstmt.setString(17, sg);
					pstmt.setString(18, u);
					pstmt.setString(19, fsu);
					pstmt.setString(20, ctn);
					pstmt.setString(21, fo);
					pstmt.setString(22, roa);
					pstmt.setString(23, ron);
					pstmt.executeUpdate();
				}
				
				/*Check for same country and Organization*/
				int i=0;
				int count[]=new int[user_count];
				psql= "INSERT INTO regp_assign" + "(`Ticket no. +`,`Assigned User`,`First_Accepted_User`,`Information Source`,`Condition Code`,`Condition Code Priority`,`Time Remaining ( Minutes )`,`Priority+`,`VTS Customer Type`,`Serv. Level`," + 
						"`Reporting Organization +`,`Destination Country+`,`LCR Destination`,`Fault Start Date (UTC)+`,`Last-modified-by`,`Fault Category`,`Fault Symptom`,`Support Group`,User,`Fault Summary`," +
						"`Carrier Ticket No`,`Fault Organization Name+`,`Rep. Org. Abbr.+`,`Ref. Report Org. No.`)"+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				psql1="DELETE FROM report WHERE `Ticket no. +` =?";
				pstmt=conn.prepareStatement(psql);
				pstmt1=conn.prepareStatement(psql1);
				for (int j=0;j<ticket_user;j++){              //Loop to iterate over number of tickets
					sql= "SELECT * FROM users";
					users = stmt.executeQuery(sql);
					i=0;
					while (users.next()&i<user_count){        //Loop to iterate over number of users
						sql= "SELECT * FROM report";
						rep = stmt1.executeQuery(sql);
						while (rep.next()&&count[i]<ticket_user){            //Loop to read report table
							String country = rep.getString("Destination Country+");     //assign country to country variable
							String org = rep.getString("Reporting Organization +"); //assign organization to org variable
							if (count[i]==ticket_user){
								break;
							}
							sql= "SELECT * FROM report";                 //Go back to start of report table
							rep = stmt1.executeQuery(sql);
								while(rep.next()){
									String country2=rep.getString("Destination Country+");   
									if (country2.equals(country)){         //check if country equal to country of ticket originally assigned
										String tr= rep.getString(1);       //if so assign ticket to original user
										String ass=users.getString("User");
										String fau= rep.getString(9);
										String is= rep.getString(2);
										String cc= rep.getString(3);
										String ccp = rep.getString(4);
										int trm= rep.getInt(5);
										int p= rep.getInt(6);
										String vct= rep.getString(7);
										String sl= rep.getString(8);
										String ro= rep.getString(10);
										String dc= rep.getString(11);
										String lcrd= rep.getString(12);
										String fsd= rep.getString(13);
										String lm= rep.getString(14);
										String fc= rep.getString(15);
										String fs= rep.getString(16);
										String sg= rep.getString(17);
										String u= rep.getString(18);
										String fsu= rep.getString(19);
										String ctn= rep.getString(20);
										String fo= rep.getString(21);
										String roa= rep.getString(22);
										String ron= rep.getString(23);
										pstmt.setString(1, tr);
										pstmt.setString(2, ass);
										pstmt.setString(3, fau);
										pstmt.setString(4, is);
										pstmt.setString(5, cc);
										pstmt.setString(6, ccp);
										pstmt.setInt(7, trm);
										pstmt.setInt(8, p);
										pstmt.setString(9, vct);
										pstmt.setString(10, sl);
										pstmt.setString(11, ro);
										pstmt.setString(12, dc);
										pstmt.setString(13, lcrd);
										pstmt.setString(14, fsd);
										pstmt.setString(15, lm);
										pstmt.setString(16, fc);
										pstmt.setString(17, fs);
										pstmt.setString(18, sg);
										pstmt.setString(19, u);
										pstmt.setString(20, fsu);
										pstmt.setString(21, ctn);
										pstmt.setString(22, fo);
										pstmt.setString(23, roa);
										pstmt.setString(24, ron);
										pstmt.executeUpdate();
										pstmt1.setString(1, tr);
										pstmt1.executeUpdate();
										count[i]++;
										if (count[i]==ticket_user){
											break;
										}
									}
								
							}
							if (count[i]==ticket_user){
								break;
							}
						sql= "SELECT * FROM report";
						rep = stmt1.executeQuery(sql);    
							while(rep.next()){
								String org2=rep.getString("Reporting Organization +");     //Now check if any organization equal
								if (org2.equals(org)){
									String tr= rep.getString(1);
									String ass=users.getString("User");
									String fau= rep.getString(9);
									String is= rep.getString(2);
									String cc= rep.getString(3);
									String ccp = rep.getString(4);
									int trm= rep.getInt(5);
									int p= rep.getInt(6);
									String vct= rep.getString(7);
									String sl= rep.getString(8);
									String ro= rep.getString(10);
									String dc= rep.getString(11);
									String lcrd= rep.getString(12);
									String fsd= rep.getString(13);
									String lm= rep.getString(14);
									String fc= rep.getString(15);
									String fs= rep.getString(16);
									String sg= rep.getString(17);
									String u= rep.getString(18);
									String fsu= rep.getString(19);
									String ctn= rep.getString(20);
									String fo= rep.getString(21);
									String roa= rep.getString(22);
									String ron= rep.getString(23);
									pstmt.setString(1, tr);
									pstmt.setString(2, ass);
									pstmt.setString(3, fau);
									pstmt.setString(4, is);
									pstmt.setString(5, cc);
									pstmt.setString(6, ccp);
									pstmt.setInt(7, trm);
									pstmt.setInt(8, p);
									pstmt.setString(9, vct);
									pstmt.setString(10, sl);
									pstmt.setString(11, ro);
									pstmt.setString(12, dc);
									pstmt.setString(13, lcrd);
									pstmt.setString(14, fsd);
									pstmt.setString(15, lm);
									pstmt.setString(16, fc);
									pstmt.setString(17, fs);
									pstmt.setString(18, sg);
									pstmt.setString(19, u);
									pstmt.setString(20, fsu);
									pstmt.setString(21, ctn);
									pstmt.setString(22, fo);
									pstmt.setString(23, roa);
									pstmt.setString(24, ron);
									pstmt.executeUpdate();
									pstmt1.setString(1, tr);
									pstmt1.executeUpdate();
									count[i]++;
									if (count[i]==ticket_user){
										break;
									}
								}
							
						}
					}
						i++;					
				}
			}
				File f = new File("Assign.csv");
				if(f.exists()){
					while(!f.delete()){
						JOptionPane.showMessageDialog(null,"Deleting"); 
						System.out.println("Deleting");
					}
				}
				path=path + "\\Assign.csv";
				psql= " SELECT * into outfile ? fields terminated by ',' enclosed by '\"' lines terminated by '\n' from regp_assign";
				pstmt=conn.prepareStatement(psql);
				try{
					System.out.println("Creating file");
					pstmt.setString(1, path);
					pstmt.executeQuery();
					stmt.executeQuery(sql);
				}catch(SQLException e){
					JOptionPane.showMessageDialog(null,e);
				}
			
			rs.close();
			rep.close();
			users.close();
			stmt.close();
			stmt1.close();
			pstmt.close();
			pstmt1.close();
			conn.close();
		}catch(SQLException se){
			se.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error with server!!");
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"ERROR!!!! Check readme files for more details.");
			System.exit(0);
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
				
			}try{
				if(stmt1!=null)
					stmt1.close();
			}catch(SQLException se2){
				
			}try{
				if(pstmt!=null)
					pstmt.close();
			}catch(SQLException se2){
				
			}try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		JOptionPane.showMessageDialog(null,"Success!!");
		JOptionPane.showMessageDialog(null, "A file called Call_Format will be opened. Call the mailing macro by clicking the format button."
				+ "\n (Assign.csv should also open automatically. If it doesn't, open it from the same directory as this program and the press the format button)");
		
		try{
			Desktop.getDesktop().open(new File("Assign.csv"));
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,e);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null,e);
		}
		try{
			Desktop.getDesktop().open(new File("Users.csv"));
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,e);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null,e);
		}
		try{
			Desktop.getDesktop().open(new File("Call_Format.xlsm"));
		}catch(IOException e){
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null,e);
		}
		/*
		try{
			Runtime.getRuntime().exec("cmd /c \"C:\\Users\\DM157\\Desktop\\myVBS.vbs\"");
		}catch(IOException e){
			e.printStackTrace();
		}*/
	
	}

}
