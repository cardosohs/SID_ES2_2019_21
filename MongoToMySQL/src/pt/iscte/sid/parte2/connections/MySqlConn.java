package pt.iscte.sid.parte2.connections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pt.iscte.sid.parte2.util.Config;
import pt.iscte.sid.parte2.util.Log;
import pt.iscte.sid.parte2.util.TypeLog;



public class MySqlConn {

	private Connection con;
	private Log logFile;
	
	public MySqlConn(Config confFile, Log logFile) throws IOException {

		this.logFile = logFile;		
		
		String user = confFile.getMySqlUser();
		String pass = confFile.getMySqlPass();//sim			
		String databasename = confFile.getMySqlDbName();//sim
		String myDriver = "com.mysql.cj.jdbc.Driver";
		String myUrl = "jdbc:mysql://localhost/" + databasename + "?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName(myDriver);
			con = DriverManager.getConnection(myUrl, user, pass);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void insertToMysql(String sqlString) throws SQLException, IOException {
		//String sqlString1 ="('tmp', '27.00', '2019-05-14 20:01:47.0')";
		try
	    {
		String sqlQuery = "INSERT INTO sensormigracaolog"
				+ " (sensor, medicao, datetime_sensor)" + " VALUES " + sqlString;

		System.out.println(sqlQuery);
		
		Statement stmt = con.createStatement();
		stmt.execute(sqlQuery);
		stmt.close();
		}
        catch (SQLException sqe)
        {
        	logFile.log("SQL Exception: " +
                    sqe.toString() + ", sqlstate = " +
                    sqe.getSQLState(), TypeLog.ERROR);
        	System.out.println("SQL Exception: " +
                                sqe.toString() + ", sqlstate = " +
                                sqe.getSQLState());
            System.exit(1);
        }
	}
	
}