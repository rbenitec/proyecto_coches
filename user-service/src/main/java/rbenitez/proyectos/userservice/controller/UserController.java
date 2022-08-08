package rbenitez.proyectos.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbenitez.proyectos.userservice.entity.User;
import rbenitez.proyectos.userservice.model.Bike;
import rbenitez.proyectos.userservice.model.Car;
import rbenitez.proyectos.userservice.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /*
    *   Get all User
     */
    @GetMapping()
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        if(users.isEmpty()){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    /*
    *   Created new User
     */
    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser = userService.createdUser(user);
        if(newUser==null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newUser);
    }

    /*
    *   find User by ID
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") int userId){
        User user = userService.findUserById(userId);
        if(user == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /*
    *   Delete User
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") int userId){
        userService.deleteUser(userId);
    }

    /*
    *   Method for get bikes by user id
     */

    @GetMapping("/bike/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.findUserById(userId);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        List<Bike> bikes = userService.getBikesByUserId(userId);
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/car/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user = userService.findUserById(userId);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCarsByUserId(userId);
        return ResponseEntity.ok(cars);
    }

    /*
    *   Method where we will use OpenFeing for save a bike from user-microservice
     */

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike){
        Bike newBike = userService.saveBike(userId,bike);
        if(newBike==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping("/savecar/{userId}")
    public  ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        Car newCar = userService.saveCar(userId, car);
        if(car==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newCar);
    }

    /*
     *  Method, for get all vehicles of a User
     */

    @GetMapping("getall/{userId}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable("userId") int userId){
        Map<String, Object> result  = new HashMap<>();
        result = userService.getCarsAndBikes(userId);
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
