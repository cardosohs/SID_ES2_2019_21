package pt.iscte.sid.projeto.sensor;

import org.bson.Document;

import pt.iscte.sid.projeto.sensor.MongoCon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {

    protected BlockingQueue<Document> failedDocuments;

    public Consumer(BlockingQueue<Document> failedDocuments) {
        this.failedDocuments = failedDocuments;
    }

    @Override
    public void run() {
        final AtomicBoolean failedInsert = new AtomicBoolean(false);

        try{
            while(!Thread.interrupted()){
                final Document document = failedDocuments.take();
                MongoCon.getInstance().getCollection().insertOne(document, (result, e) -> {
                    if( e != null){
                        failedDocuments.add(document);
                        failedInsert.lazySet(true);
                    }
                });
                if(failedInsert.get()){
                    Thread.sleep(100);
                    failedInsert.set(false);
                }
            }
        }catch (InterruptedException ignore){

        }



    }
}

