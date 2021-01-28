package hibernate.lesson8.entities;

import java.util.List;

public class Hotel {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Room> rooms;

    public Hotel(String name, String country, String city, String street, List<Room> rooms) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.rooms = rooms;
    }

    public Hotel(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.country = hotel.getCountry();
        this.city = hotel.getCity();
        this.street = hotel.getStreet();
        this.rooms = hotel.getRooms();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


}
