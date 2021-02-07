package hibernate.lesson8.entities;

import hibernate.lesson8.entities.atributeconverters.BooleanAttributeConverter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOMS")
public class Room {
    private Long id;
    private Integer numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room() {
    }

    public Room(Long id, Integer numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NUMBER_OF_GUESTS")
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Convert(converter = BooleanAttributeConverter.class)
    @Column(name = "BREAKFAST_INCLUDED")
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    @Convert(converter = BooleanAttributeConverter.class)
    @Column(name = "PETS_ALLOWED")
    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    @Column(name = "DATE_AVAILABLE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOTEL_ID")
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (Double.compare(room.price, price) != 0) return false;
        if (breakfastIncluded != room.breakfastIncluded) return false;
        if (petsAllowed != room.petsAllowed) return false;
        if (id != null ? !id.equals(room.id) : room.id != null) return false;
        if (numberOfGuests != null ? !numberOfGuests.equals(room.numberOfGuests) : room.numberOfGuests != null)
            return false;
        if (dateAvailableFrom != null ? !dateAvailableFrom.equals(room.dateAvailableFrom) : room.dateAvailableFrom != null)
            return false;
        return hotel != null ? hotel.equals(room.hotel) : room.hotel == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numberOfGuests != null ? numberOfGuests.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (breakfastIncluded ? 1 : 0);
        result = 31 * result + (petsAllowed ? 1 : 0);
        result = 31 * result + (dateAvailableFrom != null ? dateAvailableFrom.hashCode() : 0);
        result = 31 * result + (hotel != null ? hotel.hashCode() : 0);
        return result;
    }
}
