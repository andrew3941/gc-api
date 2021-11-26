------------------------------------------------------------------------------------------------------------------------
-- SCHEMA GESTION_CENTROS
------------------------------------------------------------------------------------------------------------------------
CREATE SCHEMA GESTION_CENTROS;

------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES (
    ID BIGINT NOT NULL,
    DELEGACION_ID BIGINT NOT NULL,
    SUPERFICIE NUMERIC(6, 2),
    PUESTOS_DISPONIBLES INT,
    ACCESIBILIDAD BOOLEAN,
    PLAZAS_GARAJE BOOLEAN,
    NUM_PLAZAS_GARAJE INT,
    DESCRIPCION VARCHAR(500),
    TODOS_DPTOS BOOLEAN,
    CREADO TIMESTAMP NOT NULL,
    CREADO_POR BIGINT NOT NULL,
    MODIFICADO TIMESTAMP,
    MODIFICADO_POR BIGINT,
    CONSTRAINT PC_DELEGACIONES_DETALLES_PK PRIMARY KEY (ID),
    FOREIGN KEY (DELEGACION_ID) REFERENCES GC2006_RELEASE.PC_DELEGACIONES (ID),
    FOREIGN KEY (CREADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID),
    FOREIGN KEY (MODIFICADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.DELEGACION_ID IS 'FK: GC2006_RELEASE.PC_DELEGACIONES.ID';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.SUPERFICIE IS 'Superficie total en metros cuadrados';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.PUESTOS_DISPONIBLES IS 'Puestos de trabajo disponibles';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.ACCESIBILIDAD IS 'Accesible = True | No accesible = False';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.PLAZAS_GARAJE IS 'Plaza de garaje  = True | Sin plaza de garaje = False';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.NUM_PLAZAS_GARAJE IS 'N�mero total de plazas de garaje';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.DESCRIPCION IS 'Descripci�n del centro de trabajo';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.TODOS_DPTOS IS 'Todos los dtpos = True | No todos los dptos = False';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.CREADO IS 'Fecha de creaci�n de los detalles del centro';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.CREADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.MODIFICADO IS 'Fecha de modificaci�n de los detalles del centro';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES.MODIFICADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';

CREATE SEQUENCE GESTION_CENTROS.PC_DELEGACIONES_DETALLES_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;

------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES_X_DPTOS
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES_X_DPTOS (
ID BIGINT NOT NULL,
DELEGACION_DETALLE_ID BIGINT NOT NULL,
DEPARTAMENTO_ID BIGINT NOT NULL,
CONSTRAINT PC_DELEGACIONES_X_DPTOS_PK PRIMARY KEY (ID),
FOREIGN KEY (DELEGACION_DETALLE_ID) REFERENCES GESTION_CENTROS.PC_DELEGACIONES_DETALLES (ID),
FOREIGN KEY (DEPARTAMENTO_ID) REFERENCES RRHH.TM_DEPARTAMENTOS (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES_X_DPTOS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES_X_DPTOS.DELEGACION_DETALLE_ID IS 'FK: GESTION_CENTROS.PC_DELEGACIONES_DETALLES.ID';
COMMENT ON COLUMN GESTION_CENTROS.PC_DELEGACIONES_DETALLES_X_DPTOS.DEPARTAMENTO_ID IS 'FK: RRHH.TM_DEPARTAMENTOS.ID';

CREATE SEQUENCE GESTION_CENTROS.PC_DELEGACIONES_DETALLES_X_DPTOS_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;