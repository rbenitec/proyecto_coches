package rbenitez.proyectos.userservice.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import rbenitez.proyectos.userservice.model.Car;

import java.util.List;

@FeignClient(name = "car-service", path = "/car")
public interface CarFeingClient {

    /*
    *    Method for save a car from user microservice
     */
    @PostMapping()
    Car saveCar(@RequestBody Car car);

    /*
     *  Get Cars of the user
     */

    @GetMapping("/byuser/{userId}")
    List<Car> findCarByUserId(@PathVariable("userId") int userId);
}
