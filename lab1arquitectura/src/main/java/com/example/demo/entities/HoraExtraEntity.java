package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;

@Entity
@Table(name = "hora_extra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraExtraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private Long id;
    private DateFormat fecha;
    private Long id_personal;
}
