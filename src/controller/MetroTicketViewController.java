package controller;

import model.MetroEventsEnum;
import model.MetroFacade;
import model.Metrocard;
import model.Observer;
import view.MetroTicketView;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.NoSuchElementException;

public class MetroTicketViewController implements Observer {
    MetroFacade metroFacade;
    MetroTicketView metroTicketView;

    public MetroTicketViewController(MetroFacade metroFacade, MetroTicketView metroTicketView) {
        this.metroFacade = metroFacade;
        this.metroTicketView = metroTicketView;
    }

    @Override
    public void update(MetroEventsEnum event) {
        metroTicketView.updateMetroCardIdList(metroFacade.getMetroCardIDList());
    }

    public void newMetroCard() {
        metroFacade.newMetroCard(LocalDateTime.now().getMonth(), Year.of(LocalDateTime.now().getYear()));
    }

    public void updateTotalPrice(boolean is24Min, boolean is64Plus, boolean isStudent, int metroCardId, int amount) {
        Metrocard metrocard;
        try {
            metrocard = metroFacade.getMetroCardList().stream().filter(metrocard1 -> metrocard1.getId() == metroCardId).findFirst().get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return;
        }
        if (!metrocard.checkExpired()) {
            double totalPrice = metroFacade.getPrice(is24Min, is64Plus, isStudent, metrocard) * amount;
            metroTicketView.setTotalPrice(totalPrice);
        } else {
            metroFacade.expiredCardAlert(metrocard);
        }
    }

    public void addRides(Integer metroCardId, int amount, double totalPrice) {
        metroFacade.addRides(metroCardId, amount, totalPrice);
    }
}
