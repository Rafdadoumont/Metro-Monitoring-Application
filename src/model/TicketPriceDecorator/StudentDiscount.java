package model.TicketPriceDecorator;

public class StudentDiscount extends TicketPriceDiscountDecorator {


    public StudentDiscount(TicketPrice ticketPrice) {
        super(ticketPrice);
    }

    @Override
    public double getPrice() {
        if(this.isStudent())
            return this.price - 0.25;
        return this.price;
    }
}
