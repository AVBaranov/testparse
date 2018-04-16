package abaranov.csvToJson;

import abaranov.Item;
import au.com.bytecode.opencsv.CSVReader;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleQueue {
    private List<String> jsons = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public List<String> add(File input) {
        List<Item> items = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(input));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                items.add(new Item(Integer.parseInt(nextLine[0]), Integer.parseInt(nextLine[1]), nextLine[2], nextLine[3]));
            }
            for (Item item : items) {
                this.jsons.add(this.mapper.writeValueAsString(item));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.jsons;
    }

    public void get(File output)  {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(output));
            for (String json : jsons) {
                pw.print(json);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
        this.jsons.remove(0);
    }

    public boolean isEmpty() {
        return this.jsons.isEmpty();
    }
}
