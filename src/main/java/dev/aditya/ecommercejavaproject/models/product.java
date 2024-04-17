package dev.aditya.ecommercejavaproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class product {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageURL;
    private category category;
}
