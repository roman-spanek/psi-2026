package cz.roman.spanek.tul.psi.kafkademo.controller;

import cz.roman.spanek.tul.psi.kafkademo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> body) throws Exception {
        BigDecimal total = new BigDecimal(body.get("total").toString());
        long id = service.createOrder(total);
        return Map.of("orderId", id);
    }
}