package hibernate.lesson8.entities;

import java.util.List;

public class User {
    private Long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List<Order> orders;
}


