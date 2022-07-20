package data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="tbl_cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 200)
    private String model;
}
