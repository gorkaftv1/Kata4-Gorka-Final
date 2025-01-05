package software.ulpgc.arquitecture.io;

import software.ulpgc.arquitecture.model.Wood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWoodLoader implements Loader {
    private final TsvWoodDeserializer deserializer;
    private final BufferedReader reader;

    public FileWoodLoader(File file, TsvWoodDeserializer tsvWoodDeserializer) throws IOException {
        this.deserializer = tsvWoodDeserializer;
        reader = new BufferedReader(new FileReader(file));
        skipHeader();
    }

    private void skipHeader() throws IOException {
        reader.readLine();
    }

    @Override
    public List<Wood> load() {
        List<Wood> woods = new ArrayList<>();
        while (true){
            try {
                String line = reader.readLine();
                if (line == null)  break;
                woods.add(deserializer.deserialize(line));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return woods;
    }
}
