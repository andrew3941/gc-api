------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.TM_PROVEEDORES_TIPOS
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.TM_PROVEEDORES_TIPOS (
    ID BIGINT NOT NULL,
    DENOMINACION VARCHAR(100) NOT NULL,
    OBSERVACIONES VARCHAR(200),
    ACTIVO BOOLEAN NOT NULL,
    CONSTRAINT TM_PROVEEDORES_TIPOS_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_TIPOS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_TIPOS.DENOMINACION IS 'Denominación del tipo de proveedor';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_TIPOS.OBSERVACIONES IS 'Observaciones sobre el tipo de proveedor';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_TIPOS.ACTIVO IS 'False - No activo, True -  Activo';

------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.TM_PERIODICIDAD_GASTO
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.TM_PERIODICIDAD_GASTO (
ID BIGINT NOT NULL,
DENOMINACION VARCHAR(100) NOT NULL,
OBSERVACIONES VARCHAR(200),
ACTIVO BOOLEAN NOT NULL,
CONSTRAINT TM_PERIODICIDAD_GASTO_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.TM_PERIODICIDAD_GASTO.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.TM_PERIODICIDAD_GASTO.DENOMINACION IS 'Denominación del tipo de periodicidad';
COMMENT ON COLUMN GESTION_CENTROS.TM_PERIODICIDAD_GASTO.OBSERVACIONES IS 'Observaciones sobre el tipo de periodicidad';
COMMENT ON COLUMN GESTION_CENTROS.TM_PERIODICIDAD_GASTO.ACTIVO IS 'False - No activo, True -  Activo';

INSERT INTO GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID, DENOMINACION, OBSERVACIONES, ACTIVO) VALUES (1, 'Mensual', NULL, TRUE);
INSERT INTO GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID, DENOMINACION, OBSERVACIONES, ACTIVO) VALUES (2, 'Bimensual', NULL, TRUE);
INSERT INTO GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID, DENOMINACION, OBSERVACIONES, ACTIVO) VALUES (3, 'Trimestral', NULL, TRUE);
INSERT INTO GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID, DENOMINACION, OBSERVACIONES, ACTIVO) VALUES (4, 'Cuatrimestral', NULL, TRUE);
INSERT INTO GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID, DENOMINACION, OBSERVACIONES, ACTIVO) VALUES (5, 'Semestral', NULL, TRUE);
INSERT INTO GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID, DENOMINACION, OBSERVACIONES, ACTIVO) VALUES (6, 'Anual', NULL, TRUE);

------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.TM_PROVEEDORES_AREAS
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.TM_PROVEEDORES_AREAS (
ID BIGINT NOT NULL,
DENOMINACION VARCHAR(100) NOT NULL,
OBSERVACIONES VARCHAR(200),
ACTIVO BOOLEAN NOT NULL,
CONSTRAINT TM_PROVEEDORES_AREAS_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_AREAS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_AREAS.DENOMINACION IS 'Denominación del area';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_AREAS.OBSERVACIONES IS 'Observaciones sobre el area';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_AREAS.ACTIVO IS 'False - No activo, True -  Activo';

------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO (
ID BIGINT NOT NULL,
DENOMINACION VARCHAR(100) NOT NULL,
OBSERVACIONES VARCHAR(200),
ACTIVO BOOLEAN NOT NULL,
CONSTRAINT TM_PROVEEDORES_EVALUACION_TIPO_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO.DENOMINACION IS 'Denominación de la evaluación';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO.OBSERVACIONES IS 'Observaciones sobre la evaluación';
COMMENT ON COLUMN GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO.ACTIVO IS 'False - No activo, True -  Activo';

------------------------------------------------------------------------------------------------------------------------
-- TABLE GESTION_CENTROS.PROVEEDORES
------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.PROVEEDORES (
ID BIGINT NOT NULL,
DELEGACION_ID BIGINT NOT NULL,
NOMBRE VARCHAR(100) NOT NULL,
PROVEEDOR_CENTRALIZADO BOOLEAN NOT NULL,
CIF VARCHAR(14) NOT NULL,
TIPO_ID BIGINT NOT NULL,
AREA_ID BIGINT NOT NULL,
TIPO_EVALUACION_ID BIGINT NOT NULL,
EMAIL VARCHAR(150),
DIRECCION VARCHAR(150),
PERSONA_CONTACTO VARCHAR(250) NOT NULL,
TELEFONO VARCHAR(15),
DETALLES VARCHAR(2000),
DOC_URL VARCHAR(200),
DOC_NOMBRE VARCHAR(150),
DOC_CONTENT_TYPE VARCHAR(50),
PERIODICIDAD_GASTO_ID BIGINT,
GASTO NUMERIC(10,2),
FECHA_INICIO_SERVICIO DATE,
FECHA_FIN_SERVICIO DATE,
FECHA_ALARMA_SERVICIO DATE,
RESPONSABLE_ID BIGINT,
CREADO TIMESTAMP NOT NULL,
CREADO_POR BIGINT NOT NULL,
MODIFICADO TIMESTAMP,
MODIFICADO_POR BIGINT,
CONSTRAINT PROVEEDORES_PK PRIMARY KEY (ID),
FOREIGN KEY (TIPO_ID) REFERENCES GESTION_CENTROS.TM_PROVEEDORES_TIPOS (ID),
FOREIGN KEY (AREA_ID) REFERENCES GESTION_CENTROS.TM_PROVEEDORES_AREAS (ID),
FOREIGN KEY (TIPO_EVALUACION_ID) REFERENCES GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO (ID),
FOREIGN KEY (PERIODICIDAD_GASTO_ID) REFERENCES GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID),
FOREIGN KEY (CREADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID),
FOREIGN KEY (MODIFICADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.DELEGACION_ID IS 'FK: GC2006_RELEASE.PC_DELEGACIONES.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.NOMBRE IS 'Nombre del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.PROVEEDOR_CENTRALIZADO IS 'Indica si la información del proveedor se replica para todos los centros seleccionados';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.CIF IS 'CIF del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.TIPO_ID IS 'FK: GESTION_CENTROS.TM_PROVEEDORES_TIPO.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.AREA_ID IS 'FK: GESTION_CENTROS.TM_PROVEEDORES_AREAS.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.TIPO_EVALUACION_ID IS 'FK: GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.EMAIL IS 'Email del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.DIRECCION IS 'Dirección social del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.PERSONA_CONTACTO IS 'Persona de contacto del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.TELEFONO IS 'Teléfono de contacto del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.DETALLES IS 'Observaciones sobre el proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.DOC_URL IS 'URL donde se almacena el fichero';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.DOC_NOMBRE IS 'Nombre del fichero';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.DOC_CONTENT_TYPE IS 'Content type del fichero';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.PERIODICIDAD_GASTO_ID IS 'FK: GESTION_CENTROS.TM_PERIOCIDIDAD_GASTO.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.GASTO IS 'Cuantía del gasto asociada al proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.FECHA_INICIO_SERVICIO IS 'Fecha de inicio del servicio';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.FECHA_FIN_SERVICIO IS 'Fecha de fin del servicio';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.FECHA_ALARMA_SERVICIO IS 'Fecha de alarma del servicio';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.RESPONSABLE_ID IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.CREADO IS 'Fecha de creación del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.CREADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.MODIFICADO IS 'Fecha de última modificación del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES.MODIFICADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';

CREATE SEQUENCE GESTION_CENTROS.PROVEEDORES_SEQ MINVALUE 1 MAXVALUE 9999999999999999 INCREMENT BY 1;



------------------------------------------------------------------------------------------------------------------------
-- Insert data
------------------------------------------------------------------------------------------------------------------------

-- GESTION_CENTROS.TM_PROVEEDORES_TIPOS
insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (1, 'LIMPIEZA', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (2, 'MANTENIMIENTO', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (3, 'TELECOMUNICACIONES', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (4, 'TECNOLOGICO', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (5, 'SUMINISTROS', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (6, 'ALQUILER CENTRO', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (7, 'PARKING', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (8, 'MATERIAL DE OFICINA', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (9, 'MENSAJERÍA', '', true);

insert into gestion_centros.tm_proveedores_tipos (id, denominacion, observaciones, activo)
values (10, 'OTROS', '', true);


-- GESTION_CENTROS.TM_PROVEEDORES_AREAS
insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (1, 'COMPRAS', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (2, 'INFRAESTRUCTURAS', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (3, 'TIC', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (4, 'TECNICA', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (5, 'VS', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (6, 'ADMINISTRACION', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (7, 'COMERCIAL', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (8, 'SAC', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (9, 'FORMACION', '', true);

insert into gestion_centros.tm_proveedores_areas (id, denominacion, observaciones, activo)
values (10, 'OTROS', '', true);


-- GESTION_CENTROS.TM_PROVEEDORES_EVALUACION_TIPO
insert into gestion_centros.tm_proveedores_evaluacion_tipo (id, denominacion, observaciones, activo)
values (1, 'CONTINUA', '', true);

insert into gestion_centros.tm_proveedores_evaluacion_tipo (id, denominacion, observaciones, activo)
values (2, 'TRIMESTRAL', '', true);

insert into gestion_centros.tm_proveedores_evaluacion_tipo (id, denominacion, observaciones, activo)
values (3, 'SEMESTRAL', '', true);

insert into gestion_centros.tm_proveedores_evaluacion_tipo (id, denominacion, observaciones, activo)
values (4, 'ANUAL', '', true);

