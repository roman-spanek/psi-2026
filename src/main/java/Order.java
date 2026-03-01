import java.util.ArrayList;
import java.util.List;

public class Order {

    private Long id;
    private final List<OrderItem> items = new ArrayList<>();

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }
}
