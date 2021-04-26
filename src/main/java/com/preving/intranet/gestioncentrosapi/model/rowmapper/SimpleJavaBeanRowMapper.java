package com.preving.intranet.gestioncentrosapi.model.rowmapper;

import com.preving.intranet.gestioncentrosapi.model.domain.SimpleJavaBean;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleJavaBeanRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int i) throws SQLException {

        SimpleJavaBean item = new SimpleJavaBean();
        item.setId(rs.getInt("ID"));
        item.setCodigo(rs.getString("COD"));
        item.setNombre(rs.getString("NOMBRE"));
        item.setActivo(rs.getBoolean("ACTIVO"));

        return item;
    }
}

