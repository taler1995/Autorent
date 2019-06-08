package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Cars
 * <p>
 * Created by yslabko on 08/08/2017.
 */
@Data
@NoArgsConstructor
public class Cars {
    private long id;
    private String brand;
    private String model;
    private String plateNumber;
    private String colour;
    private int capacity;
    private String typeOfBody;
    private int yearOfEdition;
    private String typeOfEngine;
    private String gearbox;
    private String status;
    private long price;

    public Cars(String brand, String model, String plateNumber, String colour, int capacity, String typeOfBody, Integer yearOfEdition,
                String typeOfEngine, String gearbox, String status, long price) {
        this.brand = brand;
        this.model = model;
        this.plateNumber = plateNumber;
        this.colour = colour;
        this.capacity = capacity;
        this.typeOfBody = typeOfBody;
        this.yearOfEdition = yearOfEdition;
        this.typeOfEngine = typeOfEngine;
        this.gearbox = gearbox;
        this.status = status;
        this.price = price;
    }
}
