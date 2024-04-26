package dev.aditya.ecommercejavaproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class product extends BaseModel{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageURL;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private category category;
}
