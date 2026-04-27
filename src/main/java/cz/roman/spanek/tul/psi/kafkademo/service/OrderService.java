package cz.roman.spanek.tul.psi.kafkademo.service;

import cz.roman.spanek.tul.psi.kafkademo.persistence.OrderEntity;
import cz.roman.spanek.tul.psi.kafkademo.persistence.OutboxEventEntity;
import cz.roman.spanek.tul.psi.kafkademo.repos.OrderRepository;
import cz.roman.spanek.tul.psi.kafkademo.repos.OutboxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orders;
    private final OutboxRepository outbox;
    private final ObjectMapper om = new ObjectMapper();

    public OrderService(OrderRepository orders, OutboxRepository outbox) {
        this.orders = orders;
        this.outbox = outbox;
    }

    @Transactional
    public long createOrder(BigDecimal total) {
        OrderEntity order = orders.save(new OrderEntity(total));

        String payload = om.writeValueAsString(Map.of(
                "orderId", order.getId(),
                "total", order.getTotal().toString()
        ));

        outbox.save(new OutboxEventEntity(
                "Order",
                String.valueOf(order.getId()),
                "OrderCreated",
                payload
        ));

        return order.getId();
    }
}
