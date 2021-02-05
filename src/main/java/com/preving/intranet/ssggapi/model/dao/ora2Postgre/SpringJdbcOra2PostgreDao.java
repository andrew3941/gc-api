package com.preving.intranet.ssggapi.model.dao.ora2Postgre;

import com.preving.intranet.ssggapi.model.domain.SimpleJavaBean;
import com.preving.intranet.ssggapi.model.domain.UsuarioMin;
import com.preving.intranet.ssggapi.model.rowmapper.SimpleJavaBeanRowMapper;
import com.preving.intranet.ssggapi.model.rowmapper.UsuarioMinRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringJdbcOra2PostgreDao implements Ora2PostgreDao {

    @Autowired
    @Qualifier("namedTemplateApp")
    private NamedParameterJdbcTemplate namedTemplateOracle;

    @Autowired
    @Qualifier("namedTemplatePostgresql")
    private NamedParameterJdbcTemplate namedTemplatePostgre;


    // TODO: 07/10/2020 JGM - Obtener todos los datos necesarios en función de las tablas de el esquema ORACLE en POSTGRE para el volcado de datos.
    //  --> Revisar objetos / rowmappers por si hubiera que modificar alguna consulta.

    @Override
    public List<SimpleJavaBean> getComunidadesPostgre() {
        String sql = "SELECT ID, NULL AS COD, NOMBRE, 'TRUE' AS ACTIVO FROM ORACLE.COMUNIDADES ORDER BY ID ASC";

        return this.namedTemplatePostgre.query(sql, new SimpleJavaBeanRowMapper());
    }

    @Override
    public List<SimpleJavaBean> getComunidadesOracle() {
        String sql = "SELECT ID, NULL AS COD, NOMBRE, 1 AS ACTIVO FROM GC2006_RELEASE.PC_COMUNIDADES ORDER BY ID ASC";

        return this.namedTemplateOracle.query(sql, new SimpleJavaBeanRowMapper());
    }

    @Override
    public List<SimpleJavaBean> getDelegacionesPostgre() {
        String sql = "SELECT ID, NULL AS COD, NOMBRE, ACTIVO FROM ORACLE.DELEGACIONES ORDER BY ID ASC";

        return this.namedTemplatePostgre.query(sql, new SimpleJavaBeanRowMapper());
    }

    @Override
    public List<SimpleJavaBean> getDelegacionesOracle() {
        String sql = "SELECT ID, NULL AS COD, NOMBRE, ACTIVO FROM GC2006_RELEASE.PC_DELEGACIONES ORDER BY ID ASC";

        return this.namedTemplateOracle.query(sql, new SimpleJavaBeanRowMapper());
    }

    @Override
    public List<SimpleJavaBean> getDepartamentosPostgre() {
        String sql = "SELECT ID, NULL AS COD, NOMBRE, ACTIVO FROM ORACLE.DEPARTAMENTOS ORDER BY ID ASC";

        return this.namedTemplatePostgre.query(sql, new SimpleJavaBeanRowMapper());
    }

    @Override
    public List<SimpleJavaBean> getDepartamentosOracle() {
        String sql = "SELECT ID, NULL AS COD, NOMBRE, ACTIVO FROM RRHH.TM_DEPARTAMENTOS ORDER BY ID ASC";

        return this.namedTemplateOracle.query(sql, new SimpleJavaBeanRowMapper());
    }



    @Override
    public List<UsuarioMin> getUsuariosPostgre() {
        String sql = "SELECT ID, USUARIO, DESHABILITADO, NOMBRE, APELLIDOS, MOVIL, TELEFONO, EMAIL, ACTIVO, POSTGRE_LAST_UPDATED " +
                "FROM ORACLE.USUARIOS";

        return this.namedTemplatePostgre.query(sql, new UsuarioMinRowMapper());
    }

    @Override
    public List<UsuarioMin> getUsuariosOracle() {
        String sql = "SELECT ID, USUARIO, DESHABILITADO, NOMBRE, APELLIDOS, MOVIL, TELEFONO, EMAIL, ACTIVO " +
                "FROM GC2006_RELEASE.PC_USUARIOS";

        return this.namedTemplateOracle.query(sql, new UsuarioMinRowMapper());
    }

    @Override
    public int persistComunidad(SimpleJavaBean comunidad, boolean isUpdate) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        String sql = "";

        if (!isUpdate) {
            sql += "INSERT INTO ORACLE.COMUNIDADES (ID, NOMBRE, POSTGRE_LAST_UPDATED) " +
                    "VALUES (:id, :nombre, NOW())";
        } else {
            sql += "UPDATE ORACLE.COMUNIDADES " +
                    "SET NOMBRE = :nombre, POSTGRE_LAST_UPDATED = NOW() " +
                    "WHERE ID = :id";
        }

        params.addValue("id", comunidad.getId());
        params.addValue("nombre", comunidad.getNombre());

        return this.namedTemplatePostgre.update(sql, params);
    }

    @Override
    public int persistDelegacion(SimpleJavaBean delegacion, boolean isUpdate) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        String sql = "";

        if (!isUpdate) {
            sql += "INSERT INTO ORACLE.DELEGACIONES (ID, NOMBRE, ACTIVO, POSTGRE_LAST_UPDATED) " +
                    "VALUES (:id, :nombre, :activo, NOW())";
        } else {
            sql += "UPDATE ORACLE.DELEGACIONES " +
                    "SET NOMBRE = :nombre, ACTIVO = :activo, POSTGRE_LAST_UPDATED = NOW() " +
                    "WHERE ID = :id";
        }

        params.addValue("id", delegacion.getId());
        params.addValue("nombre", delegacion.getNombre());
        params.addValue("activo", delegacion.isActivo());

        return this.namedTemplatePostgre.update(sql, params);

    }

    @Override
    public int persistDepartamento(SimpleJavaBean departamento, boolean isUpdate) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        String sql = "";

        if (!isUpdate) {
            sql += "INSERT INTO ORACLE.DEPARTAMENTOS (ID, NOMBRE, ACTIVO, POSTGRE_LAST_UPDATED) " +
                    "VALUES (:id, :nombre, :activo, NOW())";
        } else {
            sql += "UPDATE ORACLE.DEPARTAMENTOS " +
                    "SET NOMBRE = :nombre, ACTIVO = :activo, POSTGRE_LAST_UPDATED = NOW() " +
                    "WHERE ID = :id";
        }

        params.addValue("id", departamento.getId());
        params.addValue("nombre", departamento.getNombre());
        params.addValue("activo", departamento.isActivo());

        return this.namedTemplatePostgre.update(sql, params);

    }


    @Override
    public void deleteEmpleados() {

        MapSqlParameterSource params = new MapSqlParameterSource();

        String sql = "DELETE FROM ORACLE.EMPLEADOS";

        this.namedTemplatePostgre.update(sql, params);

    }


    @Override
    public int persistUsuario(UsuarioMin usuario, boolean isUpdate) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql;

        if (!isUpdate) {
            sql = "INSERT INTO ORACLE.USUARIOS (ID, USUARIO, DESHABILITADO, NOMBRE, APELLIDOS, " +
                    "MOVIL, TELEFONO, EMAIL, ACTIVO, POSTGRE_LAST_UPDATED) " +
                    "VALUES (:id, :usuario, :deshabilitado, :nombre, :apellidos, :movil," +
                    ":telefono, :email, :activo, NOW())";
        } else {
            sql = "UPDATE ORACLE.USUARIOS " +
                    "SET USUARIO = :usuario, DESHABILITADO = :deshabilitado, NOMBRE = :nombre, APELLIDOS = :apellidos, " +
                    "MOVIL = :movil, TELEFONO = :telefono, EMAIL = :email, ACTIVO = :activo, POSTGRE_LAST_UPDATED = NOW() " +
                    "WHERE ID = :id";
        }
        params.addValue("usuario", usuario.getUsuario());
        params.addValue("deshabilitado", usuario.isDeshabilitado());
        params.addValue("nombre", usuario.getNombre());
        params.addValue("apellidos", usuario.getApellidos());
        params.addValue("movil", usuario.getMovil());
        params.addValue("telefono", usuario.getTelefono());
        params.addValue("email", usuario.getEmail());
        params.addValue("activo", usuario.isActivo());
        params.addValue("id", usuario.getId());

        return this.namedTemplatePostgre.update(sql, params);

    }
}
