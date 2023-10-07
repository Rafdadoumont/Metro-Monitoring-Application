package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.MetroCardOverviewPane;
import view.MetroStationView;
import view.MetroTicketView;

public class MetroCardOverviewPaneController implements Observer {
    MetroFacade metroFacade;
    MetroCardOverviewPane metroCardOverviewPane;

    public MetroCardOverviewPaneController(MetroFacade metroFacade, MetroCardOverviewPane metroCardOverviewPane) {
        this.metroFacade = metroFacade;
        this.metroCardOverviewPane = metroCardOverviewPane;
    }

    @Override
    public void update(MetroEventsEnum event) {
        metroCardOverviewPane.updateMetrocardList(metroFacade.getMetroCardList());
    }
}
