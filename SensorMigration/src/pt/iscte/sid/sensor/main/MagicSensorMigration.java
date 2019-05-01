package pt.iscte.sid.sensor.main;



import pt.iscte.sid.sensor.connections.MongoCon;
import pt.iscte.sid.sensor.connections.MqttSubscriber;

public class MagicSensorMigration {

	public static void main(String[] args) {
		
		
		/* em principio não deve ser necessário alterar para Document...
		 * serve para iniciar o Produtor e Consumidor com a mesma fila
		 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html 
		*/
		//BlockingQueue<Object> myQueue = new ArrayBlockingQueue<>(1024);
		
		MqttSubscriber.getInstance().connectSensor();
		MongoCon.getInstance().connectMongoBd();
		
		
		

	}

}
