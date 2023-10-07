package model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;

public class Metrocard {
    private final int id;
    private Month month;
    private Year year;
    private int availableTrips;
    private int usedTrips;

    public Metrocard(int id, Month month, Year year, int availableTrips, int usedTrips) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.availableTrips = availableTrips;
        this.usedTrips = usedTrips;
    }

    public int getId() {
        return id;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getDate() {
        return String.format("%d/%s", year.getValue(), month.name());
    }

    public int getAvailableTrips() {
        return availableTrips;
    }

    public void setAvailableTrips(int availableTrips) {
        this.availableTrips = availableTrips;
    }

    public int getUsedTrips() {
        return usedTrips;
    }

    public void setUsedTrips(int usedTrips) {
        this.usedTrips = usedTrips;
    }

    public boolean scan() {
        if (availableTrips >= 1) {
            availableTrips--;
            usedTrips++;
            return true;
        }
        return false;
    }

    public void addRides(int amount) {
        if (amount > 0) {
            this.year = Year.now();
            this.month = LocalDate.now().getMonth();
            this.availableTrips += amount;
        }
    }

    public boolean checkExpired() {
        return Year.now().getValue() - this.year.getValue() > 1 || (Year.now().getValue() - this.year.getValue() == 1 && this.month.getValue() <  LocalDate.now().getMonth().getValue());
    }

    @Override
    public String toString() {
        return "Metrocard{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                ", availableTrips=" + availableTrips +
                ", usedTrips=" + usedTrips +
                '}';
    }
}
