package view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;	

public class AdminView {
	private final Stage stage = new Stage();

	private AdminMainPane borderPane;

	public AdminView(){
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		stage.setTitle("ADMIN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(bounds.getWidth() / 2);
		stage.setY(bounds.getMinY());
		Group root = new Group();
		Scene scene = new Scene(root, bounds.getWidth() / 2, bounds.getHeight());
		borderPane = new AdminMainPane();
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		Button button = new Button("Current");
		button.setPrefSize(100, 20);

		root.getChildren().add(borderPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

	public AdminMainPane getAdminMainPane() {
		return this.borderPane;
	}
}
