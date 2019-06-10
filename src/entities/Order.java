package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private long idClients;
    private long idCars;
    private Date start;
    private Date finish;
    private double total;

    public Order(long idClients, long idCars, Date start, Date finish, double total) {
        this.idClients = idClients;
        this.idCars = idCars;
        this.start = start;
        this.finish = finish;
        this.total = total;
    }
}
