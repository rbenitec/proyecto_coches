package rbenitez.proyectos.carservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbenitez.proyectos.carservice.entity.Car;
import rbenitez.proyectos.carservice.repository.CarRepository;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    /*
    *   Method find all bikes
     */
    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    /*
    *   Method find car by Id
     */
    public Car findById(int carId){
        return carRepository.findById(carId).orElse(null);
    }

    /*
    *   Method save car
     */

    public Car createdCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> findCarByUserId(int userId){
        return carRepository.findCarsByUserId(userId);
    }
}
