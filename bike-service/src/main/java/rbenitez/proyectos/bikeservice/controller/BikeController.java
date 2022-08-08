package rbenitez.proyectos.bikeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rbenitez.proyectos.bikeservice.entity.Bike;
import rbenitez.proyectos.bikeservice.service.BikeService;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping()
    public ResponseEntity<List<Bike>> bikes(){
        List<Bike> bikes = bikeService.getAll();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{bikeId}")
    public ResponseEntity<Bike> finById(@PathVariable("bikeId") int bikeId){
        Bike bike = bikeService.getBikeById(bikeId);
        if(bike==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bike);
    }

    @PostMapping()
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike newBike = bikeService.createdBike(bike);
        if(newBike==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newBike);
    }

    /*
    *   Method for find all bikes of the user
     */

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> findBikeByUserId(@PathVariable("userId") int userId){
        List<Bike> bikes = bikeService.findBikeByUserID(userId);
        return ResponseEntity.ok(bikes);
    }

}
