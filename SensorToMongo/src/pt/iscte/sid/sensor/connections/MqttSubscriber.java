package pt.iscte.sid.sensor.connections;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import pt.isce.sid.sensor.utils.Log;
import pt.isce.sid.sensor.utils.TypeLog;

public class MqttSubscriber {

    //private String topic = "/sid_lab_2019_2";
    private String topic = "/sid_lab_2019";
    //private String broker = "tcp://broker.hivemq.com:1883";
    private String broker = "tcp://iot.eclipse.org:1883";
    //private String clientId = "Grupo21SID2019";
    private String clientId = UUID.randomUUID().toString();
    private Boolean isMqttAlive = false; // flag para determinar se a conexão existe
    MemoryPersistence persistence = new MemoryPersistence();
    MqttClient Client;
   
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

    public void connectSensor(Log logFile) throws IOException{
        try {
        	
            Client = new MqttClient(broker, clientId, persistence);
            logFile.log("Connected to Mqtt Broker 'tcp://iot.eclipse.org:1883'", TypeLog.NORMAL);  
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            connOpts.setAutomaticReconnect(true);
            
            Client.connect(connOpts);
            Client.subscribe(topic);
            logFile.log("Topic subscribed '/sid_lab_2019'", TypeLog.NORMAL); 

        } catch (MqttException e) {
            e.printStackTrace();
        }
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
}



