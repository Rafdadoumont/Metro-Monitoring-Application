package model.database.utilities;

import java.io.*;
import java.util.*;

public abstract class TextLoadSaveTemplate<K,V> {
    private final String path;

    public TextLoadSaveTemplate(String path) {
        this.path = path;
    }

    protected abstract V makeObject(String[] tokens);

    protected abstract K getKey(String[] tokens);

    protected abstract List<String> formatObject(Map.Entry<K, V> entry);

    public final Map<K,V> load() {
        Map<K,V> returnMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(";");
                K key = getKey(tokens);
                V element = makeObject(tokens);
                returnMap.put(key,element);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnMap;
    }

    public final void save(Map<K,V> data) {
        String out = "";

        for (Map.Entry<K, V> entry : data.entrySet()) {
            List<String> object = formatObject(entry);
            for (int i = 0; i < object.size(); i++) {
                out += object.get(i);
                if (i < object.size()-1) {
                    out += ";";
                }
            }
            out += "\n";
        }

        try {
            FileWriter writer = new FileWriter(path);
            writer.write(out);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
