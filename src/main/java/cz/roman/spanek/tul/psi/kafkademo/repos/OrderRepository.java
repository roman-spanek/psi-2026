package cz.roman.spanek.tul.psi.kafkademo.repos;

import cz.roman.spanek.tul.psi.kafkademo.persistence.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}