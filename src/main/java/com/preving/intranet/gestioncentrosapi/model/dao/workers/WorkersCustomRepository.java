package com.preving.intranet.gestioncentrosapi.model.dao.workers;

import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.WorkersFilter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkersCustomRepository {
    List<Employees> getEmployeesFiltered(int workCenterId, WorkersFilter workersFilter, UsuarioWithRoles user);
}
