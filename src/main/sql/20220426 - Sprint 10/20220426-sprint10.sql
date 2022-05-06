------------------------------------------------------------------------------------------------------------------------
-- Insert data
------------------------------------------------------------------------------------------------------------------------
-- GESTION_CENTROS.TM_MANTENIMIENTOS_TIPOS
INSERT INTO TM_MANTENIMIENTOS_TIPOS (ID, DENOMINACION, OBSERVACIONES, ACTIVO)
VALUES (3, 'SINIESTRO', '', true);

-- GESTION_CENTROS.MANTENIMIENTOS
ALTER TABLE GESTION_CENTROS.MANTENIMIENTOS ADD COLUMN FECHA_RESOLUCION DATE;

-- GESTION_CENTROS.PC_DELEGACIONES_X_DOC_GENERAL
ALTER TABLE GESTION_CENTROS.PC_DELEGACIONES_X_DOC_GENERAL ADD COLUMN GASTO_COMUNIDAD NUMERIC;
