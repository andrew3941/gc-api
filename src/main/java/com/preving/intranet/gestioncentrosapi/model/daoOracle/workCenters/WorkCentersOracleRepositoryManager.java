package com.preving.intranet.gestioncentrosapi.model.daoOracle.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.DimNavision;
import com.preving.intranet.gestioncentrosapi.model.domain.Zona;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class WorkCentersOracleRepositoryManager {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveWorkCenter(WorkCenter workCenter) {

        String sql = "" +
                "INSERT INTO GC2006_RELEASE.PC_DELEGACIONES " +
                "   (ID, LOCALIDAD_ID, C_POSTAL, NOMBRE, DIRECCION, TFNO, " +
                "   MAIL, ID_IN_MP2, COD_IN_NAV, ACTIVO, VISIBLE, " +
                "   LINEA_ID, TIPO, FECHA_ALTA, " +
                "   FECHA_BAJA, RESPONSABLE, CREADO, CREADO_POR, MODIFICADO, MODIFICADO_POR) " +
                "VALUES " +
                "   (:id, :localidadId, :cPostal, :nombre, :direccion, :tfno, " +
                "   :mail, :idInMp2, :codInNav, :activo, :visible, " +
                "   :lineaId, :tipo, :fechaAlta, " +
                "   :fechaBaja, :responsable, SYSDATE, :creadoPor, NULL, NULL)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", workCenter.getId());
        params.addValue("localidadId", workCenter.getCity().getId());
        params.addValue("cPostal", workCenter.getPostalCode());
        params.addValue("nombre", workCenter.getName());
        params.addValue("direccion", workCenter.getAddress());
        params.addValue("tfno", workCenter.getPhoneNumber());
        params.addValue("mail", workCenter.getEmail());
        params.addValue("idInMp2", workCenter.getZona().getCodZona());
        params.addValue("codInNav", workCenter.getNavisionCode());
        params.addValue("activo", workCenter.getActive());
        params.addValue("visible", workCenter.getVisible());
        params.addValue("lineaId", workCenter.getDimNavision().getId());
        params.addValue("tipo", workCenter.getWorkCenterTypes().getId());
        params.addValue("fechaAlta", workCenter.getStartDate());
        params.addValue("fechaBaja", workCenter.getEndDate());
        params.addValue("responsable", workCenter.getHeadPerson().getId());
        params.addValue("creadoPor", workCenter.getCreatedBy().getId());

        namedParameterJdbcTemplate.update(sql, params);

    }

    public void saveZone(Zona zone) {

        String sql = "INSERT INTO MP2.ZONA " +
                "   (COD_ZONA, DENOMINACION, NOMBRE, TELEFONO, EMAIL, DIRECCION, COD_POSTAL, POBLACION) " +
                "VALUES " +
                "   (:codZona, :denominacion, :nombre, :telefono, :email, :direccion, :codPostal, :poblacion)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("codZona", zone.getCodZona());
        params.addValue("denominacion", zone.getDenomination());
        params.addValue("nombre", zone.getName());
        params.addValue("telefono", zone.getTelephone());
        params.addValue("email", zone.getEmail());
        params.addValue("direccion", zone.getAddress());
        params.addValue("codPostal", zone.getCodPostal());
        params.addValue("poblacion", zone.getPoblacion());

        namedParameterJdbcTemplate.update(sql, params);

    }

    public void saveDimNavision(DimNavision dimNavision) {

        String sql = "" +
                "INSERT INTO RRHH.TM_DIM_NAVISION " +
                "   (ID, TIPO, COD, NOMBRE, ACTIVO, ORDEN, PROVINCIA_COD) " +
                "VALUES " +
                "   (:id, :tipo, :cod, :nombre, :activo, :orden, :provinciaCod)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", dimNavision.getId());
        params.addValue("tipo", dimNavision.getType());
        params.addValue("cod", dimNavision.getCod());
        params.addValue("nombre", dimNavision.getName());
        params.addValue("activo", dimNavision.getActive());
        params.addValue("orden", dimNavision.getOrder());
        params.addValue("provinciaCod", dimNavision.getProvinceCod());

        namedParameterJdbcTemplate.update(sql, params);

    }

    public void editWorkCenter(int workCenterId, WorkCenter workCenter, int userId) {

        String sql = "" +
                "UPDATE GC2006_RELEASE.PC_DELEGACIONES " +
                "SET " +
                "   NOMBRE = :nombre, ACTIVO = :activo, VISIBLE = :visible, LOCALIDAD_ID = :localidadId, " +
                "   COD_IN_NAV = :codInNav, DIRECCION = :direccion, C_POSTAL = :cPostal, TFNO = :tfno, " +
                "   MAIL = :mail, RESPONSABLE = :responsable, FECHA_ALTA = :fechaAlta, FECHA_BAJA = :fechaBaja, " +
                "   TIPO = :tipo, MODIFICADO = SYSDATE, MODIFICADO_POR = :userId " +
                "WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nombre", workCenter.getName());
        params.addValue("activo", workCenter.getActive());
        params.addValue("visible", workCenter.getVisible());
        params.addValue("localidadId", workCenter.getCity().getId());
        params.addValue("codInNav", workCenter.getNavisionCode());
        params.addValue("direccion", workCenter.getAddress());
        params.addValue("cPostal", workCenter.getPostalCode());
        params.addValue("tfno", workCenter.getPhoneNumber());
        params.addValue("mail", workCenter.getEmail());
        params.addValue("responsable", workCenter.getHeadPerson().getId());
        params.addValue("fechaAlta", workCenter.getStartDate());
        params.addValue("fechaBaja", workCenter.getEndDate());
        params.addValue("tipo", workCenter.getWorkCenterTypes().getId());
        params.addValue("userId", userId);
        params.addValue("id", workCenterId);

        namedParameterJdbcTemplate.update(sql, params);

    }

    public void editZone(Zona zona) {

        String sql = "" +
                "UPDATE MP2.ZONA " +
                "SET " +
                "   DENOMINACION = :denominacion, NOMBRE = :nombre, TELEFONO = :telefono, EMAIL = :email, " +
                "   DIRECCION = :direccion, COD_POSTAL = :codPostal, POBLACION = :poblacion " +
                "WHERE COD_ZONA = :codZona";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("denominacion", zona.getName());
        params.addValue("nombre", zona.getName());
        params.addValue("telefono", zona.getTelephone());
        params.addValue("email", zona.getEmail());
        params.addValue("direccion", zona.getAddress());
        params.addValue("codPostal", zona.getCodPostal());
        params.addValue("poblacion", zona.getPoblacion());
        params.addValue("codZona", zona.getCodZona());

        namedParameterJdbcTemplate.update(sql, params);

    }

    public void editDimNavision(DimNavision dimNavision) {

        String sql = "" +
                "UPDATE RRHH.TM_DIM_NAVISION " +
                "SET COD = :cod, NOMBRE = :nombre, ORDEN = :orden, PROVINCIA_COD = :provinciaCod " +
                "WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("cod", dimNavision.getCod());
        params.addValue("nombre", dimNavision.getName());
        params.addValue("orden", dimNavision.getOrder());
        params.addValue("provinciaCod", dimNavision.getProvinceCod());
        params.addValue("id", dimNavision.getId());

        namedParameterJdbcTemplate.update(sql, params);

    }

}
