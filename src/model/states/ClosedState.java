package model.states;

public class ClosedState implements GateState {
    @Override
    public void scan(StateContext context) {
        context.setGateState(new OpenState());
    }

    public void deactivate(StateContext context) {
        context.setGateState(new InactiveState());
    }

    @Override
    public void setClosed(StateContext context) {
        context.setGateState(new ClosedState());
    }

    @Override
    public void walkThroughGate(StateContext context) {
        context.setGateState(new ClosedState());
    }

    @Override
    public void createAlert(StateContext context) {
        context.setGateState(new ClosedState());
    }
}
