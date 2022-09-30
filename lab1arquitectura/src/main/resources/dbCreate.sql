DROP TABLE IF EXISTS public.personal CASCADE;

CREATE TABLE public.personal (
  "id" serial primary key,
  "nombres" varchar(80),
  "apellidos" varchar(80),
  "rut" varchar(80),
  "fecha_nacimiento" date,
  "fecha_ingreso" date,
  "categoria" varchar(80),
  "area" varchar(80)
);

CREATE TABLE public.hora_extra (
  "id" serial primary key,
  "fecha" date,
  "id_personal" serial,
  CONSTRAINT fk_personal FOREIGN KEY (id_personal) REFERENCES personal (id)
);

CREATE TABLE public.justificativo (
  "id" serial primary key,
  "fecha" date,
  "num_horas" integer,
  "id_personal" serial,
  CONSTRAINT fk_personal FOREIGN KEY (id_personal) REFERENCES personal (id)
);

CREATE TABLE public.reloj (
  "id" serial primary key,
  "fecha" date,
  "hora" time without time zone,
  "id_personal" serial,
  CONSTRAINT fk_personal FOREIGN KEY (id_personal) REFERENCES personal (id)
);


