package rbenitez.proyectos.userservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class Car {

    private String brand;
    private String model;
    private int userId;

}
