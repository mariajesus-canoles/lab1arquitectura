DROP TABLE IF EXISTS public.personal CASCADE;
DROP TABLE IF EXISTS public.area CASCADE;
DROP TABLE IF EXISTS public.categoria CASCADE;
DROP TABLE IF EXISTS public.pago CASCADE;
DROP TABLE IF EXISTS public.hora_extra CASCADE;
DROP TABLE IF EXISTS public.justificativo CASCADE;
DROP TABLE IF EXISTS public.reloj CASCADE;

CREATE TABLE public.area (
  "id" serial primary key,
  "nombre" varchar(80)
);

CREATE TABLE public.categoria (
  "id" serial primary key,
  "nombre" varchar(80)
);

CREATE TABLE public.personal (
  "id" serial primary key,
  "nombres" varchar(80),
  "apellidos" varchar(80),
  "rut" varchar(80),
  "fecha_nacimiento" date,
  "fecha_ingreso" date,
  "id_categoria" serial,
  "id_area" serial,
  CONSTRAINT fk_area FOREIGN KEY (id_area) REFERENCES area (id),
  CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id)
);

CREATE TABLE public.pago (
  "id" serial primary key,
  "sueldo_fijo" integer,
  "valor_hora_extra" integer,
  "id_area" serial,
  "id_categoria" serial,
  CONSTRAINT fk_area FOREIGN KEY (id_area) REFERENCES area (id),
  CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id)
);

CREATE TABLE public.hora_extra (
  "id" serial primary key,
  "fecha" date,
  "num_horas" integer,
  "id_personal" serial,
  CONSTRAINT fk_personal FOREIGN KEY (id_personal) REFERENCES personal (id)
);

CREATE TABLE public.justificativo (
  "id" serial primary key,
  "fecha" date,
  "id_personal" serial,
  CONSTRAINT fk_personal FOREIGN KEY (id_personal) REFERENCES personal (id)
);

CREATE TABLE public.reloj (
  "id" serial primary key,
  "fecha" date,
  "hora_entrada" time without time zone,
  "hora_salida" time without time zone,
  "id_personal" serial,
  CONSTRAINT fk_personal FOREIGN KEY (id_personal) REFERENCES personal (id)
);


