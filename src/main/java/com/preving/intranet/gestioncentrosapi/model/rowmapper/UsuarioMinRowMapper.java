package com.preving.intranet.gestioncentrosapi.model.rowmapper;

import com.preving.intranet.gestioncentrosapi.model.domain.UsuarioMin;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMinRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int i) throws SQLException {

        UsuarioMin item = new UsuarioMin();
        item.setId(rs.getInt("ID"));
        item.setUsuario(rs.getString("USUARIO"));
        item.setDeshabilitado(rs.getBoolean("DESHABILITADO"));
        item.setNombre(rs.getString("NOMBRE"));
        item.setApellidos(rs.getString("APELLIDOS"));
        item.setMovil(rs.getString("MOVIL"));
        item.setTelefono(rs.getString("TELEFONO"));
        item.setEmail(rs.getString("EMAIL"));
        item.setActivo(rs.getBoolean("ACTIVO"));

        return item;
    }
}

