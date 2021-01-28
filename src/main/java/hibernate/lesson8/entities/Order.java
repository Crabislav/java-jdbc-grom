package hibernate.lesson8.entities;

import java.sql.Date;

public class Order {
    private long id;
    private User userOrdered;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;
}
