package model.TicketPriceDecorator;

public enum TicketPriceDiscountEnum {
    AGE64PLUSDISCOUNT ("Age64PlusDiscount", "model.TicketPriceDecorator.Age64PlusDiscount"),
    CHRISTMASLEAVEDISCOUNT ("ChristmasLeaveDiscount", "model.TicketPriceDecorator.ChristmasLeaveDiscount"),
    STUDENTDISCOUNT ("StudentDiscount", "model.TicketPriceDecorator.StudentDiscount"),
    FREQUENTTRAVELLERDISCOUNT ("FrequentTravellerDiscount", "model.TicketPriceDecorator.FrequentTravellerDiscount");

    private final String name;
    private final String className;

    TicketPriceDiscountEnum(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

}
