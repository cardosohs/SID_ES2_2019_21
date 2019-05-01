package pt.iscte.sid.sensor.connections;

public class MongoCon {
	
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
