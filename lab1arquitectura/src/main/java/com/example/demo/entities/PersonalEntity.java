package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "personal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private Long id;
    private String nombres;
    private String apellidos;
    private String rut;
    private LocalDate fecha_nacimiento;
    private LocalDate fecha_ingreso;
    private Long id_categoria;
    private Long id_area;

}
