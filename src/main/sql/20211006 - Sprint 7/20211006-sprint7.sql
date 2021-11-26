------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.TM_TIPOS_ATRIBUTOS
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (
ID BIGINT NOT NULL,
COMPONENTE VARCHAR(50) NOT NULL,
TIPO VARCHAR(50),
TIPO_DATO VARCHAR(50) NOT NULL,
CONSTRAINT TM_TIPOS_ATRIBUTOS_PK PRIMARY KEY (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.TM_TIPOS_ATRIBUTOS.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.TM_TIPOS_ATRIBUTOS.COMPONENTE IS 'Tipo de componente en el html';
COMMENT ON COLUMN GESTION_CENTROS.TM_TIPOS_ATRIBUTOS.TIPO IS 'Tipo de componente';
COMMENT ON COLUMN GESTION_CENTROS.TM_TIPOS_ATRIBUTOS.TIPO_DATO IS 'Tipo de valor del componente';

INSERT INTO GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (ID, COMPONENTE, TIPO, TIPO_DATO)
VALUES (1, 'input', 'text', 'text');
INSERT INTO GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (ID, COMPONENTE, TIPO, TIPO_DATO)
VALUES (2, 'input', 'number', 'number');
INSERT INTO GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (ID, COMPONENTE, TIPO, TIPO_DATO)
VALUES (3, 'input', 'date', 'date');
INSERT INTO GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (ID, COMPONENTE, TIPO, TIPO_DATO)
VALUES (4, 'textarea', null, 'text');
INSERT INTO GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (ID, COMPONENTE, TIPO, TIPO_DATO)
VALUES (5, 'radiobutton', null, 'number');

------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.CONF_PROVEEDORES_DETALLES
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (
ID BIGINT NOT NULL,
TIPO_PROVEEDOR_ID BIGINT NOT NULL,
TIPO_ATRIBUTO_ID BIGINT NOT NULL,
ETIQUETA VARCHAR(50) NOT NULL,
ORDEN INT NOT NULL,
-- MAX_LENGTH
-- REQUIRED
CONSTRAINT CONF_PROVEEDORES_DETALLES_PK PRIMARY KEY (ID),
FOREIGN KEY (TIPO_PROVEEDOR_ID) REFERENCES GESTION_CENTROS.TM_PROVEEDORES_TIPOS (ID),
FOREIGN KEY (TIPO_ATRIBUTO_ID) REFERENCES GESTION_CENTROS.TM_TIPOS_ATRIBUTOS (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.CONF_PROVEEDORES_DETALLES.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.CONF_PROVEEDORES_DETALLES.TIPO_PROVEEDOR_ID IS 'FK: GESTION_CENTROS.TM_PROVEEDORES_TIPOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.CONF_PROVEEDORES_DETALLES.TIPO_ATRIBUTO_ID IS 'FK: GESTION_CENTROS.TM_TIPOS_ATRIBUTOS.ID';
COMMENT ON COLUMN GESTION_CENTROS.CONF_PROVEEDORES_DETALLES.ETIQUETA IS 'Atributo del proveedor';
COMMENT ON COLUMN GESTION_CENTROS.CONF_PROVEEDORES_DETALLES.ORDEN IS 'Orden por el que se mostrar� en pantalla';

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (1, 1, 2, 'Precio/Hora', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (2, 1, 2, 'Precio/Hora extra', 2);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (3, 1, 5, 'Fungible', 3);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (4, 1, 5, 'Limpieza de cristales', 4);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (5, 1, 5, 'Limpieza UM', 5);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (6, 2, 2, 'Potencia', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (7, 2, 1, 'Tipo de tarifa', 2);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (8, 2, 1, 'CUPS (C�digo contador)', 3);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (9, 3, 5, 'Renting', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (10, 3, 5, 'Propia', 2);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (11, 3, 5, 'Alquiler', 3);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (12, 3, 2, 'Coste/copia', 4);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (13, 3, 2, 'Coste copia/media mensual', 5);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (14, 3, 2, 'Coste copia BN', 6);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (15, 3, 2, 'Coste copia Color', 7);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (16, 4, 1, 'C�digo de la delegaci�n', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (17, 4, 5, 'Recogida de paqueter�a', 2);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (18, 5, 5, 'Renting', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (19, 5, 5, 'Propio/leasing', 2);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (20, 5, 2, 'Cuota del renting', 3);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (21, 5, 2, 'Km contratados', 4);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (22, 6, 2, 'N� contador', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (23, 6, 2, 'N� contrato', 2);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (24, 7, 2, 'Coste KIT', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (25, 8, 2, 'Coste guantes / mascarillas / desinfectante', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (26, 9, 2, 'Precio medio linea', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (27, 9, 2, 'N� lineas en sede', 2);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (28, 10, 2, 'Coste mensual', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (29, 11, 2, 'Coste anual', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (30, 12, 2, 'Coste plaza', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (31, 13, 2, 'Coste m2', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (32, 13, 5, 'Fianza', 2);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (33, 13, 2, 'Fianza importe', 3);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (34, 14, 1, 'Duda', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (35, 15, 1, 'Duda', 1);

INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (36, 16, 1, 'Contenido', 1);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (37, 16, 1, 'Contienten', 2);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (38, 16, 5, 'Franquicia', 3);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (39, 16, 2, 'Franquicia importe', 4);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (40, 16, 1, 'Referencia catastral', 5);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (41, 16, 5, 'Responsabilidad Civil', 6);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (42, 16, 5, 'Da�os agua', 7);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (43, 16, 5, 'Roturas de cristales', 8);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (44, 16, 5, 'Incendio', 9);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (45, 16, 5, 'Robo', 10);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (46, 16, 5, 'Da�os fen�menos atmosf�ricos', 11);
INSERT INTO GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID, TIPO_PROVEEDOR_ID, TIPO_ATRIBUTO_ID, ETIQUETA, ORDEN)
VALUES (47, 16, 5, 'P�rdida de beneficio (lucro cesante)', 12);

------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.PROVEEDORES_DETALLES
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE GESTION_CENTROS.PROVEEDORES_DETALLES (
ID BIGINT NOT NULL,
PROV_DEL_ID BIGINT NOT NULL,
CONF_PROV_DETALLE_ID BIGINT NOT NULL,
CONF_PROV_DETALLE_VALOR TEXT,
CONSTRAINT PROVEEDORES_DETALLES_PK PRIMARY KEY (ID),
FOREIGN KEY (PROV_DEL_ID) REFERENCES GESTION_CENTROS.PROVEEDORES_X_DELEGACIONES (ID),
FOREIGN KEY (CONF_PROV_DETALLE_ID) REFERENCES GESTION_CENTROS.CONF_PROVEEDORES_DETALLES (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_DETALLES.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_DETALLES.PROV_DEL_ID IS 'FK: GESTION_CENTROS.PROVEEDORES_X_DELEGACION.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_DETALLES.CONF_PROV_DETALLE_ID IS 'FK: GESTION_CENTROS.CONF_PROVEEDORES_DETALLES.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_DETALLES.CONF_PROV_DETALLE_VALOR IS 'Valor del campo';


CREATE SEQUENCE GESTION_CENTROS.PROVEEDORES_DETALLES_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;


------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.PROVEEDORES_X_AREA
------------------------------------------------------------------------------------------------------------------------

CREATE TABLE GESTION_CENTROS.PROVEEDORES_X_AREA (
ID BIGINT NOT NULL,
PROVEEDOR_ID BIGINT NOT NULL,
AREA_ID BIGINT NOT NULL,
CONSTRAINT PROVEEDORES_X_AREA_PK PRIMARY KEY (ID),
FOREIGN KEY (PROVEEDOR_ID) REFERENCES GESTION_CENTROS.PROVEEDORES (ID),
FOREIGN KEY (AREA_ID) REFERENCES GESTION_CENTROS.TM_PROVEEDORES_AREAS (ID)
);

COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_X_AREA.ID IS 'Primary key de la tabla';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_X_AREA.PROVEEDOR_ID IS 'FK: GESTION_CENTROS.PROVEEDORES.ID';
COMMENT ON COLUMN GESTION_CENTROS.PROVEEDORES_X_AREA.AREA_ID IS 'FK: GESTION_CENTROS.TM_PROVEEDORES_AREAS.ID';

CREATE SEQUENCE GESTION_CENTROS.PROVEEDORES_X_AREA_SEQ
MINVALUE 1
MAXVALUE 9999999999999999
INCREMENT BY 1;



ALTER TABLE PROVEEDORES ALTER COLUMN AREA_ID DROP NOT NULL;
