package pt.iscte.sid.sensor.worker;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable  {
	
	protected BlockingQueue<Object> queue;
	
	public Consumer(BlockingQueue<Object> theQueue) {
        this.queue = theQueue;
    }

	@Override
	public void run() {
		
		
	}

}
