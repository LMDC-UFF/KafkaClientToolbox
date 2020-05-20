package br.uff.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaApplication {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    public KafkaApplication(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic-sender}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @KafkaListener(topics = "${kafka.topic-sender}", containerFactory = "kafkaListenerContainerFactory", autoStartup = "${kafka.auto-start}")
    public void onRecieve(String msg) {
        System.out.println("Recebendo msg: >" + msg + "<");
    }

    public void send(String msg) {
        System.out.println("Enviando para o tópico: " + topic + ", a msg " + msg);
        kafkaTemplate.send(this.topic, msg);
    }
}
