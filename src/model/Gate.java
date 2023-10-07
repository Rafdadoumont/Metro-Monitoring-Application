package model;

import javafx.scene.control.TextField;
import model.states.StateContext;


public class Gate {

    private String name;
    private final StateContext context;

    private TextField field;
    private int scannedCards;

    public Gate(String name) {
        this.name = name;
        this.context = new StateContext();
        this.scannedCards = 0;
    }

    public void setScannedCards(int i) {
        scannedCards = i;
    }

    public int getScannedCards() {
        return this.scannedCards;
    }

    public String getName() {
        return this.name;
    }

    public StateContext getContext() {
        return this.context;
    }

    public void scan() {
        context.getState().scan(context);
    }

    public void walkThroughGate() {
        context.getState().walkThroughGate(context);
    }

    public void activate() {
        context.getState().activate(context);
    }

    public void deactivate() {
        context.getState().deactivate(context);
    }

    public void createAlert() {
        context.getState().createAlert(context);
    }

    public void createWarning() {
        context.getState().createWarning(context);
    }

    public void setClosed() {
        context.getState().setClosed(context);
    }

    public void setOutCardsScanned(TextField field) {
        this.field = field;
    }

    public TextField getOutCardsScanned(){
        return field;
    }



}
