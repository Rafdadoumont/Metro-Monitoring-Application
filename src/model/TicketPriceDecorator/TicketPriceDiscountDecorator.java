package model.TicketPriceDecorator;

public abstract class TicketPriceDiscountDecorator extends TicketPrice {
    double price;

    public TicketPriceDiscountDecorator(TicketPrice ticketPrice) {
        this.setStudent(ticketPrice.isStudent());
        this.setIs24Min(ticketPrice.is24Min());
        this.setIs64Plus(ticketPrice.is64Plus());
        this.setFrequentTraveller(ticketPrice.isFrequentTraveller());
        this.price = ticketPrice.getPrice();
    }

    @Override
    public String getPriceText() {
        return this.getEuros().format(this.getPrice());
    }

}
