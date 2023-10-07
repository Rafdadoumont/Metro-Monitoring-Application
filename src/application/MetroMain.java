package application;
	
import controller.*;

import javafx.application.Application;
import javafx.stage.Stage;

import model.MetroEventsEnum;
import model.MetroFacade;

import view.*;

public class MetroMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		MetroFacade metroFacade = MetroFacade.getInstance();

		AdminView adminView = new AdminView();
		MetroTicketView metroTicketView = new MetroTicketView();
		MetroStationView metroStationView = new MetroStationView();

		// Controllers
		AdminMainPane adminMainPane = adminView.getAdminMainPane();

		MetroTicketViewController metroTicketViewController = new MetroTicketViewController(metroFacade, metroTicketView);
		metroTicketView.setMetroTicketViewController(metroTicketViewController);

		MetroStationViewController metroStationViewController = new MetroStationViewController(metroFacade,metroStationView);
		metroStationView.setMetroStationViewController(metroStationViewController);

		ControlCenterPaneController controlCenterPaneController = new ControlCenterPaneController(metroFacade, adminMainPane.getControlCenterPane());
		adminMainPane.setControlCenterPaneController(controlCenterPaneController);

		MetroCardOverviewPaneController metroCardOverviewPaneController = new MetroCardOverviewPaneController(metroFacade, adminMainPane.getMetroCardOverviewPane());
		adminMainPane.setMetroCardOverviewPaneController(metroCardOverviewPaneController);

		SettingsPaneController settingsPaneController = new SettingsPaneController(metroFacade, adminMainPane.getSettingsPane());
		adminMainPane.setSettingsPaneController(settingsPaneController);


		metroFacade.addObserver(metroTicketViewController, MetroEventsEnum.OPEN_METROSTATION);
		metroFacade.addObserver(metroStationViewController, MetroEventsEnum.OPEN_METROSTATION);
		metroFacade.addObserver(metroCardOverviewPaneController, MetroEventsEnum.OPEN_METROSTATION);
		metroFacade.addObserver(controlCenterPaneController, MetroEventsEnum.OPEN_METROSTATION);

		metroFacade.addObserver(metroTicketViewController, MetroEventsEnum.CLOSE_METROSTATION);
		metroFacade.addObserver(metroStationViewController, MetroEventsEnum.CLOSE_METROSTATION);
		metroFacade.addObserver(metroCardOverviewPaneController, MetroEventsEnum.CLOSE_METROSTATION);
		metroFacade.addObserver(controlCenterPaneController, MetroEventsEnum.CLOSE_METROSTATION);

		metroFacade.addObserver(metroTicketViewController, MetroEventsEnum.BUY_METROCARD);
		metroFacade.addObserver(metroStationViewController, MetroEventsEnum.BUY_METROCARD);
		metroFacade.addObserver(metroCardOverviewPaneController, MetroEventsEnum.BUY_METROCARD);

		metroFacade.addObserver(metroTicketViewController, MetroEventsEnum.SCAN);
		metroFacade.addObserver(metroStationViewController, MetroEventsEnum.SCAN);
		metroFacade.addObserver(metroCardOverviewPaneController, MetroEventsEnum.SCAN);

		metroFacade.addObserver(controlCenterPaneController, MetroEventsEnum.INVALID_GATE_ACTION);

		metroFacade.addObserver(controlCenterPaneController, MetroEventsEnum.UPDATE_METROCARD);
		metroFacade.addObserver(metroCardOverviewPaneController, MetroEventsEnum.UPDATE_METROCARD);

		metroFacade.addObserver(controlCenterPaneController, MetroEventsEnum.SCAN);

		metroFacade.addObserver(controlCenterPaneController, MetroEventsEnum.EXPIRED_CARD);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
