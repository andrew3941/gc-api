package com.preving.intranet.ssggapi.model.service.ora2Postgre;


import com.preving.intranet.ssggapi.model.domain.SimpleJavaBean;
import com.preving.intranet.ssggapi.model.domain.UsuarioMin;

import java.util.List;

public interface Ora2PostgreService {

    SimpleJavaBean sincronizar();

    List<UsuarioMin> getUsuariosPostgre();
}
