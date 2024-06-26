package dev.aditya.ecommercejavaproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class category extends BaseModel {
     private String title;
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

}
