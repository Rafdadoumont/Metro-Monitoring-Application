package view;

import controller.ControlCenterPaneController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Gate;
import model.database.loadSaveStrategies.LoadSaveStrategy;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;

import java.text.DecimalFormat;

public class ControlCenterPane extends Pane {
    ControlCenterPaneController controlCenterPaneController;
    private HBox gatesHBox;
    private TextArea textArea;
    private TextField numberSoldTicketsTextField;
    private TextField totalAmountTicketsTextField;
    private final DecimalFormat euros = new DecimalFormat("€0.00");

    public ControlCenterPane() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // Root VBox
        VBox rootVBox = new VBox(10);
        rootVBox.setPadding(new Insets(10));
        rootVBox.setPrefWidth(bounds.getWidth() / 2);

        // Open/Close Metro Station Vbox
        VBox openCloseStationVBox = new VBox(10);
        openCloseStationVBox.setPadding(new Insets(5));
        openCloseStationVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
        openCloseStationVBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        rootVBox.getChildren().add(openCloseStationVBox);

            // Open Metro Station Hbox
            HBox openStationHBox = new HBox(10);
            openCloseStationVBox.getChildren().add(openStationHBox);

                // Open Metro Station Button
                Button openMetroButton = new Button("Open metro station");
                openMetroButton.setOnAction((event) -> {
                    controlCenterPaneController.openMetroStation();
                });
                openStationHBox.getChildren().add(openMetroButton);

            // Close Metro Station Button
            Button closeMetroButton = new Button("Close metro station");
            closeMetroButton.setOnAction((event) -> {
                controlCenterPaneController.closeMetroStation();
            });
            openCloseStationVBox.getChildren().add(closeMetroButton);

        // Info Vbox
        VBox infoVBox = new VBox(10);
        infoVBox.setPadding(new Insets(5));
        infoVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
        infoVBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        rootVBox.getChildren().add(infoVBox);

            // Number of Sold Tickets  Hbox
            HBox numberSoldTicketsHBox = new HBox(10);
            numberSoldTicketsHBox.setAlignment(Pos.CENTER_LEFT);
            infoVBox.getChildren().add(numberSoldTicketsHBox);

                // Number of Sold Tickets Label
                Label numberSoldTicketsLabel = new Label("Number of sold tickets:");
                numberSoldTicketsHBox.getChildren().add(numberSoldTicketsLabel);

                // Number of Sold Tickets Textfield
                numberSoldTicketsTextField = new TextField("0");
                numberSoldTicketsTextField.setEditable(false);

                numberSoldTicketsHBox.getChildren().add(numberSoldTicketsTextField);

            // Total Amount Tickets  Hbox
            HBox totalAmountTicketsHBox = new HBox(10);
            totalAmountTicketsHBox.setAlignment(Pos.CENTER_LEFT);
            infoVBox.getChildren().add(totalAmountTicketsHBox);

                // Total Amount Tickets Label
                Label totalAmountTicketsLabel = new Label("Total € amount of sold tickets:");
                totalAmountTicketsHBox.getChildren().add(totalAmountTicketsLabel);

                // Total Amount Tickets TextField

                totalAmountTicketsTextField = new TextField("0");
                totalAmountTicketsTextField.setEditable(false);

                totalAmountTicketsHBox.getChildren().add(totalAmountTicketsTextField);

        // Gates Hbox
        gatesHBox = new HBox(10);
        gatesHBox.setPadding(new Insets(5));
        gatesHBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
        gatesHBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        rootVBox.getChildren().add(gatesHBox);

        // Alerts Label
        Label alertsLabel = new Label("ALERTS");
        alertsLabel.setPadding(new Insets(0, 0, 0, 10));
        alertsLabel.setFont(new Font(20));
        rootVBox.getChildren().add(alertsLabel);

        // Alerts Scroll Pane
        ScrollPane alertsScrollPane = new ScrollPane();
        textArea = new TextArea();
        textArea.setWrapText(true);


        textArea.setEditable(false);

        textArea.setStyle("-fx-text-fill: FB2C17");
        
        alertsScrollPane.setContent(textArea);
        alertsScrollPane.setFitToWidth(true);
        alertsScrollPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
        alertsScrollPane.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                BorderWidths.DEFAULT)));
        rootVBox.getChildren().add(alertsScrollPane);

        this.getChildren().add(rootVBox);
    }

    public void setControlCenterPaneController(ControlCenterPaneController controller) {
        this.controlCenterPaneController = controller;


        for(Gate gate: controlCenterPaneController.getGates()) {
            // VBox
            VBox gateVBox = new VBox(10);
            gateVBox.setPadding(new Insets(5));
            gateVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
            gateVBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
                    BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY,
                    BorderWidths.DEFAULT)));
            gatesHBox.getChildren().add(gateVBox);
            gateVBox.setStyle("-fx-background-color: #de5b0b;");
            // Label
            Label gateLabel = new Label(gate.getName() + " / INACTIVE");
            gateVBox.getChildren().add(gateLabel);

            // Activate Button
            Button gateActivateButton = new Button("Activate");
            gateVBox.getChildren().add(gateActivateButton);
            gateActivateButton.setOnAction((event) -> {
                gate.activate();
                gateVBox.setStyle("-fx-background-color: #FFFFFF;");
                gateLabel.setText(gate.getName() + " / ACTIVE");
            });

            // Deactivate Button
            Button gateDeactivateButton = new Button("Deactivate");
            gateVBox.getChildren().add(gateDeactivateButton);
            gateDeactivateButton.setOnAction((event) -> {
                gateVBox.setStyle("-fx-background-color: #de5b0b;");
                gateLabel.setText(gate.getName() + " / INACTIVE");
                gate.deactivate();
            });

            // Amount Scanned Cards Label
            Label gateAmountScannedCardsLabel = new Label("# scanned cards");
            gateVBox.getChildren().add(gateAmountScannedCardsLabel);

            // Amount Scanned Cards TextField
            TextField gateAmountScannedCardsTextField = new TextField(String.valueOf(gate.getScannedCards()));
            gate.setOutCardsScanned(gateAmountScannedCardsTextField);
            gateAmountScannedCardsTextField.setEditable(false);
            gateVBox.getChildren().add(gateAmountScannedCardsTextField);
        }
    }

    public void refresh() {
        StringBuilder out = new StringBuilder();
        for (String alert: controlCenterPaneController.getAlerts()) {
            out.insert(0, alert + '\n');
        }
        textArea.setText(out.toString());

        for (Gate gate: controlCenterPaneController.getGates()) {
            gate.getOutCardsScanned().setText(String.valueOf(gate.getScannedCards()));
        }
    }

    public void update(int amount, double price) {
        numberSoldTicketsTextField.setText(String.valueOf(amount));
        totalAmountTicketsTextField.setText(euros.format(price));
    }
}
