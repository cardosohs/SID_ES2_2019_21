package pt.iscte.sid.parte2.Worker;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;

import pt.iscte.sid.parte2.connections.MongoConn;
import pt.iscte.sid.parte2.connections.MySqlConn;
import pt.iscte.sid.parte2.util.Config;
import pt.iscte.sid.parte2.util.Log;
import pt.iscte.sid.parte2.util.TypeLog;

public class Producer {

	final static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	public static void startProgram(Config confFile, Log logFile) throws IOException, ParseException, SQLException {

		logFile.log("Program started.", TypeLog.NORMAL);

		long periodicity = Long.parseLong(confFile.getPeriodicity());		
		MySqlConn mysql = new MySqlConn(confFile, logFile);
		MongoConn mongo = new MongoConn(confFile, logFile);		
		executorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				try {
					startMigration(logFile, mongo, mysql);
				} catch (IOException | ParseException | SQLException e) {
					e.printStackTrace();
				}
			}
		}, 0, periodicity, TimeUnit.SECONDS);

	}

	public static void startMigration(Log logFile, MongoConn mongo, MySqlConn mysql)
			throws IOException, ParseException, SQLException {

		try {
			logFile.log("Extraction started getting data from MongoDB", TypeLog.NORMAL);

			mongo.getDataFromMongoDb(); //inicializa iteradores e conta quanto docs quer passar
		
			String sqlString = mongo.prepareDataToMySql();

			if(!sqlString.equals("")) {
				// insert query to mysql
				mysql.insertToMysql(sqlString);
				logFile.log("Data inserted to Mysql.", TypeLog.NORMAL);

				mongo.deleteAllInCollection();
				logFile.log("Migration Finished. Documents in collection deleted.", TypeLog.NORMAL);
			} else {
				System.out.println("All done! no data to migrate.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}

