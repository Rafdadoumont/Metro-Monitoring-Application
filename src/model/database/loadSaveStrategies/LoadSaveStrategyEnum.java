package model.database.loadSaveStrategies;

public enum LoadSaveStrategyEnum {
    TEXT("metrocards.txt", "model.database.loadSaveStrategies.MetrocardsTextLoadSaveStrategy"),
    EXCEL("metrocards.xls", "model.database.loadSaveStrategies.MetrocardsExcelLoadSaveStrategy");

    private final String path;
    private final String className;

    LoadSaveStrategyEnum(String path, String className){
        this.path = path;
        this.className = className;
    }

    public String getPath() {
        return path;
    }

    public String getClassName() {
        return className;
    }
}
