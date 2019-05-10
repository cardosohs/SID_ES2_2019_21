package pt.iscte.sid.sensor.main;

import org.bson.Document;

import pt.iscte.sid.sensor.connections.MongoCon;
import pt.iscte.sid.sensor.connections.MqttSubscriber;
import pt.iscte.sid.sensor.worker.Consumer;
import pt.iscte.sid.sensor.worker.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MagicSensorMigration {


    public static void main(String[] args) {


        /*
         * em principio não deve ser necessário alterar para Document...
         * serve para iniciar o Produtor e Consumidor com a mesma fila
         * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ArrayBlockingQueue.html
         */
        BlockingQueue<Document> myQueue = new ArrayBlockingQueue<>(1024);

        MongoCon.getInstance().connectMongoBd();
        MqttSubscriber.getInstance().connectSensor();

        // int numConsumers = 1;

        Producer myRunnable = new Producer(myQueue);
        new Thread(myRunnable).start();

        Consumer myRun = new Consumer(myQueue);
        new Thread(myRun).start();

    }

}




