package controller;

import model.Gate;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import view.ControlCenterPane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControlCenterPaneController implements Observer {
    MetroFacade metroFacade;
    ControlCenterPane controlCenterPane;

    private ArrayList<String> alerts;

    public ControlCenterPaneController(MetroFacade metroFacade, ControlCenterPane controlCenterPane) {
        this.metroFacade = metroFacade;
        this.controlCenterPane = controlCenterPane;
        this.alerts = new ArrayList<>();

    }

    public void openMetroStation() {
        this.metroFacade.openMetroStation();
    }

    public void closeMetroStation() {
        this.metroFacade.closeMetroStation();
    }

    @Override
    public void update(MetroEventsEnum event) {
        switch (event){
            case INVALID_GATE_ACTION:
                //add alert
                this.alerts.add(timeString() + " UNAUTHORIZED PASSAGE " + metroFacade.getLastInvalidGate().getName().toUpperCase());
                break;
            case EXPIRED_CARD:
                this.alerts.add(timeString() + " CARD " + metroFacade.getLastExpiredMetroCard().getId() + " EXPIRED");
                break;
            case UPDATE_METROCARD:
                this.controlCenterPane.update(this.metroFacade.getTotalCards(), this.metroFacade.getTotalPrice());
                break;
            case OPEN_METROSTATION:
                this.alerts.add(timeString() + " Opened metrostation".toUpperCase());
                break;
            case CLOSE_METROSTATION:
                this.alerts.add(timeString() + " Closed metrostation".toUpperCase());
                break;

        }
        this.controlCenterPane.refresh();
    }

    public List<Gate> getGates() {
        return metroFacade.getGates();
    }

    public List<String> getAlerts() {
        return this.alerts;
    }

    private String timeString() {
        return LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond();
    }
}
