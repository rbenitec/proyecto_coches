package rbenitez.proyectos.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbenitez.proyectos.userservice.config.RestTemplateConfig;
import rbenitez.proyectos.userservice.entity.User;
import rbenitez.proyectos.userservice.feingClients.BikeFeingClient;
import rbenitez.proyectos.userservice.feingClients.CarFeingClient;
import rbenitez.proyectos.userservice.model.Bike;
import rbenitez.proyectos.userservice.model.Car;
import rbenitez.proyectos.userservice.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplateConfig restTemplateConfig;

    @Autowired
    BikeFeingClient bikeFeingClient;

    @Autowired
    CarFeingClient carFeingClient;

    /*
    *   Retornar unaa lista de usuarios
     */
    public List<User> getAll(){
        return userRepository.findAll();
    }

    /*
    *   find user by id
     */
    public User findUserById(int userId){
        return userRepository.findById(userId).orElse(null);
    }

    /*
    *   Crear usuario
     */
    public User createdUser(User user){
        return userRepository.save(user);
    }

    /*
    *   Modificar usuario
    */
    public User updateUser(int userId, User user){
        User u = userRepository.findById(userId).orElse(null);
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        return userRepository.save(u);
    }

    /*
    *   Eliminar Usuario
     */

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    /*
    *   Method whit Rest Template
     */

    /*
    *   In this Method, use Rest Template
    *   Method, get all bikes by userId
     */

    public List<Bike> getBikesByUserId(int userId){
        List<Bike> bikes = restTemplateConfig.restTemplate().getForObject("http://bike-service/bike/byuser/"+userId, List.class);
        return bikes;
    }

    /*
     *   In this Method, use Rest Template
     *   Method, get all bikes by userId
     */

    public List<Car> getCarsByUserId(int userId){
        List<Car> cars = restTemplateConfig.restTemplate().getForObject("http://car-service/car/byuser/"+userId, List.class);
        return cars;
    }

    /*
    *   Method, for get All Bikes and Cars
     */
    public Map<Object, String> getAllVehicles(int userId){
        List<Bike> bikes = getBikesByUserId(userId);
        return null;
    }

    /*
    *   Method, where use OpenFeing
    *   Save Bike from microservice user
     */

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        return bikeFeingClient.saveBike(bike);
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        return carFeingClient.saveCar(car);
    }

    public Map<String, Object> getCarsAndBikes(int userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            result.put("Mensaje", "This User don't exist!");
            return result;
        }
        result.put("User", user);

        List<Bike> bikes = bikeFeingClient.findBikeByUserId(userId);
        if(bikes.isEmpty()){
            result.put("Bikes","This user do not have bikes!");
        }else {
            result.put("Bikes",bikes);
        }

        List<Car> cars = carFeingClient.findCarByUserId(userId);
        if(cars.isEmpty()){
            result.put("Cars","This user do not have Cars");
        }else {
            result.put("Cars",cars);
        }
        return result;
    }
}
