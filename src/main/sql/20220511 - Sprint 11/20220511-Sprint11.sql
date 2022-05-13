Update gestion_centros.conf_proveedores_detalles set etiqueta='Recogida' Where etiqueta='Recogida de paquetería';
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS'),2,'Copias media mensual byn',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS'),2,'Copias media mensual COLOR',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS'),5,'Recogida de toner,',(SELECT MAX(orden)+1 from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS'),2,'Importe de la recogida,',(SELECT MAX(orden)+1 from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MANTENIMIENTO FOTOCOPIADORAS')),true);
Update gestion_centros.conf_proveedores_detalles set activo = false where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MATERIAL IMPRENTA') OR tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'MATERIAL OFICINA') AND etiqueta = 'Coste mensual' OR etiqueta = 'Coste anual';
Insert into gestion_centros.tm_proveedores_tipos values ((Select MAX(id)+1 from gestion_centros.tm_proveedores_tipos),'MENSAJERIA',null,true,(Select MAX(count)+1 from gestion_centros.tm_proveedores_tipos));
Update gestion_centros.conf_proveedores_detalles set activo = false where tipo_proveedor_id = (SELECT id from gestion_centros.tm_proveedores_tipos where denominacion='EPIS COVID-19');
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'Consumo anual',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'P1',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'P2',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'P3',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'P4',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'P5',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);
Insert into gestion_centros.conf_proveedores_detalles values ((Select MAX(id) from gestion_centros.conf_proveedores_detalles)+1 ,(Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD'),1,'P6',(SELECT COUNT(orden) from gestion_centros.conf_proveedores_detalles where tipo_proveedor_id = (Select p.id from gestion_centros.tm_proveedores_tipos p where denominacion = 'ELECTRICIDAD')),true);



------------------------------------------------------------------------------------------------------------------------
-- Insert data
------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.PC_DELEGACIONES_X_DOC_GENERAL
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_X_DOC_GENERAL ADD COLUMN ARRENDADOR_NOMBRE VARCHAR(150);
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_X_DOC_GENERAL ADD COLUMN PERSONA_CONTACTO VARCHAR(150);
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_X_DOC_GENERAL ADD COLUMN CONCEPTO VARCHAR(150);

-- GESTION_CENTROS.PC_DELEGACIONES_DETALLES
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES ADD COLUMN ADMINISTRADOR_COMUNIDAD VARCHAR(150);
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES ADD COLUMN EMAIL VARCHAR(150);
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES ADD COLUMN TELEFONO VARCHAR(15);
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES ADD COLUMN NUM_PLAZAS_GARAJE_UM INT;
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_DETALLES RENAME NUM_PLAZAS_GARAJE TO NUM_PLAZAS_GARAJE_VEHICULOS;

--GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS
UPDATE GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS SET ACTIVO=FALSE WHERE ID=3;

--GESTION_CENTROS.MANTENIMIENTOS
ALTER TABLE GESTION_CENTROS.MANTENIMIENTOS ADD COLUMN DELEGACION_ID BIGINT NOT NULL;
ALTER TABLE GESTION_CENTROS.MANTENIMIENTOS ADD FOREIGN KEY (DELEGACION_ID) REFERENCES GC2006_RELEASE.PC_DELEGACIONES (ID);

DROP TABLE MANTENIMIENTOS_X_DELEGACIONES;
