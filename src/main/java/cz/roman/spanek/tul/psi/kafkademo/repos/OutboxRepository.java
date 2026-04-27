package cz.roman.spanek.tul.psi.kafkademo.repos;

import cz.roman.spanek.tul.psi.kafkademo.persistence.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutboxRepository extends JpaRepository<OutboxEventEntity, Long> {}