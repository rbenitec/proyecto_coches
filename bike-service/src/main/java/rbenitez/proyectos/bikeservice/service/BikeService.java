package rbenitez.proyectos.bikeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rbenitez.proyectos.bikeservice.entity.Bike;
import rbenitez.proyectos.bikeservice.repository.BikeRepository;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    /*
    *   get all bikes
     */
    public List<Bike> getAll(){
        return bikeRepository.findAll();
    }

    /*
    *   Get bike by Id
     */
    public Bike getBikeById(int bikeId){
        return bikeRepository.findById(bikeId).orElse(null);
    }

    /*
    *   Created bike
     */
    public Bike createdBike(Bike bike){
        return bikeRepository.save(bike);
    }

    /*
    *   Method, find bike of the user
     */

    public List<Bike> findBikeByUserID(int userId){
        return bikeRepository.findByUserId(userId);
    }

}
