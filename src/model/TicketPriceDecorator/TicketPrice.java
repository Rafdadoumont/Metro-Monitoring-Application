package model.TicketPriceDecorator;

import java.text.DecimalFormat;

public abstract class TicketPrice {
    private boolean is24Min;
    private boolean is64Plus;
    private boolean isStudent;

    private boolean frequentTraveller;

    public boolean isFrequentTraveller() {
        return frequentTraveller;
    }

    public void setFrequentTraveller(boolean frequentTraveller) {
        this.frequentTraveller = frequentTraveller;
    }

    private final DecimalFormat euros = new DecimalFormat( "€0.00" );

    protected TicketPrice() {
    }

    public DecimalFormat getEuros() {
        return euros;
    }

    public abstract String getPriceText();

    public abstract double getPrice();

    public boolean is24Min() {
        return is24Min;
    }

    public void setIs24Min(boolean is24Min) {
        this.is24Min = is24Min;
    }

    public boolean is64Plus() {
        return is64Plus;
    }

    public void setIs64Plus(boolean is64Plus) {
        this.is64Plus = is64Plus;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
