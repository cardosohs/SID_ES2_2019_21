package pt.iscte.sid.sensor.main;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import pt.iscte.sid.sensor.connections.MqttSubscriber;
import pt.iscte.sid.sensor.worker.Producer;

public class MagicSensorMigration {
	
	

	public static void main(String[] args) {
		
		
		/* 
		 * em principio não deve ser necessário alterar para Document...
		 * serve para iniciar o Produtor e Consumidor com a mesma fila
		 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html 
		*/
		BlockingQueue<Object> myQueue = new ArrayBlockingQueue<>(1024);
		
		
		//MongoCon.getInstance().connectMongoBd();
		MqttSubscriber.getInstance().connectSensor();		
		
	   // int numConsumers = 1;
		
		Producer myRunnable = new Producer(myQueue);
		new Thread(myRunnable).start();	    
	   
	    /*
	    for (int i = 0; i < numConsumers; i++){
	    	new Thread(new Consumer(myQueue)).start();
	    }
		*/

	}

}
