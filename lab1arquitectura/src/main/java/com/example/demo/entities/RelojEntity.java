package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reloj")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelojEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private LocalDate fecha;
    private LocalTime hora_entrada;
    private LocalTime hora_salida;
    private Long id_personal;


}
