package parse;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimpleQueue {

    private List<Map<?, ?>> list = new ArrayList();

    public List<Map<?, ?>> add(File input) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(input);
        this.list = mappingIterator.readAll();
        return this.list;
    }

    public void get(File output) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(output, this.list);
        this.list.remove(0);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }
}
