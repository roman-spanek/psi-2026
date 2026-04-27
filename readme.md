# 1) docker-compose: Kafka + Postgres (lokálně)
- Vytvorit docker-compose.yml
- docker compose up -d
# 2) Spring Boot projekt 
- Vytvorit Spring Boot projekt (pom.xml)
- Vytvorit konfiguraci src/main/resources/application.yml
- Pro demo použijeme Spring init skript. Ulož jako src/main/resources/schema.sql
- OrderEntity
- OutboxEventEntity
- OrderRepository
- OutboxEventRepository
- OrderService
- OrderController
- REST controller pro vytvoření objednávky
# 3) Kafka Producer
- OutboxPublisher
- Použít @Scheduled pro periodické odesílání zpráv z Outboxu do Kafka topicu
- Kafka consumer (jen log)
# 4) Testování
- Spustit docker:  docker compose up -d
- Spustit aplikaci: mvn spring-boot:run
- Vytvoř objednávku:
``curl -X POST http://localhost:8080/orders \
  -H 'Content-Type: application/json' \
  -d '{"total": "123.45"}'``
# 5) Co je v tom demo „správně“ a co je zjednodušené
Správně (pattern):
- business změna + outbox v jedné DB transakci
- publish mimo transakci (poller)
- SKIP LOCKED pro paralelizaci publisherů
Zjednodušeno:
- payload je string (lepší je mít jasný event model + schema verzi)
- publish ack řešíme join() (pro výkon by se řešilo batch/async)
- chybí DLQ a limit attempts (v praxi přidat)