package controller;

import model.MetroFacade;
import model.Observer;
import view.MetroCardOverviewPane;
import view.MetroStationView;
import view.MetroTicketView;
import view.SettingsPane;

import java.util.List;

public class SettingsPaneController {
    MetroFacade metroFacade;
    SettingsPane settingsPane;

    public SettingsPaneController(MetroFacade metroFacade, SettingsPane settingsPane) {
        this.metroFacade = metroFacade;
        this.settingsPane = settingsPane;
    }

    public List<String> getSelectedDiscounts() {
        return metroFacade.getSelectedDiscounts();
    }

    public List<String> getAllDiscounts() {
        return metroFacade.getAllDiscounts();
    }

    public String getSelectedLoadSaveStrategy() {
        return metroFacade.getSelectedLoadStrategy();
    }

    public List<String> getAllLoadStrategies() {
        return metroFacade.getAllLoadStrategies();
    }

    public void saveSettings(List<String> discountSelected, List<String> strategySelected) {
        metroFacade.saveSettings(discountSelected, strategySelected);
    }
}
