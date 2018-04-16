package abaranov.csvToJson;

import java.io.File;

public class ProducerConsumer {
//    private SimpleQueue queue = new SimpleQueue();
    private SimpleQueue list = new SimpleQueue();
    private File input;
    private File output;

    public ProducerConsumer(File input, File output) {
        this.input = input;
        this.output = output;
    }

    private final Object lock = new Object();

    public void getElement() throws InterruptedException{
        synchronized (this.lock) {
            if (this.list.isEmpty()) {
                System.out.println("no records available");
                lock.wait();
            }
                list.get(output);
        }
    }

    public void addElement() {
        synchronized (this.lock) {
                System.out.println(list.add(input));
            if (!this.list.isEmpty()) {
                this.lock.notify();
            }
        }
    }
}
