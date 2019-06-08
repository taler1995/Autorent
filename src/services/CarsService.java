package services;

import entities.Cars;
import entities.User;

import java.util.List;

/**
 * Created by yslabko on 08/13/2017.
 */
public interface CarsService {
    Cars getCarsByBrand(String brand);
    List<Cars> getAll();
    Cars getById(long id);
}
