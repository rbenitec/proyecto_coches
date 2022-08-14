package rbenitez.proyectos.userservice.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import rbenitez.proyectos.userservice.model.Bike;

import java.util.List;

/*
*   put the service name of your microservice and your url, in @FeingClient
*   in RequestMapping, put the name of direction controller.
 */
@FeignClient(name = "bike-service", path = "/bike")
public interface BikeFeingClient {

    /*
    *   Defined the method that use, in this case, we will use Save
    *   Method for save a car whit a UserId, from the user microservice
     */
    @PostMapping()
    Bike saveBike(@RequestBody Bike bike);

    /*
    *   Get all bike of the user
     */

    @GetMapping("/byuser/{userId}")
    List<Bike> findBikeByUserId(@PathVariable("userId") int userId);
}
