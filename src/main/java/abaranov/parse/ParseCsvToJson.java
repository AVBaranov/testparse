package abaranov.parse;

import abaranov.csvToJson.ProducerConsumer;

import java.io.File;

public class ParseCsvToJson implements Parse{
    @Override
    public void parse(String input, String output) {
        ProducerConsumer blockingWork = new ProducerConsumer(new File(input), new File(output));
        Thread consumer = new Thread() {
            @Override
            public void run() {
                try {
                    blockingWork.getElement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread producer = new Thread() {
            @Override
            public void run() {
                blockingWork.addElement();
            }
        };
        producer.start();
        consumer.start();
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
