package pt.iscte.sid.parte2.Main;

import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;
import pt.iscte.sid.parte2.Worker.Producer;
import pt.iscte.sid.parte2.util.Config;
import pt.iscte.sid.parte2.util.Log;
import pt.iscte.sid.parte2.util.TypeLog;

public class MongoToMySqlMagic {

	public static void main(String[] args) throws IOException, ParseException, SQLException {
				
		Log logFile = new Log();
		logFile.log("Log Start", TypeLog.INITIAL);
				
		Config settings = new Config();		
		try {
			settings.loadParameters();
			logFile.log("Settings File read", TypeLog.NORMAL);
		} catch (IOException | ParseException e) {
			logFile.log("Couldn't read Settings File", TypeLog.ERROR);
		}
				
		logFile.log("Mongo To MySql initialized", TypeLog.NORMAL);
		Producer.startProgram(settings, logFile);
	}

}

