package pt.iscte.sid.sensor.main;

import org.bson.Document;

import pt.isce.sid.sensor.utils.Log;
import pt.isce.sid.sensor.utils.TypeLog;
import pt.iscte.sid.sensor.connections.MongoCon;
import pt.iscte.sid.sensor.connections.MqttSubscriber;
import pt.iscte.sid.sensor.worker.Consumer;
import pt.iscte.sid.sensor.worker.Producer;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MagicSensorMigration {


    public static void main(String[] args) throws IOException {
    	
    	Log lg = new Log();
    	lg.log("Log Start", TypeLog.INITIAL);
		

        /*
         * serve para iniciar o Produtor e Consumidor com a mesma fila
         * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html
         */
        BlockingQueue<Document> myQueue = new ArrayBlockingQueue<>(1024);
        
        lg.log("Connecting to mongoDb", TypeLog.INITIAL);
        MongoCon.getInstance().connectMongoBd(lg);
        lg.log("Connecting to Mqtt Broker", TypeLog.INITIAL);
        MqttSubscriber.getInstance().connectSensor(lg);

        // int numConsumers = 1;
        
        lg.log("Starting Worker Thread Document Producer", TypeLog.INITIAL);
        Producer myRunnable = new Producer(myQueue);
        new Thread(myRunnable).start();
        
        lg.log("Starting Worker Thread Failed Document Consumer", TypeLog.INITIAL);
        Consumer myRun = new Consumer(myQueue);
        new Thread(myRun).start();

    }

}




