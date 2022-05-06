package com.preving.intranet.gestioncentrosapi.model.dto.workers;


import com.preving.intranet.gestioncentrosapi.model.domain.workers.EmpContacto;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.RolesEmployees;

public interface EmployeeProjection {
    String getName();
    String getSurnames();
    EmpHistoryLabProjection getEmpLabHistory();
    EmpContacto getEmpContacto();
    RolesEmployees getRolesEmployees();
}
