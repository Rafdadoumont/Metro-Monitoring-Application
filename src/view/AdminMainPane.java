package view;

import controller.ControlCenterPaneController;
import controller.MetroCardOverviewPaneController;

import controller.SettingsPaneController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class AdminMainPane extends BorderPane {
    private final ControlCenterPane controlCenterPane;
    private final MetroCardOverviewPane metroCardOverviewPane;

    private final SettingsPane settingsPane;

    public AdminMainPane(){
	    TabPane tabPane = new TabPane();

        metroCardOverviewPane = new MetroCardOverviewPane();
        controlCenterPane = new ControlCenterPane();
        settingsPane = new SettingsPane();

        Tab metroCardOverviewTab = new Tab("Metro cards overview",metroCardOverviewPane);
        Tab controlCenterTab = new Tab("Control Center",controlCenterPane);
        Tab settingsTab = new Tab("Settings",settingsPane);
        tabPane.getTabs().add(controlCenterTab);
        tabPane.getTabs().add(metroCardOverviewTab);
        tabPane.getTabs().add(settingsTab);
        this.setCenter(tabPane);
	}

    public ControlCenterPane getControlCenterPane() {
        return this.controlCenterPane;
    }
    public MetroCardOverviewPane getMetroCardOverviewPane() {
        return this.metroCardOverviewPane;
    }
    public SettingsPane getSettingsPane() {
        return settingsPane;
    }

    public void setControlCenterPaneController(ControlCenterPaneController controller) {
        this.controlCenterPane.setControlCenterPaneController(controller);
    }

    public void setMetroCardOverviewPaneController(MetroCardOverviewPaneController controller) {
        this.metroCardOverviewPane.setMetroCardOverviewPaneController(controller);
    }

    public void setSettingsPaneController(SettingsPaneController settingsPaneController) {
        this.settingsPane.setSettingsPaneController(settingsPaneController);
    }
}
