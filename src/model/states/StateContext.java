package model.states;

public class StateContext {
    private GateState state;

    public StateContext() {
        state = new InactiveState();
    }

    public void setGateState(GateState state) {
        this.state = state;
    }

    public GateState getState() {
        return state;
    }
}
