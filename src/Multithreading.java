import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter {
    int counter = 0;
    Lock lock = new ReentrantLock();

    public void incrementCounter() {
        lock.lock();
        try{
            counter++;
        }
        finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return counter;
    }
}

class NewThread implements Runnable {
    Counter counter;

    public NewThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.incrementCounter();
        }
    }
}

public class Multithreading {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread thread1 = new Thread(new NewThread(counter));
        Thread thread2 = new Thread(new NewThread(counter));
        Thread thread3 = new Thread(new NewThread(counter));
        Thread thread4 = new Thread(new NewThread(counter));
        Thread thread5 = new Thread(new NewThread(counter));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("Итоговый результат: %d", counter.getCounter());
    }
}
