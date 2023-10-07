package view;

import controller.MetroCardOverviewPaneController;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.Metrocard;

import java.util.List;

public class MetroCardOverviewPane extends Pane {
    private MetroCardOverviewPaneController metroCardOverviewPaneController;

    private TableView<Metrocard> table;

    public MetroCardOverviewPane() {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // Root VBox
        VBox rootVBox = new VBox(10);
        rootVBox.setPadding(new Insets(10));
        rootVBox.setPrefWidth(bounds.getWidth() / 2);

        // List Metro Cards Label
        Label listMetroCardLabel = new Label("List of metro cards:");
        rootVBox.getChildren().add(listMetroCardLabel);

        // Table Metro Cards
        this.table = new TableView<>();
        table.setPrefWidth(rootVBox.getPrefWidth() - rootVBox.getPadding().getLeft()
        - rootVBox.getPadding().getRight());

        TableColumn<Metrocard, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.prefWidthProperty().bind(table.prefWidthProperty().multiply(0.25));

        TableColumn<Metrocard, String> date = new TableColumn<>("Date bought");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.prefWidthProperty().bind(table.prefWidthProperty().multiply(0.25));

        TableColumn<Metrocard, Integer> availableTrips = new TableColumn<>("Available trips");
        availableTrips.setCellValueFactory(new PropertyValueFactory<>("availableTrips"));
        availableTrips.prefWidthProperty().bind(table.prefWidthProperty().multiply(0.25));

        TableColumn<Metrocard, Integer> usedTrips = new TableColumn<>("Used trips");
        usedTrips.setCellValueFactory(new PropertyValueFactory<>("usedTrips"));
        usedTrips.prefWidthProperty().bind(table.prefWidthProperty().multiply(0.25));

        table.getColumns().addAll(id, date, availableTrips, usedTrips);





        rootVBox.getChildren().add(table);


        this.getChildren().add(rootVBox);



    }

    public void updateMetrocardList(List<Metrocard> metrocards) {
        this.table.getItems().setAll(metrocards);
    }

    public void setMetroCardOverviewPaneController(MetroCardOverviewPaneController metroCardOverviewPaneController) {
        this.metroCardOverviewPaneController = metroCardOverviewPaneController;
    }
}
