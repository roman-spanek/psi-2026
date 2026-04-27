package cz.roman.spanek.tul.psi.kafkademo.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class OutboxPublisher {
    private final JdbcTemplate jdbc;
    private final KafkaTemplate<String, String> kafka;

    public OutboxPublisher(JdbcTemplate jdbc, KafkaTemplate<String, String> kafka) {
        this.jdbc = jdbc;
        this.kafka = kafka;
    }

    @Scheduled(fixedDelay = 500)
    @Transactional
    public void publishBatch() {
        List<Map<String, Object>> rows = jdbc.queryForList("""
      SELECT id, aggregate_id, event_type, payload
      FROM outbox_events
      WHERE published_at IS NULL
      ORDER BY created_at
      FOR UPDATE SKIP LOCKED
      LIMIT 100
    """);

        for (Map<String, Object> r : rows) {
            long id = ((Number) r.get("id")).longValue();
            String aggregateId = r.get("aggregate_id").toString();
            String eventType = r.get("event_type").toString();
            String payload = r.get("payload").toString();

            try {
                kafka.send("orders.events", aggregateId, eventType + ":" + payload).join();
                jdbc.update("UPDATE outbox_events SET published_at = now() WHERE id = ?", id);
            } catch (Exception e) {
                jdbc.update("UPDATE outbox_events SET publish_attempts = publish_attempts + 1 WHERE id = ?", id);

                log.error("Publish failed for outbox id={}, err= {}", id,  e.getMessage());
            }
        }
    }
}
