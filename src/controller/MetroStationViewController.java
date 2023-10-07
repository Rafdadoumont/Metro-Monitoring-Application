package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Gate;
import model.MetroEventsEnum;
import model.MetroFacade;
import model.Observer;
import model.states.ClosedState;
import model.states.InactiveState;
import view.MetroStationView;

import java.util.List;

public class MetroStationViewController implements Observer {
    MetroFacade metroFacade;
    MetroStationView metroStationView;

    private ObservableList<Integer> options;

    private int numberOfScannedMetroCards;

    public MetroStationViewController(MetroFacade metroFacade, MetroStationView metroStationView) {
        this.metroFacade = metroFacade;
        this.metroStationView = metroStationView;
        this.numberOfScannedMetroCards = 0;

        options = FXCollections.observableArrayList(metroFacade.getMetroCardIDList());
    }

    @Override
    public void update(MetroEventsEnum event) {
        metroStationView.updateMetroCardIdList(metroFacade.getMetroCardIDList());
    }

    public List<Gate> getGates() {
        return metroFacade.getGates();
    }

    public ObservableList<Integer> getIDs() {
        return options;
    }

    public void scanMetroCard(Gate gate, String id) {
        if (gate.getContext().getState() instanceof InactiveState) {
            metroStationView.getOutputs().get(gate).setText("Gate is inactive");
            metroFacade.invalidGateAction(gate);
        } else {
            if (id != null && !id.isEmpty()) {
                int idInt = Integer.parseInt(id);
                List<Integer> ids= metroFacade.getMetroCardIDList();

                if (ids.contains(idInt)) {
                    if (!metroFacade.checkCardExpired(idInt)) {
                        try {
                            if (metroFacade.scanCard(idInt)) {
                                gate.setScannedCards(gate.getScannedCards() + 1);
                                gate.scan();
                            } else {
                                metroStationView.getOutputs().get(gate).setText("Card has no rides left");
                                metroFacade.invalidGateAction(gate);
                                return;
                            }
                        } catch (IllegalArgumentException e) {
                            gate.setScannedCards(gate.getScannedCards() - 1);
                            metroFacade.invalidGateAction(gate);
                        }
                        metroStationView.getOutputs().get(gate).setText("Card " +  id + " is scanned");
                    } else {
                        metroStationView.getOutputs().get(gate).setText("Card has expired");
                    }
                } else {
                    gate.setClosed();
                    metroFacade.invalidGateAction(gate);
                    metroStationView.getOutputs().get(gate).setText("card " + id + " is not valid");
                }
            } else {
                gate.createAlert();
                metroFacade.invalidGateAction(gate);
                metroStationView.getOutputs().get(gate).setText("alert is generated");
            }
        }
    }

    public void walkThroughGate(Gate gate) {
        if (gate.getContext().getState() instanceof ClosedState) {
            metroStationView.getOutputs().get(gate).setText("Gate is closed");
        } else if (gate.getContext().getState() instanceof InactiveState) {
            metroStationView.getOutputs().get(gate).setText("Gate is inactive");
        } else {
            try {
                gate.walkThroughGate();
            } catch (IllegalArgumentException e) {
                metroFacade.invalidGateAction(gate);
            }
            metroStationView.getOutputs().get(gate).setText("walked through");
        }
    }
}
