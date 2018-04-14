package abaranov.csvToJson;

import java.io.File;
import java.io.IOException;

public class ProducerConsumer {
    private SimpleQueue queue = new SimpleQueue();
    private File input;
    private File output;

    public ProducerConsumer(File input, File output) {
        this.input = input;
        this.output = output;
    }

    private final Object lock = new Object();

    public void getElement() throws InterruptedException{
        synchronized (this.lock) {
            if (this.queue.isEmpty()) {
                System.out.println("no records available");
                lock.wait();
            }
            try {
                queue.get(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addElement() {
        synchronized (this.lock) {
            try {
                System.out.println(queue.add(input));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!this.queue.isEmpty()) {
                this.lock.notify();
            }
        }
    }
}
