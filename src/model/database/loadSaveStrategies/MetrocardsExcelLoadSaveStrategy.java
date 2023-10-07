package model.database.loadSaveStrategies;

import model.Metrocard;
import model.database.utilities.ExcelLoadSaveTemplate;

import java.time.Month;
import java.time.Year;
import java.util.*;

import static java.lang.Integer.parseInt;

public class MetrocardsExcelLoadSaveStrategy extends ExcelLoadSaveTemplate<Integer, Metrocard> implements LoadSaveStrategy<Integer, Metrocard> {
    public MetrocardsExcelLoadSaveStrategy(String path) {
        super(path);
    }

    @Override
    protected Integer getKey(List<String> tokens) {
        return parseInt(tokens.get(0));
    }

    @Override
    protected Metrocard makeObject(List<String> tokens) {
        String[] dateTokens = tokens.get(1).split("#");
        return new Metrocard(parseInt(tokens.get(0)), Month.of(parseInt(dateTokens[0])), Year.parse(dateTokens[1]), parseInt(tokens.get(2)), parseInt(tokens.get(3)));
    }

    @Override
    protected List<String> formatObject(Map.Entry<Integer, Metrocard> entry) {
        Metrocard m = entry.getValue();
        return Arrays.asList(String.valueOf(entry.getKey()), String.format("%d#%d", m.getMonth().getValue(), m.getYear().getValue()), String.valueOf(m.getAvailableTrips()), String.valueOf(m.getUsedTrips()));
    }
}
