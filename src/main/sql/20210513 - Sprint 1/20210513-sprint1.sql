--------------------------------------------------------------------------------------------------------------------------------
-- AGREGAMOS LOS CAMPOS NECESARIOS EN LA TABLA GC2006_RELEASE.PC_DELEGACIONES
--------------------------------------------------------------------------------------------------------------------------------

--FECHA_ALTA
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD FECHA_ALTA DATE;

--FECHA_BAJA
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD FECHA_BAJA DATE;

--RESPONSABLE
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD RESPONSABLE BIGINT(10);

--ENTIDAD_ID
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD ENTIDAD_ID BIGINT(10);

--CREADO
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD CREADO DATE;

--CREADO_POR
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD CREADO_POR BIGINT(10);

--MODIFICADO
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD MODIFICADO DATE;

--MODIFICADO_POR
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD MODIFICADO_POR BIGINT(10);



--------------------------------------------------------------------------------------------------------------------------------
-- SECUENCIAS
--------------------------------------------------------------------------------------------------------------------------------

-- Creamos secuencia para la tabla GC2006_RELEASE.PC_DELEGACIONES
create sequence GC2006_RELEASE.PC_DELEGACIONES_SQ
  minvalue 100100
  maxvalue 999999
  increment by 1;


-- Creamos secuencia para la tabla MP2.ZONA
create sequence MP2.ZONA_SQ
  minvalue 100100
  maxvalue 999999
  increment by 1;


-- Creamos secuencia para la tabla RRHH.TM_DIM_NAVISION
create sequence RRHH.TM_DIM_NAVISION_SQ
  minvalue 800
  maxvalue 999999
  increment by 1;
