package rbenitez.proyectos.userservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bike {

    private String brand;
    private String model;
    private int userId;
}
