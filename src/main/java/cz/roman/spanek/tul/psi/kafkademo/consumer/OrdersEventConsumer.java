package cz.roman.spanek.tul.psi.kafkademo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrdersEventConsumer {

    @KafkaListener(topics = "orders.events", groupId = "demo-orders")
    public void onMessage(String msg) {
      log.info("Consumed: {}", msg);
    }
}