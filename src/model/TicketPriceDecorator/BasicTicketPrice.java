package model.TicketPriceDecorator;

public class BasicTicketPrice extends TicketPrice {

    public BasicTicketPrice(int usedTrips) {
        this.setFrequentTraveller(usedTrips > 50);
    }

    @Override
    public String getPriceText() {
        return this.getEuros().format(this.getPrice());
    }

    @Override
    public double getPrice() {
        return 2.10;
    }
}
