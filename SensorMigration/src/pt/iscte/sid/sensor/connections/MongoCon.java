package pt.iscte.sid.sensor.connections;

public class MongoCon {
	
	private String dbName = "sensorbd";
	private String colName = "MedicoesSensor";
	private Boolean isMongoAlive = false; // flag para determinar se a coneção existe
	
	/**
	 * Implementação de uma singleton pattern para garantir que só existe 1 instância a correr
	 */
	private MongoCon() {};
	
	private static class MongoConHelper{
		private static final MongoCon INSTANCE = new MongoCon();
	}
	
	public static MongoCon getInstance() {
		return MongoConHelper.INSTANCE;
	}
	
	
	public void connectMongoBd() {
		
		
		
	}

}
