package com.kafka_backend.producer.config.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StringProducerServices {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message){
        kafkaTemplate.send("str-topic", message).whenComplete((result, ex)->{
            if(ex!=null){
                log.error("Error al evaluar el mensaje: {}", ex.getMessage());
            }
            log.info("Mensaje enviado con exito: {}", result.getProducerRecord().value());
            log.info("Partition {}, offset {}", result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
        });
    }
}
