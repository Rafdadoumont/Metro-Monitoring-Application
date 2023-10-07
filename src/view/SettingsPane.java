package view;


import controller.SettingsPaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsPane extends GridPane {

    SettingsPaneController settingsPaneController;
    List<String> allDiscounts;
    List<String> selectedDiscounts;

    List<String> allLoadStrategies;

    String selectedLoadStrategy;


    public SettingsPane() {

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.add(new Label("Settings:"), 0, 0, 1, 1);

    }

    public void setSettingsPaneController(SettingsPaneController settingsPaneController) {
        this.settingsPaneController = settingsPaneController;
        this.allDiscounts = this.settingsPaneController.getAllDiscounts();
        this.selectedDiscounts = this.settingsPaneController.getSelectedDiscounts();
        this.allLoadStrategies = this.settingsPaneController.getAllLoadStrategies();
        this.selectedLoadStrategy = this.settingsPaneController.getSelectedLoadSaveStrategy();
        List<RadioButton> discountRadioButtons = new ArrayList<RadioButton>();
        int i = 1;
        for (String discount : this.allDiscounts) {
            RadioButton radioButton = new RadioButton(discount);
            discountRadioButtons.add(radioButton);
            this.add(radioButton, 0, i++, 1, 1);
            if (selectedDiscounts.contains(discount)) {
                radioButton.fire();
            }
        }

        List<RadioButton> strategyRadioButtons = new ArrayList<RadioButton>();
        ToggleGroup strategyToggleGroup = new ToggleGroup();
        int j = 1;
        for (String strategy : this.allLoadStrategies) {
            RadioButton radioButton = new RadioButton(strategy);
            radioButton.setToggleGroup(strategyToggleGroup);
            strategyRadioButtons.add(radioButton);
            this.add(radioButton, 1, j++, 1, 1);
            if (Objects.equals(selectedLoadStrategy, strategy)) {
                radioButton.fire();
            }
        }

        Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                settingsPaneController.saveSettings(getRadioSelected(discountRadioButtons), getRadioSelected(strategyRadioButtons));
            }
        });
        this.add(saveButton, 0, i++, 2, 1);

    }

    private List<String> getRadioSelected(List<RadioButton> radioButtons) {
        List<String> list = new ArrayList<>();
        for (RadioButton radioButton : radioButtons) {
            if (radioButton.isSelected()) {
                list.add(radioButton.getText());
            }
        }
        return list;
    }

}

