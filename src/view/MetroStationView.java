package view;

import controller.MetroStationViewController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import model.Gate;

import java.util.*;

public class MetroStationView {
	MetroStationViewController metroStationViewController;

	private final Stage stage = new Stage();

	private List<Integer> metroCardIDList;
	private final List<ComboBox<Integer>> cbxCardIDsList;

	private Map<Gate,TextField> outputs;

	private HBox rootHBox;

	public MetroStationView(){
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
		stage.setTitle("METRO STATION VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(0);
		stage.setY(bounds.getMinY() + (bounds.getHeight() / 2));

		cbxCardIDsList = new ArrayList<>();
    
		outputs = new HashMap<>();

		// Root Hbox
		rootHBox = new HBox(10);
		rootHBox.setPadding(new Insets(10));

		Scene scene = new Scene(rootHBox, bounds.getWidth() / 2, (bounds.getHeight() / 2));
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}

	public void updateMetroCardIdList(List<Integer> IDs) {
		this.metroCardIDList = IDs;
		for (ComboBox<Integer> cbx : cbxCardIDsList) {
			cbx.getItems().setAll(metroCardIDList);
		}
	}

	public void setMetroStationViewController(MetroStationViewController metroStationViewController) {
		this.metroStationViewController = metroStationViewController;

		for (Gate gate: metroStationViewController.getGates()) {
			TextField output = new TextField();
			output.setEditable(false);
			outputs.put(gate, output);

			VBox gateVBox = new VBox(10);
			gateVBox.setPadding(new Insets(5));
			gateVBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,Insets.EMPTY)));
			gateVBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"),
					BorderStrokeStyle.SOLID,
					CornerRadii.EMPTY,
					BorderWidths.DEFAULT)));
			rootHBox.getChildren().add(gateVBox);

			Label gateLabel = new Label(gate.getName());
			gateVBox.getChildren().add(gateLabel);

			Label gateMetroCardIDLabel = new Label("Metrocard ID:");
			gateVBox.getChildren().add(gateMetroCardIDLabel);

			ComboBox<Integer> gateIDComboBox = new ComboBox<>();
			gateIDComboBox.setItems(metroStationViewController.getIDs());
			cbxCardIDsList.add(gateIDComboBox);
			gateVBox.getChildren().add(gateIDComboBox);

			// Scan Metro Card Button
			Button gateScanButton = new Button("Scan metro card");
			gateScanButton.setOnAction((event) -> {
				if (gateIDComboBox.getValue() != null) {
					this.metroStationViewController.scanMetroCard(gate, gateIDComboBox.getValue().toString());
				}
			});
			gateVBox.getChildren().add(gateScanButton);

			// Walk through Gate Button
			Button gateWalkButton = new Button("Walk through gate");
			gateWalkButton.setOnAction((event) -> {
				this.metroStationViewController.walkThroughGate(gate);
			});
			gateVBox.getChildren().add(gateWalkButton);

			gateVBox.getChildren().add(outputs.get(gate));
		}
	}

	public Map<Gate, TextField> getOutputs() {
		return this.outputs;
	}
}
