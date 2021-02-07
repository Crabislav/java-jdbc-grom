package hibernate.lesson8.entities;

import hibernate.lesson8.entities.atributeconverters.UserTypeAttributeConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    private Long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List<Order> orders;

    public User() {
    }

    public User(Long id, String userName, String password, String country, UserType userType, List<Order> orders) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
        this.orders = orders;
    }

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Convert(converter = UserTypeAttributeConverter.class)
    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userOrdered")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


