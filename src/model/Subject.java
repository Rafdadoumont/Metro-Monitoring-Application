package model;

public interface Subject {
    void addObserver(Observer observer, MetroEventsEnum metroEvent);
    void notifyObservers(MetroEventsEnum event);
}
