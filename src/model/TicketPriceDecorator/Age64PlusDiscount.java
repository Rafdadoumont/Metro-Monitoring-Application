package model.TicketPriceDecorator;

public class Age64PlusDiscount extends TicketPriceDiscountDecorator {

    public Age64PlusDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public double getPrice() {
        if (this.is64Plus())
            return price - 0.15;
        return price;
    }
}
