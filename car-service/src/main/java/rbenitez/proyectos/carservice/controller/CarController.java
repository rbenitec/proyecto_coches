package rbenitez.proyectos.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbenitez.proyectos.carservice.entity.Car;
import rbenitez.proyectos.carservice.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    /*
    *   Method find all Cars
     */

    @GetMapping()
    public ResponseEntity<List<Car>> cars(){
        List<Car> cars = carService.findAllCars();
        if(cars.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cars);
    }

    /*
    *   Method find Car by Id
     */

    @GetMapping("/{carId}")
    public ResponseEntity<Car> findCarById(@PathVariable("carId") int carId){
        Car newCar = carService.findById(carId);
        if(newCar==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newCar);
    }

    /*
    *   Method created a new car
     */

    @PostMapping()
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        Car car1 =carService.createdCar(car);
        if(car1==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car1);
    }

    /*
    *   Find car by userID
     */

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> findCarByUserId(@PathVariable("userId") int userId){
        List<Car> cars = carService.findCarByUserId(userId);
        return ResponseEntity.ok(cars);
    }

}
