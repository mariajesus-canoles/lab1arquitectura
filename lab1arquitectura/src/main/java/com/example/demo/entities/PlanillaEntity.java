package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanillaEntity {

    private String rut;
    private String nombre_empleado;
    private String categoria;
    private Integer anos_servicio_empresa;
    private Integer sueldo_fijo_mensual;
    private Integer bonificacion_horas_extras;
    private Integer bonificacion_tiempo_servicio;
    private Integer bonificacion_puntualidad;
    private Integer descuento_tardanza;
    private Integer descuento_retiro;
    private Integer sueldo_bruto;
    private Integer cotizacion_previsional;
    private Integer cotizacion_plan_salud;
    private Integer monto_suelto_final;

}
