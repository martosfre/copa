CREATE TABLE "admin".equipo (
	equ_id serial NOT NULL,
	equ_nombre varchar(150) NOT NULL,
	equ_anio_fundacion int4 NOT NULL,
	CONSTRAINT equipo_pk PRIMARY KEY (equ_id)
);
COMMENT ON TABLE "admin".equipo IS 'Tabla para almacenar los equipos de fútbol';

-- Column comments

COMMENT ON COLUMN "admin".equipo.equ_id IS 'Identificador de Equipo';
COMMENT ON COLUMN "admin".equipo.equ_nombre IS 'Nombre del equipo';
COMMENT ON COLUMN "admin".equipo.equ_anio_fundacion IS 'Año fundación equipo';


CREATE TABLE "admin".jugador (
	jug_id serial NOT NULL,
	jug_nombre varchar(50) NOT NULL,
	jug_apellido_paterno varchar(50) NOT NULL,
	jug_apellido_materno varchar(50) NULL,
	jug_fecha_nacimiento date NOT NULL,
	jug_foto bytea NULL,
	equ_id int4 NOT NULL,
	CONSTRAINT jugador_pk PRIMARY KEY (jug_id),
	CONSTRAINT jugador_fk FOREIGN KEY (equ_id) REFERENCES "admin".equipo(equ_id)
);
COMMENT ON TABLE "admin".jugador IS 'Tabla para almacenar los jugadores';

-- Column comments

COMMENT ON COLUMN "admin".jugador.jug_id IS 'Identificador de jugador';
COMMENT ON COLUMN "admin".jugador.jug_nombre IS 'Nombre del jugador';
COMMENT ON COLUMN "admin".jugador.jug_apellido_paterno IS 'Apellido parterno del jugador';
COMMENT ON COLUMN "admin".jugador.jug_apellido_materno IS 'Apellido materno jugador';
COMMENT ON COLUMN "admin".jugador.jug_fecha_nacimiento IS 'Fecha de nacimiento jugador';
COMMENT ON COLUMN "admin".jugador.jug_foto IS 'Foto del jugador';
COMMENT ON COLUMN "admin".jugador.equ_id IS 'Identifidador del equipo';

CREATE TABLE "admin".provincia (
	pro_id serial NOT NULL,
	pro_nombre varchar(50) NOT NULL,
	pro_codigo varchar(10) NOT NULL,
	CONSTRAINT provincia_pk PRIMARY KEY (pro_id)
);
COMMENT ON TABLE "admin".provincia IS 'Tabla para almacenar las provincias';

-- Column comments

COMMENT ON COLUMN "admin".provincia.pro_id IS 'Identificador de provincia';
COMMENT ON COLUMN "admin".provincia.pro_nombre IS 'Nombre de  la provincia';
COMMENT ON COLUMN "admin".provincia.pro_codigo IS 'Código dpa provincia';


CREATE TABLE "admin".canton (
	can_id serial NOT NULL,
	can_nombre varchar(50) NOT NULL,
	can_codigo varchar(10) NOT NULL,
	pro_id int4 NOT NULL,
	CONSTRAINT canton_pk PRIMARY KEY (can_id),
	CONSTRAINT provincia_fk FOREIGN KEY (pro_id) REFERENCES "admin".provincia(pro_id)
);
COMMENT ON TABLE "admin".canton IS 'Tabla para almacenar los cantones';

-- Column comments

COMMENT ON COLUMN "admin".canton.can_id IS 'Identificador de cantón';
COMMENT ON COLUMN "admin".canton.can_nombre IS 'Nombre del cantón';
COMMENT ON COLUMN "admin".canton.can_codigo IS 'Código dpa cantón';
COMMENT ON COLUMN "admin".canton.pro_id IS 'Identificador de provincia';

CREATE TABLE "admin".parroquia (
	parr_id serial NOT NULL,
	parr_nombre varchar(50) NOT NULL,
	parr_codigo varchar(10) NOT NULL,
	can_id int4 NOT NULL,
	CONSTRAINT parroquia_pk PRIMARY KEY (parr_id),
	CONSTRAINT canton_fk FOREIGN KEY (can_id) REFERENCES "admin".canton(can_id)
);
COMMENT ON TABLE "admin".parroquia IS 'Tabla para almacenar las parroquias';

-- Column comments

COMMENT ON COLUMN "admin".parroquia.parr_id IS 'Identificador de parroquia';
COMMENT ON COLUMN "admin".parroquia.parr_nombre IS 'Nombre del parroquia';
COMMENT ON COLUMN "admin".parroquia.parr_codigo IS 'Código dpa parroquia';
COMMENT ON COLUMN "admin".parroquia.can_id IS 'Identificador de cantón';


ALTER TABLE "admin".jugador RENAME CONSTRAINT jugador_fk TO equipo_jugador_fk;
ALTER TABLE "admin".jugador ADD CONSTRAINT parroquia_jugador_fk FOREIGN KEY (parr_id) REFERENCES "admin".parroquia(parr_id);



ALTER TABLE "admin".jugador ADD jug_telefono_celular varchar(10) NOT NULL;
COMMENT ON COLUMN "admin".jugador.jug_telefono_celular IS 'Teléfono celular del jugador';
ALTER TABLE "admin".jugador ADD jug_correo varchar(50) NULL;
COMMENT ON COLUMN "admin".jugador.jug_correo IS 'Correo electrónico jugador';
ALTER TABLE "admin".jugador ADD jug_direccion varchar(300) NULL;
COMMENT ON COLUMN "admin".jugador.jug_direccion IS 'Dirección del Jugador';


