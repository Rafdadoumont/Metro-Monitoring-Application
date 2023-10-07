package model.states;

public class InactiveState implements GateState{
    @Override
    public void activate(StateContext context) {
        context.setGateState(new ClosedState());
    }
}
