package com.preving.intranet.gestioncentrosapi.model.dao.workCenters;

import com.preving.intranet.gestioncentrosapi.model.domain.WorkCenterFilter;
import com.preving.intranet.gestioncentrosapi.model.domain.workCenters.WorkCenter;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkCentersCustomizeRepository {

    List<WorkCenter> getWorkCenters(WorkCenterFilter workCenterFilter, UsuarioWithRoles user);

    int getTotalEmployee(int workCenterId );

    List<WorkCenter> findAllByActive(UsuarioWithRoles user);

    List<WorkCenter> findAllByActive();
}
