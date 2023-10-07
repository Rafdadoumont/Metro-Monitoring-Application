package model.TicketPriceDecorator;

public class ChristmasLeaveDiscount extends TicketPriceDiscountDecorator {


    public ChristmasLeaveDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public double getPrice() {
        return this.price - 0.10;
    }
}
