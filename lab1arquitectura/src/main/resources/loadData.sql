INSERT INTO "area" (id, nombre)
VALUES (1, 'Administración'), (2, 'Operaciones');

INSERT INTO "categoria" (id, nombre)
VALUES (1, 'A'), (2, 'B'), (3, 'C');

INSERT INTO "personal" (id, nombres, apellidos, rut, fecha_nacimiento, fecha_ingreso, id_categoria, id_area)
VALUES (1,'Esteban','Gómez', '11.234.123-6', '1999/12/23', '2010/06/24', 3, 1),
(2, 'Daniela', 'Roa', '12.457.562-3', '1980/11/28', '1979/10/23', 2, 1),
(3, 'Gabriela', 'Rojas', '21.142.354-k', '1995/02/14', '2006/06/15', 3, 2),
(4, 'Camilo', 'Jeraldo', '17.765.876-2', '1996/01/08', '1975/04/13', 1, 2),
(5, 'Diego', 'Contreras', '10.234.123-4', '1983/05/17', '1976/11/24', 3, 1),
(6, 'Celeste', 'González', '13.346.237-2', '1980/02/23', '2012/12/12', 2, 1),
(7, 'Bastián', 'Donoso', '10.321.543-3', '1988/08/27', '2007/09/16', 1, 2);

INSERT INTO "pago" (id, sueldo_fijo, valor_hora_extra, id_categoria, id_area)
VALUES (1, 1700000, 35000, 1, 1), (2, 2700000, 25000, 2, 1), (3, 800000, 15000, 3, 1),
(4, 2300000, 55000, 1, 2), (5, 1600000, 40000, 2, 2), (6, 900000, 25000, 3, 2);





