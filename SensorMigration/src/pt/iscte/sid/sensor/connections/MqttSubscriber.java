package pt.iscte.sid.sensor.connections;



import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber {	
	
	private String topic = "/sid_lab_2019";
	private String broker = "tcp://iot.eclipse.org:1883";
	private String clientId = "Grupo21SID2019";
	private Boolean isMqttAlive = false; // flag para determinar se a conexão existe
	MemoryPersistence persistence = new MemoryPersistence();
	MqttClient Client;
	private String teste="";
		
	/**
	 * Implementação de uma singleton pattern para garantir que só existe 1 instância a correr
	 */
	private MqttSubscriber() {};
	
	private static class MqttSubscriberHelper {
		private static final MqttSubscriber INSTANCE = new MqttSubscriber();
	}
	
	public static MqttSubscriber getInstance() {
		return MqttSubscriberHelper.INSTANCE;
	}
	
	/**
	 * Metodo para ligar ao sensor e receber as mensagens
	 */
	
	public void connectSensor(){
		try {
			Client = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			Client.connect(connOpts);
			Client.subscribe(topic);
			Client.setCallback(new MqttCallback() {
				public void connectionLost(Throwable throwable) {
					throwable.printStackTrace();
				}				
				/* mqttMessage Sample 
				 {"tmp":"23.60","hum":"60.50","dat":"1/5/2019","tim":"11:43:11","cell":"1851""sens":"wifi"}
				*/
				public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
					System.out.println("Topic : " + topic + " Message : " + mqttMessage);
					msg(mqttMessage.toString());
					
				}

				public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
					System.out.println("Delivery complete : " + iMqttDeliveryToken);
				}
			});
			//timer para terminar recolha de info ... alterar conforme necessário
			//new CountDownLatch(1).await(30, TimeUnit.SECONDS);
			//Client.disconnect();
			
		//} catch (MqttException | InterruptedException e) {	
		} catch (MqttException e) {
			e.printStackTrace();
		}
			
	}
	
	public String msg(String s) {
		return s;
	}

	public Boolean getIsMqttAlive() {
		return isMqttAlive;
	}

	public void setIsMqttAlive(Boolean isMqttAlive) {
		this.isMqttAlive = isMqttAlive;
	}

	public MqttClient getClient() {
		return Client;
	}

	public String getTeste() {
		return teste;
	}
	
	
	
	
	
}
