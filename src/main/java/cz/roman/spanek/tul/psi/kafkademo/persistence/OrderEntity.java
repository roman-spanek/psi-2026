package cz.roman.spanek.tul.psi.kafkademo.persistence;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    protected OrderEntity() {}

    public OrderEntity(BigDecimal total) {
        this.total = total;
    }
}
