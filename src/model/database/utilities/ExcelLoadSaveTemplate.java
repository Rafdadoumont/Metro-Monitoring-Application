package model.database.utilities;

import java.io.File;
import java.io.IOException;
import java.util.*;

import jxl.read.biff.BiffException;
import jxl.write.*;

public abstract class ExcelLoadSaveTemplate<K,V> {
    private final String path;
    private final ExcelPlugin excelPlugin = new ExcelPlugin();

    public ExcelLoadSaveTemplate(String path) {
        this.path = path;
    }

    protected abstract V makeObject(List<String> tokens);

    protected abstract K getKey(List<String> tokens);

    protected abstract List<String> formatObject(Map.Entry<K, V> entry);

    public final Map<K, V> load() {
        List<List<String>> data;
        try {
            data = this.excelPlugin.read(new File(this.path));
        } catch (BiffException | IOException e) {
            e.printStackTrace();
            return null;
        }

        Map<K, V> result = new HashMap<>();
        for (List<String> tokens : data) {
            result.put(getKey(tokens), makeObject(tokens));
        }

        return result;
    }

    public final void save(Map<K, V> data) {
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<K, V> entry : data.entrySet()) {
            result.add(formatObject(entry));
        }

        try {
            this.excelPlugin.write(new File(this.path), result);
        } catch (WriteException | IOException e) {
            e.printStackTrace();
        }
    }
}
