package rbenitez.proyectos.bikeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rbenitez.proyectos.bikeservice.entity.Bike;

import java.util.List;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
    /*
    *   find all bike of the user
     */

    List<Bike> findByUserId(int userId);
}
