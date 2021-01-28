package hibernate.lesson8.entities;

import java.sql.Date;

public class Room {
    private Long id;
    private Integer numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

}
