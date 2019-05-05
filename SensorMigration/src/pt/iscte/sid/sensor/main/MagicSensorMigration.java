package pt.iscte.sid.sensor.main;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import pt.iscte.sid.sensor.worker.Consumer;
import pt.iscte.sid.sensor.worker.Producer;

public class MagicSensorMigration {

	public static void main(String[] args) {
		
		
		/* em principio não deve ser necessário alterar para Document...
		 * serve para iniciar o Produtor e Consumidor com a mesma fila
		 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html 
		*/
		BlockingQueue<Object> myQueue = new ArrayBlockingQueue<>(1024);
		
		//MongoCon.getInstance().connectMongoBd();
		//MqttSubscriber.getInstance().connectSensor();
		
		
		int numProducers = 1;
	    int numConsumers = 1;
	    
	    for (int i = 0; i < numProducers; i++){
	    	new Thread(new Producer(myQueue)).start();
	    }
	    /*
	    for (int i = 0; i < numConsumers; i++){
	    	new Thread(new Consumer(myQueue)).start();
	    }
		*/

	}

}
