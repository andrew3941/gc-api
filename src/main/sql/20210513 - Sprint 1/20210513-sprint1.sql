--------------------------------------------------------------------------------------------------------------------------------
-- AGREGAMOS LOS CAMPOS NECESARIOS EN LA TABLA GC2006_RELEASE.PC_DELEGACIONES
--------------------------------------------------------------------------------------------------------------------------------

--FECHA_ALTA
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD FECHA_ALTA DATE;

--FECHA_BAJA
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD FECHA_BAJA DATE;

--RESPONSABLE
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD RESPONSABLE NUMBER(10);

--CREADO
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD CREADO DATE;

--CREADO_POR
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD CREADO_POR NUMBER(10);

--MODIFICADO
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD MODIFICADO DATE;

--MODIFICADO_POR
ALTER TABLE  GC2006_RELEASE.PC_DELEGACIONES ADD MODIFICADO_POR NUMBER(10);



--------------------------------------------------------------------------------------------------------------------------------
-- SECUENCIAS
--------------------------------------------------------------------------------------------------------------------------------

-- Creamos secuencia para la tabla GC2006_RELEASE.PC_DELEGACIONES
create sequence PC_DELEGACIONES_SQ
  minvalue 200000
  maxvalue 999999
  increment by 1;


-- Creamos secuencia para la tabla MP2.ZONA
create sequence ZONA_SQ
  minvalue 200000
  maxvalue 999999
  increment by 1;
