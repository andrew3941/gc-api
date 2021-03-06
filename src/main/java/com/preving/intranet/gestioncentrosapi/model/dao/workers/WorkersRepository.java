package com.preving.intranet.gestioncentrosapi.model.dao.workers;


import com.preving.intranet.gestioncentrosapi.model.domain.workers.Employees;
import com.preving.intranet.gestioncentrosapi.model.domain.workers.WorkersFilter;
import com.preving.intranet.gestioncentrosapi.model.dto.workers.EmployeeProjection;
import com.preving.security.domain.UsuarioWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkersRepository extends JpaRepository<Employees, Long> {
    Optional<Employees> findById(Long id);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionId(int delegacionId);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionIdAndIdInAndEmpLabHistoryAreaDepartmentIdIn(int delegacionId,List<Integer> workersId,List<Integer> idDepartment);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionIdAndIdIn(int delegacionId,List<Integer> workersId);
   List<Employees> findAllByEmpLabHistoryFchSalidaIsNullAndEmpLabHistoryDelegacionIdAndEmpLabHistoryAreaDepartmentIdIn(int delegacionId,List<Integer> idDepartment);


   List<Employees> findAllByName(String name);
//   List<Employees> findAllByNameIsNotNull();

}
