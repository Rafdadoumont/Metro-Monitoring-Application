package model.TicketPriceDecorator;

public class FrequentTravellerDiscount extends TicketPriceDiscountDecorator {


    public FrequentTravellerDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public double getPrice() {
        if (isFrequentTraveller())
            return this.price - 0.20;
        return this.price;
    }
}
