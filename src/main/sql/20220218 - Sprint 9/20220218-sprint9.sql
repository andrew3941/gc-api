----------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS
----------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS (
ID BIGINT NOT NULL,
DENOMINACION CHARACTER VARYING (100) NOT NULL,
OBSERVACIONES CHARACTER VARYING (200),
ACTIVO BOOLEAN,
CONSTRAINT TM_MANTENIMIENTOS_TIPOS_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS.DENOMINACION IS 'Denominacion del tipo de mantenimiento';
COMMENT ON COLUMN GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS.OBSERVACIONES IS 'Observaciones del tipo de mantenimiento';
COMMENT ON COLUMN GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS.ACTIVO IS '0 = No activo, 1 = Activo';

----------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTRO.MANTENIMIENTOS
----------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.MANTENIMIENTOS (
ID BIGINT NOT NULL,
TIPO_ID BIGINT NOT NULL,
PROVEEDOR_ID BIGINT NOT NULL,
CUANTIA NUMERIC,
REF_FACTURA CHARACTER VARYING (80),
FECHA DATE,
PERIODICIDAD_ID BIGINT,
OBSERVACIONES CHARACTER VARYING (200),
CREADO TIMESTAMP NOT NULL,
CREADO_POR BIGINT NOT NULL,
MODIFICADO TIMESTAMP,
MODIFICADO_POR BIGINT,
BORRADO TIMESTAMP,
BORRADO_POR BIGINT,
CONSTRAINT MANTENIMIENTOS_PK PRIMARY KEY (ID),
FOREIGN KEY (TIPO_ID) REFERENCES GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS (ID),
FOREIGN KEY (PROVEEDOR_ID) REFERENCES GESTION_CENTROS.PROVEEDORES (ID),
FOREIGN KEY (PERIODICIDAD_ID) REFERENCES GESTION_CENTROS.TM_PERIODICIDAD_GASTO (ID),
FOREIGN KEY (CREADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID),
FOREIGN KEY (MODIFICADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID),
FOREIGN KEY (BORRADO_POR) REFERENCES GC2006_RELEASE.PC_USUARIOS (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.TIPO_ID IS 'FK: GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.PROVEEDOR_ID IS 'FK: GESTION_CENTROS.PROVEEDORES.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.CUANTIA IS 'Cuant�a del mantenimiento';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.REF_FACTURA IS 'Referencia a la factura';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.FECHA IS 'Fecha de ejecuci�n del mantenimiento';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.PERIODICIDAD_ID IS 'FK: GESTION_CENTROS.TM_PERIODICIDAD_GASTO.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.OBSERVACIONES IS 'Observaciones del mantenimiento';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.CREADO IS 'Fecha y hora de creaci�n del registro';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.CREADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.MODIFICADO IS 'Fecha y hora de la �ltima modificaci�n del registro';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.MODIFICADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.BORRADO IS 'Fecha y hora de borrado del registro';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS.BORRADO_POR IS 'FK: GC2006_RELEASE.PC_USUARIOS.ID';

CREATE SEQUENCE GESTION_CENTROS.MANTENIMIENTOS_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;

----------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS
----------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS (
ID BIGINT NOT NULL,
MANTENIMIENTO_ID BIGINT NOT NULL,
DOC_URL CHARACTER VARYING (200) NOT NULL,
DOC_NOMBRE CHARACTER VARYING (150) NOT NULL,
DOC_CONTENT_TYPE CHARACTER VARYING (100) NOT NULL,
CONSTRAINT MANTENIMIENTOS_X_ADJUNTOS_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS.MANTENIMIENTO_ID IS 'FK: GESTION_CENTROS.MANTENIMIENTOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS.DOC_URL IS 'URL de almacenamiento del fichero';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS.DOC_NOMBRE IS 'Nombre original del fichero';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS.DOC_CONTENT_TYPE IS 'Content type del fichero';

CREATE SEQUENCE GESTION_CENTROS.MANTENIMIENTOS_X_ADJUNTOS_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;

----------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES
----------------------------------------------------------------------------------------------------------------------------
CREATE TABLE GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES (
ID BIGINT NOT NULL,
MANTENIMIENTO_ID BIGINT NOT NULL,
DELEGACION_ID BIGINT NOT NULL,
CONSTRAINT MANTENIMIENTOS_X_DELEGACIONES_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES.MANTENIMIENTO_ID IS 'FK: GESTION_CENTROS.MANTENIMIENTOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES.DELEGACION_ID IS 'FK: GC2006_RELEASE.PC_DELEGACIONES.ID';

CREATE SEQUENCE GESTION_CENTROS.MANTENIMIENTOS_X_DELEGACIONES_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;

------------------------------------------------------------------------------------------------------------------------
-- Insert data
------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS
INSERT INTO GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS (id, denominacion, observaciones, activo)
VALUES (1, 'MANTENIMIENTO PREVENTIVO', '', true);

INSERT INTO GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS (id, denominacion, observaciones, activo)
VALUES (2, 'MANTENIMIENTO CORRECTIVO', '', true);

-- NEW FIELDS MAINTENANCE
ALTER TABLE GESTION_CENTROS.MANTENIMIENTOS ADD COLUMN CONCEPTO VARCHAR(150);
ALTER TABLE GESTION_CENTROS.MANTENIMIENTOS ADD COLUMN IMPORTE_ANUAL NUMERIC(10,2);
