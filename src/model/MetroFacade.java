package model;

import model.TicketPriceDecorator.TicketPrice;
import model.TicketPriceDecorator.TicketPriceDiscountEnum;
import model.TicketPriceDecorator.TicketPriceFactory;
import model.database.MetrocardDatabase;
import model.database.loadSaveStrategies.LoadSaveStrategyEnum;
import model.database.loadSaveStrategies.LoadSaveStrategyFactory;


import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

import java.util.*;
import java.util.stream.Collectors;

public class MetroFacade implements Subject {
    Map<MetroEventsEnum, List<Observer>> observerMap;
    private MetrocardDatabase metroDB;
    private final LoadSaveStrategyFactory<Integer, Metrocard> loadSaveStrategyFactory;
    private LoadSaveStrategyEnum loadSaveStrategy;
    private List<String> metroTicketDiscountList;
    private Gate lastInvalidGate;
    private Metrocard lastExpiredMetroCard;
    private ArrayList<Gate> gates;

    private int totalCards = 0;
    private double totalPrice = 0;

    private static MetroFacade metroFacade;

    private MetroFacade() {
        this.observerMap = new HashMap<>();
        this.loadSaveStrategyFactory = new LoadSaveStrategyFactory<>();
        this.gates = new ArrayList<>();
        this.gates.add(new Gate("Gate 1"));
        this.gates.add(new Gate("Gate 2"));
        this.gates.add(new Gate("Gate 3"));
        this.metroTicketDiscountList = TicketPriceFactory.loadDiscounts();
    }

    public static MetroFacade getInstance() {
        if (metroFacade == null) {
            metroFacade = new MetroFacade();
        }
        return metroFacade;
    }

    public List<Metrocard> getMetroCardList() {
        if (this.metroDB != null) {
            return this.metroDB.getMetrocardList();
        }
        return new ArrayList<>();
    }


    public List<Integer> getMetroCardIDList() {
        if (this.metroDB != null) {
            return this.metroDB.getMetrocardIDList();
        }
        return new ArrayList<>();
    }

    public void addObserver(Observer observer, MetroEventsEnum metroEvent) {
        if (observerMap.containsKey(metroEvent)) {
            observerMap.get(metroEvent).add(observer);
        } else {
            observerMap.put(metroEvent, new ArrayList<>(Collections.singleton(observer)));
        }
    }

    public void openMetroStation() {
        this.metroTicketDiscountList = TicketPriceFactory.loadDiscounts();
        this.loadSaveStrategy = LoadSaveStrategyFactory.loadLoadSaveStrategy();
        this.metroDB = new MetrocardDatabase(this.loadSaveStrategyFactory.createLoadSaveStrategy(this.loadSaveStrategy));
        this.metroDB.load();
        notifyObservers(MetroEventsEnum.OPEN_METROSTATION);
    }

    public void closeMetroStation() {
        if (this.metroDB != null) {
            this.metroDB.save();
            this.metroDB = null;
            notifyObservers(MetroEventsEnum.CLOSE_METROSTATION);
        }
    }

    public void newMetroCard(Month month, Year year) {
        if (this.metroDB != null) {
            this.metroDB.addMetrocard(month, year);
            notifyObservers(MetroEventsEnum.BUY_METROCARD);
        }
    }

    public void notifyObservers(MetroEventsEnum event) {
        if (observerMap.containsKey(event)) {
            for (Observer observer : observerMap.get(event)) {
                observer.update(event);
            }
        }
    }

    public double getPrice(boolean is24Min, boolean is64Plus, boolean isStudent, Metrocard metrocard) {
        TicketPrice ticketPrice = TicketPriceFactory.createTicketPrice(is24Min, is64Plus, isStudent, metrocard);
        return ticketPrice.getPrice();
    }

    public List<Gate> getGates() {
        return this.gates;
    }

    public void invalidGateAction(Gate gate) {
        this.lastInvalidGate = gate;
        notifyObservers(MetroEventsEnum.INVALID_GATE_ACTION);
    }

    public Gate getLastInvalidGate() { return lastInvalidGate; }

    public boolean scanCard(int id) {
        if (this.metroDB.scanCard(id)) {
            notifyObservers(MetroEventsEnum.SCAN);
            return true;
        }
        return false;
    }

    public boolean checkCardExpired(int id) {
        return this.metroDB.checkCardExpired(id);
    }

    public List<String> getSelectedDiscounts() {
        return metroTicketDiscountList;
    }

    public List<String> getAllDiscounts() {
        List<TicketPriceDiscountEnum> list = Arrays.asList(TicketPriceDiscountEnum.values());
        return list.stream().map(TicketPriceDiscountEnum::toString).collect(Collectors.toList());
    }

    public void saveSettings(List<String> discountSelected, List<String> strategySelected) {
        loadSaveStrategyFactory.saveSettings(discountSelected, strategySelected);
    }

    public String getSelectedLoadStrategy() {
        return loadSaveStrategyFactory.getSelectedLoadSaveStrategy();
    }

    public List<String> getAllLoadStrategies() {
        List<LoadSaveStrategyEnum> list = Arrays.asList(LoadSaveStrategyEnum.values());
        return list.stream().map(LoadSaveStrategyEnum::toString).collect(Collectors.toList());
    }

    public void addRides(Integer metroCardId, int amount, double price) {
        totalCards += amount;
        totalPrice += price;

        metroDB.addRides(metroCardId, amount);

        notifyObservers(MetroEventsEnum.UPDATE_METROCARD);
    }

    public void expiredCardAlert(Metrocard metrocard) {
        this.lastExpiredMetroCard = metrocard;
        notifyObservers(MetroEventsEnum.EXPIRED_CARD);
    }

    public int getTotalCards() {
        return totalCards;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public Metrocard getLastExpiredMetroCard() { return lastExpiredMetroCard; }
}
