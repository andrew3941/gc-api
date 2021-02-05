package com.preving.intranet.ssggapi.model.dao.ora2Postgre;

import com.preving.intranet.ssggapi.model.domain.SimpleJavaBean;
import com.preving.intranet.ssggapi.model.domain.UsuarioMin;


import java.util.List;

public interface Ora2PostgreDao {

    List<SimpleJavaBean> getComunidadesPostgre();

    List<SimpleJavaBean> getComunidadesOracle();

    List<SimpleJavaBean> getDelegacionesPostgre();

    List<SimpleJavaBean> getDelegacionesOracle();

    List<SimpleJavaBean> getDepartamentosPostgre();

    List<SimpleJavaBean> getDepartamentosOracle();

    List<UsuarioMin> getUsuariosPostgre();

    List<UsuarioMin> getUsuariosOracle();

    int persistComunidad(SimpleJavaBean comunidad, boolean isUpdate);

    int persistDelegacion(SimpleJavaBean delegacion, boolean isUpdate);

    int persistDepartamento(SimpleJavaBean departamento, boolean isUpdate);

    void deleteEmpleados();

    int persistUsuario(UsuarioMin usuario, boolean isUpdate);
}
