
-- SEQUENCE: public.seq_academico

-- DROP SEQUENCE public.seq_academico;

CREATE SEQUENCE public.seq_academico
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

ALTER SEQUENCE public.seq_academico
    OWNER TO postgres;
-- SEQUENCE: public.seq_administrador

-- DROP SEQUENCE public.seq_administrador;

CREATE SEQUENCE public.seq_administrador
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

ALTER SEQUENCE public.seq_administrador
    OWNER TO postgres;
-- SEQUENCE: public.seq_espacio

-- DROP SEQUENCE public.seq_espacio;

CREATE SEQUENCE public.seq_espacio
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

ALTER SEQUENCE public.seq_espacio
    OWNER TO postgres;

-- SEQUENCE: public.seq_solicitud

-- DROP SEQUENCE public.seq_solicitud;

CREATE SEQUENCE public.seq_solicitud
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999
    CACHE 1;

ALTER SEQUENCE public.seq_solicitud
    OWNER TO postgres;


-- Table: public.academico

-- DROP TABLE public.academico;

CREATE TABLE public.academico
(
    id_academico bigint NOT NULL DEFAULT nextval('seq_academico'::regclass),
    nombre_completo character varying(210) COLLATE pg_catalog."default" NOT NULL,
    correo_aca character varying(210) COLLATE pg_catalog."default" NOT NULL,
    password character varying(210) COLLATE pg_catalog."default" NOT NULL,
    departamento character varying(100) COLLATE pg_catalog."default" NOT NULL,
    tipo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fecha_activacion date,
    user_name character varying(100) COLLATE pg_catalog."default",
    no_trabajador character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT 1,
    activo boolean,
    CONSTRAINT academico_pkey PRIMARY KEY (id_academico)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.academico
    OWNER to postgres;


-- Table: public.administrador

-- DROP TABLE public.administrador;

CREATE TABLE public.administrador
(
    id_administrador bigint NOT NULL DEFAULT nextval('seq_administrador'::regclass),
    nombre_completo character varying(210) COLLATE pg_catalog."default" NOT NULL,
    correo_admin character varying(210) COLLATE pg_catalog."default" NOT NULL,
    password character varying(210) COLLATE pg_catalog."default" NOT NULL,
    no_trabajador character varying(20) COLLATE pg_catalog."default" NOT NULL DEFAULT 1,
    CONSTRAINT administrador_pkey PRIMARY KEY (id_administrador)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.administrador
    OWNER to postgres;


-- Table: public.espacio

-- DROP TABLE public.espacio;

CREATE TABLE public.espacio
(
    id_espacio bigint NOT NULL DEFAULT nextval('seq_espacio'::regclass),
    nombre_espacio character varying(210) COLLATE pg_catalog."default" NOT NULL,
    edificio character varying(210) COLLATE pg_catalog."default" NOT NULL,
    capacidad integer NOT NULL,
    recursos character varying(255) COLLATE pg_catalog."default" NOT NULL,
    piso integer NOT NULL,
    CONSTRAINT espacio_pkey PRIMARY KEY (id_espacio)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.espacio
    OWNER to postgres;


-- Table: public.solicitud

-- DROP TABLE public.solicitud;

CREATE TABLE public.solicitud
(
    id_solicitud bigint NOT NULL DEFAULT nextval('seq_solicitud'::regclass),
    id_academico bigint NOT NULL,
    id_espacio bigint NOT NULL,
    hora_inicio time(6) without time zone NOT NULL,
    hora_fin time(6) without time zone NOT NULL,
    fecha_solicitud date NOT NULL,
    fecha_resolucion date,
    nombre_evento character(250) COLLATE pg_catalog."default" NOT NULL,
    descripcion_evento character(500) COLLATE pg_catalog."default" NOT NULL,
    estatus boolean DEFAULT false,
    CONSTRAINT id_solicitud PRIMARY KEY (id_solicitud, id_academico, id_espacio),
    CONSTRAINT id_academico FOREIGN KEY (id_academico)
        REFERENCES public.academico (id_academico) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT id_espacio FOREIGN KEY (id_espacio)
        REFERENCES public.espacio (id_espacio) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.solicitud
    OWNER to postgres;

INSERT INTO public.administrador(
	id_administrador, nombre_completo, correo_admin, password, no_trabajador)
	VALUES (1, 'admin', 'administrador@ciencias.unam.mx', 123456, 1234567890);
