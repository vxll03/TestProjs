import java.util.ArrayList;
import java.util.List;

public class GcCollect {
    public static void main(String[] args) {
        List<byte[]> memoryConsumers = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Свободная память до выделения объектов: " + runtime.freeMemory());
        for (int i = 0; i < 100; i++) {
            memoryConsumers.add(new byte[1024 * 1024]); // Создаем объекты 1 МБ
            if (i % 100 == 0) {
                System.out.println("Создано " + i + " объектов");
            }
        }

        System.out.println("Свободная память перед GC: " + runtime.freeMemory());

        memoryConsumers.clear();
        System.gc();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Свободная память после GC: " + runtime.freeMemory());
    }
}
