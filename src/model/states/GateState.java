package model.states;

public interface GateState {
    default void scan(StateContext context) {
        throw new IllegalArgumentException("Metrocard cannot be scanned bacause the gate is " + context.getState());
    }

    default void walkThroughGate(StateContext context) {
        throw new IllegalArgumentException("You can't walk through the gate because it is " + context.getState());
    }

    default void activate(StateContext context) {
        throw new IllegalArgumentException("gate because it is " + context.getState());
    }

    default void deactivate(StateContext context) {
        throw new IllegalArgumentException("gate because it is " + context.getState());
    }

    default void createAlert(StateContext context) {
        throw new IllegalArgumentException("gate because it is " + context.getState());
    }

    default void createWarning(StateContext context) {
        throw new IllegalArgumentException("gate because it is " + context.getState());
    }

    default void setClosed(StateContext context) {
        throw new IllegalArgumentException("gate because it is " + context.getState());
    }
}
