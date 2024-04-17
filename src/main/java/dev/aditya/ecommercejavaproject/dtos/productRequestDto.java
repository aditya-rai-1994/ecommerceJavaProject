package dev.aditya.ecommercejavaproject.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class productRequestDto {
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;
}
