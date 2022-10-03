INSERT INTO "area" (id, nombre)
VALUES (1, 'Administración'), (2, 'Operaciones');

INSERT INTO "categoria" (id, nombre)
VALUES (1, 'A'), (2, 'B'), (3, 'C');

INSERT INTO "personal" (id, nombres, apellidos, rut, fecha_nacimiento, fecha_ingreso, id_categoria, id_area)
VALUES (1,'Maria Jesus','Canoles Roa', '20.300.159-2', '2000/02/23', '2021/12/20', 1, 2),
(2,'Batián','Montenegro Cruces', '19.347.109-K', '1999/12/23', '1999/06/24', 3, 1),
(3, 'Denisse', 'González', '18.768.688-1', '1980/11/28', '1979/12/23', 2, 1);

INSERT INTO "pago" (id, sueldo_fijo, valor_hora_extra, id_categoria, id_area)
VALUES (1, 1700000, 35000, 1, 1), (2, 2700000, 25000, 2, 1), (3, 800000, 15000, 3, 1),
(4, 2300000, 55000, 1, 2), (5, 1600000, 40000, 2, 2), (6, 900000, 25000, 3, 2);

INSERT INTO "reloj" (id, fecha, hora_entrada, hora_salida, id_personal)
VALUES (1, '2022/08/24', '08:00', '18:00', 1),
(2, '2022/08/24', '08:10', '19:10', 2),
(3, '2022/08/24', '07:20', '20:00', 3),
(4, '2022/08/25', '08:10', '18:00', 1),
(5, '2022/08/25', '08:00', '19:10', 2),
(6, '2022/08/25', '07:59', '20:00', 3),
(7, '2022/08/26', '08:10', '18:00', 1),;

INSERT INTO "hora_extra" (id, fecha, num_horas, id_personal)
VALUES (1, '2022/08/25', 1, 2),
(2, '2022/08/26', 2, 3);





