package pt.iscte.sid.sensor.connections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber {	
	
	String topic = "/sid_lab_2019_2";
	String broker = "tcp://broker.mqtt-dashboard.com:1883";
	String clientId = "Client1";
	String dbName = "sensorbd";
	String colName = "MedicoesSensor";
	MemoryPersistence persistence = new MemoryPersistence();
	
	/**
	 * Implementação de uma singleton pattern para garantir que só existe 1 instância a correr
	 */
	private MqttSubscriber() {};
	
	private static class MqttSubscriberHelper{
		private static final MqttSubscriber INSTANCE = new MqttSubscriber();
	}
	
	public static MqttSubscriber getInstance() {
		return MqttSubscriberHelper.INSTANCE;
	}
	
	/**
	 * Metodo para ligar ao sensor e receber as mensagens
	 */
	
	public void connectSensor() {
		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			sampleClient.connect(connOpts);

			sampleClient.subscribe(topic);
			sampleClient.setCallback(new MqttCallback() {
				public void connectionLost(Throwable throwable) {
					throwable.printStackTrace();
				}				
				/* mqttMessage Sample 
				 {"tmp":"23.60","hum":"60.50","dat":"1/5/2019","tim":"11:43:11","cell":"1851""sens":"wifi"}
				*/
				public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
					System.out.println("Topic : " + topic + " Message : " + mqttMessage);					
				}

				public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
					System.out.println("Delivery complete : " + iMqttDeliveryToken);
				}
			});
			//timer para terminar recolha de info ... alterar conforme necessário
			new CountDownLatch(1).await(100, TimeUnit.SECONDS);
			sampleClient.disconnect();            
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
			
	}

}
