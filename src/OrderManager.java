import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Order {
    int id;
    String orderName;
    int price;

    public Order(int id, String orderName, int price) {
        this.id = id;
        this.orderName = orderName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{id=%d, name='%s', price=%d".formatted(id, orderName, price);
    }
}

class OrderProcessor {
    List<Order> orderList;
    Lock lock = new ReentrantLock();

    public OrderProcessor(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void priceFilter() {
        orderList.stream()
                .filter(order -> order.price > 5000)
                .sorted(Comparator.comparingInt(o -> o.price))
                .forEach(System.out::println);
    }
}

public class OrderManager {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                List<Order> orders = new ArrayList<Order>();
                for (int j = 0; j < 10; j++) {
                    orders.add(new Order(j, "order #%d".formatted(j), random.nextInt(1000, 10000)));
                }

                OrderProcessor processor = new OrderProcessor(orders);
                processor.priceFilter();
            });
        }

        executor.shutdown();
        System.out.println("Hello world");
    }
}
