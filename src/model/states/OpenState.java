package model.states;

public class OpenState implements GateState {
    @Override
    public void scan(StateContext context) {
        context.setGateState(new OpenState());
    }

    @Override
    public void createWarning(StateContext context) {
        context.setGateState(new OpenState());
    }

    @Override
    public void walkThroughGate(StateContext context) {
        context.setGateState(new ClosedState());
    }
}
